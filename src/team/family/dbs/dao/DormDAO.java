package team.family.dbs.dao;

import team.family.dbs.bean.Dorm;
import team.family.dbs.bean.PageBean;

import java.util.List;

public interface DormDAO {
    /**
     * 返回所有的宿舍楼
     * @return
     */
    List<Dorm> queryAllDorm() throws Exception;

    /**
     * 通过传入一个宿舍对象，可以获取宿舍列表
     * @return
     */
    List<Dorm> queryAllDormCondition(PageBean page,Dorm dorm) throws Exception;

    /**
     * 修根据改宿舍楼的信息
     * @param dorm
     * @return
     */
    int updateDormByDorm_id(Dorm dorm) throws Exception;

    /**
     * 根据宿舍楼id删除宿舍楼信息
     * @param dorm_id
     * @return
     */
    int delDormByDorm_id(int dorm_id) throws Exception;

    /**
     * 通过dorm_id返回宿舍楼名字
     * @return
     */
    String selectDorm_nameByDorm_id(int dorm_id) throws Exception;

    /**
     * 通过宿舍楼名字查找宿舍楼id
     * @param dorm_name
     * @return
     */
    int selectDorm_idByDorm_Name(String dorm_name) throws Exception;

    /**
     * 判断该宿舍楼下是否有宿舍
     * @param dorm_id
     * @return
     */
    boolean isHaveRoomInDorm(int dorm_id) throws Exception;

    /**
     * 通过宿舍楼对象进行更新数据库，添加宿舍楼对象
     * @param dorm
     * @return
     */
    int addDorm(Dorm dorm) throws Exception;

    /**
     * 通过宿舍楼id返回宿舍楼对象
     * @param dorm_id
     * @return
     */
    Dorm queryDorm(int dorm_id) throws Exception;
}
