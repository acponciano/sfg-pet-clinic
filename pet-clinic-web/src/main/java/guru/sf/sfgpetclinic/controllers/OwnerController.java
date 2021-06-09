package guru.sf.sfgpetclinic.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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

    @RequestMapping({ "/{id}/show" })
    public String showOwner(Model model, @PathVariable Long id) {
        model.addAttribute("owners", ownerService.findById(id));

        return "owners/" + id + "/show";
    }

    @GetMapping("/{ownerId}")
    public ModelAndView showOwner(@PathVariable("ownerId") Long ownerId) {
        ModelAndView mav = new ModelAndView("owners/ownerDetails");

        mav.addObject(ownerService.findById(ownerId));
        return mav;
    }

}
