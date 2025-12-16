package com.bcc.cca.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bcc.cca.dto.request.AdminRequestDTO;
import com.bcc.cca.dto.response.AdminResponseDTO;
import com.bcc.cca.entites.Admin;
import com.bcc.cca.mapper.AdminMapper;
import com.bcc.cca.services.AdminService;

@RestController
@RequestMapping("/admins")
public class AdminResources extends GenericResourceDTO<Admin, Long, AdminRequestDTO, AdminResponseDTO> {
	
	@Autowired
    public AdminResources(AdminService service, AdminMapper mapper) {
        super(service, mapper);
    }
}
