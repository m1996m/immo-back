package com.gestionImmobiliere.Immobilier.controller;

import com.gestionImmobiliere.Immobilier.dto.rendezvous.RendezvousDto;
import com.gestionImmobiliere.Immobilier.generic.GenericController;
import com.gestionImmobiliere.Immobilier.generic.GenericDtoMapper;
import com.gestionImmobiliere.Immobilier.generic.GenericService;
import com.gestionImmobiliere.Immobilier.model.Rendezvous;
import com.gestionImmobiliere.Immobilier.service.RendezvousService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/rendez/vous")
public class RendezvousController extends GenericController<Rendezvous, RendezvousDto> {
    private final RendezvousService rendezvousService;

    public RendezvousController(
            GenericService<Rendezvous> genericService,
            GenericDtoMapper<RendezvousDto, Rendezvous> genericDtoMapper,
            RendezvousService rendezvousService
    ) {
        super(genericService, genericDtoMapper);
        this.rendezvousService = rendezvousService;
    }

    /**
     *
     * @param dateJour
     * @param slugOrganisme
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/date/jour")
    public List<Rendezvous> getListRendezvousBydate(
            @RequestParam("dateJour") Date dateJour,
            @RequestParam("slugOrganisme") String slugOrganisme,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "100") int size
    ){
        return rendezvousService.findDate("dateRendezvous", dateJour, page, size, slugOrganisme);
    }

    /**
     *
     * @param idUsser
     * @param slugOrganisme
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/user")
    public List<Rendezvous> getListRendezvousByidUser(
            @RequestParam("idUser") long idUsser,
            @RequestParam("slugOrganisme") String slugOrganisme,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "100") int size
    ){
        return rendezvousService.findAllByAttributeNameAndValue(idUsser, "user.idUser", page, size,slugOrganisme);
    }
}
