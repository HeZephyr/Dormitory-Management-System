package team.family.dbs.service;

import team.family.dbs.bean.RepairPeople;

public interface RepairPeopleService {
    /**
     * 判断是否存在该维修人员
     * @param work_id
     * @param password
     * @return
     */
    RepairPeople isExists(String work_id, String password);


}
