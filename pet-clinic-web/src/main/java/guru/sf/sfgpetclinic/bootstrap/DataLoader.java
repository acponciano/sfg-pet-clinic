package guru.sf.sfgpetclinic.bootstrap;

import java.time.LocalDate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import guru.sf.sfgpetclinic.model.Owner;
import guru.sf.sfgpetclinic.model.Pet;
import guru.sf.sfgpetclinic.model.PetType;
import guru.sf.sfgpetclinic.model.Specialty;
import guru.sf.sfgpetclinic.model.Vet;
import guru.sf.sfgpetclinic.model.Visit;
import guru.sf.sfgpetclinic.services.OwnerService;
import guru.sf.sfgpetclinic.services.PetTypeService;
import guru.sf.sfgpetclinic.services.SpecialtyService;
import guru.sf.sfgpetclinic.services.VetService;
import guru.sf.sfgpetclinic.services.VisitService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService PetTypeService;
    private final SpecialtyService specialtiesService;
    private final VisitService visitService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService PetTypeService,
            SpecialtyService specialtiesService, VisitService visitService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.PetTypeService = PetTypeService;
        this.specialtiesService = specialtiesService;
        this.visitService = visitService;
    }

    @Override
    public void run(String... args) throws Exception {

        int count = PetTypeService.findAll().size();

        if (count == 0) {
            loadData();
        }
    }

    public void loadData() throws Exception {

        PetType dog = new PetType();
        dog.setName("Dog");
        PetType savedDogType = PetTypeService.save(dog);
        log.info("######################################################");
        log.info("######################################################");
        log.info("bootstrap.loaddata - Dog saved");
        log.info("######################################################");
        log.info("######################################################");

        PetType cat = new PetType();
        dog.setName("Cat");
        PetType savedCatType = PetTypeService.save(cat);

        Owner owner1 = new Owner();
        owner1.setFirstName("Michael");
        owner1.setLastName("Weston");
        owner1.setAddress("123 Brickerel");
        owner1.setCity("Recife");
        owner1.setTelephone("123123123");

        Pet mikesPet = new Pet();
        mikesPet.setName("Molly");
        mikesPet.setPetType(savedDogType);
        mikesPet.setOwner(owner1);
        mikesPet.setBirthDate(LocalDate.now());
        owner1.getPets().add(mikesPet);

        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setFirstName("Fiona");
        owner2.setLastName("Glenanne");
        owner1.setAddress("123 Brickerel");
        owner1.setCity("Recife");
        owner1.setTelephone("123123123");

        Pet fionasPet = new Pet();
        fionasPet.setName("JustCat");
        fionasPet.setPetType(savedCatType);
        fionasPet.setOwner(owner2);
        fionasPet.setBirthDate(LocalDate.now());
        owner2.getPets().add(fionasPet);

        ownerService.save(owner2);

        Visit catVisit = new Visit();
        catVisit.setDate(LocalDate.now());
        catVisit.setDescription("Get help to heal a flu.");
        catVisit.setPet(fionasPet);

        visitService.save(catVisit);

        Visit dogVisit = new Visit();
        dogVisit.setDate(LocalDate.now());
        dogVisit.setDescription("Sneezy dog.");
        dogVisit.setPet(mikesPet);

        visitService.save(dogVisit);

        System.out.println("Loaded Owners....");

        Specialty radiology = new Specialty();
        radiology.setDescription("Radiology");
        Specialty savedRadiology = specialtiesService.save(radiology);

        Specialty surgery = new Specialty();
        surgery.setDescription("Surgery");
        Specialty savedSurgery = specialtiesService.save(surgery);

        Specialty dentistry = new Specialty();
        dentistry.setDescription("Dentistry");
        Specialty savedDentistry = specialtiesService.save(dentistry);

        Vet vet1 = new Vet();
        vet1.setFirstName("Sam");
        vet1.setLastName("Axe");
        vet1.getSpecialties().add(savedRadiology);

        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Jessie");
        vet2.setLastName("Porter");
        vet2.getSpecialties().add(savedRadiology);
        vet2.getSpecialties().add(savedSurgery);
        vet2.getSpecialties().add(savedDentistry);

        vetService.save(vet2);

        System.out.println("Loaded Vets....");
    }

}
