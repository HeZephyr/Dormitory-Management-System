package team.family.dbs.bean;

public class RepairRecord {
    private int record_id;
    private String userName;
    private String room_id;
    private String record_time;
    private String repair_remark;
    private int is_deal;

    public RepairRecord() {
    }

    public RepairRecord(String userName, String room_id, String record_time, String repair_remark) {
        this.userName = userName;
        this.room_id = room_id;
        this.record_time = record_time;
        this.repair_remark = repair_remark;
    }

    public int getRecord_id() {
        return record_id;
    }

    public void setRecord_id(int record_id) {
        this.record_id = record_id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRoom_id() {
        return room_id;
    }

    public void setRoom_id(String room_id) {
        this.room_id = room_id;
    }

    public String getRecord_time() {
        return record_time;
    }

    public void setRecord_time(String record_time) {
        this.record_time = record_time;
    }

    public String getRepair_remark() {
        return repair_remark;
    }

    public void setRepair_remark(String repair_remark) {
        this.repair_remark = repair_remark;
    }

    public int getIs_deal() {
        return is_deal;
    }

    public void setIs_deal(int is_deal) {
        this.is_deal = is_deal;
    }

    @Override
    public String toString() {
        return "RepairRecord{" +
                "recordId=" + record_id +
                ", userName='" + userName + '\'' +
                ", room_id='" + room_id + '\'' +
                ", record_time='" + record_time + '\'' +
                ", repair_remark='" + repair_remark + '\'' +
                ", is_deal=" + is_deal +
                '}';
    }
}
