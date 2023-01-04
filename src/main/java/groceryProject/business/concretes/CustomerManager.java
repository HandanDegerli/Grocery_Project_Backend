package groceryProject.business.concretes;

import groceryProject.business.abstracts.CustomerService;
import groceryProject.core.utilities.exceptions.BusinessException;
import groceryProject.core.utilities.modelMapper.ModelMapperService;
import groceryProject.core.utilities.result.DataResult;
import groceryProject.core.utilities.result.SuccessDataResult;
import groceryProject.dataAccess.abstracts.CustomerRepository;
import groceryProject.entity.concretes.Customer;
import groceryProject.webApi.responses.customer.GetAllCustomerResponse;
import groceryProject.webApi.responses.customer.GetByIdCustomerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerManager implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private ModelMapperService modelMapperService;

    @Override
    public DataResult<List<GetAllCustomerResponse>> getAll() {
        List<Customer> customerList = customerRepository.findAll();
        List<GetAllCustomerResponse> returnList = customerList.stream().map(u -> modelMapperService.getModelMapper().map(u, GetAllCustomerResponse.class)).toList();
        return new SuccessDataResult<>(returnList, "Customers are listed!");
    }

    @Override
    public DataResult<GetByIdCustomerResponse> getById(int id) {
        Customer inDbCustomer = customerRepository.findById(id).orElseThrow(()-> new BusinessException("Id is not found!"));
        GetByIdCustomerResponse returnObj = modelMapperService.getModelMapper().map(inDbCustomer, GetByIdCustomerResponse.class);
        return new SuccessDataResult<>(returnObj, "Customer is found by db!");
    }
}
