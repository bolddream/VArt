package com.mancheng.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mancheng.beans.CommonAttributes;
import com.mancheng.beans.Message;
import com.mancheng.beans.Setting;
import com.mancheng.controller.base.BaseController;
import com.mancheng.entity.User;
import com.mancheng.json.base.BaseResponse;
import com.mancheng.service.UserService;
import com.mancheng.utils.SettingUtils;

@Controller
@RequestMapping(value="mancheng/common")
public class CommonController extends BaseController {

    @Resource(name="userServiceImpl")
    private UserService userService;

    private Setting setting = SettingUtils.get();
    
      /**
       * 打开主页面
       * 
       * @return
       */
      @RequestMapping(value = "/main", method = RequestMethod.GET)
      public String showMain(ModelMap modelMap, HttpSession session){
        User user = userService.getCurrentUser();
        session.setAttribute(CommonAttributes.USER_KEY, user);
        return "common/main";
      }
   
    /**
     * 获得服务器加密公钥
     * 
     * @return
     */
    @RequestMapping(value = "/rsa", method = RequestMethod.POST)
    public @ResponseBody BaseResponse getRsaPublicKey() {
      BaseResponse response = new BaseResponse();
      String publicKey = setting.getServerPublicKey();
      if (StringUtils.isEmpty(publicKey)) {
        response.setCode(CommonAttributes.FAIL_INAVAILABLE_PARAM);
        response.setDesc(Message.error("").getContent());
        return response;
      }
      response.setCode(CommonAttributes.SUCCESS);
      response.setDesc(publicKey);
      return response;
    }

}
