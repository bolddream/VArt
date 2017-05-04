package com.mancheng.beans;

import java.io.Serializable;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang.StringUtils;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * 系统设置
 * 
 */
public class Setting implements Serializable {

  private static final long serialVersionUID = -1478999889661796840L;

  /** 缓存名称 */
  public static final String CACHE_NAME = "setting";

  /** 缓存Key */
  public static final Integer CACHE_KEY = 0;

  /** 分隔符 */
  private static final String SEPARATOR = ",";

  /** 密码最大长度 */
  private Integer passwordMaxlength;

  /** 密码最小长度 */
  private Integer passwordMinlength;

  /** 登录名最大长度 */
  private Integer loginNameMaxLength;

  /** 登录名最小长度 */
  private Integer loginNameMinLength;

  /** 服务器端公钥 */
  private String serverPublicKey;

  /** 客户端公钥 */
  private String serverPrivateKey;

  /** 用户token过期时间 */
  private Integer tokenTimeOut;

  /** 用户名手机号正则表达式 */
  private String mobilePattern;

  /** 短信验证码过期时间 */
  private Integer smsCodeTimeOut;

  /** 短信服务平台地址 */
  private String cloopenUrl;

  /** 短信服务平台地址 */
  private String cloopenPort;

  /** 短信平台AccountId */
  private String cloopenSid;

  /** 短信平台Token */
  private String cloopenToken;

  /** 短信平台软件版本 */
  private String ucpaasVersion;

  /** 短信平台APPID */
  private String cloopenAppId;

  /** 默认的短信平台验证码短信模板 */
  private String defaultCloopenTemplate;

  /** 短信发下密码的注册模板ID */
  private String registerNoPasswordCloopenTemplate;

  /** 短信平台购买服务短信模板 */
  private String ucpaasServiceTemplate;

  /** 短信平台语音回拨号码 */
  private String ucpaasCallDisplay;

  /** 邮箱正则表达式 */
  private String emailPattern;

  /** 网站名称 */
  private String siteName;

  /** 发件人邮箱 */
  private String smtpFromMail;

  /** SMTP服务器地址 */
  private String smtpHost;

  /** SMTP服务器端口 */
  private Integer smtpPort;

  /** SMTP用户名 */
  private String smtpUsername;

  /** SMTP密码 */
  private String smtpPassword;

  /** 默认的模糊查询下拉菜单中返回的记录条数 */
  private Integer defaultPageSize;

  /** 极光平台注册应用后生成的appKey */
  private String appKey;

  /** 极光平台注册应用后生成的masterSecret */
  private String masterSecret;

  /**
   * 网站域名
   */
  private String siteUrl;

  /**
   * app用户查询商家时的搜索半径
   */
  private Integer searchRadius;

  /**
   * 保险类别ID
   */
  private Long insuranceId;

  /**
   * 秘钥
   */
  private String wechatKey;

  /**
   * 微信分配的公众账号ID（企业号corpid即为此appId）
   */
  private String wechatAppid;

  /**
   * 商户ID
   */
  private String wechatMchId;

  /**
   * 交易类型
   */
  private String wechatTradeType;

  /**
   * 通知地址
   */
  private String wechatNotifyUrl;

  /**
   * 微信下订单接口
   */
  private String wechatAddOrderUrl;

  /**
   * 微信Token接口
   */
  private String wechatTokenUrl;

  /**
   * 消费订单前缀
   */
  private String userOrderPrefix;

  /**
   * 充值订单前缀
   */
  private String rechargePrefix;

  /**
   * 续保缴费订单前缀
   */
  private String renewalMedicarePrefix;

  /** Flash上传路径 */
  private String flashUploadPath;

  /** 媒体上传路径 */
  private String mediaUploadPath;

  /** 文件上传路径 */
  private String fileUploadPath;

  /** 上传文件最大限制 */
  private Integer uploadMaxSize;

  /** 允许上传图片扩展名 */
  private String uploadImageExtension;

  /** 允许上传Flash扩展名 */
  private String uploadFlashExtension;

  /** 允许上传媒体扩展名 */
  private String uploadMediaExtension;

