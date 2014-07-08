package group2.manager;

import group2.dao.UserDao;
import group2.factory.UserDaoFactory;
import group2.model.UserForm;

/**
 * ���û������ɾ�Ĳ�Ĺ����࣬���õ���ģʽʵ��
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
	 * �����û�
	 * @param user user����
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
