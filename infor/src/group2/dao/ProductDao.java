package group2.dao;

import java.util.List;

import group2.model.ProductForm;

public interface ProductDao {
	
	public List queryProduct(int pageSize,int showPage);
	public List querySort();
	public String addProduct(ProductForm pf);
	public ProductForm pDetail(String pid);
	public String modifyProduct(ProductForm pf);
	public String delProduct(String [] pid);
	public void addSort(String sortname);
	public ProductForm modifySort(String sortid);
	public void modifySortDo(String sortname,String sortid);
	public void delSort(String [] str);
	public void addGood(String wareid,Long userid);
	public List queryGood(String userid);
	public void delGoods(String gid);
	public List adminGood();
}
