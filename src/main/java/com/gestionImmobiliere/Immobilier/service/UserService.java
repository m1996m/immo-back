package com.gestionImmobiliere.Immobilier.service;

import com.gestionImmobiliere.Immobilier.generic.GenericService;
import com.gestionImmobiliere.Immobilier.model.User;
import com.gestionImmobiliere.Immobilier.repository.Generic.UserDAO;
import org.springframework.stereotype.Service;

@Service
public class UserService extends GenericService<User> {
    public UserService(UserDAO userDAO) {
        super(userDAO);
    }
}
