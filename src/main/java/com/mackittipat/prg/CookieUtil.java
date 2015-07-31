package com.mackittipat.prg;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieUtil {

    private static final String COOKIE_PATH = "/hanuman";

    public static Cookie createCookie(HttpServletResponse response, String name, String value) {
        Cookie cookie = new Cookie(name, value);
//        cookie.setPath(COOKIE_PATH);
        response.addCookie(cookie);
        return cookie;
    }

    public static String getCookieValue(HttpServletRequest request, String name) {
        if (request.getCookies() != null && name != null) {
            for (Cookie cookie : request.getCookies()) {
                if (name.equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

    public static void deleteCookie(HttpServletRequest request, HttpServletResponse response, String name) {
        if (request.getCookies() != null && name != null) {
            for (Cookie cookie : request.getCookies()) {
                if (name.equals(cookie.getName())) {
                    Cookie responseCookie = new Cookie(cookie.getName(), "");
                    responseCookie.setMaxAge(0);
                    // responseCookie.setPath(COOKIE_PATH);
                    response.addCookie(responseCookie);
                }
            }
        }
    }
}
