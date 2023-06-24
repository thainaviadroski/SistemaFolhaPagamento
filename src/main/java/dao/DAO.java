package dao;

import data.ConexaoBD;
import modelo.Cargo;
import modelo.Entidade;

import jakarta.persistence.EntityManager;
import java.util.List;

public abstract class DAO<T extends Entidade> {

    private final EntityManager entityManager;
    protected Class<T> classModel;

    public DAO(Class<T> classModel) {
        this.classModel = classModel;
        entityManager = ConexaoBD.getConection();
    }

    protected EntityManager getEntityManager() {
        return entityManager;
    }

    public T findById(int id) {
        return entityManager.find(classModel, id);
    }

    public List<T> findAll() {
        return entityManager.createQuery(entityManager.getCriteriaBuilder().createQuery(classModel)).getResultList();
    }

    public boolean saveOrUpdate(Entidade entidade) {
        try {
            if (entidade == null) {
                throw new IllegalArgumentException("A entidade não pode estar nula!");
            }
            entityManager.getTransaction().begin();
            if (entidade.getId() == null) {
                entityManager.persist(entidade);
            } else {
                entityManager.merge(entidade);
            }
            entityManager.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            System.err.println("Falha ao Salvar a Entidade!\n" + ex.getMessage());
            entityManager.getTransaction().rollback();
            return false;
        }
    }

    public boolean delete(T entidade) {
        try {
            if (entidade == null) {
                throw new IllegalArgumentException("A entidade não pode estar nula!");
            }
            entityManager.getTransaction().begin();
            entityManager.remove(entidade);
            entityManager.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            System.err.println("Falha ao Remover Entidade!\n" + ex.getMessage());
            entityManager.getTransaction().rollback();
            return false;
        }
    }

    public void deleteById(int id) {
        delete(findById(id));
    }
}
