package groceryProject.webApi.controllers;

import groceryProject.business.abstracts.CorporateCustomerService;
import groceryProject.core.utilities.result.DataResult;
import groceryProject.core.utilities.result.Result;
import groceryProject.webApi.requests.corporateCustomer.CreateCorporateCustomerRequest;
import groceryProject.webApi.requests.corporateCustomer.DeleteCorporateCustomerRequest;
import groceryProject.webApi.requests.corporateCustomer.UpdateCorporateCustomerRequest;
import groceryProject.webApi.responses.corporateCustomer.GetAllCorporateCustomerResponse;
import groceryProject.webApi.responses.corporateCustomer.GetByIdCorporateCustomerResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/corporatecustomer")
public class CorporateCustomersController {

    @Autowired
    private CorporateCustomerService corporateCustomerService;

    @PostMapping("/add")
    public ResponseEntity<Result> add(@Valid @RequestBody CreateCorporateCustomerRequest createCorporateCustomerRequest) {
        return ResponseEntity.ok().body(corporateCustomerService.add(createCorporateCustomerRequest));
    }

    @PutMapping("/update")
    public ResponseEntity<Result> update(@Valid @RequestBody UpdateCorporateCustomerRequest updateCorporateCustomerRequest, @Valid @RequestParam int id) {
        return ResponseEntity.ok().body(corporateCustomerService.update(updateCorporateCustomerRequest, id));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Result> delete(@Valid @RequestBody DeleteCorporateCustomerRequest deleteCorporateCustomerRequest) {
        return ResponseEntity.ok().body(corporateCustomerService.delete(deleteCorporateCustomerRequest));
    }

    @GetMapping("/getall")
    public ResponseEntity<DataResult<List<GetAllCorporateCustomerResponse>>> getAll() {
        return ResponseEntity.ok(corporateCustomerService.getAll());
    }

    @GetMapping("/getbyid")
    public ResponseEntity<DataResult<GetByIdCorporateCustomerResponse>> getById(@RequestParam int id) {
        return ResponseEntity.ok().body(corporateCustomerService.getById(id));
    }
}
