package org.backend.servlet;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.backend.domain.DynamicMesg;
import org.backend.service.DynamicMesgService;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class DynamicMesgServlet extends HttpServlet{
	protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
		//{
		//	type:list/get/insert/delete/update,
		//	params:{
		//		id:0,title:"",content:"",date_time:""	
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
        	response.getWriter().write("error");
        }
    }
	
	private void list(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
		try {            
            DynamicMesgService dynamicMesgService = new DynamicMesgService();
            List list = dynamicMesgService.list();
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
	        DynamicMesg dynamicMesg = json.fromJson(data, DynamicMesg.class);
	        System.out.println(dynamicMesg);
	        int id = dynamicMesg.getId();
	        
            DynamicMesgService dynamicMesgService = new DynamicMesgService();
            dynamicMesg = dynamicMesgService.get(id);
            System.out.println(dynamicMesg);
            String out = json.toJson(dynamicMesg);
            
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
	        DynamicMesg dynamicMesg = json.fromJson(data, DynamicMesg.class);
            
            DynamicMesgService dynamicMesgService = new DynamicMesgService();
            dynamicMesgService.insert(dynamicMesg);

            
            // Process request and render page...
            response.getWriter().write("ok");
            
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
	        DynamicMesg dynamicMesg = json.fromJson(data, DynamicMesg.class);
	        int id = dynamicMesg.getId();
            
            DynamicMesgService dynamicMesgService = new DynamicMesgService();
            dynamicMesgService.delete(id);
            
            // Process request and render page...
            response.getWriter().write("ok");
            
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
	        DynamicMesg dynamicMesg = json.fromJson(data, DynamicMesg.class);
            
            DynamicMesgService dynamicMesgService = new DynamicMesgService();
            dynamicMesgService.update(dynamicMesg);

            
            // Process request and render page...
            response.getWriter().write("ok");
            
        }
        catch (Exception ex) {
        	ex.printStackTrace();
        }
	}
}
