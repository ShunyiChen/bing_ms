package com.bing.auth.controller;

import com.microsoft.aad.msal4j.*;
import com.nimbusds.openid.connect.sdk.AuthenticationResponse;
import com.nimbusds.openid.connect.sdk.AuthenticationResponseParser;
import com.nimbusds.openid.connect.sdk.AuthenticationSuccessResponse;
import com.bing.auth.config.AADLoginProperties;
import com.bing.auth.config.AppNameProvider;
import com.bing.auth.service.SysLoginService;
import com.bing.auth.service.SysRecordLogService;
import com.bing.common.core.domain.R;
import com.bing.common.core.exception.ServiceException;
import com.bing.common.core.utils.CookieUtils;
import com.bing.common.core.utils.StringUtils;
import com.bing.common.core.utils.uuid.UUID;
import com.bing.common.core.web.controller.BaseController;
import com.bing.common.security.service.TokenService;
import com.bing.system.api.model.LoginUser;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;
import java.util.function.Supplier;

import static com.bing.common.core.constant.SsoConstants.LOGIN_D_TOKENS;

/**
 * AAD账号登录Controller
 *
 * @author Simeon
 */
@RestController
public class AADLoginController extends BaseController {
    private static final String DOMAIN_NAME = "";
    @Autowired
    private AADLoginProperties properties;
    @Autowired
    private HttpServletResponse response;
    @Autowired
    private SysLoginService sysLoginService;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private SysRecordLogService recordLogService;

    @GetMapping("/aad/test")
    public R<?> test()
    {
        return R.ok("ok");
    }

    @GetMapping("/aad/launch/{appName}")
    public void aadLaunch(@PathVariable("appName") String appName, HttpServletRequest httpRequest) throws IOException {
        String nonce = UUID.randomUUID().toString();
        String redirect = httpRequest.getParameter("redirect");
        // state作为定制参数，传过去AAD回调还会返回
        String state = (StringUtils.isEmpty(redirect) || redirect.equals("undefined")) ? "/index": redirect;
        String claims = httpRequest.getParameter("claims");
        if (appName == null) {
            throw new IllegalArgumentException("The appName parameter is required but was not provided.");
        } else if (!AppNameProvider.exists(appName)) {
            throw new IllegalArgumentException("Unavailable APP Name.");
        } else {
            // 支持多应用共享同一个AAD登录服务，gateway白名单要放行
            // 回调地址举例:
            // auth/aad/reply/code/filing
            // auth/aad/reply/code/ot
            // auth/aad/reply/code/portal
            String redirectUriSignin = getRedirectUriSignin(appName);
            String updatedScopes = "";
            if (appName.equalsIgnoreCase(AppNameProvider.LEGAL_DB)) {
                PublicClientApplication pca = PublicClientApplication.builder(properties.getLegalDBClientId())
                        .authority(properties.getLegalDBAuthority()).build();
                AuthorizationRequestUrlParameters parameters = AuthorizationRequestUrlParameters.builder(redirectUriSignin,
                                Collections.singleton(updatedScopes)).responseMode(ResponseMode.QUERY).prompt(Prompt.SELECT_ACCOUNT).state(state)
                        .nonce(nonce).claimsChallenge(claims).build();
                String sendRedirectUrl = pca.getAuthorizationRequestUrl(parameters).toString();
                response.sendRedirect(sendRedirectUrl);
            }
            else if (appName.equalsIgnoreCase(AppNameProvider.FILING_SYSTEM)) {
                PublicClientApplication pca = PublicClientApplication.builder(properties.getFilingClientId())
                        .authority(properties.getFilingAuthority()).build();
                AuthorizationRequestUrlParameters parameters = AuthorizationRequestUrlParameters.builder(redirectUriSignin,
                                Collections.singleton(updatedScopes)).responseMode(ResponseMode.QUERY).prompt(Prompt.SELECT_ACCOUNT).state(state)
                        .nonce(nonce).claimsChallenge(claims).build();
                String sendRedirectUrl = pca.getAuthorizationRequestUrl(parameters).toString();
                response.sendRedirect(sendRedirectUrl);
            }
        }
    }

