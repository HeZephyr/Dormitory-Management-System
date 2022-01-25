package team.family.dbs.service;

import team.family.dbs.bean.ChangeRoomRecord;

import java.util.List;

public interface ChangeRoomRecordService {
    /**
     * 获取所有未处理的换寝申请记录
     * @return
     */
    List<ChangeRoomRecord> getAllNotDealRecord() ;

    /**
     * 获取自己的申请换寝记录
     * @return
     */
    List<ChangeRoomRecord> getSelfRecord(String studentId) ;

    /**
     * 删除申请，即处理申请后就自动删除
     *      * @return
     * @param record_id
     * @param result
     * @return
     * @throws Exception
     */
    boolean delRecord(int record_id,int result) ;


    /**
     * 自己申请换寝(学生)
     * @param record
     * @return
     */
    boolean addRecord(ChangeRoomRecord record) ;

    ChangeRoomRecord getStudentIdByRecord_id(int record_id);
}
