package guru.sf.sfgpetclinic.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import guru.sf.sfgpetclinic.model.Image;
import guru.sf.sfgpetclinic.model.Vet;
import guru.sf.sfgpetclinic.services.ImageService;
import guru.sf.sfgpetclinic.services.VetService;

@RequestMapping({ "/vets" })
@Controller
public class VetController {

    private final VetService vetService;
    private final ImageService imageService;

    public VetController(VetService vetService, ImageService imageService) {
        this.vetService = vetService;
        this.imageService = imageService;
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

    @RequestMapping({ "/{id}/saveImage/{imageId}" })
    public String saveImageVet(Model model, @PathVariable Long id, @PathVariable Long imageId) {

        Vet vet = vetService.findById(id);
        if (vet != null) {
            Image image = imageService.findById(imageId);
            image.setId(imageId);
            vet.setImage(image);
            vetService.save(vet);
        }

        return "redirect:/vets";
    }

    @RequestMapping({ "/add" })
    public String addVet(Model model) {
        model.addAttribute("vet", new Vet());

        return "vets/update";
    }

    @PostMapping({ "", "/" })
    public String saveOrUpdate(@ModelAttribute Vet vet) {

        Vet savedVet = vetService.save(vet);

        return "redirect:/vets/" + savedVet.getId() + "/show";
    }

}
