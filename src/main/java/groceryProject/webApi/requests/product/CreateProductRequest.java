package groceryProject.webApi.requests.product;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateProductRequest {

    @NotBlank
    @NotNull
    @Size(min = 2, message = "Name can not be lower than 2 characters")
    private String name;

    @Positive
    private double price;

    private String description;

    @NotNull
    @Future
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private LocalDate expirationDate;

    @PositiveOrZero
    private int stock;

    @Positive
    private int categoryId;

}
