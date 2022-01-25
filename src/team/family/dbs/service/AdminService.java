package team.family.dbs.service;

import team.family.dbs.bean.Admin;

public interface AdminService {
    Admin isExistsAdmin(String userName, String password);

    boolean updateAdmin(Admin admin);
}
