package rock.util;

import javax.servlet.http.HttpSession;

import rock.domain.User;

public class SessionUtil {
	
	public static final String SESSION_USER_KEY = "s_user";
	
	public static boolean isLogined(HttpSession session) {
		if(getLoginUser(session) == null) {
			return false;
		}
		
		return true;
	}
	
	public static User getLoginUser(HttpSession session) {
		User user = (User)session.getAttribute(SESSION_USER_KEY);
		
		return user; 
	}
	
	public static void setLoginUser(User user, HttpSession session) {
		session.setAttribute(SESSION_USER_KEY, user);
	}

}
