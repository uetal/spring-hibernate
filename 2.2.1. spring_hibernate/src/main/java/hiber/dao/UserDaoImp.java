package hiber.dao;


import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

   @Override
   public User getByCarSeriesAndId(int series, long id) {
      TypedQuery<User> query = sessionFactory.getCurrentSession()
              .createQuery("FROM User user WHERE user.car.series=:carSeries AND user.car.id=:carId");
      query.setParameter("carSeries",series);
      query.setParameter("carId",id);
      User user = query.getResultList().get(0);
      return user;
   }
}
