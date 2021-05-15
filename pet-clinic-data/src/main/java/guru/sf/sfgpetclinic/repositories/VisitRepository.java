package guru.sf.sfgpetclinic.repositories;

import org.springframework.data.repository.CrudRepository;

import guru.sf.sfgpetclinic.model.Visit;

public interface VisitRepository extends CrudRepository<Visit, Long> {

}
