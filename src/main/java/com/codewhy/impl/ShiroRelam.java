package com.codewhy.impl;

import com.codewhy.common.web.ServiceException;
import com.codewhy.dao.Sys_UserDao;
import com.codewhy.pojo.Sys_Role;
import com.codewhy.pojo.Sys_User;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.logging.Logger;

/**
 * @program: laboratory
 * @description: shiro认证与授权
 * @author: CodeWhy
 * @create: 2021-06-01 23:27
 **/
@Service
public class ShiroRelam extends AuthorizingRealm {
    @Resource
    private Sys_UserDao userDao;
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        Sys_User primaryPrincipal = (Sys_User)principalCollection.getPrimaryPrincipal();
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        Sys_Role role = primaryPrincipal.getSysRole();
        if(role.getRoleName() != "" && role.getRoleName() != null){
            authorizationInfo.addRole(role.getRoleName());
        }
        return authorizationInfo;

    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        if (StringUtils.isEmpty(authenticationToken.getPrincipal())) {
            return null;
        }
        String name = authenticationToken.getPrincipal().toString();
        Sys_User user = userDao.findUserByMobile(name);

        if(user.getStatus().equals(1)){
            throw new LockedAccountException();
        }
        if(StringUtils.isEmpty(user)){
            throw  new ServiceException("没有此用户");
        }

        //return new SimpleAuthenticationInfo(user,user.getPassword(),"");
        return new SimpleAuthenticationInfo(user, authenticationToken.getCredentials(), this.getName());
    }
}
