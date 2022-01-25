package team.family.dbs.service;

import team.family.dbs.bean.Academy;
import team.family.dbs.dao.AcademyDAO;
import team.family.dbs.dao.AcademyDAOImpl;

import java.util.List;

public class AcademyServiceImpl implements AcademyService{
    AcademyDAO academyDAO = new AcademyDAOImpl();
    @Override
    public List<Academy> querryAllAcademy() {
        try {
            return academyDAO.querryAllAcademy();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
