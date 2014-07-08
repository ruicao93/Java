package group2.manager;

import java.util.List;
import group2.dao.ProductDao;
import group2.factory.ProductDaoFactory;
import group2.model.ProductForm;


/**
 * 对用户完成增删改查的管理类，采用单例模式实现
 * @author Administrator
 *
 */
public class ProductManager {
	
	private static ProductManager instance = new ProductManager();
	
	private ProductDao proDao = null;
	
	private ProductManager() {		
		proDao = ProductDaoFactory.getInstance().createProductDao();
	}
	
	public static ProductManager getInstance() {
		return instance;
	}
	
	/**
	 * 增加用户
	 * @param user user对象
	 */
	public List queryProduct(int pageSize,int showPage) {	
			return proDao.queryProduct(pageSize,showPage);
	}
	public List querySort(){
		
		return proDao.querySort();
	}
	public String addProduct(ProductForm pf){
		return proDao.addProduct(pf);
	}
	public ProductForm pDetail(String pid){
		return proDao.pDetail(pid);
	}
	public String modifyProduct(ProductForm pf){
		return proDao.modifyProduct(pf);
	}
	public String delProduct(String [] pid){
		return proDao.delProduct(pid);
	}
	public void addSort(String sortname){
		proDao.addSort(sortname);
	}
	public ProductForm modifySort(String sortid){
		return proDao.modifySort(sortid);
		
	}
	public void modifySortDo(String sortname,String sortid){
		proDao.modifySortDo(sortname, sortid);
	}
	public void delSort(String [] str){
		proDao.delSort(str);
	}
	public void addGood(String wareid,Long userid){
		proDao.addGood(wareid,userid);
	}
	public List queryGood(String userid){
		return proDao.queryGood(userid);
	}
	public void delGoods(String gid){
		proDao.delGoods(gid);
	}
	public List adminGood(){
		return proDao.adminGood();
	}
}
