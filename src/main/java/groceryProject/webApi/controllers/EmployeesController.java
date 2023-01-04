package groceryProject.webApi.controllers;

import groceryProject.business.abstracts.EmployeeService;
import groceryProject.core.utilities.result.DataResult;
import groceryProject.core.utilities.result.Result;
import groceryProject.webApi.requests.employee.CreateEmployeeRequest;
import groceryProject.webApi.requests.employee.DeleteEmployeeRequest;
import groceryProject.webApi.requests.employee.UpdateEmployeeRequest;
import groceryProject.webApi.responses.employee.GetAllEmployeeResponse;
import groceryProject.webApi.responses.employee.GetByIdEmployeeResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employee")
public class EmployeesController {
    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/add")
    public ResponseEntity<Result> add(@Valid @RequestBody CreateEmployeeRequest createEmployeeRequest){
        return new ResponseEntity<>(employeeService.add(createEmployeeRequest), HttpStatus.OK);
    }

    @PutMapping("/update")
    public  ResponseEntity<Result> update(@Valid @RequestBody UpdateEmployeeRequest updateEmployeeRequest, @RequestParam int id){
        return new ResponseEntity<>(employeeService.update(updateEmployeeRequest, id), HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Result> delete(@Valid @RequestBody DeleteEmployeeRequest deleteEmployeeRequest){
        // static return ResponseEntity.ok().body(employeeService.delete(deleteEmployeeRequest));
        // ya da ne w instance olustur mvc de kullanılması açısından
        return new ResponseEntity<>(employeeService.delete(deleteEmployeeRequest), HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<DataResult<List<GetAllEmployeeResponse>>> getAll(){
        return new ResponseEntity<>(employeeService.getAll(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<DataResult<GetByIdEmployeeResponse>> getById(@RequestParam int id){
        return new ResponseEntity<>(employeeService.getById(id), HttpStatus.OK);
    }
}
