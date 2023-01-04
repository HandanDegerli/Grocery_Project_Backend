package groceryProject.business.abstracts;

import groceryProject.core.utilities.result.DataResult;
import groceryProject.core.utilities.result.Result;
import groceryProject.webApi.requests.product.CreateProductRequest;
import groceryProject.webApi.requests.product.DeleteProductRequest;
import groceryProject.webApi.requests.product.UpdateProductRequest;
import groceryProject.webApi.responses.product.GetAllProductResponses;
import groceryProject.webApi.responses.product.GetByIdProductResponse;

import javax.xml.crypto.Data;
import java.util.List;

public interface ProductService {
    Result add(CreateProductRequest createProductRequest);
    Result update(UpdateProductRequest updateProductRequest, int id);
    Result delete(DeleteProductRequest deleteProductRequest);

    DataResult<List<GetAllProductResponses>> getAll();
    DataResult<GetByIdProductResponse> getById(int id);
}
