/**
 * <p>Title: LoginInfo.java</p>
 * <p>Description: 记录用户的登陆信息，包括txt_LoginID,txt_Password,cookie,__VIEWSTATE,__EVENTVALIDATION</p>
 * <p>Copyright: Copyright (c) 2011-2012 茗人居</p>
 * <p>Company: 茗人居</p>
 * @author CR
 * @version 1.0 创建时间：2014-6-2 下午12:03:26
 */
public class UserInfo {
	private String txt_LoginID;
	private String txt_Password;
	private String Cookie;
	public String txtBookDate = "";
	//登陆参数
	private String __VIEWSTATE = "/wEPDwUKMTQzNzIwMTA1OA9kFgICAQ9kFgICBw8QZA8WCWYCAQICAgMCBAIFAgYCBwIIFgkQBREt6K+36YCJ5o"
			+ "up5a2m5qChLQUCLTFnEAUU5bGx5Lic5aSn5a2mKOWogea1tykFAjE1ZxAFEueUteWtkOenkeaKgOWkp+WtpgUCMTZnEAUM5Y6m6Zeo5aSn5a2mB"
			+ "QIxN2cQBRLkuK3lm73kurrmsJHlpKflraYFAjIwZxAFEuWMl+S6rOS6pOmAmuWkp+WtpgUCNjBnEAUS5q2m5"
			+ "rGJ55CG5bel5aSn5a2mBQI2M2cQBQ/kuIrmtbfllYblrabpmaIFAjY1ZxAFEuS4reWbveaUv+azleWkp+WtpgUCNjlnZGQYAQUeX19Db250cm9sc1"
			+ "JlcXVpcmVQb3N0QmFja0tleV9fFgEFEGNoa19SZW1QYXNzcHdvcmTyQA+m3XEu7EgOHHNGOfjBnJzW15MsRNz4IPcR1Oc38Q==";
	private String __EVENTVALIDATION = "/wEWBQKnxYuLBAK1lMLgCgLS9cL8AgKA8vrfDwKXzJ6eDz8aY9rKClbofNI6IytLG+5MakTIbQ9gVRkvIkLkN6DJ";
	//阅览室座位列表参数
	private String __VIEWSTATE2 = "/wEPDwUKLTgxNTcwOTg4OQ9kFgICAw9kFgYCBw8QZA8WD2YCAQICAgMCBAIFAgYCBwIIAgkCCgILAgwCDQIOFg8QBR/kuJzp"
			+ "maLkuInmpbzljZfoh6rkuaDlrqQzMTYvMzE3BQYxMDEwMDFnEAUb5Lic6Zmi5Zub5qW85oql5YiK6ZiF6KeI5a6kBQYxMDEwMDJnEAUb5Lic6Zmi5L"
			+ "qM5qW85YyX6Ieq5Lmg5a6kMjA5BQYxMDEwMDNnEAUh6KW/6Zmi5LiJ5qW85Lit5paH5Zu+5Lmm6ZiF6KeI5a6kBQYyMDEwMDFnEAUT6KW/6Zmi5YWt5"
			+ "qW85Y2XUzYwOAUGMjAxMDAyZxAFE+ilv+mZouWFrealvOWNl1M2MDcFBjIwMTAwM2cQBRPopb/pmaLlha3mpbzljZdTNjA2BQYyMDEwMDRnEAUT6K"
			+ "W/6Zmi5LiA5qW85Y2XUzEwMQUGMjAxMDA1ZxAFIeilv+mZouWbm+alvOacn+WIiuaKpeWIiumYheiniOWupAUGMjAxMDA2ZxAFE+ilv+mZouS6lOalv"
			+ "OWNl1M1MDcFBjIwMTAwN2cQBRPopb/pmaLlha3mpbzljZdTNjA1BQYyMDEwMDhnEAUV5L2Z5Yy65LqM5qW86ZiF6KeI5a6kBQYzMDEwMDFnEAUV5L2Z"
			+ "5Yy65LiJ5qW86ZiF6KeI5a6kBQYzMDEwMDJnEAUV5L2Z5Yy65Zub5qW86ZiF6KeI5a6kBQYzMDEwMDNnEAUV5L2Z5Yy65LqU5qW86ZiF6KeI5a6kBQYz"
			+ "MDEwMDRnZGQCCQ8WBB4JaW5uZXJodG1sZR4HVmlzaWJsZWhkAgsPPCsACQBkZDf6uZceYdwnaZ4yn76ETPEqcyrS+UqYNyt7oRm/vhXf";
	private String __EVENTVALIDATION2 = "/wEWBQLa2MTqDQKP25+PBwKozKffBwKe8pjrCQLbhOG0Ak9zNOyU4XDTa7e7DmnRENXCd3rBAs5BjuKYEzstvJJC";	
	//预约座位参数
	private String __VIEWSTATE3 = "";//"/wEPDwUKLTg4NjQwNjU4Mw9kFgICAw9kFgQCAQ9kFggCAQ8WAh4JaW5uZXJodG1sBRXkvZnljLrkuozmpbzpmIXop4jlrqRkAgMPFgIfAAUDMDAxZAIFDxYCHwAFCTIwMTQvNC8xMWQCBw8WAh8ABQQ4OjAwZAIDD2QWAgIDDxYCHwAFAzAwMWRkaP26z/jxKz1TnXiJ80trfmnoC+dTXZ1Vjd+ryFBHFHU=";
	private String __EVENTVALIDATION3 = "";//"/wEWAgLlq/jUAgLCi9reA3Qp22av3g52FJmXvsxrYo1ZekiSb90qqKH56q+N1649";	
	private String selSchool = "63";
	
	//getter and setter
	public void setTxt_LoginID(String txt_LoginID){
		this.txt_LoginID = txt_LoginID;
	}
	
	public void setTxt_Password(String txt_Password){
		this.txt_Password = txt_Password;
	}
	
	public void setCookie(String Cookie){
		this.Cookie = Cookie;
	}
	
	public void set__VIEWSTATE(String __VIEWSTATE){
		this.__VIEWSTATE = __VIEWSTATE;
	}
	
	public void set__EVENTVALIDATION(String __EVENTVALIDATION){
		this.__EVENTVALIDATION = __EVENTVALIDATION;
	}
	public void set__VIEWSTATE3(String __VIEWSTATE3){
		this.__VIEWSTATE3 = __VIEWSTATE3;
	}
	
	public void set__EVENTVALIDATION3(String __EVENTVALIDATION3){
		this.__EVENTVALIDATION3 = __EVENTVALIDATION3;
	}
	
	public void setSelSchool(String selSchool){
		this.selSchool = selSchool;
	}
	
	public String getTxt_LoginID(){
		return txt_LoginID;
	}
	
	public String getTxt_Password(){
		return txt_Password;
	}
	
	public String getCookie(){
		return  Cookie;
	}
	
	public String get__VIEWSTATE(){
		return __VIEWSTATE;
	}
	
	public String get__EVENTVALIDATION(){
		return __EVENTVALIDATION;
	}
	public String get__VIEWSTATE2(){
		return __VIEWSTATE;
	}
	
	public String get__EVENTVALIDATION2(){
		return __EVENTVALIDATION;
	}
	
	public String get__VIEWSTATE3(){
		return __VIEWSTATE3;
	}
	
	public String get__EVENTVALIDATION3(){
		return __EVENTVALIDATION3;
	}
	
	public String getSelSchool(){
		return selSchool;
	}
}
