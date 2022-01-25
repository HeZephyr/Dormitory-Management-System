package team.family.dbs.service;

import team.family.dbs.bean.RepairRecord;
import team.family.dbs.dao.RepairRecordDAO;
import team.family.dbs.dao.RepairRecordDAOImpl;

import java.util.List;

public class RepairRecordServiceImpl implements RepairRecordService{
    RepairRecordDAO repairRecordDAO = new RepairRecordDAOImpl();
    @Override
    public List<RepairRecord> getAllNotDealRecord() {
        try {
            return repairRecordDAO.getAllNotDealRecord();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    @Override
    public boolean dealRepair(int record_id) {
        try {
            if(repairRecordDAO.delRepairRecord(record_id) > 0){
                return true;
            }else{
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<RepairRecord> getAllDealRecord() {
        try {
            return repairRecordDAO.getAllDealRecord();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean addRepairRecord(RepairRecord cord) {
        try {
            if(repairRecordDAO.addRepairRecord(cord) > 0){
                return true;
            }else{
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
