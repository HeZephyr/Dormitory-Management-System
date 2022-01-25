package team.family.dbs.service;

import team.family.dbs.bean.AbsenceRecord;
import team.family.dbs.bean.DormManager;

import java.util.List;

public interface DormManagerService {
    /**
     * 判断是否存在该宿管
     * @param userName
     * @param password
     * @return
     */
    DormManager isExists(String userName, String password);

    /**
     * 返回所有的宿管
     * @return
     */
    List<DormManager> queryAllDormManager();

    List<DormManager> queryAllDormManagerByCondition(DormManager dormManager);

    /**
     * 删除宿管
     * @return
     */
    boolean delDormManager(String DormManagerId);

    /**
     * 添加宿管
     * @param dormManager
     * @return
     */
    boolean addDormManager(DormManager dormManager);

    /**
     * 跟新宿管信息
     * @param dormManager
     * @return
     */
    boolean updateDormManager(DormManager dormManager);

    /**
     * 通过条件串
     */
    List<AbsenceRecord> queryAllAbsenceRecordByDorm_id(int dorm_id,AbsenceRecord record);
}
