package team.family.dbs.servlet;

import team.family.dbs.bean.Admin;
import team.family.dbs.bean.DormManager;
import team.family.dbs.bean.RepairPeople;
import team.family.dbs.bean.Student;
import team.family.dbs.service.*;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

/*
 * 登录验证，检查账号密码是否输入正确。
 */
public class LoginServlet extends HttpServlet {
    RepairPeopleService repairPeopleService = new RepairPeopleServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        HttpSession session = req.getSession();
        //获取提交的信息。
        String userName = req.getParameter("userName");
        String password = req.getParameter("password");
        String remember = req.getParameter("remember");
        String userType = req.getParameter("userType");
        if("admin".equals(userType)){
            //此时用户为超级管理员
            AdminService adminService = new AdminServiceImpl();
            Admin admin = new Admin(userName, password);
            Admin currentAdmin = adminService.isExistsAdmin(userName,password);
            System.out.println(currentAdmin);
            if(currentAdmin == null) {
                req.setAttribute("admin", admin);
                req.setAttribute("error", "用户名或密码错误！");
                req.getRequestDispatcher("login.jsp").forward(req, resp);
            } else {
                if("remember-me".equals(remember)) {
                    rememberMe(userName, password, userType,resp);
                } else {
                    deleteCookie(userName, req, resp);
                }
                session.setAttribute("currentUserType", "admin");
                session.setAttribute("currentUser", currentAdmin);
                req.setAttribute("mainPage", "admin/welcome.jsp");
                req.getRequestDispatcher("adminIndex.jsp").forward(req, resp);
            }

        }else if("dormManager".equals(userType)){
            //此时用户为宿管
            DormManagerService dormManagerService = new DormManagerServiceImpl();
            DormManager dormManager = new DormManager(userName, password);
            DormManager currentDormManager = dormManagerService.isExists(userName,password);
            if(currentDormManager == null) {
                req.setAttribute("dormManager", dormManager);
                req.setAttribute("error", "用户名或密码错误！");
                req.getRequestDispatcher("login.jsp").forward(req, resp);
            } else {
                if("remember-me".equals(remember)) {
                    rememberMe(userName, password, userType,resp);
                } else {
                    deleteCookie(userName, req, resp);
                }
                session.setAttribute("currentUserType", "dormManager");
                session.setAttribute("currentUser", currentDormManager);
                req.setAttribute("mainPage", "dormManager/welcome.jsp");
                req.getRequestDispatcher("dormManagerIndex.jsp").forward(req, resp);
            }
        }else if("student".equals(userType)){
            //此时用户为学生
            StudentService studentService = new StudentServiceImpl();
            Student student = new Student(userName, password);
            Student currentStudent = studentService.isExistsStudent(userName,password);
            System.out.println(currentStudent);
            if(currentStudent == null) {
                req.setAttribute("student", student);
                req.setAttribute("error", "用户名或密码错误！");
                req.getRequestDispatcher("login.jsp").forward(req, resp);
            } else {
                if("remember-me".equals(remember)) {
                    rememberMe(userName, password, userType,resp);
                } else {
                    deleteCookie(userName, req, resp);
                }
                session.setAttribute("currentUserType", "student");
                session.setAttribute("currentUser", currentStudent);
                req.setAttribute("mainPage", "student/welcome.jsp");
                req.getRequestDispatcher("studentIndex.jsp").forward(req, resp);
            }
        }else if("repairPeople".equals(userType)){
            System.out.println("维修人员登录");
            RepairPeople repairPeople = new RepairPeople(userName, password);
            RepairPeople currentRepairPeople = repairPeopleService.isExists(repairPeople.getWork_id(),repairPeople.getPassword());
            if(currentRepairPeople == null){
                req.setAttribute("repairPeople", repairPeople);
                req.setAttribute("error", "用户名或密码错误！");
                req.getRequestDispatcher("login.jsp").forward(req, resp);
            }else{
                if("remember-me".equals(remember)) {
                    rememberMe(userName, password, userType,resp);
                } else {
                    deleteCookie(userName, req, resp);
                }
                session.setAttribute("currentUserType", "repairPeople");
                session.setAttribute("currentUser", currentRepairPeople);
                req.setAttribute("mainPage", "repairPeople/welcome.jsp");
                req.getRequestDispatcher("repairPeopleIndex.jsp").forward(req, resp);
            }
        }

    }
    private void rememberMe(String userName, String password, String userType, HttpServletResponse response) {
        Cookie user = new Cookie("dormuser", userName+"-"+password+"-"+userType+"-"+"yes");
        user.setMaxAge(1*60*60*24*7);
        response.addCookie(user);
    }
    private void deleteCookie(String userName, HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies=request.getCookies();
        for(int i=0;cookies!=null && i<cookies.length;i++){
            if(cookies[i].getName().equals("dormuser")){
                if(userName.equals(userName=cookies[i].getValue().split("-")[0])) {
                    Cookie cookie = new Cookie(cookies[i].getName(), null);
                    cookie.setMaxAge(0);
                    response.addCookie(cookie);
                    break;
                }
            }
        }
    }
}
