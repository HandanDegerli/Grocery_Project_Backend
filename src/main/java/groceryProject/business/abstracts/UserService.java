package groceryProject.business.abstracts;

import groceryProject.core.utilities.result.DataResult;
import groceryProject.webApi.responses.user.GetAllUserResponse;
import groceryProject.webApi.responses.user.GetByIdUserResponse;

import java.util.List;

public interface UserService {

    DataResult<List<GetAllUserResponse>> getAll();
    DataResult<GetByIdUserResponse> getById(int id);

    boolean existByEmail(String email);
    boolean existById(int id);
}
