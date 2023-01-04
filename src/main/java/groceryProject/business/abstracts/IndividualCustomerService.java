package groceryProject.business.abstracts;

import groceryProject.core.utilities.result.DataResult;
import groceryProject.core.utilities.result.Result;
import groceryProject.webApi.requests.individualCustomer.CreateIndividualCustomerRequest;
import groceryProject.webApi.requests.individualCustomer.DeleteIndividualCustomerRequest;
import groceryProject.webApi.requests.individualCustomer.UpdateIndividualCustomerRequest;
import groceryProject.webApi.responses.individualCustomer.GetAllIndividualCustomerResponse;
import groceryProject.webApi.responses.individualCustomer.GetByIdIndividualCustomerResponse;

import java.util.List;

public interface IndividualCustomerService {

    Result add(CreateIndividualCustomerRequest createIndividualCustomerRequest);
    Result update(UpdateIndividualCustomerRequest updateIndividualCustomerRequest, int id);
    Result delete(DeleteIndividualCustomerRequest deleteIndividualCustomerRequest);

    DataResult<List<GetAllIndividualCustomerResponse>> getAll();
    DataResult<GetByIdIndividualCustomerResponse> getById(int id);
}
