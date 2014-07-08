package group2.factory;

import group2.dao.ProductDao;
import group2.dao.ProductDaoImp;


public class ProductDaoFactory {
private static ProductDaoFactory instance;
	
	private ProductDao proDao;
	
	private ProductDaoFactory() {
		
		//可以从配置文件中动态装载UserDao4MySqlImpl实现类,便于灵活更换
		proDao = new ProductDaoImp();
	}
	
	public static synchronized ProductDaoFactory getInstance() {
		if (instance == null) {
			instance = new ProductDaoFactory();
		}
		return instance;
	}
	
	public ProductDao createProductDao() {
		return proDao;
	}
}
