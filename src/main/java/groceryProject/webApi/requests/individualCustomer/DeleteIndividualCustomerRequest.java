package groceryProject.webApi.requests.individualCustomer;

import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DeleteIndividualCustomerRequest {

    @Positive
    private int id;
}
