package web.dao;

import org.springframework.stereotype.Repository;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public void updateUserById(long id, User user) {
        User updUser = getUserById(id);
        updUser.setName(user.getName());
        updUser.setSurname(user.getSurname());
        updUser.setAge(user.getAge());
        entityManager.merge(updUser);
    }

    @Override
    @Transactional
    public void saveUser(User user) {
        entityManager.persist(user);
        System.out.println("User с именем – " + user.getName() + " добавлен в базу данных");
    }

    @Override
    public User getUserById(long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    @Transactional
    public void removeUserById(long id) {
        User removedUser = getUserById(id);
        entityManager.remove(removedUser);
    }

    @Override
    public List<User> getAllUsers() {
        TypedQuery<User> query = entityManager.createQuery("select c from User c", User.class);
        return query.getResultList();
    }

}
