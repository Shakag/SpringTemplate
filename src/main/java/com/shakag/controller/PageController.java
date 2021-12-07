package com.shakag.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.shakag.common.Result;
import com.shakag.entity.SysUser;
import com.shakag.mapper.SysUserMapper;
import com.shakag.service.IPageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@Api(value = "分页查询")
public class PageController {

    private final IPageService pageService;
    // private final SysUserMapper sysUserMapper;

    @GetMapping("/page")
    // knife4j 注解，过滤请求参数, 下面表达式表示 接口文档中就只包含指定的 current 和 size 字段
    @ApiOperationSupport(order = 1, includeParameters = {"current", "size"})
    public Result<IPage<SysUser>> page(Page<SysUser> page) {
        // Page<SysUser> sysUserPage = sysUserMapper.selectPage(page, null); 也可以通过BaseMapper直接查询
        return Result.success(pageService.query(page));
    }
}
