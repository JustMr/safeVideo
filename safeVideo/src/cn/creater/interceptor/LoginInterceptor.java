package cn.creater.interceptor;

import java.io.InputStream;
import java.util.Enumeration;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import cn.creater.po.SafeUser;

public class LoginInterceptor implements HandlerInterceptor {

	@Override
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object handler) throws Exception {
		//判断请求的url是否公开 地址(无需登陆即可操作url)
		//正常开发时，需要将公开地址配置在配置文件中。
		//取出请求的url
		String url = request.getRequestURI();
//		System.out.println("url:"+url);
		
		//加载放行配置文件
		Properties pps = new Properties();
		InputStream in = LoginInterceptor.class.getClassLoader().getResourceAsStream("login.interceptor.properties");
		pps.load(in);
		Enumeration<Object> enu = pps.elements();
		while (enu.hasMoreElements()) {
			String value = (String) enu.nextElement();
			if(url.indexOf(value)>=0){
				//说明 公开地址
				//放行
				return true;
			}
			
			
		}
		
		//得到session
		HttpSession session = request.getSession();
		
		//从 session取出用户身份信息
		SafeUser user = (SafeUser) session.getAttribute("user");
//		System.out.println("log:"+user);
		
		if(user!=null){
			//说明 用户已登陆（用户身份合法）
			//放行
			return true;
			
		}
		
		//执行到这里说明 用户身份不合法，拦截，跳转到登陆页面
		request.getRequestDispatcher("/index.jsp").forward(request, response);

		return false;
	}

}
