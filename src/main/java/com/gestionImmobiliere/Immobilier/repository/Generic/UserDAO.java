package com.gestionImmobiliere.Immobilier.repository.Generic;

import com.gestionImmobiliere.Immobilier.generic.GenericDAO;
import com.gestionImmobiliere.Immobilier.model.User;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAO extends GenericDAO<User> {
    public UserDAO() {
        super(User.class);
    }
}
