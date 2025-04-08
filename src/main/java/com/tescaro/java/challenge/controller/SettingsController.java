package com.tescaro.java.challenge.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.tescaro.java.challenge.model.Settings;
import com.tescaro.java.challenge.repository.SettingsRepository;

@Controller
@RequestMapping("/panel/settings")
public class SettingsController {
    private final SettingsRepository settingsRepository;

    public SettingsController(SettingsRepository settingsRepository) {
        this.settingsRepository = settingsRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String listRender(Model model) {
        List<Settings> settings = settingsRepository.findAll();
        model.addAttribute("settings", settings);
        return "settings/generalSettings";
    }

    @RequestMapping(path = "/new", method = RequestMethod.GET)
    public String newRender(Model model) {
        model.addAttribute("settings", new Settings());
        return "settings/new";
    }

    @RequestMapping(path = "/new", method = RequestMethod.POST)
    public String insert(@ModelAttribute Settings settings) {
        Optional<Settings> settingsActual = settingsRepository.findByHash(settings.getHash());
        if (!settingsActual.isPresent()) {
            settingsRepository.save(settings);
        }
        return "redirect:/panel/settings";
    }

    @RequestMapping(path = "/update", method = RequestMethod.POST)
    public String update(@RequestParam Map<String, String> values) {
        values.forEach((hash, value) -> {
            Optional<Settings> settingOpt = settingsRepository.findByHash(hash);
            settingOpt.ifPresent(setting -> {
                setting.setValue(value);
                settingsRepository.save(setting);
            });
        });
        return "redirect:/panel/settings";
    }

    @RequestMapping(path = "/delete/{id}", method = RequestMethod.POST)
    public String delete(@PathVariable Long id) {
        settingsRepository.deleteById(id);
        return "redirect:/panel/settings";
    }
}
