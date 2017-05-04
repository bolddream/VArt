package com.mancheng.service;

import java.util.List;

import com.mancheng.entity.User;
import com.mancheng.framework.service.BaseService;

public interface UserService extends BaseService<User, Long>{

    /**
     * find user with user name
     * 
     * @param userName
     * @return user object
     */
    public User findUserByUserName(String userName);

    /**
     * 
     * @param principalId
     * @return
     */
    public List<String> findAuthorities(Long principalId);

    /**
     * get current user
     * 
     * @return
     */
    public User getCurrentUser();

    /**
     * user name unique validation
     * 
     * @return true means the user name is not exist, otherwise false will be returned.
     */
    public Boolean userNameUniqueValidation(String userName);
      
    public User findUserByUserInfo(String userName,String pwd);

    /**
     * user login failure count
     * 
     * @return current count.
     */
    public int doCheckFailureCount(User user);
}
