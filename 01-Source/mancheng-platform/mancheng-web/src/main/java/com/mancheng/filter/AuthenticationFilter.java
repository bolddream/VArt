package com.mancheng.filter;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

import com.mancheng.beans.AuthenticationToken;
import com.mancheng.common.LogUtil;
import com.mancheng.service.RSAService;

/**
 * Filter - 权限认证
 * 
 */
public class AuthenticationFilter extends FormAuthenticationFilter {

  /** 默认"加密密码"参数名称 */
  private static final String DEFAULT_EN_PASSWORD_PARAM = "enPassword";

  /** "加密密码"参数名称 */
  private String enPasswordParam = DEFAULT_EN_PASSWORD_PARAM;

  String url = null;

  @Resource(name = "rsaServiceImpl")
  private RSAService rsaService;

  @Override
  protected org.apache.shiro.authc.AuthenticationToken createToken(ServletRequest servletRequest,
      ServletResponse servletResponse) {
    String username = getUsername(servletRequest);
    String password = getPassword(servletRequest);
    return new AuthenticationToken(username, password);
  }

  @Override
  protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse)
      throws Exception {
    HttpServletRequest request = (HttpServletRequest) servletRequest;
    HttpServletResponse response = (HttpServletResponse) servletResponse;
    String requestType = request.getHeader("X-Requested-With");
    if (requestType != null && requestType.equalsIgnoreCase("XMLHttpRequest")) {
      response.addHeader("loginStatus", "accessDenied");
      response.sendError(HttpServletResponse.SC_FORBIDDEN);
      return false;
    }
    if (isLoginRequest(request, response)) {
      if (isLoginSubmission(request, response)) {
        if (LogUtil.isDebugEnabled(AuthenticationFilter.class)) {
          LogUtil.debug(AuthenticationFilter.class, "onAccessDenied",
              "Login submission detected.  Attempting to execute login.");
        }
        return executeLogin(request, response);
      }
      if (LogUtil.isDebugEnabled(AuthenticationFilter.class)) {
        LogUtil.debug(AuthenticationFilter.class, "onAccessDenied", "Login page view.");
      }
      return true;
    }
    url = request.getServletPath();
    if (LogUtil.isDebugEnabled(AuthenticationFilter.class)) {
      LogUtil
          .debug(
              AuthenticationFilter.class,
              "onAccessDenied",
              "Attempting to access a path which requires authentication.  Forwarding to the Authentication url ["
                  + getLoginUrl() + "]");
    }
    redirectToLogin(request, response);
    return false;

  }

  @Override
  protected boolean onLoginSuccess(org.apache.shiro.authc.AuthenticationToken token,
      Subject subject, ServletRequest servletRequest, ServletResponse servletResponse)
      throws Exception {
    Session session = subject.getSession();
    Map<Object, Object> attributes = new HashMap<Object, Object>();
    Collection<Object> keys = session.getAttributeKeys();
    for (Object key : keys) {
      attributes.put(key, session.getAttribute(key));
    }
    session.stop();
    session = subject.getSession();
    for (Entry<Object, Object> entry : attributes.entrySet()) {
      /** 
       * remove history url record
       */
      if(!"shiroSavedRequest".equals(entry.getKey())){
        session.setAttribute(entry.getKey(), entry.getValue());
      }
    }
    return super.onLoginSuccess(token, subject, servletRequest, servletResponse);
  }

  @Override
  protected String getPassword(ServletRequest servletRequest) {
    HttpServletRequest request = (HttpServletRequest) servletRequest;
    String plainText = rsaService.decryptParameter(enPasswordParam, request);
    rsaService.removePrivateKey(request);
    return plainText;
  }


  /**
   * 获取"加密密码"参数名称
   * 
   * @return "加密密码"参数名称
   */
  public String getEnPasswordParam() {
    return enPasswordParam;
  }

  /**
   * 设置"加密密码"参数名称
   * 
   * @param enPasswordParam "加密密码"参数名称
   */
  public void setEnPasswordParam(String enPasswordParam) {
    this.enPasswordParam = enPasswordParam;
  }
}
