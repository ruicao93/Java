package group2.factory;

import group2.dao.UserDao;
import group2.dao.UserDaoImp;

public class UserDaoFactory {
private static UserDaoFactory instance;
	
	private UserDao userDao;
	
	private UserDaoFactory() {
		
		//可以从配置文件中动态装载UserDao4MySqlImpl实现类,便于灵活更换
		userDao = new UserDaoImp();
	}
	
	public static synchronized UserDaoFactory getInstance() {
		if (instance == null) {
			instance = new UserDaoFactory();
		}
		return instance;
	}
	
	public UserDao createUserDao() {
		return userDao;
	}
}
