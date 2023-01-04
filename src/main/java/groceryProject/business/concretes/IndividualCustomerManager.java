package groceryProject.business.concretes;

import groceryProject.business.abstracts.IndividualCustomerService;
import groceryProject.business.abstracts.UserService;
import groceryProject.core.utilities.business.BusinessRules;
import groceryProject.core.utilities.exceptions.BusinessException;
import groceryProject.core.utilities.modelMapper.ModelMapperService;
import groceryProject.core.utilities.result.DataResult;
import groceryProject.core.utilities.result.Result;
import groceryProject.core.utilities.result.SuccessDataResult;
import groceryProject.core.utilities.result.SuccessResult;
import groceryProject.dataAccess.abstracts.IndividualCustomerRepository;
import groceryProject.entity.concretes.IndividualCustomer;
import groceryProject.webApi.requests.individualCustomer.CreateIndividualCustomerRequest;
import groceryProject.webApi.requests.individualCustomer.DeleteIndividualCustomerRequest;
import groceryProject.webApi.requests.individualCustomer.UpdateIndividualCustomerRequest;
import groceryProject.webApi.responses.individualCustomer.GetAllIndividualCustomerResponse;
import groceryProject.webApi.responses.individualCustomer.GetByIdIndividualCustomerResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class IndividualCustomerManager implements IndividualCustomerService {
    @Autowired
    private IndividualCustomerRepository individualCustomerRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private ModelMapperService modelMapperService;

    @Override
    public Result add(CreateIndividualCustomerRequest createIndividualCustomerRequest) {
        //adres enum olarak illeri tanımlarız, ülke, ilçe içermeli, mahalle sokak

        Result rules = BusinessRules.run(isExistEmail(createIndividualCustomerRequest.getEmail()),
                isExistNationalId(createIndividualCustomerRequest.getNationalIdentity()),
                isValidPassword(createIndividualCustomerRequest.getFirstName(), createIndividualCustomerRequest.getPassword(),
                        createIndividualCustomerRequest.getLastName()));

        IndividualCustomer individualCustomer = modelMapperService.getModelMapper().map(createIndividualCustomerRequest, IndividualCustomer.class);
        individualCustomerRepository.save(individualCustomer);
        log.info("Individual customer: {} {} is saved!" , createIndividualCustomerRequest.getFirstName(), createIndividualCustomerRequest.getLastName());
        return new SuccessResult("Individual customer is saved on Db!");
    }

    @Override
    public Result update(UpdateIndividualCustomerRequest updateIndividualCustomerRequest, int id) {
        Result rules = BusinessRules.run(isExistEmail(updateIndividualCustomerRequest.getEmail()),
                isExistNationalId(updateIndividualCustomerRequest.getNationalIdentity()),
                isValidPassword(updateIndividualCustomerRequest.getFirstName(), updateIndividualCustomerRequest.getPassword(),
                        updateIndividualCustomerRequest.getLastName()));


        IndividualCustomer individualCustomer1 = individualCustomerRepository.findById(id).orElseThrow(()->new BusinessException("Id is not found!"));
        IndividualCustomer individualCustomer = modelMapperService.getModelMapper().map(updateIndividualCustomerRequest, IndividualCustomer.class);
        individualCustomer.setId(individualCustomer1.getId());
        individualCustomerRepository.save(individualCustomer);
        log.info("Individual customer: {} {} is updated!" , updateIndividualCustomerRequest.getFirstName(), updateIndividualCustomerRequest.getLastName());
        return new SuccessResult("Individual customer is updated!");
    }

    @Override
    public Result delete(DeleteIndividualCustomerRequest deleteIndividualCustomerRequest) {
        Result rules = BusinessRules.run(isExistId(deleteIndividualCustomerRequest.getId()));

        IndividualCustomer individualCustomer = modelMapperService.getModelMapper().map(deleteIndividualCustomerRequest, IndividualCustomer.class);
        log.info("Individual customer on {} is removed!" , deleteIndividualCustomerRequest.getId());
        individualCustomerRepository.delete(individualCustomer);
        return new SuccessResult("Individual customer is removed!");
    }

    @Override
    public DataResult<List<GetAllIndividualCustomerResponse>> getAll() {
        List<IndividualCustomer> individualCustomerList = individualCustomerRepository.findAll();
        List<GetAllIndividualCustomerResponse> returnList = individualCustomerList.stream().map(u-> modelMapperService.getModelMapper().map(u, GetAllIndividualCustomerResponse.class)).toList();
        return new SuccessDataResult<>(returnList, "Individual customers are listed!");
    }

    @Override
    public DataResult<GetByIdIndividualCustomerResponse> getById(int id) {
        IndividualCustomer individualCustomer = individualCustomerRepository.findById(id).orElseThrow(()->new BusinessException("Id is not found!"));
        GetByIdIndividualCustomerResponse returnObj = modelMapperService.getModelMapper().map(individualCustomer, GetByIdIndividualCustomerResponse.class);
        return new SuccessDataResult<>(returnObj, "Individual customer is found!");
    }

    private Result isExistEmail(String email){
        if(userService.existByEmail(email)){
            log.error("Individual Customer's email is exist!");
            throw new BusinessException("Email was found on DB!");
        }
        return new SuccessResult();
    }

    private Result isExistNationalId(String nationalId){
        if(individualCustomerRepository.existsByNationalIdentity(nationalId)){
            log.error("National id is exist!");
            throw new BusinessException("National id was found on DB!");
        }
        return new SuccessResult();
    }

    private Result isValidPassword(String name, String password, String lastName){
        if(password.contains(name)
                || password.contains(lastName)){
            log.error("Password includes name or lastname!");
            throw new BusinessException("Password can not include name or lastname!");
        }
        return new SuccessResult();
    }

    private Result isExistId(int id){
        if(!individualCustomerRepository.existsById(id)){
            log.error("Id could not found!");
            throw new BusinessException("Individual customer's id was not found on DB!");
        }
        return new SuccessResult();
    }
}
