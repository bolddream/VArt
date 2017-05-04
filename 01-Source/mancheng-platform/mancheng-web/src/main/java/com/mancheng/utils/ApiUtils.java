package com.mancheng.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;

import com.google.gson.Gson;
import com.mancheng.beans.Setting;



/**
 * API工具类
 * 
 * @author sujinxuan
 * 
 */
public final class ApiUtils {

  public static Setting setting = SettingUtils.get();

  private static int TIME_OUT = 1000;

  /**
   * 默认按照UTF-8读取
   * 
   * @param url
   * @return
   */
  public static String post(String url) {
    return post(url, "UTF-8", "UTF-8");
  }

  /**
   * 默认按照UTF-8读取
   * 
   * @param url
   * @param params 参数：id=1&type=0
   * @return
   */
  public static String post(String url, String params) {
    return post(url, "UTF-8", "UTF-8", params);
  }

  /**
   * 默认按照UTF-8读取
   * 
   * @param url
   * @param params 参数
   * @return
   * @throws Exception
   */
  public static String post(String url, Map<String, Object> params) throws Exception {
    String query = parseMap(params, "UTF-8");
    System.out.println("编码后的参数：" + query);
    return post(url, query);
  }

  /**
   * 解析参数map
   * 
   * @param paramMap
   * @return
   * @throws UnsupportedEncodingException
   */
  private static String parseMap(Map<String, Object> paramMap, String pEnv) throws Exception {
    String target = "";
    @SuppressWarnings("rawtypes")
    Iterator ite = paramMap.keySet().iterator();
    while (ite.hasNext()) {
      String key = ite.next().toString();
      target += key + "=" + URLEncoder.encode(paramMap.get(key).toString(), pEnv) + "&";
    }
    return target.endsWith("&") ? target.substring(0, target.length() - 1) : target;
  }
  
  private static String parseMapToJsonStr(Map<String, Object> paramMap, String pEnv) throws Exception {
    /*String target = "{";
    @SuppressWarnings("rawtypes")
    Iterator ite = paramMap.keySet().iterator();
    while (ite.hasNext()) {
      String key = ite.next().toString();
//      target += "\"" + key + "\""+ ":" + "\"" +URLEncoder.encode(paramMap.get(key).toString(), pEnv) + "\",";
      target += "\"" + key + "\""+ ":" + "\"" + paramMap.get(key).toString() + "\",";
    }
    target =  target.endsWith(",") ? target.substring(0, target.length() - 1) : target;
    return target + "}";
    */
//  return JsonUtils.toJson(paramMap);
    Gson gson = new Gson();
    return gson.toJson(paramMap);
  }
  public static String postJson(String url, Map<String, Object> params) throws Exception {
    String query = parseMapToJsonStr(params, "UTF-8");
    System.out.println("编码后的参数：" + query);
    return postJson(url, query, "UTF-8");
  }
  
  public static String postJson(String url, String jsonStr, String charset) throws Exception  {
    return post(url, charset, charset, jsonStr.getBytes(charset), "application/json");
  }

  /**
   * 按照charset格式编码读取
   * 
   * @param url
   * @param p_charset 发送编码
   * @param r_charset 接收编码
   * @return
   */
  public static String post(String url, String p_charset, String r_charset) {
    return post(url, p_charset, r_charset, "");
  }

  /**
   * 按照charset格式编码读取
   * 
   * @param url
   * @param p_charset 发送编码
   * @param r_charset 接收编码
   * @param params 参数：id=1&type=0
   * @return
   */
  public static String post(String url, String p_charset, String r_charset, String params) {
    return post(url, p_charset, r_charset, params.getBytes());
  }

