package org.backend.servlet;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.backend.domain.Advertise;
import org.backend.service.AdvertiseService;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class UploadServlet extends HttpServlet{
	protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {

		response.setCharacterEncoding("UTF-8"); 
		
        String actionType = request.getParameter("type");
        System.out.println(actionType);

    }	
	
}
