package team.family.dbs.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class BlankServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		Object currentUserType = session.getAttribute("currentUserType");
		//根据类型切换到对应的界面。设置mainPage属性用来展示首页。
		if("admin".equals((String)currentUserType)) {
			request.setAttribute("mainPage", "admin/welcome.jsp");
			request.getRequestDispatcher("adminIndex.jsp").forward(request, response);
		} else if("dormManager".equals((String)currentUserType)) {
			request.setAttribute("mainPage", "dormManager/welcome.jsp");
			request.getRequestDispatcher("dormManagerIndex.jsp").forward(request, response);
		} else if("student".equals((String)currentUserType)) {
			request.setAttribute("mainPage", "student/welcome.jsp");
			request.getRequestDispatcher("studentIndex.jsp").forward(request, response);
		}else if("repairPeople".equals((String)currentUserType)){
			request.setAttribute("mainPage", "repairPeople/welcome.jsp");
			request.getRequestDispatcher("repairPeopleIndex.jsp").forward(request, response);
		}else{
			request.setAttribute("mainPage", "otherPeople/welcome.jsp");
			request.getRequestDispatcher("applyInIndex.jsp").forward(request, response);
		}
	}

	
}
