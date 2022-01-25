package team.family.dbs.service;

import team.family.dbs.bean.RepairPeople;
import team.family.dbs.dao.RepairPeopleDAO;
import team.family.dbs.dao.RepairPeopleDAOImpl;

public class RepairPeopleServiceImpl implements RepairPeopleService{
    RepairPeopleDAO repairPeopleDAO = new RepairPeopleDAOImpl();
    @Override
    public RepairPeople isExists(String work_id, String password) {
        try {
            return repairPeopleDAO.isExists(work_id,password);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


}