  /** 允许上传文件扩展名 */
  private String uploadFileExtension;

  /** 图片上传路径 */
  private String imageUploadPath;

  /** 图片上传路径 */
  private String profilePictureUploadPath;

  /**
   * 新闻图片上传路径
   */
  private String newsPictureUploadPath;

  /**
   * 广告图片上传路径
   */
  private String adPictureUploadPath;

  /**
   * 终端用户头像上传路径
   */
  private String endUserPictureUploadPath;

  /** 相册上传路径 */
  private String albumUploadPath;

  /**
   * 用户分享注册链接地址
   */
  private String endUserShareRegisterUrl;

  /**
   * 用户注册赠送余额在数据库中对应的key
   */
  private String regPresentAmount;

  /** 续保缴费对应的Key */
  private String renewalMedicareKey;

  /** 代办业务对应的Key */
  private String agencyBusinessKey;

  /** 保险业务对应的Key */
  private String insuranceBusinessKey;

  /** 商城业务对应的Key */
  private String marketplaceKey;

  /** 体检对应的Key */
  private String physicalExaminationKey;

  /** 服务包对应的Key */
  private String servicePackageKey;

  /**
   * 系统 --消息类型key
   */
  private String systemMessageTypeKey;

  /**
   * 动帐 --消息类型key
   */
  private String accountUpdateMessageTypeKey;

  /**
   * 充值 --消息类型key
   */
  private String rechargeMessageTypeKey;

  /**
   * 积分到账 --消息类型key
   */
  private String scoreMessageTypeKey;

  /**
   * 普通 --消息类型key
   */
  private String normalMessageTypeKey;

  /**
   * 终端用户身份证上传路径
   */
  private String idCardPictureUploadPath;

  /**
   * 接口调用医保Adapter的地址
   */
  private String medicareAdapterUrl;
  /** 积分相关参数配置key **/
  /**
   * 人民币和积分兑换比配置
   */
  private String rmbExchangeCreditKey;
  /**
   * 积分规则配置:活动分享-现金券是否可以算积分
   */
  private String creditFromCashCouponKey;
  /**
   * 积分规则配置:注册-余额是否可以算积分
   */
  private String creditFromBalanceKey;

  /**
   * 最大密码重试次数
   */
  private Integer passwordMaxRetryTimes;

  /**
   * 密码错误后锁定小时
   */
  private Integer failedLoginLockedHours;

  /**
   * 密码错误重置周期-小时
   */
  private Integer failedLoginIntervalHours;

  /**
   * 续保缴费开始时间和结束时间段
   */
  private String renewalMedicareDate;

  /**
   * 资阳老app提示更新设置：1有更新，0没有更新，以及更新地址
   */
  private String updateFlag;

  private String updateUrl;
  
  private String updateInfo;
  
  /**
   * 获取续保缴费信息接口
   */
  private String noticeAndUserInfoAdapter;
  
  /**
   * 生成续保缴费接口
   */
  private String generateRenewalMedicareAdapter;
  
  /**
   * 取消续保缴费通知单接口
   */
  private String cancelRenewalMedicareAdapter;
  
  private String markRenewalMedicareAdapter;
  
  public String getMarkRenewalMedicareAdapter() {
    return markRenewalMedicareAdapter;
  }

  public void setMarkRenewalMedicareAdapter(String markRenewalMedicareAdapter) {
    this.markRenewalMedicareAdapter = markRenewalMedicareAdapter;
  }

  public String getRegPresentAmount() {
    return regPresentAmount;
  }

  public void setRegPresentAmount(String regPresentAmount) {
    this.regPresentAmount = regPresentAmount;
  }

  public String getCloopenServiceTemplate() {
    return ucpaasServiceTemplate;
  }

  public void setUcpaasServiceTemplate(String ucpaasServiceTemplate) {
    this.ucpaasServiceTemplate = ucpaasServiceTemplate;
  }

  public String getWechatKey() {
    return wechatKey;
  }

  public void setWechatKey(String wechatKey) {
    this.wechatKey = wechatKey;
  }

  public String getWechatAppid() {
    return wechatAppid;
  }

