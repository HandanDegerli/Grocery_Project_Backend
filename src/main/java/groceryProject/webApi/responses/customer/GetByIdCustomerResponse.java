package groceryProject.webApi.responses.customer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GetByIdCustomerResponse {

    private int id;

    private String address;

    private String phoneNumber;
}
