package com.bcc.cca.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bcc.cca.dto.request.AdminRequestDTO;
import com.bcc.cca.dto.response.AdminResponseDTO;
import com.bcc.cca.entites.Admin;
import com.bcc.cca.mapper.AdminMapper;
import com.bcc.cca.repositories.AdminRepository;

@Service
public class AdminService extends GenericServices<Admin,AdminRequestDTO,AdminResponseDTO,AdminMapper>{

    private final AdminRepository repository;

    public AdminService(AdminRepository repository, AdminMapper mapper) {
        super(mapper);
        this.repository = repository;
    }

    @Override
    protected AdminRepository getRepository() {
        return repository;
    }
}


