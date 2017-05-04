package com.mancheng.beans;

/**
 * common parameters
 * 
 */
public final class CommonAttributes {

  /** 日期格式配比 */
  public static final String[] DATE_PATTERNS = new String[] {"yyyy", "yyyy-MM", "yyyyMM",
      "yyyy/MM", "yyyy-MM-dd", "yyyyMMdd", "yyyy/MM/dd", "yyyy-MM-dd HH:mm:ss", "yyyyMMddHHmmss",
      "yyyy/MM/dd HH:mm:ss", "yyyy-MM-dd HH:mm", "HH:mm"};

  /** common-config.xml path */
  public static final String COMMON_CONFIG_XML_PATH = "/common-config.xml";

  /** common-config.properties path */
  public static final String COMMON_CONFIG_PROPERTIES_PATH = "/common-config.properties";


  /**  DISTINCT_KEY */
  public static final String DISTINCT_KEY = "distinct";

  /** request successful */
  public static final String SUCCESS = "0000";

  /** login failed */
  public static final String FAIL_LOGIN = "0001";

  /** user not exist */
  public static final String USER_NOT_EXITS = "0003";

  /** login successful */
  public static final String LOGIN_SUCCESS = "0004";
  
  /** change password failed */
  public static final String FAIL_CHANGE_PASSWORD = "0001";
  
  /** invalid parameters */
  public static final String FAIL_INAVAILABLE_PARAM = "0007";
  
  /** fetch data failed*/
  public static final String FAIL_FETCH_DATA = "0008";
  
  /**request failed */
  public static final String ERROR = "0009";
  
  /**user already exist */
  public static final String USER_NAME_EXIST="0010";  

  /**mail address exist */
  public static final String MAIL_ADDRESS_EXIST="0011";
  
  public static final int PAGE_SIZE = 4;
  
  /**session中存储用户的Key*/
  public static final String USER_KEY = "user";
  /**
   * cannot be instantiated 
   */
  private CommonAttributes() {}

}
