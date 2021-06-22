package guru.sf.sfgpetclinic.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import guru.sf.sfgpetclinic.model.Owner;

public interface OwnerRepository extends CrudRepository<Owner, Long> {

    Owner findByLastName(String lastName);

    List<Owner> findAllByLastNameLikeIgnoreCase(String lastName);
}
