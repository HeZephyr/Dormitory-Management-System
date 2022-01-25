package team.family.dbs.dao;

import team.family.dbs.bean.ChangeRoomRecord;

import java.util.List;

public interface ChangeRoomRecordDAO {
    /**
     * 获取所有未处理的换寝申请记录
     * @return
     */
    List<ChangeRoomRecord> getAllNotDealRecord() throws Exception;

    /**
     * 获取自己的申请换寝记录
     * @return
     */
    List<ChangeRoomRecord> getSelfRecord(String studentId) throws Exception;

    /**
     * 删除申请，即处理申请后就自动删除
     *      * @return
     * @param record_id
     * @param result
     * @return
     * @throws Exception
     */
    int delRecord(int record_id,int result) throws Exception;


    /**
     * 自己申请换寝(学生)
     * @param record
     * @return
     */
    int addRecord(ChangeRoomRecord record) throws Exception;

    /**
     * 通过学号获取学生id
     * @param record_id
     * @return
     */
    ChangeRoomRecord getStudentIdByRecord_id(int record_id) throws Exception;
}
