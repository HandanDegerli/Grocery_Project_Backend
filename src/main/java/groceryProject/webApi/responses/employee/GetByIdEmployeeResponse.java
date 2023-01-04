package groceryProject.webApi.responses.employee;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GetByIdEmployeeResponse {

    private int id;

    private String firstName;

    private String lastName;

    private String nationalIdentity;

    private LocalDate birthDate;

    private double salary;
}