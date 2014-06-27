package com.twitter;

import java.io.IOException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.User;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;


@Controller
@RequestMapping("/twitterConnect")
public class twitterConnectController {
	
	private static final Logger logger = Logger.getLogger(v4RegisterWithTwitterConnectController.class);
	
	@RequestMapping(method = RequestMethod.GET,params="start")
	public void twconn(HttpServletRequest servletRequest, HttpServletResponse servletResponse) {
		
		String rurl = servletRequest.getParameter("rurl");
		
		String callbackURL = "/twitterConnect.htm";
				
		Twitter twitter = new TwitterFactory().getInstance();
		
		try {
			
	    		RequestToken requestToken = twitter.getOAuthRequestToken(callbackURL.toString());
	
	    		CommonCookie.add(servletResponse, "twitter4j", requestToken.getToken()+":"+requestToken.getTokenSecret(), "domain.com", "/", -1);            
	    		CommonCookie.add(servletResponse, "newregrurl", rurl,"domain.com", "/", -1);
	    
			servletResponse.sendRedirect(requestToken.getAuthenticationURL());
			
		}catch (IOException e) {
			logger.error("IOException.", e);	
	        } catch (TwitterException e) {
	        	logger.error("TwitterException.", e);
	        }
		
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public String get(HttpServletRequest servletRequest, HttpServletResponse servletResponse, ModelMap model) {
		
		// twitter4j
		String twitter4jtoken="";
		String twitter4jtokenSecret="";
		
		try {
			
			Cookie cookie = CommonCookie.get(servletRequest, "twitter4j");
			String twitter4jRequestToken = cookie.getValue();
			
			if( !twitter4jRequestToken.equals("") ){
				String[] cc= twitter4jRequestToken.split(":");
				twitter4jtoken =cc[0];
				twitter4jtokenSecret = cc[1];
			}
			CommonCookie.add(servletResponse, "twitter4j", "0" , "domain.com", "/", -1);
		} catch (Exception ex) {}

		
		String verifier = servletRequest.getParameter("oauth_verifier");
		String oauth_token = servletRequest.getParameter("oauth_token");
		
		Twitter twitter = new TwitterFactory().getInstance();
		
		try {
			
			AccessToken accessToken = new AccessToken(twitter4jtoken, twitter4jtokenSecret);
			twitter.setOAuthAccessToken(accessToken);            
			twitter.getOAuthAccessToken(verifier);
            
        	} catch (TwitterException e) {
        		logger.error("TwitterException", e);
        	}
				
		User user = null;
		try {
			user = twitter.verifyCredentials();
		} catch (TwitterException e1) {
			logger.error("TwitterException user info", e1);
		}
							
		return "html";
		
	}	
	
	
	
}
