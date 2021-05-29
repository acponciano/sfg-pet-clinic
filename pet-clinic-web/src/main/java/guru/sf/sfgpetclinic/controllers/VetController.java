package guru.sf.sfgpetclinic.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import guru.sf.sfgpetclinic.model.Vet;
import guru.sf.sfgpetclinic.services.VetService;

@RequestMapping({ "/vets" })
@Controller
public class VetController {

    private final VetService vetService;

    public VetController(VetService vetService) {
        this.vetService = vetService;
    }

    @RequestMapping({ "", "/", ".html", "/index.html" })
    public String listVets(Model model) {

        model.addAttribute("vets", vetService.findAll());

        return "vets/index";
    }

    @RequestMapping({ "/find", "/find/index", "/find/index.html" })
    public String findVets(Model model) {

        return "notImplemented";
    }

    @RequestMapping({ "/{id}/show" })
    public String showVet(Model model, @PathVariable Long id) {
        model.addAttribute("vet", vetService.findById(id));

        return "vets/show";
    }

    @RequestMapping({ "/{id}/update" })
    public String updateVet(Model model, @PathVariable Long id) {
        model.addAttribute("vet", vetService.findById(id));

        return "vets/update";
    }

    @RequestMapping({ "/add" })
    public String addVet(Model model) {
        model.addAttribute("vet", new Vet());

        return "vets/update";
    }

    @PostMapping({ "", "/" })
    public String saveOrUpdate(@ModelAttribute Vet vet) {

        System.out.println("Vet = START");
        System.out.println("Vet = " + vet);
        System.out.println("Vet = " + vet.getId());
        System.out.println("Vet = " + vet.getFirstName());

        Vet savedVet = vetService.save(vet);

        System.out.println("Saved Vet = " + savedVet);
        System.out.println("Saved Vet = " + savedVet.getId());
        System.out.println("Saved Vet = " + savedVet.getFirstName());

        return "redirect:/vets/" + savedVet.getId() + "/show";
    }

}
