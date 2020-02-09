package guru.springframework.sfgpetclinic.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "pets")
public class Pet extends BaseEntity{

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "type_id")
    private PetType petType;

    @ManyToOne
    @JoinColumn(name = "onwer_id")
    private Owner owner;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    //Good habit to initialize visit set so it will never be null.
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pet")
    private Set<Visit> vists = new HashSet<>();
}
