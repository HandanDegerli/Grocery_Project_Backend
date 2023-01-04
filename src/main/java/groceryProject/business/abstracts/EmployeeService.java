package groceryProject.business.abstracts;

import groceryProject.core.utilities.result.DataResult;
import groceryProject.core.utilities.result.Result;
import groceryProject.webApi.requests.employee.CreateEmployeeRequest;
import groceryProject.webApi.requests.employee.DeleteEmployeeRequest;
import groceryProject.webApi.requests.employee.UpdateEmployeeRequest;
import groceryProject.webApi.responses.employee.GetAllEmployeeResponse;
import groceryProject.webApi.responses.employee.GetByIdEmployeeResponse;

import java.util.List;

public interface EmployeeService {

    Result add(CreateEmployeeRequest createEmployeeRequest);
    Result update(UpdateEmployeeRequest updateEmployeeRequest, int id);
    Result delete(DeleteEmployeeRequest deleteEmployeeRequest);

    DataResult<List<GetAllEmployeeResponse>> getAll();
    DataResult<GetByIdEmployeeResponse> getById(int id);
}
