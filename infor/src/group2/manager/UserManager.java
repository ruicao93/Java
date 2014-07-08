package group2.manager;

import group2.dao.UserDao;
import group2.factory.UserDaoFactory;
import group2.model.UserForm;

/**
 * 对用户完成增删改查的管理类，采用单例模式实现
 * @author Administrator
 *
 */
public class UserManager {
	
	private static UserManager instance = new UserManager();
	
	private UserDao userDao = null;
	
	private UserManager() {		
		userDao = UserDaoFactory.getInstance().createUserDao();
	}
	
	public static UserManager getInstance() {
		return instance;
	}
	
	/**
	 * 增加用户
	 * @param user user对象
	 */
	public UserForm addUser(UserForm user) {	
			return userDao.addUser(user);
	}
	public UserForm loginConf(UserForm user){
		
		return userDao.loginConf(user);
	}
	public UserForm modfiyUser(UserForm user){
		return userDao.modfiyUser(user);
	}
}
