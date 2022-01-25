package team.family.dbs.dao;

import team.family.dbs.bean.AbsenceRecord;
import team.family.dbs.bean.DormManager;

import java.util.List;

public interface DormManagerDAO {
    /**
     * 验证数据库中是否有该用户，有则返回
     * @param userName
     * @param password
     * @return
     */
    DormManager isExists(String userName,String password) throws Exception;

    /**
     * 返回所有的DormManager
     * @return
     */
    List<DormManager> queryAllDormManager() throws Exception;
    List<DormManager> queryAllDormManagerByCondition(DormManager dormManager) throws Exception;

    /**
     * 删除通过DormMasterId
     * @return
     */
    boolean delDormManagerByDormMasterId(String DormMasterId) throws Exception;

    /**
     * 返回宿管数目
     * @return
     */
    int dormManagerCount() throws Exception;

    /**
     * 添加宿管
     * @param dormManager
     * @return
     */
    int addDormManager(DormManager dormManager) throws Exception;


    /**
     * 更新宿管
     * @param dormManager
     */
    int updateDormManager(DormManager dormManager) throws Exception;


    /**
     * 通过dorm_id 返回所有的晚归记录
     * @param dorm_id
     * @return
     */

    List<AbsenceRecord> queryAllAbsenceRecordByDorm_id(int dorm_id,AbsenceRecord record) throws Exception;
}
