package team.family.dbs.servlet;

import team.family.dbs.bean.ChangeRoomRecord;
import team.family.dbs.bean.Student;
import team.family.dbs.service.ChangeRoomRecordService;
import team.family.dbs.service.ChangeRoomRecordServiceImpl;
import team.family.dbs.service.StudentService;
import team.family.dbs.service.StudentServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class ChangeRoomRecordServlet extends HttpServlet {
    ChangeRoomRecordService changeRoomRecordService = new ChangeRoomRecordServiceImpl();
    StudentService studentService = new StudentServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //处理换寝
        request.setCharacterEncoding("utf-8");
        String action = request.getParameter("action");
        if("save".equals(action)){
            //读取表单数据见对象添加
            String applyStudentIdNew = request.getParameter("applyStudentIdNew");
            String applyUserNameNew = request.getParameter("applyUserNameNew");
            String oldRoom_idNew = request.getParameter("oldRoom_idNew");
            String newRoom_idNew = request.getParameter("newRoom_idNew");
            String record_timeNew = request.getParameter("record_timeNew");
            System.out.println("读取到的时间" + record_timeNew);
            String applyReasonNew = request.getParameter("applyReasonNew");
            ChangeRoomRecord record = new ChangeRoomRecord(applyStudentIdNew, applyUserNameNew, oldRoom_idNew, newRoom_idNew, record_timeNew, applyReasonNew);
            if(changeRoomRecordService.addRecord(record)){
                System.out.println("换寝申请添加成功!");
            }else{
                System.out.println("换寝申请添加失败!");
            }
            HttpSession session = request.getSession();
            Student currentStudent = (Student)session.getAttribute("currentUser");
            List<ChangeRoomRecord> selfRecords = changeRoomRecordService.getSelfRecord(currentStudent.getStudentId());
//            System.out.println("自己的换寝申请列表" + selfRecords);
            request.setAttribute("allChangeRoomRecordList",selfRecords);
            request.setAttribute("mainPage", "student/roomApplyRecordManager.jsp");
            request.getRequestDispatcher("studentIndex.jsp").forward(request, response);
        }else if("list".equals(action)){
//            System.out.println("打印自己的换寝申请");

            //先获取当前用户
            HttpSession session = request.getSession();
            Student currentStudent = (Student)session.getAttribute("currentUser");
//            System.out.println("当前用户" + currentStudent);
            //获取用户自己的申请换寝列表
            List<ChangeRoomRecord> selfRecords = changeRoomRecordService.getSelfRecord(currentStudent.getStudentId());
//            System.out.println("自己的换寝申请列表" + selfRecords);
            request.setAttribute("allChangeRoomRecordList",selfRecords);
            request.setAttribute("mainPage", "student/roomApplyRecordManager.jsp");
            request.getRequestDispatcher("studentIndex.jsp").forward(request, response);
        }else if("preSave".equals(action)){
            //直接打开申请换寝表单
            HttpSession session = request.getSession();
            Student currentStudent = (Student)session.getAttribute("currentUser");
            request.setAttribute("currentStudent",currentStudent);
            request.setAttribute("mainPage", "student/roomApplyRecordManager.jsp");
            request.getRequestDispatcher("studentIndex.jsp").forward(request, response);
        }else if("ManagerList".equals(action)){
            //返回所有未被处理的的记录
            List<ChangeRoomRecord> allNotDealRecords = changeRoomRecordService.getAllNotDealRecord();
            request.setAttribute("allChangeRoomRecordList",allNotDealRecords);
            request.setAttribute("mainPage", "dormManager/roomApplyRecordManager.jsp");
            request.getRequestDispatcher("dormManagerIndex.jsp").forward(request, response);
        }else if("del".equals(action)){
            //首先读取记录的id、和审批结果，并将其删除
            String record_id = request.getParameter("record_id");
            String isAgree = request.getParameter("isAgree");
            System.out.println("审批结果" + isAgree);
            if(changeRoomRecordService.delRecord(Integer.parseInt(record_id),Integer.parseInt(isAgree))){
                System.out.println("审批换寝成功");
                if(Integer.parseInt(isAgree)==1){
                    //进行修改个人信息
                    //1.获取该学生对象
//                    System.out.println("这他妈啥玩意" +record_id);
//                    try {
//                        Integer.parseInt(record_id);
//                    } catch (NumberFormatException e) {
//                        System.out.println("????");
//                        e.printStackTrace();
//                    } finally {
//                    }
                    ChangeRoomRecord recordDUIXIANG = changeRoomRecordService.getStudentIdByRecord_id(Integer.parseInt(record_id));
                    System.out.println("这里是啥" + recordDUIXIANG);
                    if(recordDUIXIANG != null){
                        Student student = studentService.isExistsStudent(recordDUIXIANG.getApplyStudentId());
                        student.setRoom_id(recordDUIXIANG.getNewRoom_id());
                        studentService.updateStudentByStudentId(student);
                        System.out.println("换寝数据更新成功@@@");
                    }else{
                        System.out.println("DAO有误");
                    }
                }
            }else{
                System.out.println("审批换寝失败");
            }
            List<ChangeRoomRecord> allNotDealRecords = changeRoomRecordService.getAllNotDealRecord();
            request.setAttribute("allChangeRoomRecordList",allNotDealRecords);
            request.setAttribute("mainPage", "dormManager/roomApplyRecordManager.jsp");
            request.getRequestDispatcher("dormManagerIndex.jsp").forward(request, response);
        }
    }
}
