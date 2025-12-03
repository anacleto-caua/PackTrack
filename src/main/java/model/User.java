package model;

import enums.Roles;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
//@AllArgsConstructor dangerous to inheritance related classes at Hibernate, better to avoid
@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.JOINED)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, unique = true)
    private String phone;

    @Column(nullable = false, unique = true)
    private String cpf;

    // Hibernate will create a Helper Table by itself, given these values are configured
    @ElementCollection(targetClass = Roles.class, fetch = FetchType.EAGER)
    // Define the helper table name and the foreign key to this User
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    // Store the Enum as a String ("ADMIN") instead of a number (0)
    @Enumerated(EnumType.STRING)
    @Column(name = "roles", nullable = false)
    private List<Roles> roles;

    // Better to write the constructor yourself for Inheritance related classes
    User(Long id, String username, String password, String email, String phone, String cpf, List<Roles> roles) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.cpf = cpf;
    }
}
