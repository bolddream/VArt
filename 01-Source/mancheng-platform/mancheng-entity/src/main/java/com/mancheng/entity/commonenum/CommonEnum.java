package com.mancheng.entity.commonenum;

/**
 * 公共枚举数据
 * 
 *
 */
public class CommonEnum {

    public enum FlashType {
        APK
    };
    
    /**
     * 请求日志类型
     * @author luzhang
     *
     */
    public enum RequestLogType{
      /** 请求成功 */
      SUCCESS,
      /** 请求失败 */
      FAILED,
      /** 请求异常 */
      EXCEPTION
    }
    public enum ConnectionType{
      /** 音量键+电源开关*/
      VOLUME_POWER
    }
    /**
     * 资源文件
     * @author luzhang
     *
     */
    public enum ResourceType{
      /** APK文件*/
      APK,
      /** 图标文件*/
      ICON,
      /** ROM文件*/
      ROM,
      /** 刷机工具文件*/
      TOOL,
      /** 国家代码文件*/
      COUNTRY_CODE,
      /** JSON文件*/
      JSON
    }
    public enum BrandType{
    	/** 联想 */
    	LENOVO,
    	/** 摩托 */
    	MOTO
    }
}
