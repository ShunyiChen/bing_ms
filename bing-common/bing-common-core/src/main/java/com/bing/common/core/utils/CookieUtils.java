package com.bing.common.core.utils;

import org.springframework.stereotype.Component;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CookieUtils {

    /**
     * get cookie
     *
     * @param request
     * @param cookieName
     * @return
     */
    public static String getCookie(HttpServletRequest request, String cookieName) {

        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(cookieName)) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

    /**
     * write Cookie
     *
     * @param response
     * @param cookieName
     * @param cookieExpiryDate  秒
     * @param value
     * @param domain
     */
    public static void writeCookie(HttpServletResponse response, String cookieName, Integer cookieExpiryDate, String value,
                                   String domain, boolean httpOnly, boolean secure) {
        Cookie cookie = new Cookie(cookieName, value);
        cookie.setDomain(domain);
        cookie.setPath("/");
        if (cookieExpiryDate != null) {
            cookie.setMaxAge(cookieExpiryDate);
        }
        cookie.setHttpOnly(httpOnly);
        cookie.setSecure(secure);//临时服务器没域名，暂时先改成false
        response.addCookie(cookie);
    }
}
