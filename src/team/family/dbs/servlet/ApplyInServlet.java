package team.family.dbs.servlet;

import team.family.dbs.bean.ApplyInRecord;
import team.family.dbs.bean.Dorm;
import team.family.dbs.service.ApplyInRecordService;
import team.family.dbs.service.ApplyInRecordServiceImpl;
import team.family.dbs.service.DormService;
import team.family.dbs.service.DormServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ApplyInServlet extends HttpServlet {
    ApplyInRecordService applyInRecordService = new ApplyInRecordServiceImpl();
    DormService dormService = new DormServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //处理游客访问登记
        //1/获取表单对象

        request.setCharacterEncoding("utf-8");
        String action = request.getParameter("action");
        if("save".equals(action)){
            //当期为登记对象，将对象写进数据库
            String applyNameNew = request.getParameter("applyNameNew");
            String dest_visit_drom_idNew = request.getParameter("dest_visit_drom_idNew");
            String visit_timeNew = request.getParameter("visit_timeNew");
            String visitDestNew = request.getParameter("visitDestNew");
            Dorm dorm = dormService.queryDorm(Integer.parseInt(dest_visit_drom_idNew));
            ApplyInRecord applyInRecord = new ApplyInRecord(applyNameNew, dorm.getDorm_id(),dorm.getDorm_name() , visitDestNew,visit_timeNew);
            if(applyInRecordService.addVisitRecord(applyInRecord)){
                System.out.println("访问记录添加成功!");

                request.setAttribute("code",new String("<script type=\"text/javascript\">alertSuccess()</script>"));
//                request.getRequestDispatcher("otherPeople/applyInOperation.jsp").forward(request, response);
                //#
                request.setAttribute("mainPage", "otherPeople/applyInOperation.jsp");
                request.getRequestDispatcher("applyInIndex.jsp").forward(request, response);
            }else{
                System.out.println("访问记录添加失败!");
                request.setAttribute("code",new String("<script type=\"text/javascript\">alertFalse()</script>"));
//                request.getRequestDispatcher("otherPeople/applyInOperation.jsp").forward(request, response);
                request.setAttribute("mainPage", "otherPeople/applyInOperation.jsp");
                request.getRequestDispatcher("applyInIndex.jsp").forward(request, response);
            }
        }else if("list".equals(action)){
            //当前是获取访问登记记录列表。
            List<ApplyInRecord> allVisitRecord = applyInRecordService.getAllVisitRecord();
            System.out.println("所有的访客记录都在在这：" + allVisitRecord);
            request.setAttribute("allVisitRecordList",allVisitRecord);
            request.setAttribute("mainPage", "dormManager/applyInRecord.jsp");
            request.getRequestDispatcher("dormManagerIndex.jsp").forward(request, response);

        }else if("toApplyRecord".equals(action)){
            List<Dorm> dorms = dormService.queryAllDorm();
            request.setAttribute("dorms",dorms);
            //让他跳转到指定填写界面
            request.setAttribute("mainPage", "otherPeople/applyInOperation.jsp");
            request.getRequestDispatcher("applyInIndex.jsp").forward(request, response);
        }else if("returnLogin".equals(action)){
            response.sendRedirect("login.jsp");
        }else if("del".equals(action)){
            String applyIdQuery = request.getParameter("applyIdQuery");
            if(applyInRecordService.delVisitRecord(Integer.parseInt(applyIdQuery))){
                System.out.println("访问登记删除成功!");
                List<ApplyInRecord> allVisitRecord = applyInRecordService.getAllVisitRecord();
                request.setAttribute("allVisitRecordList",allVisitRecord);
                request.setAttribute("mainPage", "dormManager/applyInRecord.jsp");
                request.getRequestDispatcher("dormManagerIndex.jsp").forward(request, response);
            }else{
                System.out.println("访问登记删除失败");
                request.setAttribute("mainPage", "dormManager/applyInRecord.jsp");
                request.getRequestDispatcher("dormManagerIndex.jsp").forward(request, response);
            }
        }
    }
}
