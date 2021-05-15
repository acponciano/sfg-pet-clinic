package guru.sf.sfgpetclinic.repositories;

import org.springframework.data.repository.CrudRepository;

import guru.sf.sfgpetclinic.model.Vet;

public interface VetRepository extends CrudRepository<Vet, Long> {

    Vet findByLastName(String lastName);

}
