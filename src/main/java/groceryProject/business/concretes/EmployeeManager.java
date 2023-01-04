package groceryProject.business.concretes;

import groceryProject.business.abstracts.EmployeeService;
import groceryProject.business.abstracts.UserService;
import groceryProject.core.utilities.business.BusinessRules;
import groceryProject.core.utilities.exceptions.BusinessException;
import groceryProject.core.utilities.modelMapper.ModelMapperService;
import groceryProject.core.utilities.result.DataResult;
import groceryProject.core.utilities.result.Result;
import groceryProject.core.utilities.result.SuccessDataResult;
import groceryProject.core.utilities.result.SuccessResult;
import groceryProject.dataAccess.abstracts.EmployeeRepository;
import groceryProject.entity.concretes.Employee;
import groceryProject.webApi.requests.employee.CreateEmployeeRequest;
import groceryProject.webApi.requests.employee.DeleteEmployeeRequest;
import groceryProject.webApi.requests.employee.UpdateEmployeeRequest;
import groceryProject.webApi.responses.employee.GetAllEmployeeResponse;
import groceryProject.webApi.responses.employee.GetByIdEmployeeResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Service
public class EmployeeManager implements EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private ModelMapperService modelMapperService;

    @Override
    public Result add(CreateEmployeeRequest createEmployeeRequest) {

        Result rules = BusinessRules.run(isExistEmail(createEmployeeRequest.getEmail()),
                isExistNationalId(createEmployeeRequest.getNationalIdentity()),
                isPermissibleAge(createEmployeeRequest.getBirthDate()),
                isValidPassword(createEmployeeRequest.getFirstName(), createEmployeeRequest.getPassword(),
                        createEmployeeRequest.getLastName(), createEmployeeRequest.getBirthDate()));

        Employee employee = modelMapperService.getModelMapper().map(createEmployeeRequest, Employee.class);
        employeeRepository.save(employee);
        log.info("Employee: {} {} is saved!" , createEmployeeRequest.getFirstName(), createEmployeeRequest.getLastName());
        return new SuccessResult("Employee is saved on db!");
    }

    @Override
    public Result update(UpdateEmployeeRequest updateEmployeeRequest, int id) {

        Result rules = BusinessRules.run(isExistId(id), isExistEmail(updateEmployeeRequest.getEmail()),
                isExistNationalId(updateEmployeeRequest.getNationalIdentity()),
                isPermissibleAge(updateEmployeeRequest.getBirthDate()),
                isValidPassword(updateEmployeeRequest.getFirstName(), updateEmployeeRequest.getPassword(),
                        updateEmployeeRequest.getLastName(), updateEmployeeRequest.getBirthDate()));

        Employee employee1 = employeeRepository.findById(id).orElseThrow(()->new BusinessException("Id is not found on DB!"));
        Employee employee = modelMapperService.getModelMapper().map(updateEmployeeRequest, Employee.class);
        employee.setId(employee1.getId());
        employeeRepository.save(employee);
        log.info("Employee: {} {} is updated!" , updateEmployeeRequest.getFirstName(), updateEmployeeRequest.getLastName());
        return new SuccessResult("Employee is updated on db!");
    }

    @Override
    public Result delete(DeleteEmployeeRequest deleteEmployeeRequest) {

        Result rules = BusinessRules.run(isExistId(deleteEmployeeRequest.getId()));

        Employee employee = modelMapperService.getModelMapper().map(deleteEmployeeRequest, Employee.class);
        log.info("Employee object on {} is removed!" , deleteEmployeeRequest.getId());
        employeeRepository.delete(employee);
        return new SuccessResult("Employee is deleted on db!");
    }

    @Override
    public DataResult<List<GetAllEmployeeResponse>> getAll() {
        List<Employee> employeeList = employeeRepository.findAll();
        List<GetAllEmployeeResponse> returnList = employeeList.stream().map(u-> modelMapperService.getModelMapper().map(u, GetAllEmployeeResponse.class)).toList();
        return new SuccessDataResult<>(returnList, "Employees are listed!");
    }

    @Override
    public DataResult<GetByIdEmployeeResponse> getById(int id) {
        Employee inDbEmployee = employeeRepository.findById(id).orElseThrow(()-> new BusinessException("Id is not found!"));
        GetByIdEmployeeResponse returnObj = modelMapperService.getModelMapper().map(inDbEmployee, GetByIdEmployeeResponse.class);
        return new SuccessDataResult<>(returnObj, "Employee is found by Id!");
    }

    private Result isExistId(int id){
        if(!employeeRepository.existsById(id)){
            log.error("Employee id could not found!");
            throw new BusinessException("Employee id was not found on DB!");
        }
        return new SuccessResult();
    }

    private Result isExistEmail(String email){
        if(userService.existByEmail(email)){
            log.error("Employee email is exist!");
            throw new BusinessException("Employee email was found on DB!");
        }
        return new SuccessResult();
    }

    private Result isExistNationalId(String nationalId){
        if(employeeRepository.existsByNationalIdentity(nationalId)){
            log.error("National id is exist!");
            throw new BusinessException("National id was found on DB!");
        }
        return new SuccessResult();
    }

    private Result isPermissibleAge(LocalDate birthDay){
        if (LocalDate.now().getYear() - birthDay.getYear() < 18){
            log.error("Employee's age younger than 18 years!");
            throw new BusinessException("Employee's age must be older than 18 years!");
        }
        return new SuccessResult();
    }

    private Result isValidPassword(String name, String password, String lastName, LocalDate birthYear){
        if(password.contains(name)
                || password.contains(lastName)
                || password.contains(String.valueOf(birthYear.getYear()))){
            log.error("Password includes name, lastname or year of birthday!");
            throw new BusinessException("Password can not include name, lastname or year of birthday!");
        }
        return new SuccessResult();
    }


}
