package com.mancheng.controller;


import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mancheng.json.request.UserChangePasswordRequest;
import com.mancheng.utils.SpringUtils;
import com.mancheng.beans.CommonAttributes;
import com.mancheng.beans.Message;
import com.mancheng.common.LogUtil;
import com.mancheng.controller.base.BaseController;
import com.mancheng.entity.User;
import com.mancheng.service.RSAService;
import com.mancheng.json.base.BaseResponse;
import com.mancheng.json.base.ResponseOne;
import com.mancheng.json.request.UserLoginRequest;
import com.mancheng.service.UserService;
import com.mancheng.utils.KeyGenerator;
import com.mancheng.utils.RSAHelper;
import com.mancheng.utils.RSAUtils;
import com.mancheng.utils.SettingUtils;


@Controller("UserController")
@RequestMapping("mancheng/user")
public class UserController extends BaseController {


    private static final String PRIVATE_KEY_ATTRIBUTE_NAME = "privateKey";

    @Resource(name = "userServiceImpl")
    private UserService userService;

    @Resource(name = "rsaServiceImpl")
    private RSAService rsaService;
    
    
    @RequestMapping(value = "/toMain", method = RequestMethod.GET)
    public String toMain(){
        return "common/main";
    }
    /**
     * user login
     * 
     * @param userLoginReq
     * @return
     */
    @Deprecated
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public @ResponseBody ResponseOne<User> login(UserLoginRequest userLoginRequest) {

        String userName = userLoginRequest.getUserName();
        String password = userLoginRequest.getPassword();
        ResponseOne<User> response = new ResponseOne<User>();
        User user = null;

        if (!StringUtils.isEmpty(userName)) {

            if (LogUtil.isDebugEnabled(UserController.class)) {
                LogUtil.debug(UserController.class, "login", "Fetching User from database with UserName: %s", userName);
            }

            user = userService.findUserByUserName(userName);
            if (null != user) {
                String serverPrivateKey = SettingUtils.get().getServerPrivateKey();

                if (!StringUtils.isEmpty(password)) {
                    try {
                        String plainTextPassword = KeyGenerator.decrypt(password,
                                RSAHelper.getPrivateKey(serverPrivateKey));
                        /**
                         * validate the password
                         */
                        if (!DigestUtils.md5(plainTextPassword).equals(user.getPwd())) {
                            response.setCode(CommonAttributes.FAIL_LOGIN);
                            response.setDesc(Message.error("mancheng.web.login.failed").getContent());
                            return response;
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            } else {
                response.setCode(CommonAttributes.USER_NOT_EXITS);
                response.setDesc(Message.error("mancheng.web.user.not.exist").getContent());
                return response;
            }
        }
        response.setCode(CommonAttributes.LOGIN_SUCCESS);
        response.setDesc(Message.success("mancheng.web.login.success").getContent());
        response.setMsg(user);
        return response;
    }

    /**
     * disable user
     * 
     * @param id
     * @return
     */
    @RequestMapping(value = "/disableUser", method = RequestMethod.GET)
    public @ResponseBody BaseResponse disableUser(Long id) {

        BaseResponse response = new BaseResponse();
        User user = userService.find(id);

        if (user != null) {
            if (user.getStatus() == 0) {
                user.setStatus(1);
                response.setDesc(Message.success("mancheng.web.user.disabledUser.success").getContent());
            } else if (user.getStatus() == 1) {
                user.setStatus(0);
                user.setFailureCount(0);
                response.setDesc(Message.success("mancheng.web.user.enabledUser.success").getContent());
            }
            userService.update(user);
            response.setCode(CommonAttributes.SUCCESS);
            if (LogUtil.isInfoEnabled(UserController.class)) {
                LogUtil.info(UserController.class, "disable", "Admin disable user with UserName: %s", user.getName());
            }
        } else {
            response.setCode(CommonAttributes.ERROR);
            response.setDesc(Message.error("mancheng.web.user.changeStatus.failed").getContent());
        }

        return response;
    }

    /**
     * initialize user password
     * 
     * @param id
     * @param enInitPassword
     * @param request
     * @return
     */
    @Deprecated
    @RequestMapping(value = "/deprecatedInitPassword", method = RequestMethod.POST)
    public @ResponseBody BaseResponse initPassword(Long id, String enInitPassword, HttpServletRequest request) {

        BaseResponse response = new BaseResponse();
        User user = userService.find(id);

        RSAPrivateKey privateKey = (RSAPrivateKey) request.getSession().getAttribute(PRIVATE_KEY_ATTRIBUTE_NAME);

        if (privateKey != null && user != null) {
            String plaintextInitPassword = RSAUtils.decrypt(privateKey, enInitPassword);
            user.setPwd(plaintextInitPassword);
            userService.update(user);
            response.setCode(CommonAttributes.SUCCESS);
        } else {
            response.setCode(CommonAttributes.ERROR);
        }

        return response;
    }

    /**
     * logout
     */
    @RequestMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            Cookie cookie = new Cookie("pre_url", null);
            cookie.setMaxAge(0);
            cookie.setPath("/");
            response.addCookie(cookie);
            subject.logout(); // session 会销毁，在SessionListener监听session销毁，清理权限缓存
        }
        return "redirect:/";
    }
    
      /**
       * edit user password
       * 
       * @param id
       * @param model
       * @return
       */
      @RequestMapping(value = "/editPwd", method = RequestMethod.GET)
      public String editPwd(ModelMap model, HttpServletRequest request) {
        RSAService rsaService = SpringUtils.getBean("rsaServiceImpl", RSAService.class);
        RSAPublicKey publicKey = rsaService.generateKey(request);
        String modulus = Base64.encodeBase64String(publicKey.getModulus().toByteArray());
        String exponent = Base64.encodeBase64String(publicKey.getPublicExponent().toByteArray());
        model.addAttribute("modulus", modulus);
        model.addAttribute("exponent", exponent);
        return "user/change_pwd";
      }
      
      /**
       * user change password
       * 
       * @param userChangePasswordRequest
       * @return
       */
      @RequestMapping(value = "/changePassword", method = RequestMethod.POST)
      public @ResponseBody BaseResponse changePassword(
          UserChangePasswordRequest userChangePasswordRequest, HttpServletRequest request) {
        BaseResponse response = new BaseResponse();
        if (userChangePasswordRequest != null
            && !userChangePasswordRequest.getNewPassword().trim()
                .equals(userChangePasswordRequest.getConfirmPassword().trim())) {
          response.setCode(CommonAttributes.FAIL_CHANGE_PASSWORD);
          response.setDesc(Message.error("mancheng.web.user.changepassword.failed.1").getContent());      
          return response;
        }
        User user = userService.getCurrentUser();
        if (null != user) {
          // decryptParameter("newPassword", userChangePasswordRequest)
          String newPlainText = rsaService.decryptParameter("newPassword", request);
          String oldPlainText = rsaService.decryptParameter("oldPassword", request);

          if (!StringUtils.isEmpty(userChangePasswordRequest.getOldPassword())) {
            try {
              /**
               * validate the password
               */
              if (!(DigestUtils.md5Hex(oldPlainText).equals(user.getPwd()))) {
                response.setCode(CommonAttributes.FAIL_CHANGE_PASSWORD);
                response.setDesc(Message.error("mancheng.web.user.changepassword.failed.2").getContent());
              } else {
                user.setPwd(DigestUtils.md5Hex(newPlainText));
                userService.update(user);
                response.setCode(CommonAttributes.SUCCESS);
                response.setDesc("Password Changed Successfully!");
                if (LogUtil.isInfoEnabled(UserController.class)) {
                    LogUtil.info(UserController.class, "disable",
                        "User change password successfully with UserName: %s", user.getName());
                  }
              }
            } catch (Exception e) {
              e.printStackTrace();
            }
          }
        }
        return response;
      }

}
