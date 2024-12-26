package com.gestionImmobiliere.Immobilier.service;

import com.gestionImmobiliere.Immobilier.model.Sequence_table;
import com.gestionImmobiliere.Immobilier.repository.Sequence_tableRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class Sequence_tableService {
    private final Sequence_tableRepository repository;

    public Sequence_table update(Sequence_table sequenceTable){
        sequenceTable.setNext_value(sequenceTable.getNext_value()+1);

        return repository.save(sequenceTable);
    }

    public Sequence_table find(String name){
        return repository.findByName(name);
    }
}
