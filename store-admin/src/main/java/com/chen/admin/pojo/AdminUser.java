package com.chen.admin.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("admin_user")
public class AdminUser  implements Serializable {

    static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    private Integer userId;
    private String userName;
    private String userAccount;
    private String userPassword;
    private String userPhone;
    private Date createTime;
    private Integer userRole;

}

