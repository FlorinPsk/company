package com.sda.company.thymeleaf.controller;

import com.sda.company.dto.CompanyDisplayDTO;
import com.sda.company.service.CompanyService;
import com.sda.company.thymeleaf.model.GDPRConsent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;
import java.util.List;

@Controller
public class CompanyControllerThymeleaf {
    
    private final CompanyService companyService;

    @Autowired
    public CompanyControllerThymeleaf(@Qualifier("companyServiceImpl") CompanyService companyService) {
        this.companyService = companyService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String indexPage(Model model, Principal principal) {
        model.addAttribute("welcomeMessage", "This is the company's landing page.");
        model.addAttribute("hello", "Hello, ");
        model.addAttribute("userName", principal.getName());

        return "index";
    }

    @RequestMapping(value = "/start", method = RequestMethod.GET)
    public String appPage() {

        return "app";
    }

    @RequestMapping(value = "/company", method = RequestMethod.POST)
    public String companyPage(
            @ModelAttribute(name = "gdprConsent") GDPRConsent gdprConsent, Model model) {
        if (gdprConsent.getName().isBlank()) {
            model.addAttribute("errorMessage", "Please enter your name!");

            return "app";
        }

        if (!gdprConsent.isGdprConsent()) {
            model.addAttribute("errorMessage", "You must accept the GDPR consent!");

            return "app";
        }

        return "company";
    }

    @RequestMapping(value = "/showAll/{pageNumber}", method = RequestMethod.GET)
    public String showAllCompanies(Model model, @PathVariable("pageNumber") Integer currentPage) {
        Page<CompanyDisplayDTO> page = companyService.findPage(currentPage);

        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("companyList", page.getContent());

        return "companyTable";
    }

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String returnHome() {

        return "company";
    }
}
