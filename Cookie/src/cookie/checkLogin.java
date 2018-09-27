package cookie;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/checkLogin")
public class checkLogin extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String name = request.getParameter("name");
		String pwd = request.getParameter("password");
		String flag = request.getParameter("remember");

		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		// 检查用户名是否正确 不正确会直接跳到error页面
		if ("zhangsan".equals(name) && "123".equals(pwd)) {
			// 如果正确会检查是否点击记住我
			if (flag!=null) {
				// 如果点击记住我 就会创建一个cookie并记录,并将cookie添加到response里面
				Cookie cookie1 = new Cookie("username", name);
				cookie1.setMaxAge(30);
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd|HH:mm:ss");
				Cookie cookie2 = new Cookie("lastVisited", sdf.format(new java.util.Date()));
				cookie2.setMaxAge(30);
				// 发送
				response.addCookie(cookie1);
				response.addCookie(cookie2);

			}
			
			request.setAttribute("name", name);

			request.getRequestDispatcher("/welcome").forward(request, response);
		} else {
			// 跳转到登录页面
			response.sendRedirect("/Cookie/login.html");
			// response.sendRedirect("login.html");

		}

		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
