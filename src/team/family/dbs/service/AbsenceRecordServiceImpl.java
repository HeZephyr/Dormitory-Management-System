package team.family.dbs.service;

import team.family.dbs.bean.AbsenceRecord;
import team.family.dbs.dao.AbsenceRecordDAO;
import team.family.dbs.dao.AbsenceRecordDAOImpl;

import java.util.List;

public class AbsenceRecordServiceImpl implements AbsenceRecordService{
    AbsenceRecordDAO absenceRecordDAO = new AbsenceRecordDAOImpl();
    @Override
    public List<AbsenceRecord> querryAllRecordCondition(AbsenceRecord record) {
        try {
            return absenceRecordDAO.querryAllAcademyByCondition(record);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean delAbsenceRecord(int record_id) {
        try {
            if(absenceRecordDAO.delAbsenceRecord(record_id) > 0){
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
    public boolean updateAbsenceRecord(AbsenceRecord record) {
        try {
            if(absenceRecordDAO.updateAbsenceRecord(record) > 0){
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
    public boolean addAbsenceRecord(AbsenceRecord record) {
        try {
            if(absenceRecordDAO.addAbsenceRecord(record) > 0){
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
    public AbsenceRecord getAbsenceRecord(int record_id) {
        try {
            return absenceRecordDAO.getAbsenceRecord(record_id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<AbsenceRecord> getAbsenceRecordListByStudentId(String studentId) {
        try {
            return absenceRecordDAO.getAbsenceRecordListByStudentId(studentId);
        } catch (Exception e) {
            e.printStackTrace();
            return  null;
        }
    }

}
