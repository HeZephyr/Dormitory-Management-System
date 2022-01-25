package team.family.dbs.service;

import team.family.dbs.bean.ApplyInRecord;
import team.family.dbs.dao.ApplyInRecordDAO;
import team.family.dbs.dao.ApplyInRecordDAOImpl;

import java.util.List;

public class ApplyInRecordServiceImpl implements ApplyInRecordService{
    ApplyInRecordDAO applyInRecordDAO = new ApplyInRecordDAOImpl();

    @Override
    public List<ApplyInRecord> getAllVisitRecord() {
        try {
            return applyInRecordDAO.getAllVisitRecord();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean addVisitRecord(ApplyInRecord applyInRecord) {
        try {
            if(applyInRecordDAO.addVisitRecord(applyInRecord) > 0){
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
    public boolean delVisitRecord(int applyId) {
        try {
            if(applyInRecordDAO.delVisitRecord(applyId) > 0){
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
