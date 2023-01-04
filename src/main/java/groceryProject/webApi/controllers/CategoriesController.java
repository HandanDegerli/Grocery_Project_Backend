package groceryProject.webApi.controllers;

import groceryProject.business.abstracts.CategoryService;
import groceryProject.core.utilities.result.DataResult;
import groceryProject.core.utilities.result.Result;
import groceryProject.webApi.requests.category.CreateCategoryRequest;
import groceryProject.webApi.requests.category.DeleteCategoryRequest;
import groceryProject.webApi.requests.category.UpdateCategoryRequest;
import groceryProject.webApi.responses.category.GetAllCategoryResponses;
import groceryProject.webApi.responses.category.GetByIdCategoryResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoriesController {

    @Autowired
   private CategoryService categoryService;

    @PostMapping("/add")
    public ResponseEntity<Result> add(@Valid @RequestBody CreateCategoryRequest createCategoryRequest){
        return new ResponseEntity<>(categoryService.add(createCategoryRequest), HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<Result> update(@Valid @RequestBody UpdateCategoryRequest updateCategoryRequest, @Valid @RequestParam int id){
        return new ResponseEntity<>(categoryService.update(updateCategoryRequest, id), HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Result> delete(@Valid @RequestBody DeleteCategoryRequest deleteCategoryRequest){
        return new ResponseEntity<>(categoryService.delete(deleteCategoryRequest), HttpStatus.OK);
    }

    @GetMapping("/getall")
    public ResponseEntity<DataResult<List<GetAllCategoryResponses>>> getAll(){
        return ResponseEntity.ok(categoryService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DataResult<GetByIdCategoryResponse>> getById(@RequestParam int id){
        return ResponseEntity.ok(categoryService.getById(id));
    }

}
