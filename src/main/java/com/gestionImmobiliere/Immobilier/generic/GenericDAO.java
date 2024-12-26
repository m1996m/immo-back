package com.gestionImmobiliere.Immobilier.generic;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import lombok.Setter;

import java.util.Date;
import java.util.List;

/**
 *
 * @param <Entity>
 */
public class GenericDAO<Entity> {
    @Setter
    private Class<Entity> type;

    public GenericDAO(Class<Entity> type){
        this.type = type;
    }

    @PersistenceContext
    private EntityManager entityManager;

    public Entity save(Entity entity){
        entityManager.persist(entity);

        return entity;
    }

    public Entity update(Entity entity){
        return entityManager.merge(entity);
    }

    public void delete(Entity entity){
        entityManager.remove(entity);
    }

    public Entity findById(long id){
        return entityManager.find(type, id);
    }

    /**
     * Cette fonction permet de recuperer les donnée en fonction d'un attribut et de l'organisme dont
     * le nom de l' attribut et les valeur seront donnée en parametre
     * @param attributeName
     * @param attributeValue
     * @param page
     * @param size
     * @param slugOrganisme
     * @return
     */
    public List<Entity> findAllByAttributeNameANDAttributeValue(
            String attributeName,
            Object attributeValue,
            int page,
            int size,
            String slugOrganisme
    ){
        String request = "SELECT e FROM "+ type.getSimpleName() +" e WHERE e." + attributeName + "=:value AND e.slugOrganisme = :slug";

        TypedQuery<Entity> query = entityManager.createQuery(request, type)
                .setParameter("value", attributeValue)
                .setParameter("slug", slugOrganisme);

        query.setFirstResult(page * size);
        query.setMaxResults(size);

        return query.getResultList();
    }

    /**
     * Cette fonction permet de recuperer les données en fontion d'une date comprise entre deux valeurs dont
     * le nom de l'attribut et les valeur seront donnée en parametre
     * @param attributeName
     * @param startDate
     * @param endDate
     * @param page
     * @param size
     * @param slugOrganisme
     * @return
     */
    public List<Entity> findAllByAttributeNameBetweenDates(
            String attributeName,
            Date startDate,
            Date endDate,
            int page,
            int size,
            String slugOrganisme
    ) {
        // Modifier la requête pour extraire uniquement jour/mois/année
        String request = "SELECT e FROM " + type.getSimpleName() + " e " +
                "WHERE FUNCTION('DATE', e." + attributeName + ") BETWEEN FUNCTION('DATE', :startDate) AND FUNCTION('DATE', :endDate)"+
                "AND e.slugOrganisme = :slug";

        // Créer la requête avec les paramètres
        TypedQuery<Entity> query = entityManager.createQuery(request, type)
                .setParameter("startDate", startDate)
                .setParameter("endDate", endDate)
                .setParameter("slug", slugOrganisme);

        // Pagination
        query.setFirstResult(page * size);
        query.setMaxResults(size);

        return query.getResultList();
    }

    /**
     * Cette fonction permet de recuperer les données en fonction de deux dates dont
     * le nom de les attribut et les valeur seront donnée en parametre
     * @param attributeDateDebut
     * @param attributeDateFin
     * @param startDate
     * @param endDate
     * @param page
     * @param size
     * @return
     */
    public List<Entity> findByDateAttributesBetweenDates(
            String attributeDateDebut,
            String attributeDateFin,
            Date startDate,
            Date endDate,
            int page,
            int size,
            String slugOrganisme
    ) {
        // Requête pour vérifier les deux attributs date
        String request = "SELECT e FROM " + type.getSimpleName() + " e " +
                "WHERE FUNCTION('DATE', e." + attributeDateDebut + ") >= FUNCTION('DATE', :startDate) " +
                "AND FUNCTION('DATE', e." + attributeDateFin + ") <= FUNCTION('DATE', :endDate)" +
                "AND e.slugOrganisme = :slug";

        // Créer la requête avec les paramètres
        TypedQuery<Entity> query = entityManager.createQuery(request, type)
                .setParameter("startDate", startDate)
                .setParameter("endDate", endDate)
                .setParameter("slug", slugOrganisme);

        // Pagination
        query.setFirstResult(page * size);
        query.setMaxResults(size);

        return query.getResultList();
    }

