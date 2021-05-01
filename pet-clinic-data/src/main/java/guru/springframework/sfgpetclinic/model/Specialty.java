package guru.springframework.sfgpetclinic.model;

public class Specialty extends BaseEntity {

    private String description;

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