  public void setWechatAppid(String wechatAppid) {
    this.wechatAppid = wechatAppid;
  }

  public String getWechatMchId() {
    return wechatMchId;
  }

  public void setWechatMchId(String wechatMchId) {
    this.wechatMchId = wechatMchId;
  }

  public String getWechatTradeType() {
    return wechatTradeType;
  }

  public void setWechatTradeType(String wechatTradeType) {
    this.wechatTradeType = wechatTradeType;
  }

  public String getWechatNotifyUrl() {
    return wechatNotifyUrl;
  }

  public void setWechatNotifyUrl(String wechatNotifyUrl) {
    this.wechatNotifyUrl = wechatNotifyUrl;
  }

  public String getWechatAddOrderUrl() {
    return wechatAddOrderUrl;
  }

  public void setWechatAddOrderUrl(String wechatAddOrderUrl) {
    this.wechatAddOrderUrl = wechatAddOrderUrl;
  }

  public String getWechatTokenUrl() {
    return wechatTokenUrl;
  }

  public void setWechatTokenUrl(String wechatTokenUrl) {
    this.wechatTokenUrl = wechatTokenUrl;
  }

  public Long getInsuranceId() {
    return insuranceId;
  }

  public void setInsuranceId(Long insuranceId) {
    this.insuranceId = insuranceId;
  }

  public String getUcpaasCallDisplay() {
    return ucpaasCallDisplay;
  }

  public void setUcpaasCallDisplay(String ucpaasCallDisplay) {
    this.ucpaasCallDisplay = ucpaasCallDisplay;
  }



  public Integer getSearchRadius() {
    return searchRadius;
  }

  public void setSearchRadius(Integer searchRadius) {
    this.searchRadius = searchRadius;
  }

  public Integer getPasswordMaxlength() {
    return passwordMaxlength;
  }

  public void setPasswordMaxlength(Integer passwordMaxlength) {
    this.passwordMaxlength = passwordMaxlength;
  }

  public Integer getPasswordMinlength() {
    return passwordMinlength;
  }

  public void setPasswordMinlength(Integer passwordMinlength) {
    this.passwordMinlength = passwordMinlength;
  }

  public String getServerPublicKey() {
    return serverPublicKey;
  }

  public void setServerPublicKey(String serverPublicKey) {
    this.serverPublicKey = serverPublicKey;
  }

  public String getServerPrivateKey() {
    return serverPrivateKey;
  }

  public void setServerPrivateKey(String serverPrivateKey) {
    this.serverPrivateKey = serverPrivateKey;
  }

  public Integer getTokenTimeOut() {
    return tokenTimeOut;
  }

  public void setTokenTimeOut(Integer tokenTimeOut) {
    this.tokenTimeOut = tokenTimeOut;
  }

  public String getMobilePattern() {
    return mobilePattern;
  }

  public void setMobilePattern(String mobilePattern) {
    this.mobilePattern = mobilePattern;
  }


  public Integer getSmsCodeTimeOut() {
    return smsCodeTimeOut;
  }

  public void setSmsCodeTimeOut(Integer smsCodeTimeOut) {
    this.smsCodeTimeOut = smsCodeTimeOut;
  }


  public String getUcpaasVersion() {
    return ucpaasVersion;
  }

  public void setUcpaasVersion(String ucpaasVersion) {
    this.ucpaasVersion = ucpaasVersion;
  }



  /**
   * 获取发件人邮箱
   * 
   * @return 发件人邮箱
   */
  public String getSmtpFromMail() {
    return smtpFromMail;
  }

  /**
   * 设置发件人邮箱
   * 
   * @param smtpFromMail 发件人邮箱
   */
  public void setSmtpFromMail(String smtpFromMail) {
    this.smtpFromMail = smtpFromMail;
  }

  /**
   * 获取SMTP服务器地址
   * 
   * @return SMTP服务器地址
   */
  public String getSmtpHost() {
    return smtpHost;
  }

  /**
   * 设置SMTP服务器地址
   * 
   * @param smtpHost SMTP服务器地址
   */
  public void setSmtpHost(String smtpHost) {
    this.smtpHost = smtpHost;
  }

