package team.family.dbs.servlet;

import team.family.dbs.bean.RepairPeople;
import team.family.dbs.bean.RepairRecord;
import team.family.dbs.service.RepairRecordService;
import team.family.dbs.service.RepairRecordServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class RepairRecordServlet extends HttpServlet {
    RepairRecordService repairRecordService = new RepairRecordServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");//设置当前接收用户的字符集编码

        HttpSession session = request.getSession();
        Object currentUserType = session.getAttribute("currentUserType");//获取当前用户类型
        String action = request.getParameter("action");
        String record_id = request.getParameter("record_id");
        //接收用户对象
        RepairPeople repairPeople = (RepairPeople) request.getAttribute("repairPeople");
        if("list".equals(action)){
            //当前请求是请求列表
            //前端页面中repairRecordList
            List<RepairRecord> allNotDealRecord = repairRecordService.getAllNotDealRecord();
            System.out.println("维修单列表:" + allNotDealRecord);
            request.setAttribute("repairRecordList",allNotDealRecord);
            request.setAttribute("mainPage", "repairPeople/repairRecord.jsp");
            request.getRequestDispatcher("repairPeopleIndex.jsp").forward(request, response);
        }else if("del".equals(action)){
            //当前请求是处理维修事物
            if(repairRecordService.dealRepair(Integer.parseInt(record_id))){
                System.out.println("维修成功！");
                request.setAttribute("repairRecordList",repairRecordService.getAllNotDealRecord());
                request.setAttribute("mainPage", "repairPeople/repairRecord.jsp");
                request.getRequestDispatcher("repairPeopleIndex.jsp").forward(request, response);
            }

        }else if("addRepair".equals(action)){
            //表示学生请求填写表单
            //让其直接跳转到表单填写
            request.setAttribute("mainPage", "student/roomApplyRecord.jsp");
            request.getRequestDispatcher("repairPeopleIndex.jsp").forward(request, response);
        }else if("studentRepairList".equals(action)){
            //请求打开宿舍维修请求列表
            List<RepairRecord> allDealRecord = repairRecordService.getAllDealRecord();
            System.out.println("学生端所有的维修记录:" + allDealRecord);
            request.setAttribute("repairRecordStudentList",allDealRecord);
            request.setAttribute("mainPage", "student/repairRecord.jsp");
            request.getRequestDispatcher("studentIndex.jsp").forward(request, response);
        }else if("save".equals(action)){
            //读取request中的数据特别是表单
            String userName = request.getParameter("userNameNew");
            String room_id = request.getParameter("room_idNew");
            String record_time = request.getParameter("record_timeNew");
            String repair_remark = request.getParameter("repair_remarkNew");
            RepairRecord record = new RepairRecord(userName, room_id, record_time, repair_remark);
            System.out.println("当前添加的表单:" + record);
            //请求保存，写入数据库
            if(repairRecordService.addRepairRecord(record)){
                System.out.println("维修信息写入成功!");
            }else{
                System.out.println("维修信息写入失败!");
            }
            List<RepairRecord> allDealRecord = repairRecordService.getAllDealRecord();
            request.setAttribute("repairRecordStudentList",allDealRecord);
            System.out.println("学生端所有的维修记录:" + allDealRecord);
            request.setAttribute("mainPage", "student/repairRecord.jsp");
            request.getRequestDispatcher("studentIndex.jsp").forward(request, response);
        }
    }


}
