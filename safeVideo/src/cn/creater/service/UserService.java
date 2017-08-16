package cn.creater.service;

import cn.creater.po.SafeUser;

public interface UserService {

	/**
	 * 登录验证
	 * @param type 登录账号类型
	 * @param account 登录账号
	 * @param userPassword 密码
	 * @return 
	 * @throws Exception
	 */
	public SafeUser loginValidate(String type, String account, String userPassword) throws Exception;
	
	/**
	 * 验证邮箱是否存在
	 * @param email 被验证邮箱
	 * @return 
	 * @throws Exception
	 */
	public SafeUser existValidate(String email) throws Exception;

	/**
	 * 创建新用户
	 * @param safeUser 新用户
	 */
	public void insertSafeUser(SafeUser safeUser) throws Exception;
	
	/**
	 * 通过主键查询该用户记录
	 * @param userid
	 * @return
	 * @throws Exception
	 */
	public SafeUser selectById(String userid) throws Exception;
}
