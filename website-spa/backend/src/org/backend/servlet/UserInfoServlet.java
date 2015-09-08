package org.backend.servlet;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.backend.domain.UserInfo;
import org.backend.service.UserInfoService;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class UserInfoServlet extends HttpServlet{
	protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
		//{
		//	type:list/get/insert/delete/update,
		//	params:{
		//		id:0,user:"",pwd:"",email:""		
		//	}
		//}
		response.setCharacterEncoding("UTF-8"); 

        String actionType = request.getParameter("type");
        System.out.println(actionType);
        
        if(actionType.equals("list")){
        	list(request,response);
        }else if(actionType.equals("get")){
        	get(request,response);
        }else if(actionType.equals("insert")){
        	insert(request,response);
        }else if(actionType.equals("delete")){
        	delete(request,response);
        }else if(actionType.equals("update")){
        	update(request,response);
        }else{//error
        	response.getWriter().write("");
        }
    }
	
	private void list(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
		try {            
            UserInfoService userInfoService = new UserInfoService();
            List list = userInfoService.list();
            Gson json = new GsonBuilder()  
        	.setDateFormat("yyyy-MM-dd HH:mm:ss")  
        	.create(); 
            String out = json.toJson(list);
            
            // Process request and render page...
            response.getWriter().write(out);
        }
        catch (Exception ex) {
        	ex.printStackTrace();
        }
	}
	
	private void get(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
		try {
			String data = request.getParameter("data");
	        System.out.println(data);
	        Gson json = new GsonBuilder()  
        	.setDateFormat("yyyy-MM-dd HH:mm:ss")  
        	.create(); 
	        UserInfo userInfo = json.fromJson(data, UserInfo.class);
	        System.out.println(userInfo);
	        int id = userInfo.getId();
	        
            UserInfoService userInfoService = new UserInfoService();
            userInfo = userInfoService.get(id);
            System.out.println(userInfo);
            String out = json.toJson(userInfo);
            
            // Process request and render page...
            response.getWriter().write(out);
            
        }
        catch (Exception ex) {
        	ex.printStackTrace();
        }
	}
	
	private void insert(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
		try {
			String data = request.getParameter("data");
	        System.out.println(data);
	        Gson json = new GsonBuilder()  
        	.setDateFormat("yyyy-MM-dd HH:mm:ss")  
        	.create(); 
	        UserInfo userInfo = json.fromJson(data, UserInfo.class);
            
            UserInfoService userInfoService = new UserInfoService();
            userInfoService.insert(userInfo);

            
            // Process request and render page...
            response.getWriter().write("");
            
        }
        catch (Exception ex) {
        	ex.printStackTrace();
        }
	}
	
	private void delete(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
		try {
            
			String data = request.getParameter("data");
	        System.out.println(data);
	        Gson json = new GsonBuilder()  
        	.setDateFormat("yyyy-MM-dd HH:mm:ss")  
        	.create(); 
	        UserInfo userInfo = json.fromJson(data, UserInfo.class);
	        int id = userInfo.getId();
            
            UserInfoService userInfoService = new UserInfoService();
            userInfoService.delete(id);
            
            // Process request and render page...
            response.getWriter().write("");
            
        }
        catch (Exception ex) {
        	ex.printStackTrace();
        }
	}
	
	private void update(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
		try {
            
			String data = request.getParameter("data");
	        System.out.println(data);
	        Gson json = new GsonBuilder()  
        	.setDateFormat("yyyy-MM-dd HH:mm:ss")  
        	.create(); 
	        UserInfo userInfo = json.fromJson(data, UserInfo.class);
            
            UserInfoService userInfoService = new UserInfoService();
            userInfoService.update(userInfo);

            
            // Process request and render page...
            response.getWriter().write("");
            
        }
        catch (Exception ex) {
        	ex.printStackTrace();
        }
	}
}
