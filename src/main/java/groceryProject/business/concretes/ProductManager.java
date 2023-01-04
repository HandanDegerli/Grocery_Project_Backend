package groceryProject.business.concretes;

import groceryProject.business.abstracts.CategoryService;
import groceryProject.business.abstracts.ProductService;
import groceryProject.core.utilities.business.BusinessRules;
import groceryProject.core.utilities.exceptions.BusinessException;
import groceryProject.core.utilities.modelMapper.ModelMapperService;
import groceryProject.core.utilities.result.DataResult;
import groceryProject.core.utilities.result.Result;
import groceryProject.core.utilities.result.SuccessDataResult;
import groceryProject.core.utilities.result.SuccessResult;
import groceryProject.dataAccess.abstracts.ProductRepository;
import groceryProject.entity.concretes.Category;
import groceryProject.entity.concretes.Product;
import groceryProject.webApi.requests.product.CreateProductRequest;
import groceryProject.webApi.requests.product.DeleteProductRequest;
import groceryProject.webApi.requests.product.UpdateProductRequest;
import groceryProject.webApi.responses.product.GetAllProductResponses;
import groceryProject.webApi.responses.product.GetByIdProductResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class ProductManager implements ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ModelMapperService modelMapperService;

    @Override
    public Result add(CreateProductRequest createProductRequest) {

        Result rules = BusinessRules.run(isExistName(createProductRequest.getName()), isExistCategoryId(createProductRequest.getCategoryId()));

        Product addProduct = modelMapperService.getModelMapper().map(createProductRequest, Product.class);
        addProduct = updateCategory(addProduct, createProductRequest.getCategoryId());
        productRepository.save((addProduct));
        log.info("Product: {} is saved!" , createProductRequest.getName());
        return new SuccessResult("Product is added!");
    }
    @Override
    public Result update(UpdateProductRequest updateProductRequest, int id) {

        Result rules = BusinessRules.run(isExistId(id), isExistName(updateProductRequest.getName()), isExistCategoryId(updateProductRequest.getCategoryId()));

        Product inDbProduct = productRepository.findById(id).orElseThrow(() -> new BusinessException("Id not found!"));
        Product product = modelMapperService.getModelMapper().map(updateProductRequest, Product.class);
        product.setId(inDbProduct.getId());
        productRepository.save(product);
        log.info("Product: {} is updated!" , updateProductRequest.getName());
        return new SuccessResult("Product was modified on DB!");
    }
    @Override
    public Result delete(DeleteProductRequest deleteProductRequest) {

        Result rules = BusinessRules.run(isExistId(deleteProductRequest.getId()));

        //ExpiryDate rule
        removeExpiryIsPassedProduct();

        //Delete
        Product product1 = productRepository.findById(deleteProductRequest.getId()).orElseThrow(()-> new BusinessException("Id does not exist!"));
        Product product = modelMapperService.getModelMapper().map(deleteProductRequest, Product.class);
        log.info("Product name: {} is removed!" , product1.getName());
        productRepository.delete(product);

        return new SuccessResult("Product object is removed from DB!");
    }
    @Override
    public DataResult<List<GetAllProductResponses>> getAll() {
        List<Product> productList = productRepository.findAll();
        List<GetAllProductResponses> returnList = new ArrayList<>();

        for (Product product:productList){
            Product product1 = productRepository.findById(product.getId()).get();
            GetAllProductResponses addFields = modelMapperService.getModelMapper().map(product, GetAllProductResponses.class);
            addFields.setCategoryId(product1.getCategory().getId());
            returnList.add(addFields);
        }

        return new SuccessDataResult<List<GetAllProductResponses>>(returnList, "Products listed.");

    }
    @Override
    public DataResult<GetByIdProductResponse> getById(int id) {
        Product product = productRepository.findById(id).orElseThrow(()-> new BusinessException("Id does not exist!"));
        GetByIdProductResponse getByIdProductResponse = modelMapperService.getModelMapper().map(product,
                GetByIdProductResponse.class);
        getByIdProductResponse.setCategoryId(product.getCategory().getId());
        return new SuccessDataResult<>(getByIdProductResponse, "Product is brought!");
    }
    private Product updateCategory(Product product,int id)
    {
        product.setCategory(categoryService.getId(id));
        return product;
    }
    private void removeExpiryIsPassedProduct(){
        for (Product product:productRepository.findAll()){
            if (product.getExpirationDate().isBefore(LocalDate.now())){
                productRepository.delete(product);
            }
        }
    }

    private Result isExistId(int id){
        if(!productRepository.existsById(id)){
            log.error("Product id could not found!");
            throw new BusinessException("Product id was not found on DB!");
        }
        return new SuccessResult();
    }

    private Result isExistName(String name){
        if(productRepository.existsByName(name)){
            log.error("Product name {} could not saved!", name);
            throw new BusinessException("Product name can not repeat!");
        }
        return new SuccessResult();
    }

    private Result isExistCategoryId(int categoryId){
        if(categoryService.getId(categoryId) == null){
            throw new BusinessException("Category id can not be null!");
        }
        return new SuccessResult();
    }


}
