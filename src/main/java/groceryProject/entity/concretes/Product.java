package groceryProject.entity.concretes;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Table(name ="products")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name="product_name", nullable = false)
    private String name;

    @Column(name = "price")
    private double price;

    @Column(name = "product_description")
    private String description;

    @Column(name ="product_expiration_date", nullable = false)
    private LocalDate expirationDate;

    @Column(name = "product_stock")
    private int stock;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

}
