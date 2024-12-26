package com.gestionImmobiliere.Immobilier.service;

import com.gestionImmobiliere.Immobilier.generic.GenericService;
import com.gestionImmobiliere.Immobilier.model.Quitance;
import com.gestionImmobiliere.Immobilier.repository.Generic.QuitanceDAO;
import org.springframework.stereotype.Service;

@Service
public class QuitanceService extends GenericService<Quitance> {
    public QuitanceService(QuitanceDAO quitanceDAO) {
        super(quitanceDAO);
    }
}
