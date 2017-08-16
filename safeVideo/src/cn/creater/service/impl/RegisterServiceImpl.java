package cn.creater.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import cn.creater.dao.mapper.UserActiveMapper;
import cn.creater.po.UserActive;
import cn.creater.service.RegisterService;

public class RegisterServiceImpl implements RegisterService {
	
	@Autowired
	UserActiveMapper userActiveMapper;

	@Override
	public UserActive regcheck(String userid) throws Exception {
		
		//查询该用户是否存在
		UserActive active = userActiveMapper.selectByPrimaryKey(userid);
		return active;
	}

	@Override
	public void insertUserActive(UserActive userActive) {
		
		userActiveMapper.insertSelective(userActive);
	}

	@Override
	public void updateUserActive(UserActive userActive) {
		
		userActiveMapper.updateByPrimaryKeySelective(userActive);
		
	}

}
