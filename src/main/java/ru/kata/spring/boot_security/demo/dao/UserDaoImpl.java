package ru.kata.spring.boot_security.demo.dao;

import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.models.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<User> getAllUsers() {
        return em.createQuery("FROM User", User.class).getResultList();
    }

    @Override
    public void delete(long id) {
        em.createQuery("delete from User user where user.id = ?1")
                .setParameter(1, id)
                .executeUpdate();
    }

    @Override
    public User update(User user) {
        return em.merge(user);
    }

    @Override
    public void saveUser(User user) {
        em.persist(user);
    }

    @Override
    public User getUserById(long id) {
        return em.find(User.class, id);
    }

    @Override
    public User getByUserName(String username) {
        return em.createQuery("FROM User u WHERE u.username = :username", User.class)
                .setParameter("username", username).getSingleResult();
    }
}
