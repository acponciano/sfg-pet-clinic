package guru.sf.sfgpetclinic.springdatajpa;

import java.util.HashSet;
import java.util.Set;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import guru.sf.sfgpetclinic.model.Image;
import guru.sf.sfgpetclinic.repositories.ImageRepository;
import guru.sf.sfgpetclinic.services.ImageService;

@Service
@Profile("springdatajpa")
public class ImageSDJpaService implements ImageService {

    private final ImageRepository imageRepository;

    public ImageSDJpaService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    @Override
    public Set<Image> findAll() {

        Set<Image> images = new HashSet<>();

        imageRepository.findAll().forEach(images::add);

        return images;
    }

    @Override
    public Image findById(Long id) {
        return imageRepository.findById(id).orElse(null);
    }

    @Override
    public Image save(Image object) {
        return imageRepository.save(object);
    }

    @Override
    public void delete(Image object) {
        imageRepository.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        imageRepository.deleteById(id);

    }

}
