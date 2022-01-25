package team.family.dbs.bean;

public class DormManager {
    private String  dormmasterId;
    private String userName;
    private String password;
    private String sex;
    private String tel;
    private int dorm_id;
    private String dorm_name;
    private int is_effective;

    public DormManager() {

    }

    public DormManager(String dormmasterId, String userName, String password, String sex, String tel, int dorm_id, int is_effective) {
        this.dormmasterId = dormmasterId;
        this.userName = userName;
        this.password = password;
        this.sex = sex;
        this.tel = tel;
        this.dorm_id = dorm_id;
        this.is_effective = is_effective;
    }

    public DormManager(String dormmasterId, String userName, String password, String sex, String tel, int dorm_id, String dorm_name, int is_effective) {
        this.dormmasterId = dormmasterId;
        this.userName = userName;
        this.password = password;
        this.sex = sex;
        this.tel = tel;
        this.dorm_id = dorm_id;
        this.dorm_name = dorm_name;
        this.is_effective = is_effective;
    }

    public DormManager(String dormmasterId, String password) {
        this.dormmasterId = dormmasterId;
        this.password = password;
    }

    public String getDormmasterId() {
        return dormmasterId;
    }

    public void setDormmasterId(String dormmasterId) {
        this.dormmasterId = dormmasterId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public int getDorm_id() {
        return dorm_id;
    }

    public void setDorm_id(int dorm_id) {
        this.dorm_id = dorm_id;
    }

    public String getDorm_name() {
        return dorm_name;
    }

    public void setDorm_name(String dorm_name) {
        this.dorm_name = dorm_name;
    }

    public int getIs_effective() {
        return is_effective;
    }

    public void setIs_effective(int is_effective) {
        this.is_effective = is_effective;
    }

    @Override
    public String toString() {
        return "DormManager{" +
                "dormmasterId='" + dormmasterId + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", sex='" + sex + '\'' +
                ", tel='" + tel + '\'' +
                ", dorm_id=" + dorm_id +
                ", dorm_name='" + dorm_name + '\'' +
                ", is_effective='" + is_effective + '\'' +
                '}';
    }
}
