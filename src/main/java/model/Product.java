package model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;

    @NotBlank(message = "Nome não pode estar em branco")
    @Column(nullable = false)
    private String name;

    @NotBlank(message = "Descrição não pode estar em branco")
    @Column(nullable = false)
    private String description;

    @NotNull(message = "Preço não pode estar em branco")
    @DecimalMin(value = "0.00", inclusive = false, message = "Preço tem de ser maior que 0")
    @Digits(integer = 10, fraction = 2, message = "Formato inválido. Use: XX.XX")
    @Column(nullable = false)
    private BigDecimal value;
}