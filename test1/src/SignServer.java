

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.io.FileInputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;




public class SignServer {
	//private String courseTime = null; //服务器端课程上课周时数据（格式为：1-n周）
		//private String weekTime = null; //服务器端课程每周上课时间数据（格式为：150;251）
		//private String courseSignHistory[] = null;   //签到记录
		//private int currentWeek = 0;  //现在的周数
		//private int currentTime = 0;        //现在周几、、、
		//private String courseList[][] = null;
	private String ClassForName = "com.mysql.jdbc.Driver";
	private String jdbc = "jdbc:mysql://127.0.0.1:3306/cwj";
	private String dbUser = "root";
	private String password = "123456";
	
	private String termStartTime = "2014-2-16";
	

	
	
	private int termStartWeek = 0;     //学期的起始周是本年第几周
	
	public SignServer(){  
		try {
			setTermStartTime("2013-09-01");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
		//public服务，返回某学生某课程的签到记录
		public String getStudentCourseSignData(String studentID,String courseID)
				throws Exception{   //获取学生某课程签到信息
			String courseSignData[] = new String[5];  //存储信息返回给客户端
			String courseTime = null; //服务器端课程上课周时数据（格式为：1-n周）
			String weekTime = null; //服务器端课程每周上课时间数据（格式为：150;251）
			String courseSignHistory = null;   //签到记录
			courseSignData[3] = getCurrentWeek();
			String tmp = getCurrentTime();
			courseSignData[4] = tmp.substring(0, tmp.length()-1);
			Class.forName(ClassForName);
			Connection conn = DriverManager.getConnection(
					jdbc,
					dbUser,password);
			Statement stmt = conn.createStatement();
			//获取课程时间信息
			ResultSet rsCourseTime = stmt.executeQuery("select course_list.course_time,course_list.week_time" +
					" " +
					" from course_list,student_course" +
					" where student_course.stu_id='"+studentID+"' and" +
					" student_course.course_id='"+courseID+"'");
			if(rsCourseTime.next()){
				//System.out.println(rs.getInt(1)+"\t"
				//		+rs.getDouble(2)+"\t"
				//		+rs.getString(3));
				courseTime = rsCourseTime.getString(1);
				weekTime = rsCourseTime.getString(2);
				courseSignData[0] = courseTime;
				courseSignData[1] =weekTime;
			}
			//获取学生签到信息
			ResultSet rsSign = stmt.executeQuery("select sign_time from student_sign" +
					" where stu_id='"+studentID+"' and"+
					" course_id ='"+courseID+"'");
			String tmpStr="";
			while(rsSign.next()){
				if(tmpStr.equals("")){
					tmpStr = rsSign.getString(1);
				}else{
					tmpStr = tmpStr+";"+rsSign.getString(1);
				}
			}
			courseSignData[2] = tmpStr;
			rsCourseTime.close();
			rsSign.close();
			stmt.close();
			conn.close();
			String result = "";
			for(int x=0; x<courseSignData.length; x++){
				if(result.equals("")){
					result = result+courseSignData[x];
				}else{
					result = result+"@"+courseSignData[x];
				}
			}
			rsSign.close();
			stmt.close();
			conn.close();
			
			return result;
		}
		
		public String getCurrentWeek(){   //获取现在是第几周
			return "8";
		}
		
		public String getCurrentTime(){   //获取现在是周几(格式：12（周一第一节）)
			return "52";
		}
		
		//将时间转换为周数
		public int timeToWeekNum(){
			return 1;
		}
		//将时间转换为周几的第几节课
		public int timeToCNum(){
			return 0;    //0为非签到时间
		}
		
		
		public void setTermStartTime(String startTime) throws ParseException{
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date startDate = (Date)format.parse(startTime);
			Calendar calendar = Calendar.getInstance();
			calendar.setFirstDayOfWeek(Calendar.MONDAY);
			calendar.setTime(startDate);
			termStartWeek = calendar.get(Calendar.WEEK_OF_YEAR);
			
		}
		
		//获取某学生课程列表
		public String getStudentCourseList(String studentID) throws Exception{
			Map<String,String> map = new HashMap<String, String>();
			Class.forName(ClassForName);
			Connection conn = DriverManager.getConnection(
					jdbc,
					dbUser,password);
			Statement stmt = conn.createStatement();
			//获取课程时间信息
			ResultSet rs = stmt.executeQuery("select course_list.course_id,course_list.course_name"+
					 " FROM course_list,student_course "+
					 " WHERE student_course.stu_id='"+ studentID+
					 "' and course_list.course_id=student_course.course_id"
					  );
			String str = "";
			while(rs.next()){
				//System.out.println(rs.getInt(1)+"\t"
				//		+rs.getDouble(2)+"\t"
				//		+rs.getString(3));
				map.put(rs.getString(1), rs.getString(2));
				if(str.equals("")){
					str = str+rs.getString(1)+","+rs.getString(2);
				}
				else{
					str = str+";"+rs.getString(1)+","+rs.getString(2);
				}
			}
			rs.close();
			stmt.close();
			conn.close();
			return str;
		}	
		/*
		 * @author
		 * 形式参数：无
		 * @return 字符串类型,中间用;字符隔开，前面数字为表示第几周，后面数字表示为第几节课
		 * @param classnum 表示第几周
		 * @param weeknum 表示第几节课
		 */
		public String getWeekAndNum() {
			int classnum=0;//第几节课
			int weeknum=0;//第几周
			int weekday=0;//周几
			weekday = java.util.Calendar.getInstance().get(java.util.Calendar.DAY_OF_WEEK)-1;
			Calendar c = Calendar.getInstance();//可以对每个时间域单独修改
			int year = c.get(Calendar.YEAR); 
			int month = c.get(Calendar.MONTH)+1; 
			int date = c.get(Calendar.DAY_OF_MONTH); 
			int hour = c.get(Calendar.HOUR_OF_DAY); 
			int minute = c.get(Calendar.MINUTE); 
			try{
				/*
			    *获得第几周的部分
			    **/
				SimpleDateFormat dataformat= new SimpleDateFormat("yyyy-MM-dd");
				long to = dataformat.parse(year+"-"+month+"-"+date).getTime();
			    long from = dataformat.parse(termStartTime).getTime();
			    long daysnum=Long.getLong(null, (to - from) / (1000 * 60 * 60 * 24));
			    weeknum=(int) (daysnum/7);
			    /*
			     * 获得第几节课的部分
			     */
			    int summin=hour*60+minute;
			   
			    if(summin<=580&&summin>=480){
			    	classnum=1;
			    }
			    if(summin<=710&&summin>=610){
			    	classnum=2;
			    }
			    if(summin<=940&&summin>=840){
			    	classnum=3;
			    }
			    if(summin<=1060&&summin>=960){
			    	classnum=4;
			    }
			    if(summin<=1260&&summin>1110){
			    	classnum=5;
			    }
			}catch(Exception e){
				e.printStackTrace();
			}
		    
			String result=String.valueOf(weeknum)+";"
							+String.valueOf(weekday)+";"+String.valueOf(classnum);
			return result;
		}
		
		/*public服务验证是否为可签到时间
		 * @author
		 * 形式参数：学号studentID
		 * @return 字符串类型,";"分割，格式为"true/false;courseID;courseName"
		 * @param studentID 表示学生的学号
		 */
		//
		public String getIsSignTime(String studentID) throws Exception{
			String result = ""; //储存返回值		
			//获得每个课程的时间，（周数；时间）
			Class.forName(ClassForName);
			Connection conn = DriverManager.getConnection(
					jdbc,
					dbUser,password);
			Statement stmt = conn.createStatement();
			//获取课程时间信息
			ResultSet rs = stmt.executeQuery("select course_list.course_time,course_list.week_time"
					+ ",course_list.course_id,course_list.course_name"+
					" from course_list,student_course"+
					" where student_course.stu_id='"+studentID+
					"' AND student_course.course_id=course_list.course_id");
			boolean flag = false;
			while(rs.next()){
				//System.out.println(rs.getInt(1)+"\t"
				//		+rs.getDouble(2)+"\t"
				//		+rs.getString(3));
				if(checkTime(rs.getString(1),rs.getString(2))){
					if(getIsHaveSign(studentID, rs.getString(3))){
						return "false;"+rs.getString(3)+";"+rs.getString(4)+"已签";
					}
					return "true;"+rs.getString(3)+";"+rs.getString(4);
				}
			}
			rs.close();
			stmt.close();
			conn.close();
			//检查是否有符合时间的课程
			
			return "false";
		}
		
		/*检查时间是否为合法签到时间
		 * return:boolean
		 * @param String 课程上课周（"1-16"）
		 * @param String 课程上课时间("120;340;")
		 */
		public boolean checkTime(String weekTime,String courseTime){
			int classnum=0;//现在是第几节课
			int weeknum=0;//现在是第几周
			int weekday=0;//现在是周几
			int startWeek =0;//课程起始周
			int endWeek = 0;//课程末周
			//获得现在的时间
			String tStr = getWeekAndNum();
			String[] time = tStr.split(";");
			weeknum = Integer.valueOf(time[0]);
			weekday = Integer.valueOf(time[1]);
			classnum = Integer.valueOf(time[2]);
			time = weekTime.split("-");
			startWeek = Integer.valueOf(time[0]);//课程起始周
			endWeek = Integer.valueOf(time[1]);//课程末周
			time = courseTime.split(";");
			int timeType[] = new int[time.length];
			for(int x=0; x<timeType.length; x++){
				timeType[x] = Integer.valueOf(time[x]);
			}
			//查看周数是否在范围内
			if(!(weeknum>=startWeek &&  weeknum<=endWeek)){
				return false;
			}
			//查看周几是否正确
			int x = 0;
			for(x=0; x<timeType.length; x++){
				if(weekday == timeType[x]/100){
					break;
				}
				else continue;
			}
			if(x == timeType.length) return false;
			//查看第几节是否正确
			for(x=0; x<timeType.length; x++){
				//若不是上课时间（如晚上）
				if(classnum == 0) return false;
				//若为单双周
				if(timeType[x]%10 == 0){
					if(classnum == timeType[x]%100/10) return true;
				}
				//若为单周
				if(timeType[x]%10 == 1){
					if(weeknum/2 !=1) return false;
					if(classnum == timeType[x]%100/10) return true;
				}
				//若为双周
				if(timeType[x]%10 == 2){
					if(weeknum/2 != 0) return false;
					if(classnum == timeType[x]%100/10) return true;
				}
			}
			return false;
		}
		
		
		//查看是否已签到
		public boolean getIsHaveSign(String studentID,String courseID) throws Exception{
			int classnum=0;//现在是第几节课
			int weeknum=0;//现在是第几周
			int weekday=0;//现在是周几
			String signTime = "";
			String tStr = getWeekAndNum();
			String[] time = tStr.split(";");
			weeknum = Integer.valueOf(time[0]);
			weekday = Integer.valueOf(time[1]);
			classnum = Integer.valueOf(time[2]);
			//模拟签到时间
			signTime = String.valueOf(weeknum*100+weekday*10+classnum);
			Class.forName(ClassForName);
			Connection conn = DriverManager.getConnection(
					jdbc,
					dbUser,password);
			Statement stmt = conn.createStatement();
			//获取课程时间信息
			ResultSet rs = stmt.executeQuery("select * FROM student_sign"+
					" WHERE student_sign.stu_id='"+studentID+"'"+
					" AND student_sign.course_id='"+courseID+"'"+
					" AND student_sign.sign_time='"+signTime+"'");
			if(rs.next()){
				rs.close();
				stmt.close();
				conn.close();
				return true;
			}else{
				rs.close();
				stmt.close();
				conn.close();
				return false;
			}
			
		}
		
		/*
		 * 功能：学生签到服务
		 * @return: Boolean 是否签到成功
		 * @param: String studentID
		 * @param: String courseID
		 */
		public String getStudentSign(String studentID,String courseID) throws Exception{
			
			String time = getWeekAndNum();
			String sql = "INSERT INTO student_sign(stu_id,course_id,sign_time)"
					+ "VALUES ("+studentID+","+courseID+","+time+")";
			Class.forName(ClassForName);
			Connection conn = DriverManager.getConnection(jdbc,dbUser,password);
			Statement stmt = conn.createStatement();
			int num = stmt.executeUpdate(sql);
			stmt.close();
			conn.close();
			if(num == 1){
				return "true";
			}else{
				return "false";
			}
		}
		
		
		
}
