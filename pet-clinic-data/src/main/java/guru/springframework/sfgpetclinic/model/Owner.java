package guru.springframework.sfgpetclinic.model;

import java.util.Set;

/**
 * Created by jt on 7/13/18.
 */
public class Owner extends Person {

    private Set<Vet> vets;

    public Set<Vet> getVets() {
        return this.vets;
    }

    public void setVets(Set<Vet> vets) {
        this.vets = vets;
    }
}
