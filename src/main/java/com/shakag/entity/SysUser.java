package com.shakag.entity;

import java.time.LocalDateTime;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.shakag.common.validate.GroupAdd;
import com.shakag.common.validate.GroupUpdate;
import io.swagger.annotations.ApiModelProperty;
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

    private Long id;

    @Size(max = 8,message = "名称长度不能超过8", groups = {GroupAdd.class, GroupUpdate.class})
    @NotBlank(message = "名称不能为空", groups = {GroupAdd.class, GroupUpdate.class})
    @ApiModelProperty("姓名") //swagger2 字段信息
    private String username;

    //序列化属性为另外一个名称，如把password属性序列化为pwd
    @JsonProperty("pwd")
    private String password;

    @ApiModelProperty("角色权限")
    private String role;

    private String avatar;

    private String email;

    private String city;

    private LocalDateTime created;

    private LocalDateTime updated;

    private LocalDateTime lastLogin;

    private Integer status;


}
