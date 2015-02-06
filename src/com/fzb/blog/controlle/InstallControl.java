package com.fzb.blog.controlle;

import java.util.HashMap;
import java.util.Map;

import com.fzb.common.util.InstallUtil;
import com.jfinal.core.Controller;
import com.jfinal.kit.PathKit;

public class InstallControl extends Controller{

	public void testDbConn(){
		Map<String,String> dbConn=new HashMap<String,String>();
	    dbConn.put("jdbcUrl", "jdbc:mysql://"+getPara("dbhost")+":"+getPara("port")+"/"+getPara("dbname")+"?&characterEncoding=UTF-8");
	    dbConn.put("user", getPara("dbuser"));
	    dbConn.put("password", getPara("dbpwd"));
	    dbConn.put("deviceClass", "com.mysql.jdbc.Driver");
		setSessionAttr("dbConn", dbConn);
	    if(new InstallUtil(PathKit.getWebRootPath()+"/WEB-INF",dbConn).testDbConn()){
			 render("/install/message.jsp");
		}
	    else{
	    	setAttr("errorMsg", "请检查数据信息是否正常");
	    	render("/install/index.jsp");
	    }
	}
	public void installJblog(){
		String home = getRequest().getScheme() + "://" + getRequest().getHeader("host") +getRequest().getContextPath()+"/";
		
		Map<String,String> dbConn=getSessionAttr("dbConn");
		Map<String,String> configMsg=new HashMap<String,String>();
	    configMsg.put("webTitle", getPara("webTitle"));
	    configMsg.put("username", getPara("username"));
	    configMsg.put("password", getPara("password"));
	    configMsg.put("email", getPara("email"));
	    configMsg.put("home",home);
	    if(new InstallUtil(PathKit.getWebRootPath()+"/WEB-INF",dbConn,configMsg).installJblog()){
			 render("/install/success.jsp");
		}
		
	}
	
	public void index(){
		render("/install/index.jsp");
	} 
}
