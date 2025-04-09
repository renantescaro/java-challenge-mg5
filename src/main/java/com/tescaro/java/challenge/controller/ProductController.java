package com.tescaro.java.challenge.controller;

import com.tescaro.java.challenge.model.Product;
import com.tescaro.java.challenge.repository.ProductKindRepository;
import com.tescaro.java.challenge.repository.ProductRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/panel/product")
public class ProductController {

    private final ProductKindRepository productKindRepository;

    private final ProductRepository productRepository;

    public ProductController(
        ProductRepository productRepository,
        ProductKindRepository productKindRepository) {
        this.productRepository = productRepository;
        this.productKindRepository = productKindRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String listRender(Model model) {
        List<Product> products = productRepository.findAll();
        model.addAttribute("products", products);
        return "product/index";
    }

    @RequestMapping(path = "/new", method = RequestMethod.GET)
    public String newRender(Model model) {
        model.addAttribute("productKinds", productKindRepository.findAll());
        model.addAttribute("product", new Product());
        return "product/new";
    }

    @RequestMapping(path = "/edit/{id}", method = RequestMethod.GET)
    public String editRender(@PathVariable Long id, Model model) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            model.addAttribute("productKinds", productKindRepository.findAll());
            model.addAttribute("product", product.get());
            return "product/edit";
        }
        return "redirect:/panel/product";
    }

    @RequestMapping(path = "/new", method = RequestMethod.POST)
    public String insert(@ModelAttribute Product product) {
        productRepository.save(product);
        return "redirect:/panel/product";
    }

    @RequestMapping(path = "/edit/{id}", method = RequestMethod.POST)
    public String update(@PathVariable Long id, @ModelAttribute Product product) {
        Optional<Product> existing = productRepository.findById(id);
        if (existing.isPresent()) {
            Product updated = existing.get();

            updated.setName(product.getName());
            updated.setProductKind(product.getProductKind());
            updated.setPrice(product.getPrice());
            updated.setVolumeLiters(product.getVolumeLiters());

            productRepository.save(updated);
        }
        return "redirect:/panel/product";
    }

    @RequestMapping(path = "/delete/{id}", method = RequestMethod.POST)
    public String delete(@PathVariable Long id) {
        productRepository.deleteById(id);
        return "redirect:/panel/product";
    }
}
