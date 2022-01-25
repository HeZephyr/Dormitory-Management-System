package team.family.dbs.dao;

import team.family.dbs.bean.RepairPeople;

public interface RepairPeopleDAO {
    RepairPeople isExists(String work_id,String password) throws Exception;


}
