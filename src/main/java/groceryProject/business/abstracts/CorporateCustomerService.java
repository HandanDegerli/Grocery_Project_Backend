package groceryProject.business.abstracts;

import groceryProject.core.utilities.result.DataResult;
import groceryProject.core.utilities.result.Result;
import groceryProject.webApi.requests.corporateCustomer.CreateCorporateCustomerRequest;
import groceryProject.webApi.requests.corporateCustomer.DeleteCorporateCustomerRequest;
import groceryProject.webApi.requests.corporateCustomer.UpdateCorporateCustomerRequest;
import groceryProject.webApi.responses.corporateCustomer.GetAllCorporateCustomerResponse;
import groceryProject.webApi.responses.corporateCustomer.GetByIdCorporateCustomerResponse;

import java.util.List;

public interface CorporateCustomerService {

    Result add(CreateCorporateCustomerRequest createCorporateCustomerRequest);
    Result update(UpdateCorporateCustomerRequest updateCorporateCustomerRequest, int id);
    Result delete(DeleteCorporateCustomerRequest deleteCorporateCustomerRequest);

    DataResult<List<GetAllCorporateCustomerResponse>> getAll();
    DataResult<GetByIdCorporateCustomerResponse> getById(int id);
}
