package team.family.dbs.bean;

public class Room {
    private String room_id;
    private int belong_dormId;
    private int room_hygiene;//寝室卫生等级0-优秀 1-良好 2-合格 3-不合格
    private String room_hygiene_remark;
    public Room() {
    }

    public Room(String room_id, int belong_dormId, int room_hygiene, String room_hygiene_remark) {
        this.room_id = room_id;
        this.belong_dormId = belong_dormId;
        this.room_hygiene = room_hygiene;
        this.room_hygiene_remark = room_hygiene_remark;
    }

    public String getRoom_id() {
        return room_id;
    }

    public void setRoom_id(String room_id) {
        this.room_id = room_id;
    }

    public int getBelong_dormId() {
        return belong_dormId;
    }

    public void setBelong_dormId(int belong_dormId) {
        this.belong_dormId = belong_dormId;
    }

    public int getRoom_hygiene() {
        return room_hygiene;
    }

    public void setRoom_hygiene(int room_hygiene) {
        this.room_hygiene = room_hygiene;
    }

    public String getRoom_hygiene_remark() {
        return room_hygiene_remark;
    }

    public void setRoom_hygiene_remark(String room_hygiene_remark) {
        this.room_hygiene_remark = room_hygiene_remark;
    }

    @Override
    public String toString() {
        return "Room{" +
                "room_id='" + room_id + '\'' +
                ", belong_dormId=" + belong_dormId +
                ", room_hygiene=" + room_hygiene +
                ", room_hygiene_remark='" + room_hygiene_remark + '\'' +
                '}';
    }
}
