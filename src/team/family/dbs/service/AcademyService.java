package team.family.dbs.service;

import team.family.dbs.bean.Academy;

import java.util.List;

public interface AcademyService {
    /**
     * 查询所有的学院集合
     * @return
     */
    List<Academy> querryAllAcademy();
}