  /**
   * 获取SMTP服务器端口
   * 
   * @return SMTP服务器端口
   */
  public Integer getSmtpPort() {
    return smtpPort;
  }

  /**
   * 设置SMTP服务器端口
   * 
   * @param smtpPort SMTP服务器端口
   */
  public void setSmtpPort(Integer smtpPort) {
    this.smtpPort = smtpPort;
  }

  /**
   * 获取SMTP用户名
   * 
   * @return SMTP用户名
   */
  public String getSmtpUsername() {
    return smtpUsername;
  }

  /**
   * 设置SMTP用户名
   * 
   * @param smtpUsername SMTP用户名
   */
  public void setSmtpUsername(String smtpUsername) {
    this.smtpUsername = smtpUsername;
  }

  /**
   * 获取SMTP密码
   * 
   * @return SMTP密码
   */
  public String getSmtpPassword() {
    return smtpPassword;
  }

  /**
   * 设置SMTP密码
   * 
   * @param smtpPassword SMTP密码
   */
  public void setSmtpPassword(String smtpPassword) {
    this.smtpPassword = smtpPassword;
  }

  public String getEmailPattern() {
    return emailPattern;
  }

  public void setEmailPattern(String emailPattern) {
    this.emailPattern = emailPattern;
  }

  public String getSiteName() {
    return siteName;
  }

  public void setSiteName(String siteName) {
    this.siteName = siteName;
  }

  public Integer getDefaultPageSize() {
    return defaultPageSize;
  }

  public void setDefaultPageSize(Integer defaultPageSize) {
    this.defaultPageSize = defaultPageSize;
  }

  public String getAppKey() {
    return appKey;
  }

  public void setAppKey(String appKey) {
    this.appKey = appKey;
  }

  public String getMasterSecret() {
    return masterSecret;
  }

  public void setMasterSecret(String masterSecret) {
    this.masterSecret = masterSecret;
  }

  public String getSiteUrl() {
    return siteUrl;
  }

  public void setSiteUrl(String siteUrl) {
    this.siteUrl = siteUrl;
  }

  public String getCloopenUrl() {
    return cloopenUrl;
  }

  public void setCloopenUrl(String cloopenUrl) {
    this.cloopenUrl = cloopenUrl;
  }

  public String getCloopenPort() {
    return cloopenPort;
  }

  public void setCloopenPort(String cloopenPort) {
    this.cloopenPort = cloopenPort;
  }

  public String getCloopenSid() {
    return cloopenSid;
  }

  public void setCloopenSid(String cloopenSid) {
    this.cloopenSid = cloopenSid;
  }

  public String getCloopenToken() {
    return cloopenToken;
  }

  public void setCloopenToken(String cloopenToken) {
    this.cloopenToken = cloopenToken;
  }

  public String getCloopenAppId() {
    return cloopenAppId;
  }

  public void setCloopenAppId(String cloopenAppId) {
    this.cloopenAppId = cloopenAppId;
  }


  public String getUcpaasServiceTemplate() {
    return ucpaasServiceTemplate;
  }

  public String getUserOrderPrefix() {
    return userOrderPrefix;
  }

  public void setUserOrderPrefix(String userOrderPrefix) {
    this.userOrderPrefix = userOrderPrefix;
  }

  public String getRechargePrefix() {
    return rechargePrefix;
  }

  public void setRechargePrefix(String rechargePrefix) {
    this.rechargePrefix = rechargePrefix;
  }

  public Integer getLoginNameMaxLength() {
    return loginNameMaxLength;
  }

  public void setLoginNameMaxLength(Integer loginNameMaxLength) {
    this.loginNameMaxLength = loginNameMaxLength;
  }

  public Integer getLoginNameMinLength() {
    return loginNameMinLength;
  }

  public void setLoginNameMinLength(Integer loginNameMinLength) {
    this.loginNameMinLength = loginNameMinLength;
  }

  public String getRenewalMedicareKey() {
    return renewalMedicareKey;
  }

  public void setRenewalMedicareKey(String renewalMedicareKey) {
    this.renewalMedicareKey = renewalMedicareKey;
  }

