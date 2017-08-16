package cn.creater.service;

import cn.creater.po.UserActive;

public interface RegisterService {

	/**
	 * 查询是否已存在该用户
	 * @param userid 用户ID
	 * @return 存在返回该记录，不存在返回null
	 * @throws Exception
	 */
	public UserActive regcheck(String userid) throws Exception;

	/**
	 * 创建用户激活表
	 * @param userActive 待激活用户
	 */
	public void insertUserActive(UserActive userActive);

	/**
	 * 更新该用户激活记录
	 * @param userActive
	 */
	public void updateUserActive(UserActive userActive);
	
}
