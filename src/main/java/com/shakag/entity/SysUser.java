package com.shakag.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;

import com.shakag.common.validate.GroupAdd;
import com.shakag.common.validate.GroupUpdate;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * <p>
 * 
 * </p>
 *
 * @author Shakag
 * @since 2021-06-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SysUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @Size(max = 8,message = "名称长度不能超过8", groups = {GroupAdd.class, GroupUpdate.class})
    @NotBlank(message = "名称不能为空", groups = {GroupAdd.class, GroupUpdate.class})
    private String username;

    private String password;

    private String avatar;

    private String email;

    private String city;

    private LocalDateTime created;

    private LocalDateTime updated;

    private LocalDateTime lastLogin;

    private Integer status;


}
