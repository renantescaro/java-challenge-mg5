package com.tescaro.java.challenge.controller;

import com.tescaro.java.challenge.model.StockSessionCapacityByKind;
import com.tescaro.java.challenge.repository.ProductKindRepository;
import com.tescaro.java.challenge.repository.StockSessionCapacityByKindRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/panel/stock-session-capacity-by-kind")
public class StockSessionCapacityByKindController {

    private final ProductKindRepository productKindRepository;

    private final StockSessionCapacityByKindRepository stockSessionCapacityByKindRepository;

    public StockSessionCapacityByKindController(
            StockSessionCapacityByKindRepository stockSessionCapacityByKindRepository, ProductKindRepository productKindRepository) {
        this.stockSessionCapacityByKindRepository = stockSessionCapacityByKindRepository;
        this.productKindRepository = productKindRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String listRender(Model model) {
        List<StockSessionCapacityByKind> StockSessionCapacityByKinds = stockSessionCapacityByKindRepository.findAll();
        model.addAttribute("stockSessionCapacityByKinds", StockSessionCapacityByKinds);
        return "stockSessionCapacityByKind/index";
    }

    @RequestMapping(path = "/new", method = RequestMethod.GET)
    public String newRender(Model model) {
        model.addAttribute("productKinds", productKindRepository.findAll());
        model.addAttribute("stockSessionCapacityByKind", new StockSessionCapacityByKind());
        return "stockSessionCapacityByKind/new";
    }

    @RequestMapping(path = "/edit/{id}", method = RequestMethod.GET)
    public String editRender(@PathVariable Long id, Model model) {
        Optional<StockSessionCapacityByKind> StockSessionCapacityByKind = stockSessionCapacityByKindRepository
                .findById(id);
        if (StockSessionCapacityByKind.isPresent()) {
            model.addAttribute("productKinds", productKindRepository.findAll());
            model.addAttribute("stockSessionCapacityByKind", StockSessionCapacityByKind.get());
            return "stockSessionCapacityByKind/edit";
        }
        return "redirect:/panel/stock-session-capacity-by-kind";
    }

    @RequestMapping(path = "/new", method = RequestMethod.POST)
    public String insert(@ModelAttribute StockSessionCapacityByKind StockSessionCapacityByKind) {
        stockSessionCapacityByKindRepository.save(StockSessionCapacityByKind);
        return "redirect:/panel/stock-session-capacity-by-kind";
    }

    @RequestMapping(path = "/edit/{id}", method = RequestMethod.POST)
    public String update(@PathVariable Long id, @ModelAttribute StockSessionCapacityByKind stockSessionCapacityByKind) {
        Optional<StockSessionCapacityByKind> existing = stockSessionCapacityByKindRepository.findById(id);
        if (existing.isPresent()) {
            StockSessionCapacityByKind updated = existing.get();
            updated.setId(stockSessionCapacityByKind.getId());
            updated.setProductKind(stockSessionCapacityByKind.getProductKind());
            updated.setCapacityLiters(stockSessionCapacityByKind.getCapacityLiters());
            updated.setCreatedAt(stockSessionCapacityByKind.getCreatedAt());
            stockSessionCapacityByKindRepository.save(updated);
        }
        return "redirect:/panel/stock-session-capacity-by-kind";
    }

    @RequestMapping(path = "/delete/{id}", method = RequestMethod.POST)
    public String delete(@PathVariable Long id) {
        stockSessionCapacityByKindRepository.deleteById(id);
        return "redirect:/panel/stock-session-capacity-by-kind";
    }
}
