package com.mancheng.dao;

import com.mancheng.entity.User;
import com.mancheng.framework.dao.BaseDao;
/**
 * 平台用户
 * 
 */
public interface UserDao extends BaseDao<User, Long> {

      /**
       * find user with user name
       * 
       * @param userName
       * @return user object
       */
      public User findUserByUserName(String userName);

      /**
       * user name unique validation
       * 
       * @return
       */
      public Boolean userNameUniqueValidation(String userName);
      
      /**
       * find user by name and password
       * 
       * @return
       */
      public User findUserByUserInfo(String userName, String pwd);
      
}