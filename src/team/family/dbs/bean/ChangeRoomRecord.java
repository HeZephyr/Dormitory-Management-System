package team.family.dbs.bean;

public class ChangeRoomRecord {
    private int record_id;
    private String applyStudentId;
    private String applyUserName;
    private String oldRoom_id;
    private String newRoom_id;
    private String record_time;
    private String applyReason;
    private int isAgree;

    public ChangeRoomRecord() {
    }

    public ChangeRoomRecord(String applyStudentId, String applyUserName, String oldRoom_id, String newRoom_id, String record_time, String applyReason) {
        this.applyStudentId = applyStudentId;
        this.applyUserName = applyUserName;
        this.oldRoom_id = oldRoom_id;
        this.newRoom_id = newRoom_id;
        this.record_time = record_time;
        this.applyReason = applyReason;
        this.isAgree = 0;//未审批状态
    }

    public int getRecord_id() {
        return record_id;
    }

    public void setRecord_id(int record_id) {
        this.record_id = record_id;
    }

    public String getApplyStudentId() {
        return applyStudentId;
    }

    public void setApplyStudentId(String applyStudentId) {
        this.applyStudentId = applyStudentId;
    }

    public String getApplyUserName() {
        return applyUserName;
    }

    public void setApplyUserName(String applyUserName) {
        this.applyUserName = applyUserName;
    }

    public String getOldRoom_id() {
        return oldRoom_id;
    }

    public void setOldRoom_id(String oldRoom_id) {
        this.oldRoom_id = oldRoom_id;
    }

    public String getNewRoom_id() {
        return newRoom_id;
    }

    public void setNewRoom_id(String newRoom_id) {
        this.newRoom_id = newRoom_id;
    }

    public String getRecord_time() {
        return record_time;
    }

    public void setRecord_time(String record_time) {
        this.record_time = record_time;
    }

    public String getApplyReason() {
        return applyReason;
    }

    public void setApplyReason(String applyReason) {
        this.applyReason = applyReason;
    }

    public int getIsAgree() {
        return isAgree;
    }

    public void setIsAgree(int isAgree) {
        this.isAgree = isAgree;
    }

    @Override
    public String toString() {
        return "ChangeRoomRecord{" +
                "record_id=" + record_id +
                ", applyStudentId='" + applyStudentId + '\'' +
                ", applyUserName='" + applyUserName + '\'' +
                ", oldRoom_id='" + oldRoom_id + '\'' +
                ", newRoom_id='" + newRoom_id + '\'' +
                ", record_time='" + record_time + '\'' +
                ", applyReason='" + applyReason + '\'' +
                ", isAgree=" + isAgree +
                '}';
    }
}
