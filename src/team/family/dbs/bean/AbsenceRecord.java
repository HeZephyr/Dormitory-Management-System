package team.family.dbs.bean;

public class AbsenceRecord {
    private int recordId;
    private String studentId;
    private String name;
    private String academy;
    private String major_and_class;
    private int dorm_id;
    private String dorm_name;
    private String room_id;
    private String absenceTime;
    private String remark;

    public AbsenceRecord() {
    }

    public AbsenceRecord(int recordId, String studentId, String name, String academy, String major_and_class, int dorm_id, String dorm_name, String room_id, String absenceTime, String remark) {
        this.recordId = recordId;
        this.studentId = studentId;
        this.name = name;
        this.academy = academy;
        this.major_and_class = major_and_class;
        this.dorm_id = dorm_id;
        this.dorm_name = dorm_name;
        this.room_id = room_id;
        this.absenceTime = absenceTime;
        this.remark = remark;
    }

    public AbsenceRecord(String studentId, String absenceTime, String remark) {
        this.studentId = studentId;
        this.absenceTime = absenceTime;
        this.remark = remark;
    }

    public int getRecordId() {
        return recordId;
    }

    public void setRecordId(int recordId) {
        this.recordId = recordId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getDorm_name() {
        return dorm_name;
    }

    public void setDorm_name(String dorm_name) {
        this.dorm_name = dorm_name;
    }

    public String getRoom_id() {
        return room_id;
    }

    public void setRoom_id(String room_id) {
        this.room_id = room_id;
    }

    public String getAbsenceTime() {
        return absenceTime;
    }

    public void setAbsenceTime(String absenceTime) {
        this.absenceTime = absenceTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "AbsenceRecord{" +
                "recordId=" + recordId +
                ", studentId='" + studentId + '\'' +
                ", name='" + name + '\'' +
                ", academy='" + academy + '\'' +
                ", major_and_class='" + major_and_class + '\'' +
                ", dorm_id=" + dorm_id +
                ", dorm_name='" + dorm_name + '\'' +
                ", room_id='" + room_id + '\'' +
                ", absenceTime=" + absenceTime +
                ", remark='" + remark + '\'' +
                '}';
    }
}
