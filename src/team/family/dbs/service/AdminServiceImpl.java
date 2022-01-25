package team.family.dbs.service;

import team.family.dbs.bean.Admin;
import team.family.dbs.dao.AdminDAO;
import team.family.dbs.dao.AdminDAOImpl;

public class AdminServiceImpl implements AdminService{
    AdminDAO adminDAO = new AdminDAOImpl();

    @Override
    public Admin isExistsAdmin(String userName, String password) {
        return adminDAO.isExistsAdmin(userName,password);
    }

    @Override
    public boolean updateAdmin(Admin admin) {
        try {
            if(adminDAO.updateAdmin(admin) > 0){
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
