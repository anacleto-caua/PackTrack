package model;

import enums.Roles;
import jakarta.persistence.*;
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

    // Store the Enum as a String ("ADMIN") instead of a number (0)
    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private Roles role;

    // Better to write the constructor yourself for Inheritance related classes
    User(Long id, String username, String password, String email, String phone, String cpf, Roles role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.cpf = cpf;
        this.role = role;
    }
}
