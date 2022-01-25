package team.family.dbs.dao;

import team.family.dbs.bean.RepairRecord;

import java.util.List;

public interface RepairRecordDAO {
    List<RepairRecord> getAllNotDealRecord() throws Exception;

    /**
     * 删除维修事物，处理置1
     * @param record_id
     * @return
     */
    int delRepairRecord(int record_id) throws Exception;

    List<RepairRecord> getAllDealRecord() throws Exception;
    /**
     * 增加维修申请
     */
    int addRepairRecord(RepairRecord record) throws Exception;
}