    @GetMapping("/aad/reply/code/{appName}")
    public void aadCallback(@PathVariable("appName") String appName, HttpServletRequest httpRequest) throws IOException {
        String code = httpRequest.getParameter("code");
        String state = httpRequest.getParameter("state");
        String sessionState = httpRequest.getParameter("session_state");
        String fullUrl = getRedirectUriSignin(appName).concat("?code=").concat(code)
                .concat("&state=").concat(state).concat("&session_state=")
                .concat(sessionState);
        Map<String, List<String>> params = new HashMap<>();
        params.put("code", Collections.singletonList(code));
        params.put("state", Collections.singletonList(state));
        params.put("session_state", Collections.singletonList(sessionState));

        //拿着code请求access_token
        AuthenticationResponse authResponse;
        try {
            authResponse = AuthenticationResponseParser.parse(new URI(fullUrl), params);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        AuthenticationSuccessResponse oidcResponse = (AuthenticationSuccessResponse) authResponse;
        IAuthenticationResult result;
        ConfidentialClientApplication app = null;
        try {
            if(AppNameProvider.LEGAL_DB.equalsIgnoreCase(appName)) {
                app = ConfidentialClientApplication.builder(properties.getLegalDBClientId(),
                                ClientCredentialFactory.createFromSecret(properties.getLegalDBSecretKey()))
                        .authority(properties.getLegalDBAuthority()).build();
            }
            else if (AppNameProvider.FILING_SYSTEM.equalsIgnoreCase(appName)) {
                app = ConfidentialClientApplication.builder(properties.getFilingClientId(),
                                ClientCredentialFactory.createFromSecret(properties.getFilingSecretKey()))
                        .authority(properties.getFilingAuthority()).build();
            } else {
                throw new IllegalArgumentException("Unavailable APP Name.");
            }
            AuthorizationCodeParameters parameters = AuthorizationCodeParameters.builder(oidcResponse.getAuthorizationCode().getValue(),
                    new URI(getRedirectUriSignin(appName))).build();
            Future<IAuthenticationResult> future = app.acquireToken(parameters);
            result = future.get();
            if (result == null) {
                throw new RuntimeException("The authentication result is null, indicating that the authentication process failed.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("AAD login failed due to an unknown error.");
        }
        // 获取AAD邮箱
        String email = result.account().username();
        try {
            // 创建一个固定密码
            String password = "i$zG#~qNUJ6qD5@sTehuqk.7V";
            // 为AAD账号创建一个系统用户
            sysLoginService.register(email, password);
        } catch (ServiceException e) {
            System.out.println("AAD - "+e.getMessage());
        }
        // 二次登录检查
        LoginUser userInfo = sysLoginService.passwordFreeLogin(email);
        // 生成系统Token默认有效期12小时
        Map<String, Object> rspMap = tokenService.createToken(userInfo);
        // 缓存d参数一分钟，随后前端通过d参数取正式token
        String uuid = UUID.randomUUID().toString();
        sysLoginService.setCacheD(LOGIN_D_TOKENS+uuid, rspMap);
        CookieUtils.writeCookie(response, "d", 60, uuid,"", false, false);
        // 重定向APP登录页面
        redirectToLogin(appName, state, response);
    }

    @PostMapping("/aad/login")
    public R<?> aadLogin(@RequestBody String d) {
        Map<String, Object> rspMap = sysLoginService.getCacheD(LOGIN_D_TOKENS+d);
        CookieUtils.writeCookie(response, "d", 0, "","", false, false);
        if(rspMap == null) {
            return R.fail("Failed to retrieve AAD token due to timeout.");
        }
        return R.ok(rspMap);
    }

    /**
     * Retrieves the redirect URI for signing in based on the provided application name.
     *
     * @param appName The name of the application for which the redirect URI is requested.
     * @return The redirect URI for signing in.
     * @throws IllegalArgumentException If the provided application name is invalid or not supported.
     */
    private String getRedirectUriSignin(String appName) {
        // 创建一个映射，将应用程序名称与相应的重定向 URI 提供者关联
        Map<String, Supplier<String>> redirectUriMap = new HashMap<>();
        redirectUriMap.put(AppNameProvider.LEGAL_DB, properties::getLegalDBRedirectUriSignin);
        redirectUriMap.put(AppNameProvider.FILING_SYSTEM, properties::getFilingRedirectUriSignin);

        // 根据应用程序名称获取相应的重定向 URI 提供者
        Supplier<String> redirectUriSupplier = redirectUriMap.get(appName.toLowerCase());

        // 如果找到相应的提供者，则返回重定向 URI；否则抛出异常
        if (redirectUriSupplier != null) {
            return redirectUriSupplier.get();
        } else {
            // 处理无效的应用程序名称，抛出异常
            throw new IllegalArgumentException("Invalid AppName parameter: " + appName);
        }
    }

    /**
     * Redirects the user to the appropriate login page based on the application name.
     *
     * @param appName The name of the application for which the user is trying to log in.
     * @param redirect The URL to redirect to after a successful login.
     * @param response The HttpServletResponse object used to send the redirect response.
     * @throws IOException If an input or output exception occurs during the redirect process.
     * @throws IllegalArgumentException If the provided application name is unsupported.
     */
    private void redirectToLogin(String appName, String redirect, HttpServletResponse response) throws IOException {
        // Construct the redirect parameters
        String params = "?redirect=" + URLEncoder.encode(redirect);

        // Check the application name and redirect to the corresponding login URI
        if (AppNameProvider.LEGAL_DB.equalsIgnoreCase(appName)) {
            // Redirect to the Legal DB SSO login URI
            response.sendRedirect(properties.getLegalDBSsoLoginUri() + params);
        } else if (AppNameProvider.FILING_SYSTEM.equalsIgnoreCase(appName)) {
            // Redirect to the Filing System SSO login URI
            response.sendRedirect(properties.getFilingSsoLoginUri() + params);
        } else {
            // Handle unsupported application names by throwing an exception
            throw new IllegalArgumentException("Unsupported application name: " + appName);
        }
    }
}
