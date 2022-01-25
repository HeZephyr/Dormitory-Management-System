package team.family.dbs.service;

import team.family.dbs.bean.RepairRecord;

import java.util.List;

public interface RepairRecordService {
    List<RepairRecord> getAllNotDealRecord();
    boolean dealRepair(int record_id);
    List<RepairRecord> getAllDealRecord();

    /**
     * 添加维修表单
     * @param cord
     * @return
     */
    boolean addRepairRecord(RepairRecord cord);
}
