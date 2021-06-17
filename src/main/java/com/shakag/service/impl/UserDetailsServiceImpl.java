package com.shakag.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.shakag.entity.SysUser;
import com.shakag.mapper.SysUserMapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Resource
    SysUserMapper sysUserMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //1、根据用户名去数据库查询，如果不存在抛出 UsernameNotFoundException 异常
        QueryWrapper<SysUser> wrapper = new QueryWrapper<>();
        wrapper.eq("NAME",username);
        SysUser user = sysUserMapper.selectOne(wrapper);
        if(user == null){
            throw new UsernameNotFoundException("用户名不存在");
        }

        //2、返回数据库查到的用户密码，Security 内部会自动比较密码
        String password = user.getPassword();

        //3、用户权限
        List<GrantedAuthority> role = AuthorityUtils.commaSeparatedStringToAuthorityList("role");

        return new User(username, password, role);
    }
}
