package team.family.dbs.servlet;

import team.family.dbs.bean.DormManager;
import team.family.dbs.bean.PageBean;
import team.family.dbs.service.DormManagerService;
import team.family.dbs.service.DormManagerServiceImpl;
import team.family.dbs.util.PropertiesUtil;
import team.family.dbs.util.StringUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DormManagerServlet extends HttpServlet{
	//宿管管理对象。
	DormManagerService dormManagerService = new DormManagerServiceImpl();
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//设置编码格式
		request.setCharacterEncoding("utf-8");
		//获取各类参数。
		HttpSession session = request.getSession();
		String s_dormManagerText = request.getParameter("s_dormManagerText");
		String searchType = request.getParameter("searchType");
		String page = request.getParameter("page");
		//获取动作类型，即要采取什么样的行为。
		String action = request.getParameter("action");
		DormManager dormManager = new DormManager();
		if("preSave".equals(action)) {
			//调用添加方法，完成添加请求。
			dormManagerPreSave(request, response);
			return;
		} else if("save".equals(action)){
			dormManagerSave(request, response);
			return;
		} else if("delete".equals(action)){ //处理超级管理员的删除请求
			System.out.println("删除");
			dormManagerDelete(request, response);
			return;
		} else if("list".equals(action)) {
			//获取管理员列表。
			if(StringUtil.isNotEmpty(s_dormManagerText)) {
				//按姓名或者用户名查。
				if("name".equals(searchType)) {
					dormManager.setUserName(s_dormManagerText);
				} else if("userName".equals(searchType)) {
					dormManager.setDormmasterId(s_dormManagerText);
				}
			}
			//移除session的属性值放入request中去，避免下次请求干扰。
			session.removeAttribute("s_dormManagerText");
			session.removeAttribute("searchType");
			request.setAttribute("s_dormManagerText", s_dormManagerText);
			request.setAttribute("searchType", searchType);
		} else if("search".equals(action)){
			//查询操作。
			if (StringUtil.isNotEmpty(s_dormManagerText)) {
				//按姓名查。
				if ("name".equals(searchType)) {
					dormManager.setUserName(s_dormManagerText);
				} else if ("userName".equals(searchType)) {
					dormManager.setDormmasterId(s_dormManagerText);
				}
				session.setAttribute("searchType", searchType);
				session.setAttribute("s_dormManagerText", s_dormManagerText);
			} else {
				session.removeAttribute("s_dormManagerText");
				session.removeAttribute("searchType");
			}
		} else {
			if(StringUtil.isNotEmpty(s_dormManagerText)) {
				if("name".equals(searchType)) {
					dormManager.setUserName(s_dormManagerText);
				} else if("userName".equals(searchType)) {
					dormManager.setDormmasterId(s_dormManagerText);
				}
				session.setAttribute("searchType", searchType);
				session.setAttribute("s_dormManagerText", s_dormManagerText);
			}
			if(StringUtil.isEmpty(s_dormManagerText)) {
				Object o1 = session.getAttribute("s_dormManagerText");
				Object o2 = session.getAttribute("searchType");
				if(o1!=null) {
					if("name".equals((String)o2)) {
						dormManager.setUserName((String)o1);
					} else if("userName".equals((String)o2)) {
						dormManager.setDormmasterId((String)o1);
					}
				}
			}
		}
		//判断page是否为空，使其至少为1。
		if(StringUtil.isEmpty(page)) {
			page="1";
		}
		PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(PropertiesUtil.getValue("pageSize")));
		request.setAttribute("pageSize", pageBean.getPageSize());
		request.setAttribute("page", pageBean.getPage());
		//查询宿管信息存储到list容器中。
		List<DormManager> dormManagers = dormManagerService.queryAllDormManager();
		String pageCode = this.genPagation(dormManagers.size(), Integer.parseInt(page), Integer.parseInt(PropertiesUtil.getValue("pageSize")));
		//将这些放入request内置对象中存储。
		request.setAttribute("pageCode", pageCode);
		request.setAttribute("dormManagers", dormManagers);
		request.setAttribute("mainPage", "admin/dormManagerOperation.jsp");
		//通过此方法实现转发，并且传递一些数据参数。
		request.getRequestDispatcher("adminIndex.jsp").forward(request, response);
	}

	private void dormManagerDelete(HttpServletRequest request,
			HttpServletResponse response) {
		String dormManagerId = request.getParameter("dormManagerId");
		System.out.println("齐纳方高能" +dormManagerId);
		if(dormManagerService.delDormManager(dormManagerId)){
			System.out.println("宿管删除成功!");
		}
		try {
			request.getRequestDispatcher("dormManager?action=list").forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void dormManagerSave(HttpServletRequest request,
			HttpServletResponse response)throws ServletException, IOException {
		//获取相应参数。
		String dormManagerId = request.getParameter("dormManagerId");
		String dormmasterId = request.getParameter("dormmasterId");
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		String sex = request.getParameter("sex");
		String tel = request.getParameter("tel");
		int dorm_id = Integer.parseInt(request.getParameter("dorm_id"));
		boolean saveStatue = false;
		//生成两个相同的宿管对象。
		DormManager dormManager = new DormManager(dormManagerId, userName, password, sex, tel,dorm_id,0);
		DormManager dormManager1 = new DormManager(dormmasterId, userName, password, sex, tel,dorm_id,0);
		if(StringUtil.isNotEmpty(dormManagerId)) {
			//进行修改操作
			saveStatue = dormManagerService.updateDormManager(dormManager);
			if(saveStatue){
				System.out.println("???????????????宿管更新完成");
			}
		} else if(dormManagerService.isExists(dormManager.getDormmasterId(),dormManager.getPassword()) != null){
			//判断是否存在相同的。
			request.setAttribute("dormManager", dormManager);
			request.setAttribute("error", "该用户名已存在");
			request.setAttribute("mainPage", "admin/dormManagerOperationSave.jsp");
			//又跳转回保存界面
			request.getRequestDispatcher("adminIndex.jsp").forward(request, response);
			return;
		} else {
			//添加宿管。
			saveStatue = dormManagerService.addDormManager(dormManager1);
			if(saveStatue){
				System.out.println("添加宿管成功!");
			}else{
				System.out.println("添加宿管失败!");
			}
		}
		//根据状态输出信息。
		if(saveStatue) {
				request.getRequestDispatcher("dormManager?action=list").forward(request, response);
		} else {
				request.setAttribute("dormManager", dormManager);
				//设置错误提示信息。
				request.setAttribute("error", "保存失败");
				request.setAttribute("mainPage", "admin/dormManagerOperationSave.jsp");
				request.getRequestDispatcher("adminIndex.jsp").forward(request, response);
		}
	}

	private void dormManagerPreSave(HttpServletRequest request,
			HttpServletResponse response)throws ServletException, IOException {
		String dormManagerId = request.getParameter("dormManagerId");
		String password = request.getParameter("password");
		if(StringUtil.isNotEmpty(dormManagerId)) {
			DormManager dormManager = dormManagerService.isExists(dormManagerId,password);
			request.setAttribute("dormManager", dormManager);
		}
		request.setAttribute("mainPage", "admin/dormManagerOperationSave.jsp");
		//跳转界面，传递参数过去。
		request.getRequestDispatcher("adminIndex.jsp").forward(request, response);
	}

	//分页部分。
	private String genPagation(int totalNum, int currentPage, int pageSize){
		//计算需要多少页。
		int totalPage = totalNum % pageSize == 0 ? totalNum/pageSize : totalNum / pageSize + 1;
		StringBuffer pageCode = new StringBuffer();
		pageCode.append("<li><a href='dormManager?page=1'>首页</a></li>");
		//根据当前所处页面分配对应的li。
		if(currentPage == 1) {
			pageCode.append("<li class='disabled'><a href='#'>上一页</a></li>");
		}else {
			pageCode.append("<li><a href='dormManager?page="+(currentPage-1)+"'>上一页</a></li>");
		}
		for(int i = currentPage - 2; i <= currentPage + 2; ++ i) {
			if(i < 1 || i > totalPage) {
				continue;
			}
			if(i == currentPage) {
				pageCode.append("<li class='active'><a href='#'>"+i+"</a></li>");
			} else {
				pageCode.append("<li><a href='dormManager?page="+i+"'>"+i+"</a></li>");
			}
		}
		if(currentPage==totalPage) {
			pageCode.append("<li class='disabled'><a href='#'>下一页</a></li>");
		} else {
			pageCode.append("<li><a href='dormManager?page="+(currentPage+1)+"'>下一页</a></li>");
		}
		pageCode.append("<li><a href='dormManager?page="+totalPage+"'>尾页</a></li>");
		return pageCode.toString();
	}
	
}
