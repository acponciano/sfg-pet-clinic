package guru.sf.sfgpetclinic.repositories;

import org.springframework.data.repository.CrudRepository;

import guru.sf.sfgpetclinic.model.Image;

public interface ImageRepository extends CrudRepository<Image, Long> {

}