  public String getAgencyBusinessKey() {
    return agencyBusinessKey;
  }

  public void setAgencyBusinessKey(String agencyBusinessKey) {
    this.agencyBusinessKey = agencyBusinessKey;
  }

  public String getInsuranceBusinessKey() {
    return insuranceBusinessKey;
  }

  public void setInsuranceBusinessKey(String insuranceBusinessKey) {
    this.insuranceBusinessKey = insuranceBusinessKey;
  }

  public String getMarketplaceKey() {
    return marketplaceKey;
  }

  public void setMarketplaceKey(String marketplaceKey) {
    this.marketplaceKey = marketplaceKey;
  }

  public String getPhysicalExaminationKey() {
    return physicalExaminationKey;
  }

  public void setPhysicalExaminationKey(String physicalExaminationKey) {
    this.physicalExaminationKey = physicalExaminationKey;
  }

  public String getServicePackageKey() {
    return servicePackageKey;
  }

  public void setServicePackageKey(String servicePackageKey) {
    this.servicePackageKey = servicePackageKey;
  }

  public void setAlbumUploadPath(String albumUploadPath) {
    this.albumUploadPath = albumUploadPath;
  }

  /**
   * 获取上传文件最大限制
   * 
   * @return 上传文件最大限制
   */
  @NotNull
  @Min(0)
  public Integer getUploadMaxSize() {
    return uploadMaxSize;
  }

  /**
   * 设置上传文件最大限制
   * 
   * @param uploadMaxSize 上传文件最大限制
   */
  public void setUploadMaxSize(Integer uploadMaxSize) {
    this.uploadMaxSize = uploadMaxSize;
  }

  /**
   * 获取允许上传图片扩展名
   * 
   * @return 允许上传图片扩展名
   */
  @Length(max = 200)
  public String getUploadImageExtension() {
    return uploadImageExtension;
  }

  /**
   * 设置允许上传图片扩展名
   * 
   * @param uploadImageExtension 允许上传图片扩展名
   */
  public void setUploadImageExtension(String uploadImageExtension) {
    if (uploadImageExtension != null) {
      uploadImageExtension =
          uploadImageExtension.replaceAll("[,\\s]*,[,\\s]*", ",").replaceAll("^,|,$", "")
              .toLowerCase();
    }
    this.uploadImageExtension = uploadImageExtension;
  }

  /**
   * 获取允许上传Flash扩展名
   * 
   * @return 允许上传Flash扩展名
   */
  @Length(max = 200)
  public String getUploadFlashExtension() {
    return uploadFlashExtension;
  }

  /**
   * 设置允许上传Flash扩展名
   * 
   * @param uploadFlashExtension 允许上传Flash扩展名
   */
  public void setUploadFlashExtension(String uploadFlashExtension) {
    if (uploadFlashExtension != null) {
      uploadFlashExtension =
          uploadFlashExtension.replaceAll("[,\\s]*,[,\\s]*", ",").replaceAll("^,|,$", "")
              .toLowerCase();
    }
    this.uploadFlashExtension = uploadFlashExtension;
  }

  /**
   * 获取允许上传媒体扩展名
   * 
   * @return 允许上传媒体扩展名
   */
  @Length(max = 200)
  public String getUploadMediaExtension() {
    return uploadMediaExtension;
  }

  /**
   * 设置允许上传媒体扩展名
   * 
   * @param uploadMediaExtension 允许上传媒体扩展名
   */
  public void setUploadMediaExtension(String uploadMediaExtension) {
    if (uploadMediaExtension != null) {
      uploadMediaExtension =
          uploadMediaExtension.replaceAll("[,\\s]*,[,\\s]*", ",").replaceAll("^,|,$", "")
              .toLowerCase();
    }
    this.uploadMediaExtension = uploadMediaExtension;
  }

  /**
   * 获取允许上传文件扩展名
   * 
   * @return 允许上传文件扩展名
   */
  @Length(max = 200)
  public String getUploadFileExtension() {
    return uploadFileExtension;
  }

