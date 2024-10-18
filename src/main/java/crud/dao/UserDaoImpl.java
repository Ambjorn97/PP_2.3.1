package crud.dao;

import crud.model.User;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public List<User> getAllUsers() {
        Session session = entityManager.unwrap(Session.class);
        TypedQuery<User> query = session.createQuery("from User", User.class);
        return query.getResultList();
    }

    @Override
    public void addUser(User user) {
        Session session = entityManager.unwrap(Session.class);
        session.saveOrUpdate(user);
    }

    @Override
    public void updateUser(User user) {
        Session session = entityManager.unwrap(Session.class);
        int id = user.getId();
        User existingUser = session.get(User.class, id);
        if (existingUser == null) {
            throw new EntityNotFoundException();
        }
        session.merge(user);
    }

    @Override
    public void deleteUser(int id) {
        Session session = entityManager.unwrap(Session.class);
        User user = session.get(User.class, id);
        if (user == null) {
            throw new EntityNotFoundException();
        }
        Query query = session.createQuery("delete from User where id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    public User getUserById(int id) {
        Session session = entityManager.unwrap(Session.class);
        return session.get(User.class, id);
    }
}
