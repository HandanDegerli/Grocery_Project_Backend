package groceryProject.webApi.requests.category;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateCategoryRequest {

    //Requestler son kullanıcıdan alındığı için entityden bağımsız olarak validation anotasyonları burada uygulanır
    @NotNull
    @NotBlank
    @Size(min = 2, message = "Name can not be lower than 2 characters")
    private String name;
}
