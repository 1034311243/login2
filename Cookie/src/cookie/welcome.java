package cookie;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/welcome")
public class welcome extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String name = null;
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		// 获取cookie对象
		Cookie[] cookie = request.getCookies();
		Cookie c = null;
		if (cookie != null) {
			// 如果cookie对象不为空，就会获取到cookie的信息，并输出
			for (int i = 0; i < cookie.length; i++) {
				c = cookie[i];
				if (c.getName().equals("username")) {
					out.println(c.getValue() + "您好，欢迎回来！");
				}
				if (c.getName().equals("lastVisited")) {
					out.println("您上次登录时间：" + c.getValue() + "<br>");
				}
			}

		}
		else 
		{
			// 如果cookie对象为空，会从requset中获得信息，并输出
			name = (String) request.getAttribute("name");
			if(name!=null) 
			{
				name = (String) request.getAttribute("name");
				out.println(name + "您好，欢迎回来！");
			}
			else 
			{
				// 如果request的对象为空则会进入error页面
				response.sendRedirect("/Cookie/error");
				//response.sendRedirect("error");
			}
		
		}

		


	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
