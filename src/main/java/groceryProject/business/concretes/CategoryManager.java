package groceryProject.business.concretes;

import groceryProject.business.abstracts.CategoryService;
import groceryProject.core.utilities.business.BusinessRules;
import groceryProject.core.utilities.exceptions.BusinessException;
import groceryProject.core.utilities.modelMapper.ModelMapperService;
import groceryProject.core.utilities.result.*;
import groceryProject.dataAccess.abstracts.CategoryRepository;
import groceryProject.entity.concretes.Category;
import groceryProject.webApi.requests.category.CreateCategoryRequest;
import groceryProject.webApi.requests.category.DeleteCategoryRequest;
import groceryProject.webApi.requests.category.UpdateCategoryRequest;
import groceryProject.webApi.responses.category.GetAllCategoryResponses;
import groceryProject.webApi.responses.category.GetByIdCategoryResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class CategoryManager implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ModelMapperService modelMapper;

    @Override
    public Result add(CreateCategoryRequest createCategoryRequest) {

        Result rules = BusinessRules.run(isExistName(createCategoryRequest.getName()));

        Category addCategory = modelMapper.getModelMapper().map(createCategoryRequest, Category.class);
        categoryRepository.save(addCategory);
        log.info("Category: {} is saved!" , createCategoryRequest.getName());

        return new SuccessResult("Category is saved on DB!");

    }
    @Override
    public Result update(UpdateCategoryRequest updateCategoryRequest, int id) {

        Result rules = BusinessRules.run(isExistId(id), isExistName(updateCategoryRequest.getName()));

        Category category1 = categoryRepository.findById(id).orElseThrow(()-> new BusinessException("Id does not exist!"));
        Category category = modelMapper.getModelMapper().map(updateCategoryRequest, Category.class);
        category.setId(category1.getId());
        categoryRepository.save(category);
        log.info("Category name: {} is updated!" , updateCategoryRequest.getName());

        return new SuccessResult("Category is modified on DB!");
    }

    @Override
    public Result delete(DeleteCategoryRequest deleteCategoryRequest) {

        Result rules = BusinessRules.run(isExistId(deleteCategoryRequest.getId()));

        Category deleteCategory = modelMapper.getModelMapper().map(deleteCategoryRequest, Category.class);
        log.info("Category name: {} is removed!" , getId(deleteCategoryRequest.getId()).getName());
        categoryRepository.delete(deleteCategory);

        return new SuccessResult("Category object is removed from DB!");
    }

    @Override
    public DataResult<List<GetAllCategoryResponses>> getAll() {
        List<Category> categoryList = categoryRepository.findAll();
        List<GetAllCategoryResponses> returnList = categoryList.stream().map(cl -> modelMapper.getModelMapper().map(cl, GetAllCategoryResponses.class)).toList();
            return new SuccessDataResult<>(returnList,"Category is listed!");
        }

    @Override
    public DataResult<GetByIdCategoryResponse> getById(int id) {
        GetByIdCategoryResponse getByIdCategoryResponse = modelMapper.getModelMapper().map(categoryRepository.findById(id).orElseThrow(()-> new BusinessException("Id does not exist!")), GetByIdCategoryResponse.class);
            return new SuccessDataResult<>(getByIdCategoryResponse, "Category is found by Id!");
    }

    //Product Manager sınıfında bağımlılığı azaltmak için kullanıldı.(Amaç repositorye gitmemek. DB dokunulmazdır!)
    public Category getId(int id){
        Category category = categoryRepository.findById(id).orElseThrow(()-> new BusinessException("Category id does not exist!"));
        return category;
    }

    private Result isExistId(int id){
        if(!categoryRepository.existsById(id)){
            log.error("Category id could not found!");
            throw new BusinessException("Id was not found on DB!");
        }
        return new SuccessResult();
    }

    private Result isExistName(String name){
        if (categoryRepository.existsByName(name)){
            log.error("Category name: {} could not saved!", name);
            throw new BusinessException("Category name can not repeat!");
        }
        return new SuccessResult();
    }

}