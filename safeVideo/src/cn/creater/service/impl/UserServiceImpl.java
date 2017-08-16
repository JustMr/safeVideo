package cn.creater.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import cn.creater.dao.mapper.SafeUserMapper;
import cn.creater.po.SafeUser;
import cn.creater.po.SafeUserExample;
import cn.creater.service.UserService;

public class UserServiceImpl implements UserService {

	@Autowired
	SafeUserMapper safeUserMapper;
	
	@Override
	public SafeUser loginValidate(String type, String account, String userPassword) throws Exception {
		List<SafeUser> users = new ArrayList<SafeUser>();
		//定义查询对象
		SafeUserExample userExample = new SafeUserExample();
		SafeUserExample.Criteria criteria = userExample.createCriteria();
		
		if (type.equals("tel")) {
			//使用手机登录
			criteria.andUserTelEqualTo(account);
		}else if (type.equals("email")) {
			//使用邮箱登录
			criteria.andUserEmailEqualTo(account);
		}else if (type.equals("id")) {
			//使用ID登录
			criteria.andUserIdEqualTo(account);
		}else {
			return null;
		}
		
		criteria.andUserPasswordEqualTo(userPassword);
		users = safeUserMapper.selectByExample(userExample);
		if(users.size()==1) {
			//找到一条记录，用户存在
			return users.get(0);
		}
		
		return null;
	}

	@Override
	public SafeUser existValidate(String email) throws Exception {
		SafeUserExample safeUserExample = new SafeUserExample();
		SafeUserExample.Criteria cri = safeUserExample.createCriteria();
		cri.andUserEmailEqualTo(email);
		
		List<SafeUser> list = safeUserMapper.selectByExample(safeUserExample);
		
		if (list.size()>0) {
			return list.get(0);
		}
		
		return null;
	}

	@Override
	public void insertSafeUser(SafeUser safeUser) throws Exception {
		safeUserMapper.insertSelective(safeUser);
	}

	@Override
	public SafeUser selectById(String userid) throws Exception {
		
		return safeUserMapper.selectByPrimaryKey(userid);
	}

}
