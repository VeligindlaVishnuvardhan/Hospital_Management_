package com.org.servlet.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionIdListener;

import com.org.dao.UserDao;
import com.org.entity.User;

@WebServlet("/userLogin")
public class UserLogin extends HttpServlet
{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email=req.getParameter("email");
		String password=req.getParameter("password");
		
		UserDao dao=new UserDao();
		User u=dao.logIn(email, password);
		HttpSession hs=req.getSession();
	if(u!=null)
	{
//		System.out.println("success");
		hs.setAttribute("userObj", u);
		resp.sendRedirect("index.jsp");
	}
	else {
//		System.out.println("failed");
		hs.setAttribute("errorMsg", "invalid user or password");
		resp.sendRedirect("user_login.jsp");
	}
	}	
}
