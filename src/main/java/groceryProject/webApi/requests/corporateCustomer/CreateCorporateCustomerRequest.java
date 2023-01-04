package groceryProject.webApi.requests.corporateCustomer;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateCorporateCustomerRequest {

    @NotNull
    @NotBlank
    @Email
    private String email;

    @NotBlank
    @NotNull
    @Size(min = 8)
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,15}$")
    private String password;

    @NotBlank
    @NotNull
    private String address;

    @NotBlank
    @NotNull
    @Pattern(regexp = "^(\\+\\d{1,3}( )?)?((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$"
            + "|^(\\+\\d{1,3}( )?)?(\\d{3}[ ]?){2}\\d{3}$"
            + "|^(\\+\\d{1,3}( )?)?(\\d{3}[ ]?)(\\d{2}[ ]?){2}\\d{2}$")
    private String phoneNumber;

    @NotBlank
    @NotNull
    private String companyName;

    @Size(min = 9, max = 9)
    private String taxNumber;
}