  /**
   * 设置允许上传文件扩展名
   * 
   * @param uploadFileExtension 允许上传文件扩展名
   */
  public void setUploadFileExtension(String uploadFileExtension) {
    if (uploadFileExtension != null) {
      uploadFileExtension =
          uploadFileExtension.replaceAll("[,\\s]*,[,\\s]*", ",").replaceAll("^,|,$", "")
              .toLowerCase();
    }
    this.uploadFileExtension = uploadFileExtension;
  }

  /**
   * 获取图片上传路径
   * 
   * @return 图片上传路径
   */
  @NotEmpty
  @Length(max = 200)
  public String getImageUploadPath() {
    return imageUploadPath;
  }

  /**
   * 获取通知图片上传路径
   * 
   * @return
   */
  @NotEmpty
  @Length(max = 200)
  public String getNewsPictureUploadPath() {
    return newsPictureUploadPath;
  }

  public void setNewsPictureUploadPath(String newsPictureUploadPath) {
    this.newsPictureUploadPath = newsPictureUploadPath;
  }

  @NotEmpty
  @Length(max = 200)
  public String getAdPictureUploadPath() {
    return adPictureUploadPath;
  }

  public void setAdPictureUploadPath(String adPictureUploadPath) {
    this.adPictureUploadPath = adPictureUploadPath;
  }

  @NotEmpty
  @Length(max = 200)
  public String getEndUserPictureUploadPath() {
    return endUserPictureUploadPath;
  }

  public void setEndUserPictureUploadPath(String endUserPictureUploadPath) {
    this.endUserPictureUploadPath = endUserPictureUploadPath;
  }

  /**
   * 设置图片上传路径
   * 
   * @param imageUploadPath 图片上传路径
   */
  public void setImageUploadPath(String imageUploadPath) {
    if (imageUploadPath != null) {
      if (!imageUploadPath.startsWith("/")) {
        imageUploadPath = "/" + imageUploadPath;
      }
      if (!imageUploadPath.endsWith("/")) {
        imageUploadPath += "/";
      }
    }
    this.imageUploadPath = imageUploadPath;
  }

  /**
   * 获取基础资料照路径
   * 
   * @return
   */
  @NotEmpty
  @Length(max = 200)
  public String getProfilePictureUploadPath() {
    return profilePictureUploadPath;
  }

  public void setProfilePictureUploadPath(String profilePictureUploadPath) {
    if (profilePictureUploadPath != null) {
      if (!profilePictureUploadPath.startsWith("/")) {
        profilePictureUploadPath = "/" + profilePictureUploadPath;
      }
      if (!profilePictureUploadPath.endsWith("/")) {
        profilePictureUploadPath += "/";
      }
    }
    this.profilePictureUploadPath = profilePictureUploadPath;
  }

  /**
   * 获取Flash上传路径
   * 
   * @return Flash上传路径
   */
  @NotEmpty
  @Length(max = 200)
  public String getFlashUploadPath() {
    return flashUploadPath;
  }

  /**
   * 设置Flash上传路径
   * 
   * @param flashUploadPath Flash上传路径
   */
  public void setFlashUploadPath(String flashUploadPath) {
    if (flashUploadPath != null) {
      if (!flashUploadPath.startsWith("/")) {
        flashUploadPath = "/" + flashUploadPath;
      }
      if (!flashUploadPath.endsWith("/")) {
        flashUploadPath += "/";
      }
    }
    this.flashUploadPath = flashUploadPath;
  }

  /**
   * 获取媒体上传路径
   * 
   * @return 媒体上传路径
   */
  @NotEmpty
  @Length(max = 200)
  public String getMediaUploadPath() {
    return mediaUploadPath;
  }

  /**
   * 设置媒体上传路径
   * 
   * @param mediaUploadPath 媒体上传路径
   */
  public void setMediaUploadPath(String mediaUploadPath) {
    if (mediaUploadPath != null) {
      if (!mediaUploadPath.startsWith("/")) {
        mediaUploadPath = "/" + mediaUploadPath;
      }
      if (!mediaUploadPath.endsWith("/")) {
        mediaUploadPath += "/";
      }
    }
    this.mediaUploadPath = mediaUploadPath;
  }

