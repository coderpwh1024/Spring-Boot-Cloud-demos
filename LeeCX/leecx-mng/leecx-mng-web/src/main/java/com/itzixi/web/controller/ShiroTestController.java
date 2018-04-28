package com.itzixi.web.controller;


import com.itzixi.common.utils.LeeJSONResult;
import com.itzixi.pojo.ActiveUser;
import com.itzixi.web.shiro.ShiroDBRealm;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;

/**
 * @author coderpwh
 * @Date: 2018/4/28.
 * @Description:
 */
@Controller
@RequestMapping("shiroTest")
public class ShiroTestController extends BaseController {

    @Autowired
    private ShiroDBRealm realm;

    @RequestMapping("/shiroPage")
    @RequiresPermissions(value = {"company:mng", "appuser:check", "company:check"}, logical = Logical.OR)
    public ModelAndView index() {
        ActiveUser user = (ActiveUser) SecurityUtils.getSubject().getPrincipal();
        user.setUsername(new Date() + " ");
        System.out.println("当前主体用户有权限进入本controller~");

        ModelAndView modelAndView = new ModelAndView("shiro/shiroPage");
        return modelAndView;
    }

    /***
     * 清除缓存
     * @return
     */
    @RequestMapping("/clearCache")
    @ResponseBody
    public LeeJSONResult clearCache() {
        return LeeJSONResult.ok();
    }

}
