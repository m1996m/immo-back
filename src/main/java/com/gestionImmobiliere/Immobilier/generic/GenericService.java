package com.gestionImmobiliere.Immobilier.generic;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class GenericService<Entity> {
    private final GenericDAO<Entity> genericDAO;

    public GenericService(GenericDAO<Entity> genericDAO) {
        this.genericDAO = genericDAO;
    }

    @Transactional
    public Entity create(Entity entity){
        return this.genericDAO.save(entity);
    }

    @Transactional
    public Entity update(Entity entity, Object slug){
        Entity entity1 = genericDAO.findUniqueByAttributeName("slug", slug);

        if (entity1 == null){
            throw new IllegalArgumentException("L'entité n'existe pas");
        }

        return genericDAO.update(entity);
    }

    public Entity findUniqueWithValueAndAttribut(Object value, String attributName){
        return genericDAO.findUniqueByAttributeName(attributName, value);
    }

    @Transactional
    public String delete(String slug){
        Entity entity = genericDAO.findUniqueByAttributeName("slug", slug);

        if (entity == null){
            throw new IllegalArgumentException("L'entité n'existe pas");
        }

        genericDAO.delete(entity);

        return "Suppression réussie";
    }

    @Transactional
    public Entity findUniqueById(long id){
        return genericDAO.findById(id);
    }

    public List<Entity> findAllByAttributeNameAndValue(
            Object value,
            String attributName,
            int page,
            int size,
            String slugOrganisme
    ){
        return genericDAO.findAllByAttributeNameANDAttributeValue(attributName, value, page, size, slugOrganisme);
    }

    public List<Entity> findListOrTwoValue(
            Object value1,
            String attributName1,
            Object value2,
            String attributName2,
            int page,
            int size,
            String slugOrganisme
    ){
        return genericDAO.findByOrTwoAttributeValue(attributName1, attributName2, value1, value2, page, size, slugOrganisme);
    }

    public List<Entity> findListAndTwoValue(
            Object value1,
            String attributName1,
            Object value2,
            String attributName2,
            int page,
            int size,
            String slugOrganisme
    ){
        return genericDAO.findByAndTwoAttributeValue(attributName1, attributName2, value1, value2, page, size, slugOrganisme);
    }

    public List<Entity> findListAndThreeValue(
            String attributName1,
            String attributName2,
            String attributName3,
            Object value1,
            Object value2,
            Object value3,
            int page,
            int size,
            String slugOrganisme
    ){
        return genericDAO.findByAndThreeAttributeValue(
                attributName1,
                attributName2,
                attributName3,
                value1,
                value2,
                value3,
                page,
                size,
                slugOrganisme
        );
    }

    public List<Entity> findDynamicWithJoinTableOnTable(
            String joinTable,
            String table,
            String attributName,
            Object attributValue,
            int page,
            int size,
            String slugOrganisme
    ){
        return genericDAO.findByDynamicTableOnTable(joinTable, table, attributName, attributValue, page, size, slugOrganisme);
    }

    public List<Entity> findDynamicWithJoinOneTable(
            String joinTable,
            String attributName,
            Object attributValue,
            int page,
            int size,
            String slugOrganisme
    ){
        return genericDAO.findByDynamicOneTable(joinTable, attributName, attributValue, page, size, slugOrganisme);
    }

    public List<Entity> findByDateAttributBetweenDate(
            String attributNameDateDebut,
            String attributNameDateFin,
            Date dateDebut,
            Date dateFin,
            int page,
            int size,
            String slugOrganisme
    ){
        return genericDAO.findByDateAttributesBetweenDates(attributNameDateDebut, attributNameDateFin, dateDebut, dateFin, page, size, slugOrganisme);
    }

    public List<Entity> findAttributeNameBetwennTwoDate(
            String attributeName,
            Date dateDebut,
            Date dateFin,
            int page,
            int size,
            String slugOrganisme
    ){
        return genericDAO.findAllByAttributeNameBetweenDates(attributeName, dateDebut, dateFin, page, size, slugOrganisme);
    }

    public List<Entity> findDate(
            String attributeName,
            Date date,
            int page,
            int size,
            String slugOrganisme
    ){
        return genericDAO.findByAttributesAndDate(attributeName, date, page, size, slugOrganisme);
    }

    public List<Entity> findMontantBetWeen(
            String attributeName,
            double montantDebut,
            double montantFin,
            int page,
            int size,
            String slugOrganisme
    ){
        return genericDAO.findByMontant(attributeName, montantDebut, montantFin, page, size, slugOrganisme);
    }
}
