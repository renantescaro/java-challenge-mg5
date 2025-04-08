package com.tescaro.java.challenge.controller;

import com.tescaro.java.challenge.model.ProductKind;
import com.tescaro.java.challenge.repository.ProductKindRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/panel/product-kind")
public class ProductKindController {

    private final ProductKindRepository ProductKindRepository;

    public ProductKindController(ProductKindRepository ProductKindRepository) {
        this.ProductKindRepository = ProductKindRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String listRender(Model model) {
        List<ProductKind> ProductKinds = ProductKindRepository.findAll();
        model.addAttribute("ProductKinds", ProductKinds);
        return "productKind/index";
    }

    @RequestMapping(path = "/new", method = RequestMethod.GET)
    public String newRender(Model model) {
        model.addAttribute("ProductKind", new ProductKind());
        return "productKind/new";
    }

    @RequestMapping(path = "/edit/{id}", method = RequestMethod.GET)
    public String editRender(@PathVariable Long id, Model model) {
        Optional<ProductKind> ProductKind = ProductKindRepository.findById(id);
        if (ProductKind.isPresent()) {
            model.addAttribute("ProductKind", ProductKind.get());
            return "productKind/edit";
        }
        return "redirect:/panel/product-kind";
    }

    @RequestMapping(path = "/new", method = RequestMethod.POST)
    public String insert(@ModelAttribute ProductKind ProductKind) {
        ProductKindRepository.save(ProductKind);
        return "redirect:/panel/product-kind";
    }

    @RequestMapping(path = "/edit/{id}", method = RequestMethod.POST)
    public String update(@PathVariable Long id, @ModelAttribute ProductKind ProductKind) {
        Optional<ProductKind> existing = ProductKindRepository.findById(id);
        if (existing.isPresent()) {
            ProductKind updated = existing.get();
            updated.setId(ProductKind.getId());
            updated.setName(ProductKind.getName());
            updated.setSeparateStock(ProductKind.getSeparateStock());
            updated.setCreatedAt(ProductKind.getCreatedAt());
            ProductKindRepository.save(updated);
        }
        return "redirect:/panel/product-kind";
    }

    @RequestMapping(path = "/delete/{id}", method = RequestMethod.POST)
    public String delete(@PathVariable Long id) {
        ProductKindRepository.deleteById(id);
        return "redirect:/panel/product-kind";
    }
}