  /**
   * 获取文件上传路径
   * 
   * @return 文件上传路径
   */
  @NotEmpty
  @Length(max = 200)
  public String getFileUploadPath() {
    return fileUploadPath;
  }

  /**
   * 设置文件上传路径
   * 
   * @param fileUploadPath 文件上传路径
   */
  public void setFileUploadPath(String fileUploadPath) {
    if (fileUploadPath != null) {
      if (!fileUploadPath.startsWith("/")) {
        fileUploadPath = "/" + fileUploadPath;
      }
      if (!fileUploadPath.endsWith("/")) {
        fileUploadPath += "/";
      }
    }
    this.fileUploadPath = fileUploadPath;
  }

  /**
   * 获取允许上传图片扩展名
   * 
   * @return 允许上传图片扩展名
   */
  public String[] getUploadImageExtensions() {
    return StringUtils.split(uploadImageExtension, SEPARATOR);
  }

  /**
   * 获取允许上传Flash扩展名
   * 
   * @return 允许上传Flash扩展名
   */
  public String[] getUploadFlashExtensions() {
    return StringUtils.split(uploadFlashExtension, SEPARATOR);
  }

  /**
   * 获取允许上传媒体扩展名
   * 
   * @return 允许上传媒体扩展名
   */
  public String[] getUploadMediaExtensions() {
    return StringUtils.split(uploadMediaExtension, SEPARATOR);
  }

  /**
   * 获取允许上传文件扩展名
   * 
   * @return 允许上传文件扩展名
   */
  public String[] getUploadFileExtensions() {
    return StringUtils.split(uploadFileExtension, SEPARATOR);
  }

  /**
   * 获取相册上传路径
   * 
   * @return
   */
  @NotEmpty
  @Length(max = 200)
  public String getAlbumUploadPath() {
    return albumUploadPath;
  }

  /**
   * 用户注册分享链接地址
   * 
   * @return
   */
  public String getEndUserShareRegisterUrl() {
    return endUserShareRegisterUrl;
  }

  public void setEndUserShareRegisterUrl(String endUserShareRegisterUrl) {
    this.endUserShareRegisterUrl = endUserShareRegisterUrl;
  }

  /**
   * 默认的短信验证码模板ID
   * 
   * @return
   */
  public String getDefaultCloopenTemplate() {
    return defaultCloopenTemplate;
  }

  public void setDefaultCloopenTemplate(String defaultCloopenTemplate) {
    this.defaultCloopenTemplate = defaultCloopenTemplate;
  }

  /**
   * 短信下发密码注册时的模板ID
   * 
   * @return
   */
  public String getRegisterNoPasswordCloopenTemplate() {
    return registerNoPasswordCloopenTemplate;
  }

  public void setRegisterNoPasswordCloopenTemplate(String registerNoPasswordCloopenTemplate) {
    this.registerNoPasswordCloopenTemplate = registerNoPasswordCloopenTemplate;
  }

  public String getSystemMessageTypeKey() {
    return systemMessageTypeKey;
  }

  public void setSystemMessageTypeKey(String systemMessageTypeKey) {
    this.systemMessageTypeKey = systemMessageTypeKey;
  }

  public String getAccountUpdateMessageTypeKey() {
    return accountUpdateMessageTypeKey;
  }

  public void setAccountUpdateMessageTypeKey(String accountUpdateMessageTypeKey) {
    this.accountUpdateMessageTypeKey = accountUpdateMessageTypeKey;
  }

  public String getRechargeMessageTypeKey() {
    return rechargeMessageTypeKey;
  }

  public void setRechargeMessageTypeKey(String rechargeMessageTypeKey) {
    this.rechargeMessageTypeKey = rechargeMessageTypeKey;
  }

  public String getScoreMessageTypeKey() {
    return scoreMessageTypeKey;
  }

  public void setScoreMessageTypeKey(String scoreMessageTypeKey) {
    this.scoreMessageTypeKey = scoreMessageTypeKey;
  }

  public String getNormalMessageTypeKey() {
    return normalMessageTypeKey;
  }

