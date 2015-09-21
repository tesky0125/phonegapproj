package org.backend.servlet;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.backend.domain.RecruitInfo;
import org.backend.service.RecruitInfoService;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class RecruitInfoServlet extends HttpServlet{
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
            RecruitInfoService recruitInfoService = new RecruitInfoService();
            List list = recruitInfoService.list();
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
	        RecruitInfo recruitInfo = json.fromJson(data, RecruitInfo.class);
	        System.out.println(recruitInfo);
	        int id = recruitInfo.getId();
	        
            RecruitInfoService recruitInfoService = new RecruitInfoService();
            recruitInfo = recruitInfoService.get(id);
            System.out.println(recruitInfo);
            String out = json.toJson(recruitInfo);
            
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
	        RecruitInfo recruitInfo = json.fromJson(data, RecruitInfo.class);
            
            RecruitInfoService recruitInfoService = new RecruitInfoService();
            recruitInfoService.insert(recruitInfo);

            
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
	        RecruitInfo recruitInfo = json.fromJson(data, RecruitInfo.class);
	        int id = recruitInfo.getId();
            
            RecruitInfoService recruitInfoService = new RecruitInfoService();
            recruitInfoService.delete(id);
            
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
	        RecruitInfo recruitInfo = json.fromJson(data, RecruitInfo.class);
            
            RecruitInfoService recruitInfoService = new RecruitInfoService();
            recruitInfoService.update(recruitInfo);

            
            // Process request and render page...
            response.getWriter().write("ok");
            
        }
        catch (Exception ex) {
        	ex.printStackTrace();
        }
	}
}
