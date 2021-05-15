package guru.sf.sfgpetclinic.repositories;

import org.springframework.data.repository.CrudRepository;

import guru.sf.sfgpetclinic.model.Pet;

public interface PetRepository extends CrudRepository<Pet, Long> {

}
