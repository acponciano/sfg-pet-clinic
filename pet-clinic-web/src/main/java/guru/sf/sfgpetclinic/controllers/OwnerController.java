package guru.sf.sfgpetclinic.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import guru.sf.sfgpetclinic.services.OwnerService;

@RequestMapping({ "/owners" })
@Controller
public class OwnerController {

    private final OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @RequestMapping({ "/", "", "/index", "/index.html" })
    public String listOwners(Model model) {

        model.addAttribute("owners", ownerService.findAll());
        return "owners/index";
    }

    @RequestMapping({ "/find", "/find/index", "/find/index.html" })
    public String findOwners(Model model) {

        return "notImplemented";
    }
}