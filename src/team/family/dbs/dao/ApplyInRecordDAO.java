package team.family.dbs.dao;

import team.family.dbs.bean.ApplyInRecord;

import java.util.List;

public interface ApplyInRecordDAO {
    /**
     * 得到所有的访问记录
     * @return
     */
    List<ApplyInRecord> getAllVisitRecord() throws Exception;

    /**
     * 增加访问记录
     */
    int addVisitRecord(ApplyInRecord applyInRecord) throws Exception;


    /**
     * 删除访问记录，访问记录的有效性设置为1
     */
    int delVisitRecord(int applyId) throws Exception;
}
