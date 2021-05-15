package guru.sf.sfgpetclinic.repositories;

import org.springframework.data.repository.CrudRepository;

import guru.sf.sfgpetclinic.model.Owner;

public interface OwnerRepository extends CrudRepository<Owner, Long> {

    Owner findByLastName(String lastName);

}
