package team.family.dbs.servlet;

import team.family.dbs.bean.Dorm;
import team.family.dbs.bean.PageBean;
import team.family.dbs.service.DormService;
import team.family.dbs.service.DormServiceImpl;
import team.family.dbs.util.PropertiesUtil;
import team.family.dbs.util.StringUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class DormServlet extends HttpServlet {

    /**
     *
     */
    DormService dormService = new DormServiceImpl();


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        HttpSession session = request.getSession();
        String s_dormBuildName = request.getParameter("s_dormBuildName");//这个从弄哪里来？？？？
        String page = request.getParameter("page");
        String action = request.getParameter("action");
        Dorm dormBuild = new Dorm();
        if("preSave".equals(action)) {//点击打开保存页面，进行修改信息
            dormBuildPreSave(request, response);
            return;
        } else if("save".equals(action)){//增加宿舍楼或修改宿舍楼信息，进行保存请求
            dormBuildSave(request, response);
            return;
        } else if("delete".equals(action)){//删除宿舍楼请求，删除数据库中的信息即可
            dormBuildDelete(request, response);
            return;
        } else if("manager".equals(action)){//禁止发送这个请求，没有意义！
//            dormBuildManager(request, response);
            return;
        } else if("addManager".equals(action)){
//            dormBuildAddManager(request, response);
        } else if("move".equals(action)){
//            managerMove(request, response);
        } else if("list".equals(action)) {
            if(StringUtil.isNotEmpty(s_dormBuildName)) {
                dormBuild.setDorm_name(s_dormBuildName);
            }
            session.removeAttribute("s_dormBuildName");
            request.setAttribute("s_dormBuildName", s_dormBuildName);
        } else if("search".equals(action)){
            if(StringUtil.isNotEmpty(s_dormBuildName)) {
                dormBuild.setDorm_name(s_dormBuildName);
                session.setAttribute("s_dormBuildName", s_dormBuildName);
            }else {
                session.removeAttribute("s_dormBuildName");
            }
        } else {
            if(StringUtil.isNotEmpty(s_dormBuildName)) {
                dormBuild.setDorm_name(s_dormBuildName);
                session.setAttribute("s_dormBuildName", s_dormBuildName);
            }
            if(StringUtil.isEmpty(s_dormBuildName)) {
                Object o = session.getAttribute("s_dormBuildName");
                if(o!=null) {
                    dormBuild.setDorm_name((String)o);
                }
            }
        }
        if(StringUtil.isEmpty(page)) {
            page="1";
        }
        PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(PropertiesUtil.getValue("pageSize")));
        request.setAttribute("pageSize", pageBean.getPageSize());
        request.setAttribute("page", pageBean.getPage());

        List<Dorm> dormBuildList = dormService.queryAllDormCondition(pageBean,dormBuild);
        System.out.println("返回的楼栋集合如下:" + dormBuildList);
        int total = 0;
        try {
            total = dormBuildList.size();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("@@@@@@@dormBuildList空指针了");
        } finally {

        }
        String pageCode = this.genPagation(total, Integer.parseInt(page), Integer.parseInt(PropertiesUtil.getValue("pageSize")));
        request.setAttribute("pageCode", pageCode);
        request.setAttribute("dormBuildList", dormBuildList);
        request.setAttribute("mainPage", "admin/dormOperation.jsp");
        request.getRequestDispatcher("adminIndex.jsp").forward(request, response);
    }
    private void dormBuildDelete(HttpServletRequest request,
                                 HttpServletResponse response) {
        String dormBuildId = request.getParameter("dormBuildId");//后台数据对应变量dorm_id
        if(dormService.isHaveRoomInDorm(Integer.parseInt(dormBuildId))) {
            request.setAttribute("error", "宿舍楼下有宿舍或宿管，不能删除该宿舍楼");
        } else {
//            dormBuildDao.dormBuildDelete(con, dormBuildId);
            if(dormService.delDormByDorm_id(Integer.parseInt(dormBuildId))){
                System.out.println("删除宿舍楼成功！");
            }
        }
        try {
            request.getRequestDispatcher("dormBuild?action=list").forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void dormBuildSave(HttpServletRequest request,
                               HttpServletResponse response)throws ServletException, IOException {
        String dormBuildId = request.getParameter("dormBuildId");
        String dormBuildNew = request.getParameter("dormBuildNew");
        String dormBuildName = request.getParameter("dormBuildName");
        String remark = request.getParameter("detail");//对应后台数据remark
        Dorm dormBuild = new Dorm(dormBuildName, remark);
        if(StringUtil.isNotEmpty(dormBuildId)) {
            dormBuild.setDorm_id(Integer.parseInt(dormBuildId));
        }
        System.out.println("dormBuildId:" + dormBuildId + "*****" + "dormBuildNew" + dormBuildNew);
        boolean save = false;
        if(StringUtil.isNotEmpty(dormBuildId)) {//如果有则是说明是修改宿舍楼信息
//            save = dormBuildDao.dormBuildUpdate(con, dormBuild);
//            save = dormService.selectDorm_nameByDorm_id(Integer.parseInt(dormBuildId)) != null;
             save = dormService.updateDormByDorm(dormBuild);//如果返回true，则说明更新数据库成功！
            if(save){
                System.out.println("宿舍楼的信息修改成功!");
            }
        } else {//说明是添加，则判断数据库里有没有这个宿舍楼
//            saveNum = dormBuildDao.dormBuildAdd(con, dormBuild);
            dormBuild.setDorm_id(Integer.parseInt(dormBuildNew));
            save = dormService.addDorm(dormBuild);
        }
        if(save) {
            request.getRequestDispatcher("dormBuild?action=list").forward(request, response);
        } else {
            request.setAttribute("dormBuild", dormBuild);
            request.setAttribute("error", "保存失败");
            request.setAttribute("mainPage", "dormBuild/dormOperationSave.jsp");
            request.getRequestDispatcher("adminIndex.jsp").forward(request, response);
        }
    }

    /**
     * 羽保存，就是打开保存页面，如果是修改信息，就将dormBuildId加载到那个请求中，自动填写原来的表单
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void dormBuildPreSave(HttpServletRequest request,
                                  HttpServletResponse response)throws ServletException, IOException {
        String dormBuildId = request.getParameter("dormBuildId");
        if(StringUtil.isNotEmpty(dormBuildId)) {
            Dorm dormBuild = dormService.queryDorm(Integer.parseInt(dormBuildId));
            request.setAttribute("dormBuild", dormBuild);
        }
        request.setAttribute("mainPage", "admin/dormOperationSave.jsp");
        request.getRequestDispatcher("adminIndex.jsp").forward(request, response);
    }

    private String genPagation(int totalNum, int currentPage, int pageSize){
        int totalPage = totalNum%pageSize==0?totalNum/pageSize:totalNum/pageSize+1;
        StringBuffer pageCode = new StringBuffer();
        pageCode.append("<li><a href='dormBuild?page=1'>首页</a></li>");
        if(currentPage==1) {
            pageCode.append("<li class='disabled'><a href='#'>上一页</a></li>");
        }else {
            pageCode.append("<li><a href='dormBuild?page="+(currentPage-1)+"'>上一页</a></li>");
        }
        for(int i=currentPage-2;i<=currentPage+2;i++) {
            if(i<1||i>totalPage) {
                continue;
            }
            if(i==currentPage) {
                pageCode.append("<li class='active'><a href='#'>"+i+"</a></li>");
            } else {
                pageCode.append("<li><a href='dormBuild?page="+i+"'>"+i+"</a></li>");
            }
        }
        if(currentPage==totalPage) {
            pageCode.append("<li class='disabled'><a href='#'>下一页</a></li>");
        } else {
            pageCode.append("<li><a href='dormBuild?page="+(currentPage+1)+"'>下一页</a></li>");
        }
        pageCode.append("<li><a href='dormBuild?page="+totalPage+"'>尾页</a></li>");
        return pageCode.toString();
    }

}