    /**
     * Cette fonction recupere les données d'une date specifique dont
     * le nom de l' attribut et la valeur seront donnée en parametre
     * @param attributeDate
     * @param startDate
     * @param page
     * @param size
     * @param slugOrganisme
     * @return
     */
    public List<Entity> findByAttributesAndDate(
            String attributeDate,
            Date startDate,
            int page,
            int size,
            String slugOrganisme
    ) {
        // Requête pour vérifier les deux attributs date
        String request = "SELECT e FROM " + type.getSimpleName() + " e " +
                "WHERE FUNCTION('DATE', e." + attributeDate + ") = FUNCTION('DATE', :startDate) AND e.slugOrganisme = :slug";

        // Créer la requête avec les paramètres
        TypedQuery<Entity> query = entityManager.createQuery(request, type)
                .setParameter("startDate", startDate)
                .setParameter("slug", slugOrganisme);

        // Pagination
        query.setFirstResult(page * size);
        query.setMaxResults(size);

        return query.getResultList();
    }

    /**
     * Elle permet de recuperer les données en fonction du montant compris entre deux valeurs dont
     * le nom de l' attribut et les valeur seront donnée en parametre
     * @param attributeName
     * @param montant
     * @param montantEnd
     * @param page
     * @param size
     * @param slugOrganisme
     * @return
     */
    public List<Entity> findByMontant(
            String attributeName,
            double montant,
            double montantEnd,
            int page,
            int size,
            String slugOrganisme
    ) {
        // Requête pour vérifier les deux attributs date
        String request = "SELECT e FROM "+ type.getSimpleName() + " e " +
                "WHERE :montant>= e."+ attributeName +" AND e."+ attributeName +" <= :montantEnd AND e.slugOrganisme = :slug";

        // Créer la requête avec les paramètres
        TypedQuery<Entity> query = entityManager.createQuery(request, type)
                .setParameter("montant", montant)
                .setParameter("montantEnd", montant)
                .setParameter("slug", slugOrganisme);

        // Pagination
        query.setFirstResult(page * size);
        query.setMaxResults(size);

        return query.getResultList();
    }

    /**
     * Cette fonction permet de recuperer les données comprises entre l'une de deux valeurs dont
     * le nom de les attribut et les valeur seront donnée en parametre
     * @param attributeName1
     * @param attributeName2
     * @param value1
     * @param value2
     * @param page
     * @param size
     * @param slugOrganisme
     * @return
     */
    public List<Entity> findByOrTwoAttributeValue(
            String attributeName1,
            String attributeName2,
            Object value1,
            Object value2,
            int page,
            int size,
            String slugOrganisme
    ){
        String request = "SELECT e FROM "+ type.getSimpleName() +" e WHERE e." + attributeName1 +
                "=:value1 OR e." + attributeName2 +"=:value2 AND e.slugOrganisme = :slug";

        TypedQuery<Entity> query = entityManager.createQuery(request, type)
                .setParameter("value1", value1)
                .setParameter("value2", value2)
                .setParameter("slug", slugOrganisme);

        query.setFirstResult(page * size);
        query.setMaxResults(size);

        return query.getResultList();

    }

    /**
     * Elle de recuperer les donnée comprise entre trois valeurs dont
     * le nom de les attribut et les valeur seront donnée en parametre
     * @param attributeName1
     * @param attributeName2
     * @param attributeName3
     * @param value1
     * @param value2
     * @param value3
     * @param page
     * @param size
     * @param slugOrganisme
     * @return
     */
    public List<Entity> findByAndThreeAttributeValue(
            String attributeName1,
            String attributeName2,
            String attributeName3,
            Object value1,
            Object value2,
            Object value3,
            int page,
            int size,
            String slugOrganisme
    ){
        String request = "SELECT e FROM "+ type.getSimpleName() +" e WHERE e." + attributeName1 +
                "=:value1 AND e." + attributeName2 +"=:value2 AND e."+ attributeName3 +"=:value3 AND e.slugOrganisme = :slug";

        TypedQuery<Entity> query = entityManager.createQuery(request, type)
                .setParameter("value1", value1)
                .setParameter("value2", value2)
                .setParameter("value3", value3)
                .setParameter("slug", slugOrganisme);

        query.setFirstResult(page * size);
        query.setMaxResults(size);

        return query.getResultList();
    }

