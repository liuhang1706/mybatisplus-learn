package com.liuhang.mybatisplus.mybatisplusday01basic.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.time.LocalDateTime;

/**
 * CREATE TABLE user (
 *     id BIGINT(20) PRIMARY KEY NOT NULL COMMENT '主键',
 *     name VARCHAR(30) DEFAULT NULL COMMENT '姓名',
 *     age INT(11) DEFAULT NULL COMMENT '年龄',
 *     email VARCHAR(50) DEFAULT NULL COMMENT '邮箱',
 *     manager_id BIGINT(20) DEFAULT NULL COMMENT '直属上级id',
 *     create_time DATETIME DEFAULT NULL COMMENT '创建时间',
 *     CONSTRAINT manager_fk FOREIGN KEY (manager_id)
 *         REFERENCES user (id)
 * )  ENGINE=INNODB CHARSET=UTF8;
 *
 *      @TableName("user") ：指定实体类对应的数据库表
 *
 *
 *      AR模式：通过操作实体对象操作数据库
 *           extends Model<User>
 */
@TableName("user")
public class User extends Model<User> {

    /**
     * @TableId 指定该属性对应数据库中的表主键
     */
    @TableId
    private Long id;
    /**
     * 指定该属性对应数据库中的表的字段
     */
    @TableField("name")
    private String name;
    @TableField("age")
    private Integer age;
    @TableField("email")
    private String email;
    // 直属上级ID
    @TableField("manager_id")
    private Long managerId;
    @TableField("create_time")
    private LocalDateTime createTime;

    /**
     * 该字段不是数据库中的表字段
     */
    @TableField(exist = false)
    private String remark;


    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getManagerId() {
        return managerId;
    }

    public void setManagerId(Long managerId) {
        this.managerId = managerId;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }
}
