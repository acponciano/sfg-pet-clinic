package guru.sf.sfgpetclinic.services;

import guru.sf.sfgpetclinic.model.Vet;

public interface VetService extends CrudService<Vet, Long> {

    Vet findByLastName(String lastName);
}
