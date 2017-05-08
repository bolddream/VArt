<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@page import="java.security.interfaces.RSAPublicKey"%>
<%@page import="org.apache.commons.codec.binary.Base64"%>
<%@page import="org.apache.shiro.web.filter.authc.FormAuthenticationFilter"%>
<%@page import="org.springframework.context.ApplicationContext"%>
<%@page import="com.mancheng.beans.Setting"%>
<%@page import="com.mancheng.utils.SettingUtils"%>
<%@page import="com.mancheng.utils.SpringUtils"%>
<%@page import="com.mancheng.service.RSAService"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%
String base = request.getContextPath();
ApplicationContext applicationContext = SpringUtils.getApplicationContext();
Setting setting = SettingUtils.get();
if (applicationContext != null) {
%>
<shiro:authenticated>
<%
response.sendRedirect(base + "/lmsa/common/main.jhtml");
%>
</shiro:authenticated>
<%
}
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>LMSA Web</title>
    <!-- Bootstrap core CSS -->
    <link href="resources/css/bootstrap.min.css" rel="stylesheet">
    <!-- Custom styles for this template -->
    <link href="resources/css/dialog.css" rel="stylesheet">
    <link href="resources/css/login.css" rel="stylesheet">
<%
	RSAService rsaService = SpringUtils.getBean("rsaServiceImpl", RSAService.class);
	RSAPublicKey publicKey = rsaService.generateKey(request);
	String modulus = Base64.encodeBase64String(publicKey.getModulus().toByteArray());
	String exponent = Base64.encodeBase64String(publicKey.getPublicExponent().toByteArray());
	
	String message = null;
	String email=null;
	String loginFailure = (String) request.getAttribute(FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME);
	
//	System.out.print(loginFailure);
//	loginFailure ="org.apache.shiro.authc.UnknownAccountException";
	
	if (loginFailure != null) {
		if (loginFailure.equals("org.apache.shiro.authc.UnknownAccountException")) {
			message = "lmsa.web.user.not.exist";
		} else if (loginFailure.equals("org.apache.shiro.authc.IncorrectCredentialsException")) {
			message = "lmsa.web.authentication.failed";
		} else if (loginFailure.equals("org.apache.shiro.authc.AuthenticationException")) {
			message = "lmsa.web.login.failed";
		} else if(loginFailure.equals("org.apache.shiro.authc.DisabledAccountException")){
		 	message = "lmsa.web.login.disabledAccount";
		} 
	}
%>
</head>
<body onload="loadTopWindow()">
<div class="container">
    <form action="login.jsp" id="loginForm" class="form-signin" method="post">
    	<input type="hidden" id="enPassword" name="enPassword" value=""/>
    	
        <div class="logo">
            <img src="resources/images/login-logo.png" alt="logo">
        </div>
        
        <div class="form-group">
		   <input autocomplete="off" type="text" id="username" name="username" placeholder="Username" class="form-controller">
		   <label class="form-label">Username</label>
		</div>
		 
		<div class="form-group">
			<input autocomplete="off" type="password" id="password" placeholder="Password" class="form-controller">
			<label class="form-label">Password</label>
		</div>
		<div class="login-warning">
			<%if (message != null) {%>
	   		<span>! &nbsp;&nbsp;<span id="loginWarn"><%=SpringUtils.getMessage(message)%></span></span>
	    	<%}%>	
		</div>
		<div class="input-group input-group-btn subBtn">
		     <input id="subBtn" class="btn btn-block" type="submit" value="Log In">
		</div>
    </form>
    <div id="alertMsg"class="alert alert-danger hidden" role="alert" style="width: 300px;margin-top: -20%;margin-left: 65%;">..</div>
</div>
<script type="text/javascript" src="resources/js/jquery-2.1.1.min.js"></script>
<script type="text/javascript" src="resources/js/jquery.cookie.js"></script>
<script type="text/javascript" src="resources/js/dialog.min.js"></script>
<script type="text/javascript" src="resources/js/jsbn.js"></script>
<script type="text/javascript" src="resources/js/prng4.js"></script>
<script type="text/javascript" src="resources/js/rng.js"></script>
<script type="text/javascript" src="resources/js/rsa.js"></script>
<script type="text/javascript" src="resources/js/base64.js"></script>
<script type="text/javascript">
   $(function(){
   	var $loginForm =$("#loginForm");
       var $username = $("#username");
       var $password =$("#password");
       var $enPassword=$("#enPassword");
       var $alertMsg = $("#alertMsg");
       var $subBtn =$("#subBtn");
       var $loginWarn= $("#loginWarn");
       $loginForm.submit(function(){
       	
        if($username.val()==""||$username.val().length<1){
        	$loginWarn.text("username is null");
            //$alertMsg.text("username is null");
            //$username.focus();
            //$alertMsg.removeClass("hidden");
            //$alertMsg.addClass("show");
            return false;
        }else if($password.val()==""||$password.val().length<8){
        	$loginWarn.text("password length check failure");
            //$alertMsg.text("password is null");
            //$password.focus();
            //$alertMsg.removeClass("hidden");
            //$alertMsg.addClass("show");
            return false;
        }
        //else{
            //$alertMsg.removeClass("show");
            //$alertMsg.addClass("hidden");
        //}
        
        var rsaKey = new RSAKey();
		rsaKey.setPublic(b64tohex("<%=modulus%>"), b64tohex("<%=exponent%>"));
		var enPassword = hex2b64(rsaKey.encrypt($password.val()));
		
		$enPassword.val(enPassword);
		
		$.cookie("loginUserName",$username.val());
	})	

   });
   
</script>
<script type="text/javascript"> 
	function loadTopWindow(){
		if (window.top!=null && window.top.document.URL!=document.URL){
			window.top.location= document.URL;
		}
	}
</script>  
</body>
</html>
