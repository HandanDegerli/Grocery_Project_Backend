package groceryProject.webApi.requests.employee;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateEmployeeRequest {

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
    private String firstName;

    @NotBlank
    @NotNull
    private String lastName;

    @NotBlank
    @NotNull
    @Size(min = 11, max = 11)
    private String nationalIdentity;

    @NotNull
    @Past
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private LocalDate birthDate;

    @PositiveOrZero
    private double salary;
}
