package com.tescaro.java.challenge.controller;

import com.tescaro.java.challenge.model.StockSession;
import com.tescaro.java.challenge.repository.StockSessionRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/panel/stock-session")
public class StockSessionController {

  private final StockSessionRepository stockSessionRepository;

  public StockSessionController(StockSessionRepository stockSessionRepository) {
    this.stockSessionRepository = stockSessionRepository;
  }

  @RequestMapping(method = RequestMethod.GET)
  public String listRender(Model model) {
    List<StockSession> stockSessions = stockSessionRepository.findAll();
    model.addAttribute("stockSessions", stockSessions);
    return "stockSession/index";
  }

  @RequestMapping(path = "/new", method = RequestMethod.GET)
  public String newRender(Model model) {
    model.addAttribute("stockSession", new StockSession());
    return "stockSession/new";
  }

  @RequestMapping(path = "/edit/{id}", method = RequestMethod.GET)
  public String editRender(@PathVariable Long id, Model model) {
    Optional<StockSession> stockSession = stockSessionRepository.findById(id);
    if (stockSession.isPresent()) {
      model.addAttribute("stockSession", stockSession.get());
      return "stockSession/edit";
    }
    return "redirect:/panel/stock-session";
  }

  @RequestMapping(path = "/new", method = RequestMethod.POST)
  public String insert(@ModelAttribute StockSession stockSession) {
    stockSessionRepository.save(stockSession);
    return "redirect:/panel/stock-session";
  }

  @RequestMapping(path = "/edit/{id}", method = RequestMethod.POST)
  public String update(@PathVariable Long id, @ModelAttribute StockSession stockSession) {
    Optional<StockSession> existing = stockSessionRepository.findById(id);
    if (existing.isPresent()) {
      StockSession updated = existing.get();
      updated.setId(stockSession.getId());
      updated.setName(stockSession.getName());
      updated.setCreatedAt(stockSession.getCreatedAt());
      stockSessionRepository.save(updated);
    }
    return "redirect:/panel/stock-session";
  }

  @RequestMapping(path = "/delete/{id}", method = RequestMethod.POST)
  public String delete(@PathVariable Long id) {
    stockSessionRepository.deleteById(id);
    return "redirect:/panel/stock-session";
  }
}
