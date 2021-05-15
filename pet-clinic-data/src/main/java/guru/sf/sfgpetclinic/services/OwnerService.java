package guru.sf.sfgpetclinic.services;

import guru.sf.sfgpetclinic.model.Owner;

public interface OwnerService extends CrudService<Owner, Long> {

    Owner findByLastName(String lastName);

}
