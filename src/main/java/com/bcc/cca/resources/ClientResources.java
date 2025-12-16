package com.bcc.cca.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bcc.cca.dto.request.ClientRequestDTO;
import com.bcc.cca.dto.response.ClientResponseDTO;
import com.bcc.cca.entites.Client;
import com.bcc.cca.mapper.ClientMapper;
import com.bcc.cca.services.ClientService;

@RestController
@RequestMapping("/clients")
public class ClientResources extends GenericResourceDTO<Client, Long, ClientRequestDTO, ClientResponseDTO> {
	@Autowired
    public ClientResources(ClientService service, ClientMapper mapper) {
        super(service, mapper);
    }
}
