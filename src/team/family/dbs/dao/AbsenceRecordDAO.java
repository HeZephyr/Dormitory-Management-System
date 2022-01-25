package team.family.dbs.dao;

import team.family.dbs.bean.AbsenceRecord;

import java.util.List;

public interface AbsenceRecordDAO {
    /**
     * 返回一定条件的全部记录
     * @param record
     * @return
     * @throws Exception
     */
    List<AbsenceRecord> querryAllAcademyByCondition(AbsenceRecord record) throws Exception;


    /**
     * 删除晚归记录
     */
    int delAbsenceRecord(int record_id) throws Exception;

    /**
     * 更新晚归信息
     * @param record
     * @return
     */
    int updateAbsenceRecord(AbsenceRecord record) throws Exception;

    /**
     * 添加晚归信息
     * @param record
     * @return
     */
    int addAbsenceRecord(AbsenceRecord record) throws Exception;


    AbsenceRecord getAbsenceRecord(int record_id) throws Exception;

    /**
     * 通过学号获取晚归记录集合
     * @param studentId
     * @return
     */
    List<AbsenceRecord> getAbsenceRecordListByStudentId(String studentId) throws Exception;
}
