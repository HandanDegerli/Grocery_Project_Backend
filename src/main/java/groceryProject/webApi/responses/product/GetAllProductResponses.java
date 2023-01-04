package groceryProject.webApi.responses.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetAllProductResponses {
    private int id;
    private String name;
    private double price;
    private String description;
    private LocalDate expirationDate;
    private int stock;
    private int categoryId;
}
