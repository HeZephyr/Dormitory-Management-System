package team.family.dbs.servlet;


import team.family.dbs.bean.Admin;
import team.family.dbs.bean.DormManager;
import team.family.dbs.bean.Student;
import team.family.dbs.service.*;
import team.family.dbs.service.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class PasswordServlet extends HttpServlet {
	AdminService adminService = new AdminServiceImpl();
	DormManagerService dormManagerService = new DormManagerServiceImpl();
	StudentService studentService = new StudentServiceImpl();


	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String action = request.getParameter("action");
		
		if("preChange".equals(action)) {
			passwordPreChange(request, response);
			return;
		} else if("change".equals(action)) {
			passwordChange(request, response);
			return;
		}
	}

	private void passwordChange(HttpServletRequest request,
			HttpServletResponse response)throws ServletException, IOException {
		HttpSession session = request.getSession();
		Object currentUserType = session.getAttribute("currentUserType");
		String oldPassword = request.getParameter("oldPassword");
		String newPassword = request.getParameter("newPassword");

		if("admin".equals((String)currentUserType)) {
			Admin admin = (Admin)(session.getAttribute("currentUser"));

			if(oldPassword.equals(admin.getPassword())) {
				admin.setPassword(newPassword);
				if(adminService.updateAdmin(admin)){
					System.out.println("系统管理员密码修改成功!");
				}else{
					System.out.println("系统管理员密码修改失败!");
				}
				request.setAttribute("oldPassword", oldPassword);
				request.setAttribute("newPassword", newPassword);
				request.setAttribute("rPassword", newPassword);
				request.setAttribute("error", "修改成功 ");
				request.setAttribute("mainPage", "admin/changePassword.jsp");
				request.getRequestDispatcher("adminIndex.jsp").forward(request, response);
			} else {
				request.setAttribute("oldPassword", oldPassword);
				request.setAttribute("newPassword", newPassword);
				request.setAttribute("rPassword", newPassword);
				request.setAttribute("error", "原密码错误");
				request.setAttribute("mainPage", "admin/changePassword.jsp");
				request.getRequestDispatcher("adminIndex.jsp").forward(request, response);
			}
		} else if("dormManager".equals((String)currentUserType)) {
			DormManager manager = (DormManager)(session.getAttribute("currentUser"));
			if(oldPassword.equals(manager.getPassword())) {
				manager.setPassword(newPassword);
				if(dormManagerService.updateDormManager(manager)){
					System.out.println("宿舍管理员:" + manager.getUserName() + " 密码修改成功!");
				}else{
					System.out.println("宿舍管理员:" + manager.getUserName() + " 密码修改失败!");
				}
				request.setAttribute("oldPassword", oldPassword);
				request.setAttribute("newPassword", newPassword);
				request.setAttribute("rPassword", newPassword);
				request.setAttribute("error", "修改成功 ");
				request.setAttribute("mainPage", "dormManager/changePassword.jsp");
				request.getRequestDispatcher("dormManagerIndex.jsp").forward(request, response);
			} else {
				request.setAttribute("oldPassword", oldPassword);
				request.setAttribute("newPassword", newPassword);
				request.setAttribute("rPassword", newPassword);
				request.setAttribute("error", "原密码错误");
				request.setAttribute("mainPage", "dormManager/changePassword.jsp");
				request.getRequestDispatcher("dormManagerIndex.jsp").forward(request, response);
			}
		} else if("student".equals((String)currentUserType)) {
			Student student = (Student)(session.getAttribute("currentUser"));
			if(oldPassword.equals(student.getPassword())) {
				student.setPassword(newPassword);
				if(studentService.updateStudentByStudentId(student)){
					System.out.println("学生:" + student.getUserName() + "修改密码成功!");
				}else{
					System.out.println("学生:" + student.getUserName() + "修改密码失败!");
				}
				request.setAttribute("oldPassword", oldPassword);
				request.setAttribute("newPassword", newPassword);
				request.setAttribute("rPassword", newPassword);
				request.setAttribute("error", "修改成功 ");
				request.setAttribute("mainPage", "student/changePassword.jsp");
				request.getRequestDispatcher("studentIndex.jsp").forward(request, response);
			} else {
				request.setAttribute("oldPassword", oldPassword);
				request.setAttribute("newPassword", newPassword);
				request.setAttribute("rPassword", newPassword);
				request.setAttribute("error", "原密码错误");
				request.setAttribute("mainPage", "student/changePassword.jsp");
				request.getRequestDispatcher("studentIndex.jsp").forward(request, response);
			}
		}

	}

	private void passwordPreChange(HttpServletRequest request,
			HttpServletResponse response)throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		Object currentUserType = session.getAttribute("currentUserType");
		if("admin".equals((String)currentUserType)) {
			request.setAttribute("mainPage", "admin/changePassword.jsp");
			request.getRequestDispatcher("adminIndex.jsp").forward(request, response);
		} else if("dormManager".equals((String)currentUserType)) {
			request.setAttribute("mainPage", "dormManager/changePassword.jsp");
			request.getRequestDispatcher("dormManagerIndex.jsp").forward(request, response);
		} else if("student".equals((String)currentUserType)) {
			request.setAttribute("mainPage", "student/changePassword.jsp");
			request.getRequestDispatcher("studentIndex.jsp").forward(request, response);
		}
	}
	
}
