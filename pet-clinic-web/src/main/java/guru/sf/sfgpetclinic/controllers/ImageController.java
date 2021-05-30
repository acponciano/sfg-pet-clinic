package guru.sf.sfgpetclinic.controllers;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import guru.sf.sfgpetclinic.model.Image;
import guru.sf.sfgpetclinic.services.ImageService;

@RequestMapping({ "/images" })
@Controller
public class ImageController {

    private final ImageService imageService;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @RequestMapping({ "/{id}" })
    public String showImage(Model model, @PathVariable Long id) {
        model.addAttribute("image", imageService.findById(id));

        return "images/show";
    }

    @RequestMapping({ "/{id}/show" })
    public String showImageData(@PathVariable Long id, HttpServletResponse response) throws IOException {

        Image image = imageService.findById(id);

        if (image.getData() != null) {
            byte[] byteArray = new byte[image.getData().length];
            int i = 0;

            for (Byte wrappedByte : image.getData()) {
                byteArray[i++] = wrappedByte; // auto unboxing
            }

            response.setContentType("image/jpeg");
            InputStream is = new ByteArrayInputStream(byteArray);
            IOUtils.copy(is, response.getOutputStream());
        }

        return "images/show";
    }

    @RequestMapping({ "/{id}/update", "/{id}/update/{target}/{targetId}" })
    public String updateImage(Model model, @PathVariable Long id, @PathVariable String target,
            @PathVariable Long targetId) {
        model.addAttribute("image", imageService.findById(id));
        model.addAttribute("target", target);
        model.addAttribute("targetId", targetId);

        return "images/upload";
    }

    @RequestMapping({ "/add", "/add/{target}/{targetId}" })
    public String selectNewImage(Model model, @PathVariable String target, @PathVariable Long targetId) {
        System.out.println("image = selectNewImage");
        System.out.println("image = " + target);
        System.out.println("image = " + targetId);

        model.addAttribute("target", target);
        model.addAttribute("targetId", targetId);

        return "images/upload";
    }

    @PostMapping({ "", "/", "/{target}/{targetId}" })
    public String save(@PathVariable String target, @PathVariable Long targetId,
            @RequestParam("imagefile") MultipartFile file) {

        Image image = new Image();
        image.setData(getImageFromFile(file));

        Image savedImage = imageService.save(image);

        String targetRedirect = target == null ? "images" : target;

        return "redirect:/" + targetRedirect + "/" + targetId + "/saveImage/" + savedImage.getId();

    }

    private Byte[] getImageFromFile(MultipartFile file) {

        try {

            Byte[] byteObjects = new Byte[file.getBytes().length];

            int i = 0;

            for (byte b : file.getBytes()) {
                byteObjects[i++] = b;
            }
            return byteObjects;

        } catch (IOException e) {

            e.printStackTrace();
        }
        return null;
    }

}
