package pl.coderslab.charity.Donation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.coderslab.charity.Category.Category;
import pl.coderslab.charity.Institution.Institution;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "donation")
public class Donation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "street")
    private String street;

    @Column(name = "city")
    @Pattern(regexp = "[0-9]{2}-[0-9]{3}")
    private String city;

    @NotNull
    @Future
    @Column(name = "pickUpDate")
    private LocalDate pickUpDate;

    @NotNull
    @Column(name = "pickUpTime")
    private LocalTime pickUpTime;

    @Column(name = "pickUpComment")
    private String Comment;

    @Column(name = "zipCode")
    private String zipCode;


    @OneToMany
    @JoinColumn(name="id_person")
    private List<Category> categories = new ArrayList<>();

    @ManyToOne
    private Institution institution;

}
