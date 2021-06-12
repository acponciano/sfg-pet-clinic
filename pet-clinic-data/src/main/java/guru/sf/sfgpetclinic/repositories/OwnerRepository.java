package guru.sf.sfgpetclinic.repositories;

import java.util.Set;

import org.springframework.data.repository.CrudRepository;

import guru.sf.sfgpetclinic.model.Owner;

public interface OwnerRepository extends CrudRepository<Owner, Long> {

    Owner findByLastName(String lastName);

    Set<Owner> findAllByLastName(String lastName);
}
