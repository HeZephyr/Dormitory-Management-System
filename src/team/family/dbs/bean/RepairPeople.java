package team.family.dbs.bean;

public class RepairPeople {
    private String work_id;
    private String userName;
    private String password;
    private int is_effective;

    public RepairPeople() {
    }

    public RepairPeople(String work_id, String userName, String password) {
        this.work_id = work_id;
        this.userName = userName;
        this.password = password;
        this.is_effective = 0;
    }

    public RepairPeople(String work_id, String password) {
        this.work_id = work_id;
        this.password = password;
    }

    public String getWork_id() {
        return work_id;
    }

    public void setWork_id(String work_id) {
        this.work_id = work_id;
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

    public int getIs_effective() {
        return is_effective;
    }

    public void setIs_effective(int is_effective) {
        this.is_effective = is_effective;
    }

    @Override
    public String toString() {
        return "RepairPeople{" +
                "work_id='" + work_id + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", is_effective=" + is_effective +
                '}';
    }
}
