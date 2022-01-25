package team.family.dbs.dao;

import team.family.dbs.bean.Admin;

public interface AdminDAO {
    Admin isExistsAdmin(String userName,String password);

    int updateAdmin(Admin admin) throws Exception;
}
