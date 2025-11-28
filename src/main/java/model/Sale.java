package model;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.util.ArrayList; // Important
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "sale")
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @OneToMany(mappedBy = "sale", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<SaleItem> items = new ArrayList<>();

    @Column(nullable = false)
    private BigDecimal totalValue;

    @Column(length = 3)
    private String currency;

    // Use this method to add items. It ensures the Item knows about the Sale.
    public void addItem(SaleItem item) {
        items.add(item);
        item.setSale(this); // The Item needs to know who its parent is!
    }
}