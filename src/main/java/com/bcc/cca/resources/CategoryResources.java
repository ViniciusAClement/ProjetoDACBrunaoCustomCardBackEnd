package com.bcc.cca.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bcc.cca.dto.request.CategoryRequestDTO;
import com.bcc.cca.dto.response.CategoryResponseDTO;
import com.bcc.cca.entites.Category;
import com.bcc.cca.mapper.CategoryMapper;
import com.bcc.cca.services.CategoryService;

@RestController
@RequestMapping("/categories")
public class CategoryResources extends GenericResourceDTO<Category, Long, CategoryRequestDTO, CategoryResponseDTO> {
	@Autowired
    public CategoryResources(CategoryService service, CategoryMapper mapper) {
        super(service, mapper);
    }
}
