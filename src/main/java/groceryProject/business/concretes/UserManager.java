package groceryProject.business.concretes;

import groceryProject.business.abstracts.UserService;
import groceryProject.core.utilities.exceptions.BusinessException;
import groceryProject.core.utilities.modelMapper.ModelMapperService;
import groceryProject.core.utilities.result.DataResult;
import groceryProject.core.utilities.result.SuccessDataResult;
import groceryProject.dataAccess.abstracts.UserRepository;
import groceryProject.entity.concretes.User;
import groceryProject.webApi.responses.user.GetAllUserResponse;
import groceryProject.webApi.responses.user.GetByIdUserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserManager implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapperService modelMapperService;

    @Override
    public DataResult<List<GetAllUserResponse>> getAll() {
        List<User> userList = userRepository.findAll();
        List<GetAllUserResponse> returnList = userList.stream()
                .map(u -> modelMapperService.getModelMapper().map(u, GetAllUserResponse.class)).toList();
        return new SuccessDataResult<>(returnList, "Users are listed!");
    }

    @Override
    public DataResult<GetByIdUserResponse> getById(int id) {
        User inDbUser = userRepository.findById(id).orElseThrow(()-> new BusinessException("Id is not found!"));
        GetByIdUserResponse returnObj = modelMapperService.getModelMapper().map(inDbUser, GetByIdUserResponse.class);
        return new SuccessDataResult<>(returnObj, "User is found by Id!");
    }

    @Override
    public boolean existByEmail(String email){
        return userRepository.existsByEmail(email);
    }

    @Override
    public boolean existById(int id){
        return userRepository.existsById(id);
    }

}
