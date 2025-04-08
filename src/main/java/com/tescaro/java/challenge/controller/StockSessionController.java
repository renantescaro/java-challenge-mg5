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

  private final StockSessionRepository StockSessionRepository;

  public StockSessionController(StockSessionRepository StockSessionRepository) {
    this.StockSessionRepository = StockSessionRepository;
  }

  @RequestMapping(method = RequestMethod.GET)
  public String listRender(Model model) {
    List<StockSession> StockSessions = StockSessionRepository.findAll();
    model.addAttribute("stockSessions", StockSessions);
    return "stockSession/index";
  }

  @RequestMapping(path = "/new", method = RequestMethod.GET)
  public String newRender(Model model) {
    model.addAttribute("stockSession", new StockSession());
    return "stockSession/new";
  }

  @RequestMapping(path = "/edit/{id}", method = RequestMethod.GET)
  public String editRender(@PathVariable Long id, Model model) {
    Optional<StockSession> StockSession = StockSessionRepository.findById(id);
    if (StockSession.isPresent()) {
      model.addAttribute("stockSession", StockSession.get());
      return "stockSession/edit";
    }
    return "redirect:/panel/stock-session";
  }

  @RequestMapping(path = "/new", method = RequestMethod.POST)
  public String insert(@ModelAttribute StockSession StockSession) {
    StockSessionRepository.save(StockSession);
    return "redirect:/panel/stock-session";
  }

  @RequestMapping(path = "/edit/{id}", method = RequestMethod.POST)
  public String update(@PathVariable Long id, @ModelAttribute StockSession StockSession) {
    Optional<StockSession> existing = StockSessionRepository.findById(id);
    if (existing.isPresent()) {
      StockSession updated = existing.get();
      updated.setId(StockSession.getId());
      updated.setName(StockSession.getName());
      updated.setCreatedAt(StockSession.getCreatedAt());
      StockSessionRepository.save(updated);
    }
    return "redirect:/panel/stock-session";
  }

  @RequestMapping(path = "/delete/{id}", method = RequestMethod.POST)
  public String delete(@PathVariable Long id) {
    StockSessionRepository.deleteById(id);
    return "redirect:/panel/stock-session";
  }
}
