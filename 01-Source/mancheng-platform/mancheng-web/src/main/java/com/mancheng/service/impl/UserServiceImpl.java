package com.mancheng.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mancheng.beans.Principal;
import com.mancheng.dao.UserDao;
import com.mancheng.entity.User;
import com.mancheng.framework.filter.Filter;
import com.mancheng.framework.ordering.Ordering;
import com.mancheng.framework.paging.Page;
import com.mancheng.framework.paging.Pageable;
import com.mancheng.framework.service.impl.BaseServiceImpl;
import com.mancheng.service.UserService;

@Service("userServiceImpl")
public class UserServiceImpl extends BaseServiceImpl<User, Long> implements UserService {

  @Resource(name = "userDaoImpl")
  private UserDao userDao;

  @Resource(name = "userDaoImpl")
  public void setBaseDao(UserDao userDao) {
    super.setBaseDao(userDao);
  }
  
  public User findUserByUserInfo(String userName,String pwd) {
    return userDao.findUserByUserInfo(userName,pwd);
  } 

  public User findUserByUserName(String userName) {
    return userDao.findUserByUserName(userName);
  }

  @Transactional(readOnly = true)
  public User getCurrentUser() {
    Subject subject = SecurityUtils.getSubject();
    if (subject != null) {
      Principal principal = (Principal) subject.getPrincipal();
      if (principal != null) {
        return userDao.find(principal.getId());
      }
    }
    return null;
  }

  public Boolean userNameUniqueValidation(String userName) {
    return userDao.userNameUniqueValidation(userName);
  }

    /**
     * check user login failure count, or disable count.
     * 
     */
    @Override
    public int doCheckFailureCount(User user) {
        
        if (user.getFailureCount() >= 10) {
            user.setStatus(1);
        } else {
            user.setFailureCount((user.getFailureCount() + 1));
        }
        update(user);
        return user.getFailureCount();
    }

    @Override
    public List<String> findAuthorities(Long principalId) {
        // TODO Auto-generated method stub
        return null;
    }

}
