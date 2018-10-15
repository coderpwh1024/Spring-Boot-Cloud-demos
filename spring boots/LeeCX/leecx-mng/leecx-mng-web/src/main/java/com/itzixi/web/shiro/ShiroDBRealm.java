package com.itzixi.web.shiro;

import com.itzixi.pojo.ActiveUser;
import com.itzixi.pojo.SysPermission;
import com.itzixi.pojo.SysUser;
import com.itzixi.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * 自定义realm
 *
 * @author coderpwh
 * @Date: 2018/4/27.
 * @Description:
 */
public class ShiroDBRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    public ShiroDBRealm(CacheManager cacheManager) {
        super(cacheManager);
    }

    /**
     * 用于登录认证
     *
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        // 获取token中的用户信息
        UsernamePasswordToken usertoken = (UsernamePasswordToken) token;
        String username = usertoken.getUsername();
        String password = String.valueOf(usertoken.getPassword());


        // 数据库中获取永和路信息
        SysUser user = userService.queryUserByUsername(username);

        if (user == null) {
            return null;
        }

        String dbPassword = user.getPassword();
        String dbSalt = user.getAuthSalt();
        String userPassword = ShiroPasswordUtil.getShiroPassword(password, dbSalt, 2);

        if (!userPassword.equals(dbPassword)) {
            throw new IncorrectCredentialsException();
        }

        ActiveUser activeUser = new ActiveUser();
        activeUser.setUserId(user.getId());
        activeUser.setUsername(user.getUsername());

        AuthenticationInfo info = new SimpleAuthenticationInfo(activeUser, password, getName());
        return info;
    }

    /**
     * 用于授权鉴权
     *
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

        ActiveUser sessionUser = (ActiveUser) principals.getPrimaryPrincipal();
        String userid = sessionUser.getUserId();

        List<SysPermission> permissionList = null;

        try {
            permissionList = userService.findPermissionListByUserId(userid);
        } catch (Exception e) {
            e.printStackTrace();
        }

        List<String> percodeList = new ArrayList<String>();
        for (SysPermission p : permissionList) {
            percodeList.add(p.getPercode());
        }
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.addStringPermissions(percodeList);


        return simpleAuthorizationInfo;
    }


}
