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
		// ��ȡcookie����
		Cookie[] cookie = request.getCookies();
		Cookie c = null;
		if (cookie != null) {
			// ���cookie����Ϊ�գ��ͻ��ȡ��cookie����Ϣ�������
			for (int i = 0; i < cookie.length; i++) {
				c = cookie[i];
				if (c.getName().equals("username")) {
					out.println(c.getValue() + "���ã���ӭ������");
				}
				if (c.getName().equals("lastVisited")) {
					out.println("���ϴε�¼ʱ�䣺" + c.getValue() + "<br>");
				}
			}

		}
		else 
		{
			// ���cookie����Ϊ�գ����requset�л����Ϣ�������
			name = (String) request.getAttribute("name");
			if(name!=null) 
			{
				name = (String) request.getAttribute("name");
				out.println(name + "���ã���ӭ������");
			}
			else 
			{
				// ���request�Ķ���Ϊ��������errorҳ��
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
