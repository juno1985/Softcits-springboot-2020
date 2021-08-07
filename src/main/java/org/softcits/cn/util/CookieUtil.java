package org.softcits.cn.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieUtil {
    public static String getCookieByName(HttpServletRequest request, HttpServletResponse response, String cookieName){
        Cookie[] cookieList = request.getCookies();

        for (int i = 0; i < cookieList.length; i++) {
            if(cookieList[i].getName().equals(cookieName)){
                return cookieList[i].getValue();
            }
        }
        return null;
    }
}