  public void setNormalMessageTypeKey(String normalMessageTypeKey) {
    this.normalMessageTypeKey = normalMessageTypeKey;
  }

  @NotEmpty
  @Length(max = 200)
  public String getIdCardPictureUploadPath() {
    return idCardPictureUploadPath;
  }

  public void setIdCardPictureUploadPath(String idCardPictureUploadPath) {
    this.idCardPictureUploadPath = idCardPictureUploadPath;
  }

  public String getMedicareAdapterUrl() {
    return medicareAdapterUrl;
  }

  public void setMedicareAdapterUrl(String medicareAdapterUrl) {
    this.medicareAdapterUrl = medicareAdapterUrl;
  }

  public String getRmbExchangeCreditKey() {
    return rmbExchangeCreditKey;
  }

  public void setRmbExchangeCreditKey(String rmbExchangeCreditKey) {
    this.rmbExchangeCreditKey = rmbExchangeCreditKey;
  }

  public String getCreditFromCashCouponKey() {
    return creditFromCashCouponKey;
  }

  public void setCreditFromCashCouponKey(String creditFromCashCouponKey) {
    this.creditFromCashCouponKey = creditFromCashCouponKey;
  }

  public String getCreditFromBalanceKey() {
    return creditFromBalanceKey;
  }

  public void setCreditFromBalanceKey(String creditFromBalanceKey) {
    this.creditFromBalanceKey = creditFromBalanceKey;
  }

  public String getRenewalMedicarePrefix() {
    return renewalMedicarePrefix;
  }

  public void setRenewalMedicarePrefix(String renewalMedicarePrefix) {
    this.renewalMedicarePrefix = renewalMedicarePrefix;
  }

  public Integer getPasswordMaxRetryTimes() {
    return passwordMaxRetryTimes;
  }

  public void setPasswordMaxRetryTimes(Integer passwordMaxRetryTimes) {
    this.passwordMaxRetryTimes = passwordMaxRetryTimes;
  }

  public Integer getFailedLoginLockedHours() {
    return failedLoginLockedHours;
  }

  public void setFailedLoginLockedHours(Integer failedLoginLockedHours) {
    this.failedLoginLockedHours = failedLoginLockedHours;
  }

  public Integer getFailedLoginIntervalHours() {
    return failedLoginIntervalHours;
  }

  public void setFailedLoginIntervalHours(Integer failedLoginIntervalHours) {
    this.failedLoginIntervalHours = failedLoginIntervalHours;
  }

  public String getRenewalMedicareDate() {
    return renewalMedicareDate;
  }

  public void setRenewalMedicareDate(String renewalMedicareDate) {
    this.renewalMedicareDate = renewalMedicareDate;
  }

  public String getUpdateFlag() {
    return updateFlag;
  }

  public void setUpdateFlag(String updateFlag) {
    this.updateFlag = updateFlag;
  }

  public String getUpdateUrl() {
    return updateUrl;
  }

  public void setUpdateUrl(String updateUrl) {
    this.updateUrl = updateUrl;
  }

  public String getUpdateInfo() {
    return updateInfo;
  }

  public void setUpdateInfo(String updateInfo) {
    this.updateInfo = updateInfo;
  }

  public String getNoticeAndUserInfoAdapter() {
    return noticeAndUserInfoAdapter;
  }

  public void setNoticeAndUserInfoAdapter(String noticeAndUserInfoAdapter) {
    this.noticeAndUserInfoAdapter = noticeAndUserInfoAdapter;
  }

  public String getGenerateRenewalMedicareAdapter() {
    return generateRenewalMedicareAdapter;
  }

  public void setGenerateRenewalMedicareAdapter(String generateRenewalMedicareAdapter) {
    this.generateRenewalMedicareAdapter = generateRenewalMedicareAdapter;
  }

  public String getCancelRenewalMedicareAdapter() {
    return cancelRenewalMedicareAdapter;
  }

  public void setCancelRenewalMedicareAdapter(String cancelRenewalMedicareAdapter) {
    this.cancelRenewalMedicareAdapter = cancelRenewalMedicareAdapter;
  }

}
