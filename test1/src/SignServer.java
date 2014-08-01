

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
	//private String courseTime = null; //�������˿γ��Ͽ���ʱ���ݣ���ʽΪ��1-n�ܣ�
		//private String weekTime = null; //�������˿γ�ÿ���Ͽ�ʱ�����ݣ���ʽΪ��150;251��
		//private String courseSignHistory[] = null;   //ǩ����¼
		//private int currentWeek = 0;  //���ڵ�����
		//private int currentTime = 0;        //�����ܼ�������
		//private String courseList[][] = null;
	private String ClassForName = "com.mysql.jdbc.Driver";
	private String jdbc = "jdbc:mysql://127.0.0.1:3306/cwj";
	private String dbUser = "root";
	private String password = "123456";
	
	private String termStartTime = "2014-2-16";
	

	
	
	private int termStartWeek = 0;     //ѧ�ڵ���ʼ���Ǳ���ڼ���
	
	public SignServer(){  
		try {
			setTermStartTime("2013-09-01");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
		//public���񣬷���ĳѧ��ĳ�γ̵�ǩ����¼
		public String getStudentCourseSignData(String studentID,String courseID)
				throws Exception{   //��ȡѧ��ĳ�γ�ǩ����Ϣ
			String courseSignData[] = new String[5];  //�洢��Ϣ���ظ��ͻ���
			String courseTime = null; //�������˿γ��Ͽ���ʱ���ݣ���ʽΪ��1-n�ܣ�
			String weekTime = null; //�������˿γ�ÿ���Ͽ�ʱ�����ݣ���ʽΪ��150;251��
			String courseSignHistory = null;   //ǩ����¼
			courseSignData[3] = getCurrentWeek();
			String tmp = getCurrentTime();
			courseSignData[4] = tmp.substring(0, tmp.length()-1);
			Class.forName(ClassForName);
			Connection conn = DriverManager.getConnection(
					jdbc,
					dbUser,password);
			Statement stmt = conn.createStatement();
			//��ȡ�γ�ʱ����Ϣ
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
			//��ȡѧ��ǩ����Ϣ
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
		
		public String getCurrentWeek(){   //��ȡ�����ǵڼ���
			return "8";
		}
		
		public String getCurrentTime(){   //��ȡ�������ܼ�(��ʽ��12����һ��һ�ڣ�)
			return "52";
		}
		
		//��ʱ��ת��Ϊ����
		public int timeToWeekNum(){
			return 1;
		}
		//��ʱ��ת��Ϊ�ܼ��ĵڼ��ڿ�
		public int timeToCNum(){
			return 0;    //0Ϊ��ǩ��ʱ��
		}
		
		
		public void setTermStartTime(String startTime) throws ParseException{
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date startDate = (Date)format.parse(startTime);
			Calendar calendar = Calendar.getInstance();
			calendar.setFirstDayOfWeek(Calendar.MONDAY);
			calendar.setTime(startDate);
			termStartWeek = calendar.get(Calendar.WEEK_OF_YEAR);
			
		}
		
		//��ȡĳѧ���γ��б�
		public String getStudentCourseList(String studentID) throws Exception{
			Map<String,String> map = new HashMap<String, String>();
			Class.forName(ClassForName);
			Connection conn = DriverManager.getConnection(
					jdbc,
					dbUser,password);
			Statement stmt = conn.createStatement();
			//��ȡ�γ�ʱ����Ϣ
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
		 * ��ʽ��������
		 * @return �ַ�������,�м���;�ַ�������ǰ������Ϊ��ʾ�ڼ��ܣ��������ֱ�ʾΪ�ڼ��ڿ�
		 * @param classnum ��ʾ�ڼ���
		 * @param weeknum ��ʾ�ڼ��ڿ�
		 */
		public String getWeekAndNum() {
			int classnum=0;//�ڼ��ڿ�
			int weeknum=0;//�ڼ���
			int weekday=0;//�ܼ�
			weekday = java.util.Calendar.getInstance().get(java.util.Calendar.DAY_OF_WEEK)-1;
			Calendar c = Calendar.getInstance();//���Զ�ÿ��ʱ���򵥶��޸�
			int year = c.get(Calendar.YEAR); 
			int month = c.get(Calendar.MONTH)+1; 
			int date = c.get(Calendar.DAY_OF_MONTH); 
			int hour = c.get(Calendar.HOUR_OF_DAY); 
			int minute = c.get(Calendar.MINUTE); 
			try{
				/*
			    *��õڼ��ܵĲ���
			    **/
				SimpleDateFormat dataformat= new SimpleDateFormat("yyyy-MM-dd");
				long to = dataformat.parse(year+"-"+month+"-"+date).getTime();
			    long from = dataformat.parse(termStartTime).getTime();
			    long daysnum=Long.getLong(null, (to - from) / (1000 * 60 * 60 * 24));
			    weeknum=(int) (daysnum/7);
			    /*
			     * ��õڼ��ڿεĲ���
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
		
		/*public������֤�Ƿ�Ϊ��ǩ��ʱ��
		 * @author
		 * ��ʽ������ѧ��studentID
		 * @return �ַ�������,";"�ָ��ʽΪ"true/false;courseID;courseName"
		 * @param studentID ��ʾѧ����ѧ��
		 */
		//
		public String getIsSignTime(String studentID) throws Exception{
			String result = ""; //���淵��ֵ		
			//���ÿ���γ̵�ʱ�䣬��������ʱ�䣩
			Class.forName(ClassForName);
			Connection conn = DriverManager.getConnection(
					jdbc,
					dbUser,password);
			Statement stmt = conn.createStatement();
			//��ȡ�γ�ʱ����Ϣ
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
						return "false;"+rs.getString(3)+";"+rs.getString(4)+"��ǩ";
					}
					return "true;"+rs.getString(3)+";"+rs.getString(4);
				}
			}
			rs.close();
			stmt.close();
			conn.close();
			//����Ƿ��з���ʱ��Ŀγ�
			
			return "false";
		}
		
		/*���ʱ���Ƿ�Ϊ�Ϸ�ǩ��ʱ��
		 * return:boolean
		 * @param String �γ��Ͽ��ܣ�"1-16"��
		 * @param String �γ��Ͽ�ʱ��("120;340;")
		 */
		public boolean checkTime(String weekTime,String courseTime){
			int classnum=0;//�����ǵڼ��ڿ�
			int weeknum=0;//�����ǵڼ���
			int weekday=0;//�������ܼ�
			int startWeek =0;//�γ���ʼ��
			int endWeek = 0;//�γ�ĩ��
			//������ڵ�ʱ��
			String tStr = getWeekAndNum();
			String[] time = tStr.split(";");
			weeknum = Integer.valueOf(time[0]);
			weekday = Integer.valueOf(time[1]);
			classnum = Integer.valueOf(time[2]);
			time = weekTime.split("-");
			startWeek = Integer.valueOf(time[0]);//�γ���ʼ��
			endWeek = Integer.valueOf(time[1]);//�γ�ĩ��
			time = courseTime.split(";");
			int timeType[] = new int[time.length];
			for(int x=0; x<timeType.length; x++){
				timeType[x] = Integer.valueOf(time[x]);
			}
			//�鿴�����Ƿ��ڷ�Χ��
			if(!(weeknum>=startWeek &&  weeknum<=endWeek)){
				return false;
			}
			//�鿴�ܼ��Ƿ���ȷ
			int x = 0;
			for(x=0; x<timeType.length; x++){
				if(weekday == timeType[x]/100){
					break;
				}
				else continue;
			}
			if(x == timeType.length) return false;
			//�鿴�ڼ����Ƿ���ȷ
			for(x=0; x<timeType.length; x++){
				//�������Ͽ�ʱ�䣨�����ϣ�
				if(classnum == 0) return false;
				//��Ϊ��˫��
				if(timeType[x]%10 == 0){
					if(classnum == timeType[x]%100/10) return true;
				}
				//��Ϊ����
				if(timeType[x]%10 == 1){
					if(weeknum/2 !=1) return false;
					if(classnum == timeType[x]%100/10) return true;
				}
				//��Ϊ˫��
				if(timeType[x]%10 == 2){
					if(weeknum/2 != 0) return false;
					if(classnum == timeType[x]%100/10) return true;
				}
			}
			return false;
		}
		
		
		//�鿴�Ƿ���ǩ��
		public boolean getIsHaveSign(String studentID,String courseID) throws Exception{
			int classnum=0;//�����ǵڼ��ڿ�
			int weeknum=0;//�����ǵڼ���
			int weekday=0;//�������ܼ�
			String signTime = "";
			String tStr = getWeekAndNum();
			String[] time = tStr.split(";");
			weeknum = Integer.valueOf(time[0]);
			weekday = Integer.valueOf(time[1]);
			classnum = Integer.valueOf(time[2]);
			//ģ��ǩ��ʱ��
			signTime = String.valueOf(weeknum*100+weekday*10+classnum);
			Class.forName(ClassForName);
			Connection conn = DriverManager.getConnection(
					jdbc,
					dbUser,password);
			Statement stmt = conn.createStatement();
			//��ȡ�γ�ʱ����Ϣ
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
		 * ���ܣ�ѧ��ǩ������
		 * @return: Boolean �Ƿ�ǩ���ɹ�
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
