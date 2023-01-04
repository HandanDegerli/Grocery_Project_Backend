package groceryProject.webApi.controllers;

import groceryProject.business.abstracts.ProductService;
import groceryProject.core.utilities.result.DataResult;
import groceryProject.core.utilities.result.Result;
import groceryProject.webApi.requests.product.CreateProductRequest;
import groceryProject.webApi.requests.product.DeleteProductRequest;
import groceryProject.webApi.requests.product.UpdateProductRequest;
import groceryProject.webApi.responses.product.GetAllProductResponses;
import groceryProject.webApi.responses.product.GetByIdProductResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductsController {

    @Autowired
    private ProductService productService;

    @PostMapping("/add")
    public ResponseEntity<Result> add(@Valid @RequestBody CreateProductRequest createProductRequest){
        return new ResponseEntity<>(productService.add(createProductRequest), HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<Result> update(@Valid @RequestBody UpdateProductRequest updateProductRequest, @Valid @RequestParam int id){
        return new ResponseEntity<>(productService.update(updateProductRequest, id), HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Result> delete(@Valid @RequestBody DeleteProductRequest deleteProductRequest){
        return new ResponseEntity<>(productService.delete(deleteProductRequest), HttpStatus.OK);
    }

    @GetMapping("/getall")
    public ResponseEntity<DataResult<List<GetAllProductResponses>>> getAll(){
        return ResponseEntity.ok(productService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DataResult<GetByIdProductResponse>> getById(@RequestParam int id){
        return ResponseEntity.ok(productService.getById(id));
    }

}
