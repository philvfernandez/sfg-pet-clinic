package guru.springframework.sfgpetclinic.bootstrap;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.model.Vet;
import guru.springframework.sfgpetclinic.services.OwnerService;
import guru.springframework.sfgpetclinic.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;

    public DataLoader(OwnerService ownerService, VetService vetService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
    }

    @Override
    public void run(String... args) throws Exception {

        Owner owner1 = new Owner();
        owner1.setFirstName("Phil");
        owner1.setLastName("Fernandez");
        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setFirstName("Isabella");
        owner2.setLastName("Fernandez");
        ownerService.save(owner2);

        System.out.println("Loaded Owners....");

        Vet vet1 = new Vet();
        vet1.setFirstName("Cadence");
        vet1.setLastName("Fernandez");
        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Coco");
        vet2.setLastName("Burton");
        vetService.save(vet2);

        System.out.println("Loaded Vets....");
    }
}
