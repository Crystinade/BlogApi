package cryst.blogapi.service.impl;

import cryst.blogapi.entities.Category;
import cryst.blogapi.exceptions.ResourceNotFoundException;
import cryst.blogapi.payloads.CategoryDto;
import cryst.blogapi.repository.CategoryRepo;
import cryst.blogapi.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {

        Category cat = this.modelMapper.map(categoryDto, Category.class);
        Category addedCart = this.categoryRepo.save(cat);

        return this.modelMapper.map(addedCart, CategoryDto.class);
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {

        Category cat = this.categoryRepo.findById(categoryId)
                .orElseThrow(()->new ResourceNotFoundException("Category", "CategoryId", categoryId));

        cat.setCategoryTitle(categoryDto.getCategoryTitle());
        categoryDto.setCategoryDescription(categoryDto.getCategoryDescription());

        Category updatedcat = this.categoryRepo.save(cat);

        return this.modelMapper.map(updatedcat, CategoryDto.class);
    }

    @Override
    public void deleteCategory(Integer categoryId) {

        Category cat = this.categoryRepo.findById(categoryId)
                .orElseThrow(()->new ResourceNotFoundException("Category", "category Id", categoryId));
        this.categoryRepo.delete(cat);
    }

    @Override
    public CategoryDto getCategory(Integer categoryId) {
        Category cat = this.categoryRepo.findById(categoryId)
                .orElseThrow(()->new ResourceNotFoundException("Category", "category Id", categoryId));

        return this.modelMapper.map(cat, CategoryDto.class);
    }

    @Override
    public List<CategoryDto> getCategories() {
       List<Category> categories = this.categoryRepo.findAll();
       List<CategoryDto> catDtos = categories.stream().map((cat)-> this.modelMapper.map(cat, CategoryDto.class)).collect(Collectors.toList());
        return catDtos;
    }
}
