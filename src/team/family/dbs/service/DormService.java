package team.family.dbs.service;

import team.family.dbs.bean.Dorm;
import team.family.dbs.bean.PageBean;

import java.util.List;

public interface DormService {
    /**
     * 通过宿舍楼号查找宿舍楼名
     * @param dorm_id
     * @return
     */
    String selectDorm_nameByDorm_id(int dorm_id);

    /**
     * 通过宿舍楼名查找宿舍楼号
     * @param Dorm_name
     * @return
     */
    int selectDorm_idByDorm_Name(String Dorm_name);

    /**
     * 查找指定的宿舍楼
     * @param dorm_id
     * @return
     */
    Dorm queryDorm(int dorm_id);


    /**
     * 返回所有的宿舍楼
     * @return
     */
    List<Dorm> queryAllDorm();

    /**
     * 通过传入一个Dorm对象来确定返回的所有的宿舍漏洞的集合
     * @param page
     * @param dorm
     * @return
     */
    List<Dorm> queryAllDormCondition(PageBean page, Dorm dorm);

    /**
     * 判断该宿舍楼里是否有宿舍！(未测试)
     * @param dorm_id
     * @return
     */
    boolean isHaveRoomInDorm(int dorm_id);

    /**
     * 通过宿舍楼id删除宿舍楼，设置可用性为1
     * @param dorm_id
     * @return
     */
    boolean delDormByDorm_id(int dorm_id);

    /**
     * 通过Dorm对象进行更新宿舍信息
     * @param dorm
     * @return
     */
    boolean updateDormByDorm(Dorm dorm);

    /**
     * 通过dorm对象增加一个宿舍对象
     * @param dorm
     * @return
     */
    boolean addDorm(Dorm dorm);
}
