package guru.springframework.sfgpetclinic.bootstrap;

import guru.springframework.sfgpetclinic.model.*;
import guru.springframework.sfgpetclinic.services.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;
    private final SpecialtyService specialtyService;
    private final VisitService visitService;


    public DataLoader(OwnerService ownerService, VetService vetService,
                      PetTypeService petTypeService, SpecialtyService specialtyService, VisitService visitService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.specialtyService = specialtyService;
        this.visitService = visitService;
    }

    @Override
    public void run(String... args){

        int count = petTypeService.findAll().size();

        if(count == 0){
            loadData();
        }


    }

    private void loadData() {
        PetType dog = new PetType();
        dog.setName("dog");
        PetType savedDogPetType = petTypeService.save(dog);

        PetType cat = new PetType();
        dog.setName("cat");
        PetType savedCatPetType = petTypeService.save(cat);

        Specialty radiology = new Specialty();
        radiology.setDescription("Radiology");
        specialtyService.save(radiology);

        Specialty surgery = new Specialty();
        radiology.setDescription("Surgery");
        specialtyService.save(surgery);

        Specialty dentistry = new Specialty();
        radiology.setDescription("Dentistry");
        specialtyService.save(dentistry);

        Owner owner1 = new Owner();
        owner1.setFirstName("Ruslan");
        owner1.setLastName("Simeonov");
        owner1.setAddress("123 Woensel");
        owner1.setCity("Eindhoven");
        owner1.setTelephone("123234151");

        Pet ruslansPet = new Pet();
        ruslansPet.setPetType(savedDogPetType);
        ruslansPet.setOwner(owner1);
        ruslansPet.setBirthDate(LocalDate.now());
        ruslansPet.setName("Beki");
        owner1.getPets().add(ruslansPet);

        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setFirstName("An");
        owner2.setLastName("Nguyen");
        owner2.setAddress("123 Woensel");
        owner2.setCity("Eindhoven");
        owner2.setTelephone("123234151");

        Pet ansPet = new Pet();
        ansPet.setName("Jessica");
        ansPet.setPetType(savedCatPetType);
        ansPet.setBirthDate(LocalDate.now());
        ansPet.setOwner(owner2);
        owner2.getPets().add(ansPet);

        ownerService.save(owner2);

        Visit visit1 = new Visit();
        visit1.setPet(ruslansPet);
        visit1.setDate(LocalDate.now());
        visit1.setDescription("Sneezy Dog");

        visitService.save(visit1);

        System.out.println("Loaded Owners ...");

        Vet vet1 = new Vet();
        vet1.setFirstName("Ivan");
        vet1.setLastName("Popov");
        vet1.getSpecialties().add(radiology);
        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Dimo");
        vet2.setLastName("Ivanov");
        vet2.getSpecialties().add(dentistry);

        vetService.save(vet2);
        System.out.println("Loaded Vets ...");
    }
}
