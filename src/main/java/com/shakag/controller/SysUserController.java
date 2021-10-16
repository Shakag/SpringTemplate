package com.shakag.controller;


import com.baomidou.mybatisplus.extension.api.R;
import com.shakag.common.Result;
import com.shakag.common.validate.GroupAdd;
import com.shakag.common.validate.ValidList;
import com.shakag.entity.SysUser;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Shakag
 * @since 2021-06-10
 */
@Controller
@RequestMapping("/sysUser")
public class SysUserController {

    /**
     * ValidList<SysUser>:
     *  ValidList为自定义类，@Valid只能校验JavaBean，而List<E>不是JavaBean所以校验会失败
     *  既然List不是JavaBean，那我们就把它封装成JavaBean
     *  ValidList<E>就是封装list后的JavaBean
     */
    @PostMapping("/add")
    public Result add(@Validated(GroupAdd.class) @RequestBody ValidList<SysUser> user){
        System.out.println(user);
        return Result.success();
    }
}