    /**
     * Elle de recuperer les donnée comprise entre deux valeurs dont
     * le nom de les attribut et les valeur seront donnée en parametre
     * @param attributeName1
     * @param attributeName2
     * @param value1
     * @param value2
     * @param page
     * @param size
     * @param slugOrganisme
     * @return
     */
    public List<Entity> findByAndTwoAttributeValue(
            String attributeName1,
            String attributeName2,
            Object value1,
            Object value2,
            int page,
            int size,
            String slugOrganisme
    ){
        String request = "SELECT e FROM "+ type.getSimpleName() +" e WHERE e." + attributeName1 +
                "=:value1 AND e." + attributeName2 +"=:value2 AND e.slugOrganisme = :slug";

        TypedQuery<Entity> query = entityManager.createQuery(request, type)
                .setParameter("value1", value1)
                .setParameter("value2", value2)
                .setParameter("slug", slugOrganisme);

        query.setFirstResult(page * size);
        query.setMaxResults(size);

        return query.getResultList();
    }

    /**
     * Elle de recuperer faire deux jointures entre trois tables dont
     * Une jointure entre deux tables d'abord et à partir de cette jointure faire une jointure
     * @param joinTable
     * @param table
     * @param attributName
     * @param attributValue
     * @param page
     * @param size
     * @param slugOrganisme
     * @return
     */
    public List<Entity> findByDynamicTableOnTable(
            String joinTable,
            String table,
            String attributName,
            Object attributValue,
            int page,
            int size,
            String slugOrganisme
    ){
        String request = "SELECT e FROM " + type.getSimpleName() + " e JOIN e." + joinTable + " j WHERE j."+ table+"." +
                attributName + "=:attributValue AND e.slugOrganisme = :slug";

        TypedQuery<Entity> query = entityManager.createQuery(request, type);
        query.setParameter("attributValue", attributValue)
                .setParameter("slug", slugOrganisme);

        query.setFirstResult(page * size);
        query.setMaxResults(size);

        return query.getResultList();
    }

    /**
     * Elle de recuperer faire une jointure entre deux tables(une table mere et celle donnée en parametre)
     * @param joinTable
     * @param attributName
     * @param attributValue
     * @param page
     * @param size
     * @param slugOrganisme
     * @return
     */
    public List<Entity> findByDynamicOneTable(
            String joinTable,
            String attributName,
            Object attributValue,
            int page,
            int size,
            String slugOrganisme
    ){
        String request = "SELECT e FROM " + type.getSimpleName() + " e JOIN e." + joinTable +
                " j WHERE j." + attributName + "=:attributValue AND e.slugOrganisme = :slug";

        TypedQuery<Entity> query = entityManager.createQuery(request, type);
        query.setParameter("attributValue", attributValue)
                .setParameter("slug", slugOrganisme);

        query.setFirstResult(page * size);

        query.setMaxResults(size);

        return query.getResultList();
    }

    /**
     * Elle de recuperer de recuperer une donnée à partir d'ne valeur dont
     * le nom de l'attribut et de la valeur seront donnée en parametre
     * @param attributeName
     * @param value
     * @return
     */
    public Entity findUniqueByAttributeName(
            String attributeName,
            Object value
    ) {
        String jpql = "SELECT e FROM " + type.getSimpleName() + " e WHERE e." +
                attributeName + " = :value";

        Entity entity = entityManager.createQuery(jpql, type)
                .setParameter("value", value)
                .getSingleResult();

        if(entity == null){
            return null;
        }
        return entity;
    }
}
