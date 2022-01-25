package team.family.dbs.bean;

public class Student {
    private String studentId;
    private String userName;
    private String password;
    private String sex;
    private String tel;
    private String native_place;
    private String academy;
    private String major_and_class;
    private int dorm_id;
    private String dorm_name;
    private String room_id;
    private int is_effective = 0;

    public Student() {
    }

    public String getDorm_name() {
        return dorm_name;
    }

    public void setDorm_name(String dorm_name) {
        this.dorm_name = dorm_name;
    }

    public Student(String studentId, String password) {
        this.studentId = studentId;
        this.password = password;
    }

    public Student(String studentId, String userName, String password, String sex, String tel, String native_place, String academy, String major_and_class, int dorm_id, String dorm_name, String room_id, int is_effective) {
        this.studentId = studentId;
        this.userName = userName;
        this.password = password;
        this.sex = sex;
        this.tel = tel;
        this.native_place = native_place;
        this.academy = academy;
        this.major_and_class = major_and_class;
        this.dorm_id = dorm_id;
        this.dorm_name = dorm_name;
        this.room_id = room_id;
        this.is_effective = is_effective;
    }

    public Student(String studentId, String userName, String password, String sex, String tel, String native_place, String academy, String major_and_class, int dorm_id, String room_id, int is_effective) {
        this.studentId = studentId;
        this.userName = userName;
        this.password = password;
        this.sex = sex;
        this.tel = tel;
        this.native_place = native_place;
        this.academy = academy;
        this.major_and_class = major_and_class;
        this.dorm_id = dorm_id;
        this.room_id = room_id;
        this.is_effective = is_effective;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
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

    public String getNative_place() {
        return native_place;
    }

    public void setNative_place(String native_place) {
        this.native_place = native_place;
    }

    public String getAcademy() {
        return academy;
    }

    public void setAcademy(String academy) {
        this.academy = academy;
    }

    public String getMajor_and_class() {
        return major_and_class;
    }

    public void setMajor_and_class(String major_and_class) {
        this.major_and_class = major_and_class;
    }

    public int getDorm_id() {
        return dorm_id;
    }

    public void setDorm_id(int dorm_id) {
        this.dorm_id = dorm_id;
    }

    public String getRoom_id() {
        return room_id;
    }

    public void setRoom_id(String room_id) {
        this.room_id = room_id;
    }

    public int getIs_effective() {
        return is_effective;
    }

    public void setIs_effective(int is_effective) {
        this.is_effective = is_effective;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentId='" + studentId + '\'' +
                ", name='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", sex='" + sex + '\'' +
                ", tel='" + tel + '\'' +
                ", native_place='" + native_place + '\'' +
                ", academy='" + academy + '\'' +
                ", major_and_class='" + major_and_class + '\'' +
                ", dorm_id=" + dorm_id +
                ", room_id='" + room_id + '\'' +
                ", is_effective=" + is_effective +
                '}';
    }
}
