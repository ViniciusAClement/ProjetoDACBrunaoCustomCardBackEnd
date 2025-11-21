package com.bcc.cca.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bcc.cca.entites.User;
import com.bcc.cca.services.UserService;

@RestController
@RequestMapping("/users")
public class UserResources extends GenericResource<User, Long>{
	@Autowired
    public UserResources (UserService service) {
        super(service);
    }
}
