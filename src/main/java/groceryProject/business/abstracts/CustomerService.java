package groceryProject.business.abstracts;

import groceryProject.core.utilities.result.DataResult;
import groceryProject.webApi.responses.customer.GetAllCustomerResponse;
import groceryProject.webApi.responses.customer.GetByIdCustomerResponse;

import java.util.List;

public interface CustomerService {

    DataResult<List<GetAllCustomerResponse>> getAll();
    DataResult<GetByIdCustomerResponse> getById(int id);
}
