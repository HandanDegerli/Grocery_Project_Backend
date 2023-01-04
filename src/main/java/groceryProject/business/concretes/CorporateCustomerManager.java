package groceryProject.business.concretes;

import groceryProject.business.abstracts.CorporateCustomerService;
import groceryProject.business.abstracts.UserService;
import groceryProject.core.utilities.business.BusinessRules;
import groceryProject.core.utilities.exceptions.BusinessException;
import groceryProject.core.utilities.modelMapper.ModelMapperService;
import groceryProject.core.utilities.result.DataResult;
import groceryProject.core.utilities.result.Result;
import groceryProject.core.utilities.result.SuccessDataResult;
import groceryProject.core.utilities.result.SuccessResult;
import groceryProject.dataAccess.abstracts.CorporateCustomerRepository;
import groceryProject.entity.concretes.CorporateCustomer;
import groceryProject.webApi.requests.corporateCustomer.CreateCorporateCustomerRequest;
import groceryProject.webApi.requests.corporateCustomer.DeleteCorporateCustomerRequest;
import groceryProject.webApi.requests.corporateCustomer.UpdateCorporateCustomerRequest;
import groceryProject.webApi.responses.corporateCustomer.GetAllCorporateCustomerResponse;
import groceryProject.webApi.responses.corporateCustomer.GetByIdCorporateCustomerResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class CorporateCustomerManager implements CorporateCustomerService {
    @Autowired
    private CorporateCustomerRepository corporateCustomerRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private ModelMapperService modelMapperService;

    @Override
    public Result add(CreateCorporateCustomerRequest createCorporateCustomerRequest) {

        Result rules = BusinessRules.run(isExistEmail(createCorporateCustomerRequest.getEmail()), isExistTaxNumber(createCorporateCustomerRequest.getTaxNumber()));

        CorporateCustomer corporateCustomer = modelMapperService.getModelMapper().map(createCorporateCustomerRequest, CorporateCustomer.class);
        corporateCustomerRepository.save(corporateCustomer);
        log.info("Corporate customer: {} is saved!" , createCorporateCustomerRequest.getCompanyName());
        return new SuccessResult("Corporate customer is saved!");
    }

    @Override
    public Result update(UpdateCorporateCustomerRequest updateCorporateCustomerRequest, int id) {
        Result rules = BusinessRules.run(isExistEmail(updateCorporateCustomerRequest.getEmail()), isExistTaxNumber(updateCorporateCustomerRequest.getTaxNumber()));

        CorporateCustomer corporateCustomer1 = corporateCustomerRepository.findById(id).orElseThrow(()-> new BusinessException("Id is not found!"));
        CorporateCustomer corporateCustomer = modelMapperService.getModelMapper().map(updateCorporateCustomerRequest, CorporateCustomer.class);
        corporateCustomer.setId(corporateCustomer1.getId());
        corporateCustomerRepository.save(corporateCustomer);
        log.info("Corporate customer: {} is updated!" , updateCorporateCustomerRequest.getCompanyName());
        return new SuccessResult("Corporate customer is updated!");
    }

    @Override
    public Result delete(DeleteCorporateCustomerRequest deleteCorporateCustomerRequest) {
        Result rules = BusinessRules.run(isExistId(deleteCorporateCustomerRequest.getId()));

        CorporateCustomer corporateCustomer = modelMapperService.getModelMapper().map(deleteCorporateCustomerRequest, CorporateCustomer.class);
        log.info("Corporate customer on {} is removed!" , deleteCorporateCustomerRequest.getId());
        corporateCustomerRepository.delete(corporateCustomer);
        return new SuccessResult("Corporate customer is removed!");
    }

    @Override
    public DataResult<List<GetAllCorporateCustomerResponse>> getAll() {
        List<CorporateCustomer> corporateCustomerList = corporateCustomerRepository.findAll();
        List<GetAllCorporateCustomerResponse> returnList = corporateCustomerList.stream().map(u-> modelMapperService.getModelMapper().map(u, GetAllCorporateCustomerResponse.class)).toList();
        return new SuccessDataResult<>(returnList, "Corporate customers are listed!");
    }

    @Override
    public DataResult<GetByIdCorporateCustomerResponse> getById(int id) {
        CorporateCustomer corporateCustomer = corporateCustomerRepository.findById(id).orElseThrow(()->new BusinessException("Id is not found!"));
        GetByIdCorporateCustomerResponse returnObj = modelMapperService.getModelMapper().map(corporateCustomer, GetByIdCorporateCustomerResponse.class);
        return new SuccessDataResult<>(returnObj, "Corporate customer is found!");
    }

    private Result isExistEmail(String email){
        if(userService.existByEmail(email)){
            log.error("Corporate Customer's email is exist!");
            throw new BusinessException("Email was found on DB!");
        }
        return new SuccessResult();
    }

    private  Result isExistTaxNumber(String taxNumber){
        if(corporateCustomerRepository.existsByTaxNumber(taxNumber)){
            throw new BusinessException("Tax number can not repeat!");
        }
        return new SuccessResult();
    }

    private Result isExistId(int id){
        if(!corporateCustomerRepository.existsById(id)){
            log.error("Id could not found!");
            throw new BusinessException("Corporate customer's id was not found on DB!");
        }
        return new SuccessResult();
    }

}
