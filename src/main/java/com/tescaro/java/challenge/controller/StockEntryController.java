package com.tescaro.java.challenge.controller;

import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tescaro.java.challenge.enums.StockKindEnum;
import com.tescaro.java.challenge.model.Stock;
import com.tescaro.java.challenge.repository.StockSessionRepository;
import com.tescaro.java.challenge.repository.ProductRepository;
import com.tescaro.java.challenge.repository.StockRepository;

@Controller
@RequestMapping("/panel/stock-entry")
public class StockEntryController {

    private final StockRepository stockRepository;
    private final ProductRepository productRepository;
    private final StockSessionRepository stockSessionRepository;

    public StockEntryController(
            StockRepository stockRepository,
            ProductRepository productRepository,
            StockSessionRepository stockSessionRepository) {
        this.stockRepository = stockRepository;
        this.productRepository = productRepository;
        this.stockSessionRepository = stockSessionRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String indexRender(Model model) {
        List<Stock> stocks = stockRepository.findAll();
        model.addAttribute("stocks", stocks);
        return "stockEntry/index";
    }

    @RequestMapping(path = "/new", method = RequestMethod.GET)
    public String newRender(Model model) {
        model.addAttribute("stockEntry", new Stock());
        model.addAttribute("stockKinds", StockKindEnum.values());
        model.addAttribute("products", productRepository.findAll());
        model.addAttribute("stockSessions", stockSessionRepository.findAll());
        return "stockEntry/new";
    }

    @RequestMapping(path = "/new", method = RequestMethod.POST)
    public String insert(@ModelAttribute Stock stock) {
        stockRepository.save(stock);
        return "redirect:/panel/stock-entry";
    }
}
