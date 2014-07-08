package group2.dao;

import group2.DB.ConnectDB;
import group2.model.ProductForm;


import java.util.*;
import java.sql.*;


//这个类继承自ConnectDB类
public class ProductDaoImp extends ConnectDB implements ProductDao
{
	//数据库连接对象
	private Connection dbConn;
	private PreparedStatement stmt;
	private ResultSet rs;	
   
	
	//初始化操作
	public ProductDaoImp()
	{
		dbConn = super.getConn();
		stmt = null;
		rs = null;		
	}
	
	public List queryProduct(int pageSize,int showPage){
		
		String sql="select w.wareid,w.warename,w.company,w.addr,w.price,w.picurl,w.sortid,s.sortname,w.intro,w.createtime " +
				" from wares w join(sorts s) on (s.sortid=w.sortid) order by w.wareid desc";
		List list=null;
		//List<ProductForm> list=null;
		try {
			stmt=dbConn.prepareStatement(sql);
			rs=stmt.executeQuery();
			
	
			ShowByPage sbp=new ShowByPage();
			sbp.initialize(rs,pageSize);
			list=sbp.getPage(showPage,sbp);
			System.out.println("测试list的长度（查询商品信息的结果集）ProductDaoImp.queryProduct()="+list.size());
			System.out.println(""); 
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				if(rs!=null)rs.close();
				if(stmt!=null)stmt.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	public List querySort(){
		
		String sql="select * from sorts order by sortid desc";
		List <ProductForm>list=new ArrayList<ProductForm>();
		try {
			stmt=dbConn.prepareStatement(sql);
			rs=stmt.executeQuery();
			//System.out.println("测试list的长度（查询商品信息的结果集）ProductDaoImp.queryProduct()="+list.size());
			
			while(rs.next()){
				
				ProductForm pf=new ProductForm();
				pf.setSortid(rs.getLong(1));
				pf.setSortname(rs.getString(2));
				list.add(pf);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				if(rs!=null)rs.close();
				if(stmt!=null)stmt.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		System.out.println("测试list的长度（查询商品类别结果集）ProductDaoImp.querySort()="+list.size());
		return list;
	}	
	
	public String addProduct(ProductForm pf){
		
		String sql="insert into wares(warename,company,addr,price,picurl,sortid,intro,createtime)values" +
				"(?,?,?,?,?,?,?,now())";
		String path=null;
		try
		{
			stmt=dbConn.prepareStatement(sql);
			stmt.setString(1, pf.getPname());
			stmt.setString(2, pf.getCompany());
			stmt.setString(3, pf.getAddress());
			stmt.setFloat(4, pf.getPrice());
			stmt.setString(5, pf.getPicurl());
			stmt.setLong(6, pf.getSortid());
			stmt.setString(7,pf.getIntroduce());
			stmt.executeUpdate();
			path="queryProduct.do";
		}
		catch(Exception e)
		{
			System.out.println(e);
					
		}
		finally{
			try {
				if(rs!=null)rs.close();
				if(stmt!=null)stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return path;
	}
	public ProductForm pDetail(String pid){
		
		String sql="select w.wareid,w.warename,w.company,w.addr,w.price,w.picurl,w.sortid,s.sortname,w.intro,w.createtime " +
				" from wares w join(sorts s) on (s.sortid=w.sortid )where w.wareid=?";
		ProductForm pf=new ProductForm();
		try
		{
			stmt=dbConn.prepareStatement(sql);
			System.out.println("skldfklsdfksdko");
			stmt.setInt(1, Integer.parseInt(pid));
			rs=stmt.executeQuery();
			if(rs.next()){System.out.println("坎坎坷坷坎坎坷坷坎坎坷坷");}
			pf.setPid(rs.getString(1));
			pf.setPname(rs.getString(2));
			pf.setCompany(rs.getString(3));
			pf.setAddress(rs.getString(4));
			pf.setPrice(rs.getFloat(5));
			pf.setPicurl(rs.getString(6));
			pf.setSortid(rs.getLong(7));
			pf.setSortname(rs.getString(8));
			pf.setIntroduce(rs.getString(9));
			pf.setCreatetime(rs.getString(10));
		
		}
		catch(Exception e)
		{
			System.out.println(e);
					
		}
		finally{
			try {
				if(rs!=null)rs.close();
				if(stmt!=null)stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return pf;
	}
	public String modifyProduct(ProductForm pf){
		
		String sql="update wares set warename=?,company=?,addr=?,price=?,picurl=?,sortid=?,intro=? " +
				"where wareid=?";
		String path=null;
		try
		{
			stmt=dbConn.prepareStatement(sql);
			stmt.setString(1, pf.getPname());
			stmt.setString(2, pf.getCompany());
			stmt.setString(3, pf.getAddress());
			stmt.setFloat(4, pf.getPrice());
			stmt.setString(5, pf.getPicurl());
			stmt.setLong(6, pf.getSortid());
			stmt.setString(7,pf.getIntroduce());
			System.out.println("测试ProductDaoImp.modfiyProduct()的pid="+Integer.parseInt(pf.getPid()));
			stmt.setInt(8, Integer.parseInt(pf.getPid()));
			stmt.executeUpdate();
			path="pdetail.do?pid="+pf.getPid();
		}
		catch(Exception e)
		{
			System.out.println(e);
					
		}
		finally{
			try {
				if(rs!=null)rs.close();
				if(stmt!=null)stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return path;
	}
	public String delProduct(String [] pid){
		
		StringBuffer sbfSql = new StringBuffer();
		for (int i = 0; i < pid.length; i++) {
			sbfSql.append("'")
				.append(pid[i])
				.append("'")
				.append(",");
		}
		String sql="delete from wares where wareid in(" + sbfSql.substring(0, sbfSql.length()-1) + ")";
		
		
		String path=null;
		try
		{
			Statement sstmt=dbConn.createStatement();
			
			sstmt.executeUpdate(sql);
			path="queryProduct.do";
		}
		catch(Exception e)
		{
			System.out.println(e);
					
		}
		finally{
			try {
				if(rs!=null)rs.close();
				if(stmt!=null)stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return path;
	}
	public void addSort(String sortname){
		
		String sql="insert into sorts (sortname) values(?)";
		try {
			stmt=dbConn.prepareStatement(sql);
			stmt.setString(1, sortname);
			stmt.executeUpdate();
			//System.out.println("测试list的长度（查询商品信息的结果集）ProductDaoImp.queryProduct()="+list.size());
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				if(rs!=null)rs.close();
				if(stmt!=null)stmt.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}	
	public ProductForm modifySort(String sortid){
		
		String sql="select * from sorts where sortid=?";
		ProductForm pf=new ProductForm();
		try {
			stmt=dbConn.prepareStatement(sql);
			stmt.setString(1, sortid);
			rs=stmt.executeQuery();
			//System.out.println("测试list的长度（查询商品信息的结果集）ProductDaoImp.queryProduct()="+list.size());
			
			while(rs.next()){
				System.out.println("ProductDaoImp.modifyProduct()=");
				
				pf.setSortid(rs.getLong(1));
				pf.setSortname(rs.getString(2));
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				if(rs!=null)rs.close();
				if(stmt!=null)stmt.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return pf;
	}
	public void modifySortDo(String sortname,String sortid){
		
		String sql="update sorts set sortname=? where sortid=?";
		try {
			stmt=dbConn.prepareStatement(sql);
			stmt.setString(1, sortname);
			stmt.setString(2, sortid);
			stmt.executeUpdate();
			//System.out.println("测试list的长度（查询商品信息的结果集）ProductDaoImp.queryProduct()="+list.size());
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				if(rs!=null)rs.close();
				if(stmt!=null)stmt.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
	public void delSort(String [] str){
		StringBuffer sbfSql = new StringBuffer();
		for (int i = 0; i < str.length; i++) {
			sbfSql.append("'")
				.append(str[i])
				.append("'")
				.append(",");
		}
		String sql="delete from sorts where sortid in(" + sbfSql.substring(0, sbfSql.length()-1) + ")";
		
		
		try
		{
			Statement sstmt=dbConn.createStatement();
			
			sstmt.executeUpdate(sql);
			stmt.close();
			
		}
		catch(Exception e)
		{
			System.out.println(e);
					
		}
		finally{
			try {
				
				if(rs!=null)rs.close();
				if(stmt!=null)stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	public void addGood(String wareid,Long userid){
		
		String sql="insert into goods (wareid,userid,createtime) values(?,?,now())";
		try {
			stmt=dbConn.prepareStatement(sql);
			stmt.setString(1, wareid);
			stmt.setLong(2, userid);
			stmt.executeUpdate();
			//System.out.println("测试list的长度（查询商品信息的结果集）ProductDaoImp.queryProduct()="+list.size());
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				if(rs!=null)rs.close();
				if(stmt!=null)stmt.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}	
	public List queryGood(String userid){
		List<ProductForm> list=new ArrayList<ProductForm>();
		String sql="select w.warename,w.addr,w.company,w.price,g.buynumber,g.goodsid from wares w,goods g where w.wareid=g.wareid and g.userid=?";
		try {
			stmt=dbConn.prepareStatement(sql);
			stmt.setString(1, userid);
			
			rs=stmt.executeQuery();
			//System.out.println("测试list的长度（查询商品信息的结果集）ProductDaoImp.queryProduct()="+list.size());
			float money=0;
			while(rs.next()){
				ProductForm pf=new ProductForm();
				pf.setPname(rs.getString(1));
				pf.setAddress(rs.getString(2));
				pf.setCompany(rs.getString(3));
				pf.setPrice(rs.getFloat(4));
				pf.setPicurl(rs.getString(5));
				money+=rs.getFloat(4);
				pf.setSumMoney(money);
				pf.setPid(rs.getString(6));
				System.out.println("测试ProductDaoImp.queryGood()的sumMoney="+money);
				list.add(pf);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				if(rs!=null)rs.close();
				if(stmt!=null)stmt.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}	
	public void delGoods(String gid){
		
		String sql="delete from goods where goodsid=?";
		try {
			stmt=dbConn.prepareStatement(sql);
			stmt.setString(1, gid);
			stmt.executeUpdate();
			//System.out.println("测试list的长度（查询商品信息的结果集）ProductDaoImp.queryProduct()="+list.size());
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				if(rs!=null)rs.close();
				if(stmt!=null)stmt.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
	public List adminGood(){
		List<ProductForm> list=new ArrayList<ProductForm>();
		String sql="select g.goodsid,w.warename,g.userid,u.username,g.buynumber,g.createtime,w.wareid from wares w,goods g,users u where w.wareid=g.wareid and g.userid=u.userid";
		try {
			stmt=dbConn.prepareStatement(sql);
			
			rs=stmt.executeQuery();
			//System.out.println("测试list的长度（查询商品信息的结果集）ProductDaoImp.queryProduct()="+list.size());
			while(rs.next()){
				ProductForm pf=new ProductForm();
				pf.setPid(rs.getString(1));//商品id
				pf.setPname(rs.getString(2));//商品名称
				pf.setIntroduce(rs.getString(3));//用户id
				pf.setCompany(rs.getString(4));//用户名称
				pf.setPicurl(rs.getString(5));//购买数量
				String time=rs.getString(6);
				time=time.substring(0,16);
				pf.setSortname(rs.getString(7));//商品id
				pf.setCreatetime(time);//购买时间
				list.add(pf);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				if(rs!=null)rs.close();
				if(stmt!=null)stmt.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}	
	
}








