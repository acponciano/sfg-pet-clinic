package guru.sf.sfgpetclinic.repositories;

import org.springframework.data.repository.CrudRepository;

import guru.sf.sfgpetclinic.model.PetType;

public interface PetTypeRepository extends CrudRepository<PetType, Long> {

}
