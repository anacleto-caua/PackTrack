package model;

import enums.Roles;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "employee")
@PrimaryKeyJoinColumn(name = "user_id")
public class Employee extends User {

    @Column(name = "salary", nullable = false)
    private BigDecimal salary;

    @Column(name = "hire_date", nullable = false)
    private Date hireDate;

    // Better to write the constructor yourself for Inheritance related classes
    public Employee(Long id, String username, String password, String email, String phone, String cpf, Roles role, BigDecimal salary, Date hiredate) {
        super(id, username, password, email, phone, cpf, role); // Initialize Parent fields
        this.salary = salary;
        this.hireDate = new Date();
    }
}
