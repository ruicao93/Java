package group2.dao;

import group2.model.ProductForm;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ShowByPage {
	
	
		//定义结果集对象
		private ResultSet rs=null;
		//总记录数目
		private int sumCount;
		//所分的逻辑页数
		private int pageCount;
		//每页显示的记录数目
		private int pageSize;
		
		//初始化,获取数据表中的信息
		public void initialize(ResultSet rs,int pageSize)
		{		
			this.pageSize=pageSize;
			try
			{				
				this.rs=rs; 
				if (this.rs!=null)
				{
					this.rs.last();
					this.sumCount = this.rs.getRow();				
					this.rs.first();
					this.pageCount = (this.sumCount - 1) / this.pageSize + 1;				
				}
				else
				{
					this.sumCount=0;									
				}
			}
			catch(SQLException e)
			{
				System.out.println(e.toString());
			} 		 
		}
		
		//将显示结果存到Vector集合类中
		public List getPage(int ipage ,ShowByPage sp)
		{ 
			List <ProductForm>list=new ArrayList <ProductForm>();
			int n=ipage; 
			int m=0; 
			m=(n-1)*this.pageSize+1; 
			try
			{
				if (this.rs!=null)
				{
					if (n!=1) 
					{
						this.rs.absolute(m);
					} 
					for(int i=0;i<this.pageSize;i++)
					{ 
						//w.wareid,w.warename,w.company,w.addr,w.price,w.picurl,w.sortid,s.sortname,w.intro,w.createtime
						ProductForm product =new ProductForm();
						product.setPid(rs.getString(1));
						product.setPname(rs.getString(2));
						product.setCompany(rs.getString(3));
						product .setAddress(rs.getString(4));
						product.setPrice(rs.getFloat(5));
						product.setPicurl(rs.getString(6));
						product.setSortid(rs.getLong(7));
						product.setSortname(rs.getString(8));
						product.setIntroduce(rs.getString(9));
						product.setCreatetime(rs.getString(10).substring(0,16));
						product.setSumcount(sp.getSumCount());
						product.setPageCount(sp.getPageCount());
						product.setIpage(ipage);
						list.add(product);
						this.rs.next();
					} 
					
				} 						
			} 
			catch(SQLException e)
			{
				System.out.println(e.toString());
			} 
			return list; 
		} 
		
		//获得页面总数
		public int getPageCount()
		{
			return this.pageCount;
		}

		public int getSumCount() {
			return sumCount;
		}

		public void setSumCount(int sumCount) {
			this.sumCount = sumCount;
		}
		
		
	

}
