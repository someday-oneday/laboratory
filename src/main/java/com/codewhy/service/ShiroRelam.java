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

    //realm的认证方法，从数据库查询用户信息
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //从token中取出用户名
        String name = authenticationToken.getPrincipal().toString();
        //根据用户输入的username从数据库查询
        User user = userMapper.findUserByMobile(name);
        //token中无用户身份信息
        if (StringUtils.isEmpty(authenticationToken.getPrincipal())) {
            return null;
        }
        //用户禁用状态
        if(user.getStatus().equals(1)){
            throw new LockedAccountException();
        }
        //数据库中无此用户
        if(StringUtils.isEmpty(user)){
            throw  new ServiceException("没有此用户");
        }
        // 如果查询到返回认证信息SimpleAuthenticationInfo
        return new SimpleAuthenticationInfo(user, authenticationToken.getCredentials(), this.getName());
    }

    //realm的授权方法
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //从 principalCollection获取主身份信息
        //将getPrimaryPrincipal方法返回值转为真实身份类型
        //（在上边的doGetAuthenticationInfo认证通过填充到SimpleAuthenticationInfo中身份类型）
        User primaryPrincipal = (User)principalCollection.getPrimaryPrincipal();
        //根据身份信息从数据库获取到权限数据
        Role role = primaryPrincipal.getSysRole();
        //查到权限数据，返回授权信息
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        if(role.getRoleName() != "" && role.getRoleName() != null){
            //将上边查询到授权信息填充到simpleAuthorizationInfo对象中
            simpleAuthorizationInfo.addRole(role.getRoleName());
        }
        return simpleAuthorizationInfo;
    }


}
