package group2.dao;

import group2.model.UserForm;

public interface UserDao {
	
	public UserForm addUser(UserForm user);
	public UserForm loginConf(UserForm user);
	public UserForm modfiyUser(UserForm user);
}
