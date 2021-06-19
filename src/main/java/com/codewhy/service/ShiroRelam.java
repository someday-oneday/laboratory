package com.codewhy.service;

import com.codewhy.common.web.ServiceException;
import com.codewhy.mapper.UserMapper;
import com.codewhy.entity.Role;
import com.codewhy.entity.User;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

//shiro认证与授权
@Service
public class ShiroRelam extends AuthorizingRealm {
    @Resource
    private UserMapper userMapper;
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        User primaryPrincipal = (User)principalCollection.getPrimaryPrincipal();
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        Role role = primaryPrincipal.getSysRole();
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
        User user = userMapper.findUserByMobile(name);

        if(user.getStatus().equals(1)){
            throw new LockedAccountException();
        }
        if(StringUtils.isEmpty(user)){
            throw  new ServiceException("没有此用户");
        }
        return new SimpleAuthenticationInfo(user, authenticationToken.getCredentials(), this.getName());
    }
}
