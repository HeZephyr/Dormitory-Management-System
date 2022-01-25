package team.family.dbs.service;

import team.family.dbs.bean.AbsenceRecord;

import java.util.List;

public interface AbsenceRecordService {
    /**
     * 返回数据库中所有的晚归记录(待测试)
     * @param record
     * @return
     */
    List<AbsenceRecord> querryAllRecordCondition(AbsenceRecord record);


    /**
     * 删除晚归记录
     */
    boolean delAbsenceRecord(int record_id);

    /**
     * 更新晚归信息
     * @param record
     * @return
     */
    boolean updateAbsenceRecord(AbsenceRecord record);

    /**
     * 添加晚归记录
     */
    boolean addAbsenceRecord(AbsenceRecord record);

    /**
     * 通过id获取记录对象
     */
    AbsenceRecord getAbsenceRecord(int record_id);

    /**
     * 通过学号获取集合
     * @param studentId
     * @return
     */
    List<AbsenceRecord> getAbsenceRecordListByStudentId(String studentId);

}
