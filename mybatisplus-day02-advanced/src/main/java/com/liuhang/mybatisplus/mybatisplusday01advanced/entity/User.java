package com.liuhang.mybatisplus.mybatisplusday01advanced.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.time.LocalDateTime;

/**
 * CREATE TABLE user_advance (
 *     id BIGINT(20) PRIMARY KEY NOT NULL COMMENT '主键',
 *     name VARCHAR(30) DEFAULT NULL COMMENT '姓名',
 *     age INT(11) DEFAULT NULL COMMENT '年龄',
 *     email VARCHAR(50) DEFAULT NULL COMMENT '邮箱',
 *     manager_id BIGINT(20) DEFAULT NULL COMMENT '直属上级id',
 *     create_time DATETIME DEFAULT NULL COMMENT '创建时间',
 *     update_time DATETIME DEFAULT NULL COMMENT '修改时间',
 *     version INT(11) DEFAULT '1' COMMENT '版本',
 *     deleted INT(1) DEFAULT '0' COMMENT '逻辑删除标识(0.未删除,1.已删除)',
 *     CONSTRAINT manager_fk_advance FOREIGN KEY (manager_id)
 *         REFERENCES user_advance (id)
 * )  ENGINE=INNODB CHARSET=UTF8;
 */
@TableName("user_advance")
public class User{

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

    // fill = FieldFill.INSERT：表示该字段在插入数据库表时，自动填充当前时间
    @TableField(value = "create_time",fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    // fill = FieldFill.UPDATE：表示该字段在记录修改时自动填充当前时间
    @TableField(value = "update_time",fill = FieldFill.UPDATE)
    private LocalDateTime update_time;

    @TableField("version")
    // 表示该属性对应数据库表中的乐观锁字段，配置 OptimisticLockerInterceptor 使用
    @Version
    private Integer version;

    // select = false：表示查询操作时，该字段不进行查询
    @TableField(value = "deleted",select = false)
    /**
     * 该字段为逻辑删除字段，与配置文件中的
     *       # 逻辑删除：0表示逻辑上未删除，比如：在职的员工
     *       logic-not-delete-value: 0
     *       # 逻辑删除：1表示逻辑上已删除，比如：已经离职的员工
     *       logic-delete-value: 1
     */
    @TableLogic
    private Integer deleted;

    /**
     * 该字段不是数据库中的表字段
     */
    @TableField(exist = false)
    private String remark;


    public LocalDateTime getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(LocalDateTime update_time) {
        this.update_time = update_time;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

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
