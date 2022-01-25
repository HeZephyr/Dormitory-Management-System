package team.family.dbs.bean;

public class Dorm {
    private int dorm_id;
    private String dorm_name;
    private String remark;
    private  int is_effective;

    public Dorm() {
    }

    public Dorm(String dorm_name, String remark) {
        this.dorm_name = dorm_name;
        this.remark = remark;
        this.is_effective = 0;
    }

    public Dorm(int dorm_id, String dorm_name, String remark) {
        this.dorm_id = dorm_id;
        this.dorm_name = dorm_name;
        this.remark = remark;
    }

    public Dorm(int dorm_id, String dorm_name, String remark, int is_effective) {
        this.dorm_id = dorm_id;
        this.dorm_name = dorm_name;
        this.remark = remark;
        this.is_effective = is_effective;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getIs_effective() {
        return is_effective;
    }

    public void setIs_effective(int is_effective) {
        this.is_effective = is_effective;
    }

    @Override
    public String toString() {
        return "Dorm{" +
                "dorm_id=" + dorm_id +
                ", dorm_name='" + dorm_name + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }

}
