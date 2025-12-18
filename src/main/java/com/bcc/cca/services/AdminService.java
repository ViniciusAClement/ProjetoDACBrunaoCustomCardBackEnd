package com.bcc.cca.services;

import com.bcc.cca.Exceptions.ConflictException;
import org.springframework.stereotype.Service;

import com.bcc.cca.dto.request.AdminRequestDTO;
import com.bcc.cca.dto.response.AdminResponseDTO;
import com.bcc.cca.entites.Admin;
import com.bcc.cca.mapper.AdminMapper;
import com.bcc.cca.repositories.AdminRepository;
import org.springframework.transaction.annotation.Transactional;

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

    @Override
    @Transactional
    public AdminResponseDTO create(AdminRequestDTO dto){
        Admin admin = mapper.toEntity(dto);

        if (repository.existsByEmail(admin.getEmail())){
            throw new ConflictException("Email ja cadastrado");
        }

        if (repository.existsByCpf(admin.getCpf())){
            throw new ConflictException("CPF já cadastrado");
        }

        repository.save(admin);
        return mapper.toResponseDTO(admin);
    }
}


