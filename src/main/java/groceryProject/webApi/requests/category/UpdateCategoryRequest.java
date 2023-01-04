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
public class UpdateCategoryRequest {

    //id yi parametre olarak alıcaz değişiklik olmıcagı için id
    // fieldı olusturmadık kendime not business ve controllerda vericez parametreleri

    @NotNull
    @NotBlank
    @Size(min = 2, message = "Name can not be lower than 2 characters")
    private String name;

}
