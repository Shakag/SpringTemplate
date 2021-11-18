package com.shakag.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.shakag.entity.SysUser;


public interface IPageService extends IService<SysUser> {

    IPage<SysUser> query(IPage<SysUser> page);

}
