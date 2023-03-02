package ez.web.bbs.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

//public class LoginInterceptor implements HandlerInterceptor{
public class LoginInterceptor extends HandlerInterceptorAdapter{
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		HttpSession session = request.getSession();
		
		Object dtoObj = session.getAttribute("loginDto");		
		
		// 로그인 안한 경우
		if(dtoObj == null) {
			session.setAttribute("loginMsg", "로그인이 필요한 서비스 입니다!!");
			response.sendRedirect("memberLogin.do?moveUrl="+request.getRequestURL()+"?"+request.getQueryString());
			return false;
		}
		
		return true;
	}
}
