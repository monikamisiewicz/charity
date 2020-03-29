package pl.coderslab.charity.User;

import lombok.*;
import org.hibernate.validator.constraints.Length;
import pl.coderslab.charity.Role.Role;


import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;


@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    @NotEmpty(message = "*Podaj imię")
    private String firstName;

    @Column(name = "last_name")
    @NotEmpty(message = "*Podaj nazwisko")
    private String lastName;

    @Column(name = "email")
    @Email(message = "*Podaj adres email")
    @NotEmpty(message = "*Podaj adres email")
    private String email;

    @Column(name = "password")
    @Length(min = 5, message = "*Hasło musi zawierać co najmniej 5 znaków")
    @NotNull(message = "*Podaj hasło")
    private String password;

    @Column(name = "user_name", unique = true, length = 60)
    @Length(min = 5, message = "*Nazwa użytkownika musi mieć co najmniej 5 znaków")
    @NotEmpty(message = "*Podaj nazwę użytkownika")
    private String userName;

    @Column(name = "active")
    private Boolean active;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

}