  /**
   * 按照charset格式编码读取
   * 
   * @param url
   * @param p_charset 发送编码
   * @param r_charset 接收编码
   * @param data
   * @return
   */
  public static String post(String url, String p_charset, String r_charset, byte[] data) {
    String target = "";
    try {
      URL add = new URL(url);
      HttpURLConnection connection = (HttpURLConnection) add.openConnection();
      connection.setDoOutput(true);// 设置长连接
      connection.setDoInput(true);
      connection.setUseCaches(false);// 设置非缓存 避免多次请求不能取到最新数据
      connection.setRequestMethod("POST");// 请求方式
      connection.setRequestProperty("Charset", p_charset);// 请求编码
      connection.setConnectTimeout(TIME_OUT);// 响应超时时长
      connection.getOutputStream().write(data);// 发送字节流的请求
      connection.getOutputStream().flush();// 清空字节流
      connection.getOutputStream().close();// 关闭请求流
      // 获取响应字节流
      InputStream stream = connection.getInputStream();
      // 将流转换成reder
      BufferedReader reader = new BufferedReader(new InputStreamReader(stream, r_charset));
      String temp = null;
      while ((temp = reader.readLine()) != null) {
        target += temp;
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return target;
  }

  /**
   * 默认按照UTF-8读取
   * 
   * @param url
   * @param params post数据
   * @param reqProperty RequestProperty包头设置参数
   * @return
   */
  public static String post(String url, String params, Map<String, String> reqsProperty) {
    return post(url, "UTF-8", params.getBytes(), reqsProperty);
  }

  /**
   * 按照charset格式编码读取
   * 
   * @param url
   * @param p_charset 发送编码
   * @param r_charset 接收编码
   * @param data post数据
   * @param reqsProperty RequestProperty包头设置参数
   * @return
   */
  public static String post(String url, String r_charset, byte[] data,
      Map<String, String> reqsProperty) {
    String target = "";
    try {
      URL add = new URL(url);
      HttpURLConnection connection = (HttpURLConnection) add.openConnection();
      connection.setDoOutput(true);// 设置长连接
      connection.setDoInput(true);
      connection.setUseCaches(false);// 设置非缓存 避免多次请求不能取到最新数据
      connection.setRequestMethod("POST");// 请求方式
      if (!reqsProperty.isEmpty()) {
        @SuppressWarnings("rawtypes")
        Iterator ite = reqsProperty.keySet().iterator();
        while (ite.hasNext()) {
          String key = ite.next() + "";
          connection.setRequestProperty(key, reqsProperty.get(key) + "");
        }
      }
      connection.setConnectTimeout(TIME_OUT);// 响应超时时长
      connection.getOutputStream().write(data);// 发送字节流的请求
      connection.getOutputStream().flush();// 清空字节流
      connection.getOutputStream().close();// 关闭请求流
      // 获取响应字节流
      InputStream stream = connection.getInputStream();
      // 将流转换成reder
      BufferedReader reader = new BufferedReader(new InputStreamReader(stream, r_charset));
      String temp = null;
      while ((temp = reader.readLine()) != null) {
        target += temp;
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return target;
  }

  /**
   * 按照charset格式编码读取
   * 
   * @param url
   * @param p_charset 发送编码
   * @param r_charset 接收编码
   * @param data get数据
   * @param reqsProperty RequestProperty包头设置参数
   * @return
   */
  public static String get(String url) {
    String target = "";
    try {
//      String getURL = url + "?rylsh=" + URLEncoder.encode("512081485485", "utf-8");
      URL getUrl = new URL(url);
      HttpURLConnection connection = (HttpURLConnection) getUrl  
              .openConnection();  
      connection.setRequestProperty("contentType", "UTF-8");
      connection.setRequestProperty("Accept-Charset", "UTF-8");
      connection.connect();  
      BufferedReader reader = new BufferedReader(new InputStreamReader(  
              connection.getInputStream(), "UTF-8"));
      String temp = null; 
      while ((temp = reader.readLine()) != null){  
        target += temp;
      }  
      reader.close();  
      // 断开连接  
      connection.disconnect();  
    } catch (Exception e) {
      e.printStackTrace();
    }
    return target;
  }

  /**
   * 生成get请求带参数url
   * 
   * @return
   * @throws UnsupportedEncodingException
   */
  public static String generateGetRequestUrl(String url, Map<String, String> params)
      throws UnsupportedEncodingException {
    if (params != null) {
      Iterator<String> it = params.keySet().iterator();
      StringBuffer sb = null;
      while (it.hasNext()) {
        String key = it.next();
         String value = URLEncoder.encode(params.get(key), "utf-8");
        if (sb == null) {
          sb = new StringBuffer();
          sb.append("?");
        } else {
          sb.append("&");
        }
        sb.append(key);
        sb.append("=");
        sb.append(value);
      }
      url += sb.toString();
    }
    return url;
  }

  /**
   * 编码utf8
   * @return
   * @throws UnsupportedEncodingException 
   */
  public static String encodeUTF8(String src) throws UnsupportedEncodingException{
    return new String(src.getBytes("ISO-8859-1"), "UTF-8");
  }

  public static String post(String url, String p_charset, String r_charset, byte[] data,String contentType) {
	    String target = "";
	    try {
	      URL add = new URL(url);
	      HttpURLConnection connection = (HttpURLConnection) add.openConnection();
	      connection.setDoOutput(true);// 设置长连接
	      connection.setDoInput(true);
	      connection.setUseCaches(false);// 设置非缓存 避免多次请求不能取到最新数据
	      connection.setRequestMethod("POST");// 请求方式
	      connection.setRequestProperty("Charset", p_charset);// 请求编码
	      if(contentType != null){
	    	  connection.setRequestProperty("Content-Type", contentType);
	      }
	      connection.setConnectTimeout(TIME_OUT);// 响应超时时长
	      connection.getOutputStream().write(data);// 发送字节流的请求
	      connection.getOutputStream().flush();// 清空字节流
	      connection.getOutputStream().close();// 关闭请求流
	      // 获取响应字节流
	      InputStream stream = connection.getInputStream();
	      // 将流转换成reder
	      BufferedReader reader = new BufferedReader(new InputStreamReader(stream, r_charset));
	      String temp = null;
	      while ((temp = reader.readLine()) != null) {
	        target += temp;
	      }
	    } catch (Exception e) {
	      e.printStackTrace();
	    }
	    return target;
	  }
  
  
}
