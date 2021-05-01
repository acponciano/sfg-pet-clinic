package guru.springframework.sfgpetclinic.model;

import java.util.Set;

/**
 * Created by jt on 7/13/18.
 */
public class Owner extends Person {

    private String address;
    private String city;
    private String telephone;
    private Set<Vet> vets;

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getTelephone() {
        return this.telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Set<Vet> getVets() {
        return this.vets;
    }

    public void setVets(Set<Vet> vets) {
        this.vets = vets;
    }
}
