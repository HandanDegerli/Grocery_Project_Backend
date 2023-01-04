package groceryProject.webApi.controllers;

import groceryProject.business.abstracts.IndividualCustomerService;
import groceryProject.core.utilities.result.DataResult;
import groceryProject.core.utilities.result.Result;
import groceryProject.webApi.requests.individualCustomer.CreateIndividualCustomerRequest;
import groceryProject.webApi.requests.individualCustomer.DeleteIndividualCustomerRequest;
import groceryProject.webApi.requests.individualCustomer.UpdateIndividualCustomerRequest;
import groceryProject.webApi.responses.individualCustomer.GetAllIndividualCustomerResponse;
import groceryProject.webApi.responses.individualCustomer.GetByIdIndividualCustomerResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/individualcustomer")
public class IndividualCustomersController {

    @Autowired
    private IndividualCustomerService individualCustomerService;

    @PostMapping("/add")
    public ResponseEntity<Result> add(@Valid @RequestBody CreateIndividualCustomerRequest createIndividualCustomerRequest) {
        return ResponseEntity.ok().body(individualCustomerService.add(createIndividualCustomerRequest));
    }

    @PutMapping("/update")
    public ResponseEntity<Result> update(@Valid @RequestBody UpdateIndividualCustomerRequest updateIndividualCustomerRequest,
                                         @Valid @RequestParam int id) {
        return ResponseEntity.ok().body(individualCustomerService.update(updateIndividualCustomerRequest, id));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Result> delete(@Valid @RequestBody DeleteIndividualCustomerRequest deleteIndividualCustomerRequest) {
        return ResponseEntity.ok().body(individualCustomerService.delete(deleteIndividualCustomerRequest));
    }

    @GetMapping("/getall")
    public ResponseEntity<DataResult<List<GetAllIndividualCustomerResponse>>> getAll() {
        return ResponseEntity.ok(individualCustomerService.getAll());
    }

    @GetMapping("/getbyid")
    public ResponseEntity<DataResult<GetByIdIndividualCustomerResponse>> getById(@RequestParam int id) {
        return ResponseEntity.ok().body(individualCustomerService.getById(id));
    }
}

