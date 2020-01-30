package guru.springframework.sfgpetclinic.bootstrap;

import guru.springframework.sfgpetclinic.model.*;
import guru.springframework.sfgpetclinic.services.OwnerService;
import guru.springframework.sfgpetclinic.services.PetTypeService;
import guru.springframework.sfgpetclinic.services.SpecialityService;
import guru.springframework.sfgpetclinic.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;
    private final SpecialityService specialityService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService, SpecialityService specialityService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.specialityService = specialityService;
    }

    @Override
    public void run(String... args) throws Exception {

        int count = petTypeService.findAll().size();

        if (count == 0) {
            loadData();
        }
    }

    private void loadData() {
        PetType dog = new PetType();
        dog.setName("dog");
        PetType saveDogPetType = petTypeService.save(dog);

        PetType cat = new PetType();
        cat.setName("cat");
        PetType saveCatPetType = petTypeService.save(cat);

        Speciality radiology = new Speciality();
        radiology.setDescription("Radiology");
        Speciality savedRadiology = specialityService.save(radiology);

        Speciality surgery = new Speciality();
        surgery.setDescription("Surgery");
        Speciality savedSurgery = specialityService.save(surgery);

        Speciality dentistry =  new Speciality();
        dentistry.setDescription("Dentistry");
        Speciality savedDentistry = specialityService.save(dentistry);

        Owner owner1 = new Owner();
        owner1.setFirstName("Phil");
        owner1.setLastName("Fernandez");
        owner1.setAddress("9230 E 52nd Drive");
        owner1.setCity("Denver");
        owner1.setTelephone("720-201-9949");
        ownerService.save(owner1);

        Pet philsPet = new Pet();
        philsPet.setPetType(saveDogPetType);
        philsPet.setOwner(owner1);
        philsPet.setBirthDate(LocalDate.now());
        philsPet.setName("Cadence");
        owner1.getPets().add(philsPet);

        Owner owner2 = new Owner();
        owner2.setFirstName("Isabella");
        owner2.setLastName("Fernandez");
        owner2.setAddress("9230 E 52nd Dr");
        owner2.setCity("Denver");
        owner2.setTelephone("1221248848");

        Pet issysCat = new Pet();
        issysCat.setPetType(saveCatPetType);
        issysCat.setOwner(owner2);
        issysCat.setBirthDate(LocalDate.now());
        issysCat.setName("Crazy Cat");
        owner2.getPets().add(issysCat);

        ownerService.save(owner2);

        System.out.println("Loaded Owners....");

        Vet vet1 = new Vet();
        vet1.setFirstName("Cadence");
        vet1.setLastName("Fernandez");

        vet1.getSpecialities().add(savedRadiology);

        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Coco");
        vet2.setLastName("Burton");

        vet2.getSpecialities().add(savedSurgery);

        vetService.save(vet2);

        System.out.println("Loaded Vets....");
    }
}
