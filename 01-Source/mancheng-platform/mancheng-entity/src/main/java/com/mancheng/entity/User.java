package com.mancheng.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.mancheng.entity.base.BaseEntity;

@Entity
@Table(name="mancheng_user")
@SequenceGenerator(name="sequenceGenerator", sequenceName="mancheng_user_sequence")
public class User extends BaseEntity{
    /**
     *  上次登录时间
     */
    private Date lastLoginTime;
    /**
     *  用户名
     */
    private String name;
    /**
     *  密码
     */
    private String pwd;
    /**
     *  密码错误次数
     */
    private int failureCount;
    /**
     *  状态
     */
    private int status;
    /**
     *  备注
     */
    private String remark;
    @Column(nullable = false)
    public Date getLastLoginTime() {
        return lastLoginTime;
    }
    @Column(nullable = false,length = 30)
    public String getName() {
        return name;
    }
    @Column(nullable = false,length = 45)
    public String getPwd() {
        return pwd;
    }
    @Column(nullable = false,length = 1)
    public int getStatus() {
        return status;
    }
    @Column(nullable = false,length = 255)
    public String getRemark() {
        return remark;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
    public int getFailureCount() {
        return failureCount;
    }
    public void setFailureCount(int failureCount) {
        this.failureCount = failureCount;
    }
}
