package com.mall.common.bean;

import com.mall.common.utils.TokenUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;

/**
 * @Created with IntelliJ IDEA.
 * @Author: C-aranlism
 * @Date: 2020/9/21
 */
public class JWTRealm extends AuthorizingRealm {

    @Override
    public String getName() {
        return "tokenRealm";
    }

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JWTToken;
    }

    /**
     * 信息认证
     * @param authenticationToken
     * @return
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String token = String.valueOf(authenticationToken.getCredentials());
        String staffId = TokenUtils.getId(token);
        if (staffId == null) {
            throw new AuthenticationException("认证失败[01]");
        }
        return new SimpleAuthenticationInfo(token , token , getName());
    }

    /**
     * 信息授权
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String roleId = MapUtils.getString(TokenUtils.verify(principals.toString()) , "roleId");
        String userId = MapUtils.getString(TokenUtils.verify(principals.toString()) , "userId");
        return new SimpleAuthorizationInfo(){
            {
                addRole(roleId);
            }
        };
    }

    @Override
    protected void clearCachedAuthenticationInfo(PrincipalCollection principals) {
        SimplePrincipalCollection collection = new SimplePrincipalCollection(
                principals.getPrimaryPrincipal().toString() , getName()
        );
        super.clearCachedAuthenticationInfo(collection);
    }

    @Override
    protected void clearCachedAuthorizationInfo(PrincipalCollection principals) {
        super.clearCachedAuthorizationInfo(principals);
    }

}
