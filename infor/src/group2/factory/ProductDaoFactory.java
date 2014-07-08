package group2.factory;

import group2.dao.ProductDao;
import group2.dao.ProductDaoImp;


public class ProductDaoFactory {
private static ProductDaoFactory instance;
	
	private ProductDao proDao;
	
	private ProductDaoFactory() {
		
		//���Դ������ļ��ж�̬װ��UserDao4MySqlImplʵ����,����������
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
