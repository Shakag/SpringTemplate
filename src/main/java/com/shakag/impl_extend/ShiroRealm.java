package com.shakag.impl_extend;

import com.shakag.entity.SysUser;
import com.shakag.service.SysUserService;
import lombok.AllArgsConstructor;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;

import javax.annotation.Resource;

public class ShiroRealm extends AuthorizingRealm {
    @Resource
    SysUserService sysUserService;

    /**
     * 授权
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        //拿到当前登录的 subject 对象
        Subject subject = SecurityUtils.getSubject();
        SysUser currentUser = (SysUser) subject.getPrincipal();
        //给用户赋权
        info.addStringPermission(currentUser.getRole());

        return info;
    }

    /**
     * 认证
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //获取用户的输入的账号.
        String userName = (String) token.getPrincipal();
        //连接数据库查询
        SysUser user = sysUserService.getSysUserByName(userName);
        if (user == null) {
            throw new UnknownAccountException();// 没找到帐号
        }
        // 从数据库查询出来的账号名和密码,与用户输入的账号和密码对比
        // 当用户执行登录时,在方法处理上要实现subject.login(token)，然后会自动进入这个类进行认证
        // 交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配，如果觉得shiro自带的不好可以自定义实现
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user, user.getPassword(), "");
        // 当验证都通过后，把用户信息放在session里
        Session session = SecurityUtils.getSubject().getSession();
        // 用户对象
        session.setAttribute("userSession", user);
        return authenticationInfo;
    }
}
