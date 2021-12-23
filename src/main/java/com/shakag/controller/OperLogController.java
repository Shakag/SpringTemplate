package com.shakag.controller;


import com.shakag.annotation.OperLog;
import com.shakag.common.Result;
import com.shakag.common.validate.GroupAdd;
import com.shakag.common.validate.ValidList;
import com.shakag.entity.SysUser;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Shakag
 * @since 2021-12-23
 */
@Controller
public class OperLogController {

    /**
     * ValidList<SysUser>:
     *  ValidList为自定义类，@Valid只能校验JavaBean，而List<E>不是JavaBean所以校验会失败
     *  既然List不是JavaBean，那我们就把它封装成JavaBean
     *  ValidList<E>就是封装list后的JavaBean
     */
    @PostMapping("/log")
    @OperLog(operModule = "用户管理",operType = "添加",operDesc = "添加角色")
    public Result<Object> add(SysUser user){
        System.out.println(user);
        return Result.success();
    }
}

