package groceryProject.webApi.controllers;

import groceryProject.business.abstracts.CustomerService;
import groceryProject.core.utilities.result.DataResult;
import groceryProject.webApi.responses.customer.GetAllCustomerResponse;
import groceryProject.webApi.responses.customer.GetByIdCustomerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customer")
public class CustomersController {
    @Autowired
    private CustomerService customerService;

    @GetMapping("/getall")
    public ResponseEntity<DataResult<List<GetAllCustomerResponse>>> getAll() {
        return ResponseEntity.ok(customerService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DataResult<GetByIdCustomerResponse>> getById(@RequestParam int id) {
        return ResponseEntity.ok().body(customerService.getById(id));
    }
}
