package pl.coderslab.charity.Donation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import pl.coderslab.charity.Category.Category;
import pl.coderslab.charity.DonationStatus.Status;
import pl.coderslab.charity.Institution.Institution;
import pl.coderslab.charity.User.User;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
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

    @NotNull(message = "*podaj ilość worków")
    @Column(name = "quantity")
    private Integer quantity;

    @NotNull
    @Size(min = 3, message = "*podaj min 3 znaki")
    @Column(name = "street")
    private String street;

    @NotNull
    @Size(min = 3, message = "*podaj min 3 znaki")
    @Column(name = "city")
    private String city;

    @NotNull(message = "format xx-xxx")
    @Column(name = "zipCode")
    @Pattern(regexp = "[0-9]{2}-[0-9]{3}", message = "*podaj kod w formacie xx-xxx")
    private String zipCode;

    @NotNull(message = "*podaj numer telefonu")
    @Size(min = 9, max = 9, message = "*numer telefonu musi składać się 9 cyfr")
    @Column(name = "phone")
    private String phone;

    @NotNull(message = "*podaj datę odbioru")
    @Future(message = "*data musi być w przyszłości")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "pickUpDate")
    private LocalDate pickUpDate;

    @NotNull(message = "*podaj godzinę odbioru")
    @Column(name = "pickUpTime")
    private LocalTime pickUpTime;

    @Column(name = "pickUpComment")
    private String Comment;

    @NotNull(message = "*wybierz kategorie")
    @ManyToMany
    private List<Category> categories = new ArrayList<>();

    @NotNull(message = "*wybierz instytucję")
    @ManyToOne
    private Institution institution;

    @NotNull(message = "*wybierz status")
    @ManyToOne
    private Status status;

    @ManyToOne
    private User user;




}
