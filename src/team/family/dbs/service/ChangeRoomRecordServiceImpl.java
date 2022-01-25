package team.family.dbs.service;

import team.family.dbs.bean.ChangeRoomRecord;
import team.family.dbs.dao.ChangeRoomRecordDAO;
import team.family.dbs.dao.ChangeRoomRecordDAOImpl;

import java.util.List;

public class ChangeRoomRecordServiceImpl implements ChangeRoomRecordService{
    ChangeRoomRecordDAO changeRoomRecordDAO = new ChangeRoomRecordDAOImpl();
    @Override
    public List<ChangeRoomRecord> getAllNotDealRecord() {
        try {
            return changeRoomRecordDAO.getAllNotDealRecord();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<ChangeRoomRecord> getSelfRecord(String studentId) {
        try {
            return  changeRoomRecordDAO.getSelfRecord(studentId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean delRecord(int record_id, int result) {
        try {
            if(changeRoomRecordDAO.delRecord(record_id,result) > 0){
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
    public boolean addRecord(ChangeRoomRecord record) {
        try {
            if(changeRoomRecordDAO.addRecord(record) > 0){
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
    public ChangeRoomRecord getStudentIdByRecord_id(int record_id) {
        try {
            return changeRoomRecordDAO.getStudentIdByRecord_id(record_id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
