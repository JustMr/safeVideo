package cn.creater.action;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.creater.po.JSONObject;
import cn.creater.po.SafeUser;
import cn.creater.po.UserActive;
import cn.creater.service.RegisterService;
import cn.creater.service.UserService;

/**
 * <p>Description:用户管理 </p>
 * @author 刘双渤
 *
 */
@Controller
@RequestMapping("/user")
public class SafeUserAction {

	@Autowired
	private UserService userService;
	@Autowired
	private RegisterService registerService;
	
	@RequestMapping("/login")
	public @ResponseBody JSONObject loginVailet(HttpSession session, String account, String pwd, String type) throws Exception {
//		System.out.println("account:"+account);
		JSONObject obj = new JSONObject();
		SafeUser user = new SafeUser();
		user = userService.loginValidate(type, account, pwd);
		if (user!=null) {
			UserActive userActive = new UserActive();
			userActive = registerService.regcheck(user.getUserId());
			if (userActive.getActiveState().equals("N")) {
				obj.setMsg("noact");
			}else {
				session.setAttribute("user", user);
				obj.setSuccess(true);
			}
		}else {
			obj.setMsg("wrong");
		}
		
		return obj;
	}
	
}
