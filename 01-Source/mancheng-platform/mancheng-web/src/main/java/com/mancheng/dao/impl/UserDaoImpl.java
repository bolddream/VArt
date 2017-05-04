package com.mancheng.dao.impl;

import javax.persistence.FlushModeType;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import com.mancheng.dao.UserDao;
import com.mancheng.entity.User;
import com.mancheng.framework.dao.impl.BaseDaoImpl;

@Repository("userDaoImpl")
public class UserDaoImpl extends BaseDaoImpl<User, Long> implements UserDao {

  public User findUserByUserName(String userName) {

    String jpql = "select user from User user where user.name = :userName";
    try {
      return entityManager.createQuery(jpql, User.class).setFlushMode(FlushModeType.COMMIT)
          .setParameter("userName", userName).getSingleResult();
    } catch (NoResultException nre) {
      return null;
    }
  }

  public Boolean userNameUniqueValidation(String userName) {
    
    String jpql = "select user from User user where user.name = :userName";
    User user = null;
    try {
      user =  entityManager.createQuery(jpql, User.class).setFlushMode(FlushModeType.COMMIT)
          .setParameter("userName", userName).getSingleResult();
    } catch (NoResultException nre) {
      return null;
    }
    return user==null?true:false;
  }

    @Override
    public User findUserByUserInfo(String userName, String pwd) {

        String jpql = "select user from User user where user.name = :userName "
                + "and user.pwd = :pwd and isDeleted = 0 and status = 0";

        TypedQuery<User> typedQuery = entityManager.createQuery(jpql, User.class).setFlushMode(FlushModeType.COMMIT);
        typedQuery.setParameter("userName", userName);
        typedQuery.setParameter("pwd", pwd);
        try {
            return typedQuery.getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }
    }

}
