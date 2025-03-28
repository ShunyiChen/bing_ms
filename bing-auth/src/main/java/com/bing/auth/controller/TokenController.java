package com.bing.auth.controller;

import com.bing.auth.form.LoginBody;
import com.bing.auth.service.SysLoginService;
import com.bing.common.core.domain.R;
import com.bing.common.core.utils.AESUtils;
import com.bing.common.core.utils.JwtUtils;
import com.bing.common.core.utils.StringUtils;
import com.bing.common.security.auth.AuthUtil;
import com.bing.common.security.service.TokenService;
import com.bing.common.security.utils.SecurityUtils;
import com.bing.system.api.model.LoginUser;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


/**
 * token 控制
 * 
 * @author ruoyi
 */
@RestController
public class TokenController
{

    @Autowired
    private TokenService tokenService;

    @Autowired
    private SysLoginService sysLoginService;

    @PostMapping("login")
    public R<?> login(@RequestBody LoginBody form)
    {
        try {
            form.setUsername(AESUtils.complexAESDecrypt(form.getUsername()));
            form.setPassword(AESUtils.complexAESDecrypt(form.getPassword()));
            // 用户登录
            LoginUser userInfo = sysLoginService.login(form.getUsername(), form.getPassword());
            // 获取登录token
            return R.ok(tokenService.createToken(userInfo));
        } catch (Exception e) {
            return R.fail(e.getMessage());
        }
    }

    @PostMapping("logout")
    public R<?> logout(HttpServletRequest request)
    {
        String token = SecurityUtils.getToken(request);
        if (StringUtils.isNotEmpty(token))
        {
            String username = JwtUtils.getUserName(token);
            // 删除用户缓存记录
            AuthUtil.logoutByToken(token);
            // 记录用户退出日志
            sysLoginService.logout(username);
        }
        return R.ok();
    }

    @PostMapping("refresh")
    public R<?> refresh(HttpServletRequest request)
    {
        LoginUser loginUser = tokenService.getLoginUser(request);
        if (StringUtils.isNotNull(loginUser))
        {
            // 刷新令牌有效期
            tokenService.refreshToken(loginUser);
            return R.ok();
        }
        return R.ok();
    }

//    @PostMapping("register")
//    public R<?> register(@RequestBody RegisterBody registerBody)
//    {
//        // 用户注册
//        sysLoginService.register(registerBody.getUsername(), registerBody.getPassword());
//        return R.ok();
//    }
}
