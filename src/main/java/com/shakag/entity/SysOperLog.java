package com.shakag.entity;

import lombok.Data;

import java.util.Date;

/**
 * @Description: 审计日志
 **/
@Data
public class SysOperLog {

    private static final long serialVersionUID = 1L;
    
    private String id;

    /**
     * 操作模块
     */
    private String operModule;

    /**
     * 操作方法
     */
    private String operMethod;

    /**
     * 操作类型
     */
    private String operType;

    /**
     * 操作描述
     */
    private String operDesc;

    /**
     * 请求方法
     */
    private String reqMethod;

    /**
     * 请求参数
     */
    private String operParam;

    /**
     * 请求参数
     */
    private String operIp;

    /**
     * 请求uri
     */
    private String operUri;

    /**
     * 操作人
     */
    private String operUser;

    /**
     * 操作时间
     */
    private Date createTime;

}
