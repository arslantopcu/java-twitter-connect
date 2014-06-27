package com.mynet.utilities.servlet;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CommonCookie
{
  public static Cookie get(HttpServletRequest request, String name)
  {
    Cookie[] cookies = request.getCookies();
    Cookie cookie = null;
    if (cookies != null) {
      for (int i = 0; i < cookies.length; i++) {
        if (cookies[i].getName().equals(name))
        {
          try
          {
            cookie = (Cookie)cookies[i].clone();
          }
          catch (NumberFormatException localNumberFormatException) {}
          break;
        }
      }
    }
    return cookie;
  }
  
  public static Cookie add(HttpServletResponse response, String name, String value, String domain, String path, int age)
  {
    Cookie cookie = new Cookie(name, value);
    cookie.setDomain(domain);
    cookie.setMaxAge(age);
    cookie.setPath(path);
    response.addCookie(cookie);
    return cookie;
  }
  
  public static Cookie add(HttpServletResponse response, String name, String value)
  {
    Cookie cookie = new Cookie(name, value);
    response.addCookie(cookie);
    return cookie;
  }
}
