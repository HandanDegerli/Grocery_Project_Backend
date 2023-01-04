package groceryProject.webApi.responses.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetByIdProductResponse {

    private int id;
    private String name;
    private double price;
    private int categoryId;
}
