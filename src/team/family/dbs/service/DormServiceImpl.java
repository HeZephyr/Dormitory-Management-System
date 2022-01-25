package team.family.dbs.service;

import team.family.dbs.bean.Dorm;
import team.family.dbs.bean.PageBean;
import team.family.dbs.dao.DormDAO;
import team.family.dbs.dao.DormDAOImpl;

import java.util.List;

public class DormServiceImpl implements DormService{
    DormDAO dormDAO = new DormDAOImpl();
    @Override
    public String selectDorm_nameByDorm_id(int dorm_id) {
        try {
            return dormDAO.selectDorm_nameByDorm_id(dorm_id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public int selectDorm_idByDorm_Name(String Dorm_name) {
        try {
            return dormDAO.selectDorm_idByDorm_Name(Dorm_name);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public Dorm queryDorm(int dorm_id) {
        try {
            return dormDAO.queryDorm(dorm_id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Dorm> queryAllDorm() {
        try {
            return dormDAO.queryAllDorm();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public List<Dorm> queryAllDormCondition(PageBean page, Dorm dorm) {
        try {
            return dormDAO.queryAllDormCondition(page,dorm);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean isHaveRoomInDorm(int dorm_id) {
        try {
            return dormDAO.isHaveRoomInDorm(dorm_id);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delDormByDorm_id(int dorm_id) {
        try {
            if(dormDAO.delDormByDorm_id(dorm_id) > 0){
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
    public boolean updateDormByDorm(Dorm dorm) {
        try {
            if(dormDAO.updateDormByDorm_id(dorm) > 0){
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
    public boolean addDorm(Dorm dorm) {
        try {
            if(dormDAO.addDorm(dorm) > 0){
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
