package cn.creater.action;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.creater.po.JSONObject;
import cn.creater.po.SafeUser;
import cn.creater.po.UserActive;
import cn.creater.service.RegisterService;
import cn.creater.service.UserService;
import cn.creater.util.BaseUntil;
import cn.creater.util.MailUtilByYock;
import cn.creater.util.TimeIP;

/**
 * <p>Description:注册管理 </p>
 * @author 刘双渤
 *
 */
@Controller
@RequestMapping("/register")
public class RegisterAction {

	@Autowired
	UserService userService;
	@Autowired
	RegisterService registerService;
	
	@RequestMapping("/check")
	public @ResponseBody JSONObject regcheck(String email) throws Exception {
		JSONObject obj = new JSONObject();
		SafeUser safeUser = userService.existValidate(email);
		if (safeUser!=null) {
			obj.setSuccess(true);
			UserActive active = registerService.regcheck(safeUser.getUserId());
			if (active.getActiveState().equals("Y")) {
				obj.setMsg("activated");
			}else {
				obj.setMsg("unactivated");
			}
		}
		
		return obj;
	}
	
	@Transactional
	@RequestMapping("/activesub")
	public @ResponseBody JSONObject sv(HttpServletRequest request, String email, String nickName, String pwd) throws Exception {
		JSONObject obj = new JSONObject();
		
		//验证该邮箱是否存在
		SafeUser safeUser = userService.existValidate(email);
		if (safeUser!=null) {
			//存在
			obj.setSuccess(false);
			UserActive act = new UserActive();
			act = registerService.regcheck(safeUser.getUserId());
			if (act.getActiveState().equals("N")) {
				obj.setMsg("unactivated");
			}else {
				obj.setMsg("exist");
			}
		}else {
			//不存在
			//发送验证邮件
			int activeCode = (int)(1+Math.random()*((99999-1)+1)); //激活码
			//生成user_id
			String userid = TimeIP.recordDate() + "221" + BaseUntil.getFixLenthString(3);
			Long activateTime = System.currentTimeMillis();
			String token = activateTime+"";
			String path = request.getContextPath();
			String URL = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
			System.out.println(URL);
			String contents = "<p>SafeStudy<br>" +
					"<br>账户需要激活才可以正常使用，请点击下面链接<br>" +
					"<a target='_blank' href='"+URL+"/register/active.action?token="+token+"&userid="+userid+"&activeCode="+activeCode+"'>" +
							""+URL+"/register/active.action?token="+token+"&userid="+userid+"&activeCode="+activeCode+"</a></p>";
//			String contents = URL+"activeCustomerAction?token="+token+"&UId="+uidInteger+"&UActivecode="+UActivecode;
			System.out.println(contents);
			String receiveEmail = email;
	        String subject="SafeStudy官方激活邮箱";
	        MailUtilByYock.sendMail(receiveEmail, subject, contents);
			
			//填加用户safe_user，添加激活码user_active
			//safe_user添加
			safeUser = new SafeUser();
			safeUser.setUserEmail(email);
			safeUser.setUserNickname(nickName);
			safeUser.setUserPassword(pwd);
			safeUser.setUserId(userid);
			userService.insertSafeUser(safeUser);
			
			//创建激活记录user_active
			UserActive userActive = new UserActive();
			userActive.setUserId(userid);
			userActive.setActiveCode(String.valueOf(activeCode));
			registerService.insertUserActive(userActive);
			
			obj.setSuccess(true);
		}
		
		return obj;
	}
	
	@RequestMapping("/active")
	public String active(Model model, String activeCode, String userid) throws Exception{
		JSONObject obj = new JSONObject();
		UserActive userActive = new UserActive();
		userActive = registerService.regcheck(userid);
		SafeUser safeUser = new SafeUser();
		safeUser = userService.selectById(userid);
		
		if (userActive!=null) {
			if (userActive.getActiveCode().equals(activeCode)) {
				//激活码验证通过
				if (userActive.getActiveState().equals("N")) {
					//更新激活状态
					userActive.setActiveState("Y");
					registerService.updateUserActive(userActive);
				}
				
				obj.setSuccess(true);
				obj.setMsg("该邮箱用户激活成功");
				obj.setObj(safeUser.getUserEmail());
			}else {
				//验证未通过
				obj.setMsg("链接中激活码（activeCode）错误，请在邮箱中确认");
			}
		}else {
			obj.setMsg("该邮箱用户不存在");
		}
		
		model.addAttribute("obj", obj);
		
		return "register/activeresult";
	}
}
