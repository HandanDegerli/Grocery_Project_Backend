package groceryProject.webApi.controllers;

import groceryProject.business.abstracts.UserService;
import groceryProject.core.utilities.result.DataResult;
import groceryProject.webApi.responses.user.GetAllUserResponse;
import groceryProject.webApi.responses.user.GetByIdUserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UsersController {

    @Autowired
    private UserService userService;

    @GetMapping("/getall")
    public ResponseEntity<DataResult<List<GetAllUserResponse>>> getAll(){
        return ResponseEntity.ok(userService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DataResult<GetByIdUserResponse>> getById(@RequestParam int id){
        return ResponseEntity.ok(userService.getById(id));
    }
}
