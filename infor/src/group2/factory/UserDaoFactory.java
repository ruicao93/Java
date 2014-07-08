package group2.factory;

import group2.dao.UserDao;
import group2.dao.UserDaoImp;

public class UserDaoFactory {
private static UserDaoFactory instance;
	
	private UserDao userDao;
	
	private UserDaoFactory() {
		
		//���Դ������ļ��ж�̬װ��UserDao4MySqlImplʵ����,����������
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
