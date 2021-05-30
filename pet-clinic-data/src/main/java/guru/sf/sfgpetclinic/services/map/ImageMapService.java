package guru.sf.sfgpetclinic.services.map;

import java.util.Set;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import guru.sf.sfgpetclinic.model.Image;
import guru.sf.sfgpetclinic.services.ImageService;

@Service
@Profile({ "default", "map" })
public class ImageMapService extends AbstractMapService<Image, Long> implements ImageService {

    @Override
    public Set<Image> findAll() {
        return super.findAll();
    }

    @Override
    public Image findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Image save(Image image) {
        if (image.getData() == null) {
            throw new RuntimeException("Invalid Image");
        }
        return super.save(image);
    }

    @Override
    public void delete(Image object) {
        super.delete(object);

    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);

    }

}
