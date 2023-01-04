package groceryProject.business.abstracts;

import groceryProject.core.utilities.result.DataResult;
import groceryProject.core.utilities.result.Result;
import groceryProject.entity.concretes.Category;
import groceryProject.webApi.requests.category.CreateCategoryRequest;
import groceryProject.webApi.requests.category.DeleteCategoryRequest;
import groceryProject.webApi.requests.category.UpdateCategoryRequest;
import groceryProject.webApi.responses.category.GetAllCategoryResponses;
import groceryProject.webApi.responses.category.GetByIdCategoryResponse;

import java.util.List;

public interface CategoryService {

    Result add(CreateCategoryRequest createCategoryRequest);
    Result update(UpdateCategoryRequest updateCategoryRequest, int id);
    Result delete(DeleteCategoryRequest deleteCategoryRequest);

    DataResult<List<GetAllCategoryResponses>> getAll();
    DataResult<GetByIdCategoryResponse> getById(int id);

    Category getId(int id);
}
