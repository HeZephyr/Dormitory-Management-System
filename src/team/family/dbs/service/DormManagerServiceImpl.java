package team.family.dbs.service;

import team.family.dbs.bean.AbsenceRecord;
import team.family.dbs.bean.DormManager;
import team.family.dbs.dao.DormManagerDAO;
import team.family.dbs.dao.DormManagerDAOImpl;

import java.util.List;

public class DormManagerServiceImpl implements DormManagerService{
    DormManagerDAO dormManagerDAO = new DormManagerDAOImpl();
    @Override
    public DormManager isExists(String userName, String password) {
        try {
           return  dormManagerDAO.isExists(userName,password);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public List<DormManager> queryAllDormManager() {
        List<DormManager> dormManagers = null;
        try {
            dormManagers = dormManagerDAO.queryAllDormManager();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dormManagers;
    }
    @Override
    public List<DormManager> queryAllDormManagerByCondition(DormManager dormManager){
        List<DormManager> dormManagers = null;
        try {
            dormManagers = dormManagerDAO.queryAllDormManagerByCondition(dormManager);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dormManagers;
    }

    @Override
    public boolean delDormManager(String DormManagerId) {
        try {
            return dormManagerDAO.delDormManagerByDormMasterId(DormManagerId);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean addDormManager(DormManager dormManager)  {
        try {
            if(dormManagerDAO.addDormManager(dormManager) > 0){
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
    public boolean updateDormManager(DormManager dormManager) {
        try {
            //执行更新操作。
            if(dormManagerDAO.updateDormManager(dormManager) > 0){
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
    public List<AbsenceRecord> queryAllAbsenceRecordByDorm_id(int dorm_id, AbsenceRecord record) {
        try {
            return dormManagerDAO.queryAllAbsenceRecordByDorm_id(dorm_id,record);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
