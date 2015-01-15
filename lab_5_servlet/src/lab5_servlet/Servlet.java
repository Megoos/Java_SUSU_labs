package lab5_servlet;

import java.text.DateFormat;
import java.util.Calendar;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.*;
import javax.servlet.http.*;

public class Servlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		List<String> template = Files.readAllLines(Paths.get("src\\web\\time.html"));
		
		for(String s:template) {
			out.println(s.replaceAll("%MESSAGE%", GetCurrentDateTime()));
		}			
	}
	
	public String GetCurrentDateTime() {
	   DateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss.SSS");
	   Calendar cal = Calendar.getInstance();
	   return dateFormat.format(cal.getTime());
	}
}