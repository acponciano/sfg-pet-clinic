package guru.sf.sfgpetclinic.services;

import java.util.Set;

import guru.sf.sfgpetclinic.model.Owner;

public interface OwnerService extends CrudService<Owner, Long> {

    Owner findByLastName(String lastName);

    Set<Owner> findAllByLastName(String lastName);

}
