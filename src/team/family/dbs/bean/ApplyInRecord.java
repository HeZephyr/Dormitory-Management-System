package team.family.dbs.bean;

public class ApplyInRecord {
    private int applyId;
    private String applyName;
    private int dest_visit_dorm_id;
    private String dest_visit_dorm_name;

    private String visitDest;
    private String visit_time;
    private int is_effective;


    public ApplyInRecord() {
    }

    public ApplyInRecord(String applyName, int dest_visit_dorm_id, String dest_visit_dorm_name, String visitDest, String visit_time) {
        this.applyName = applyName;
        this.dest_visit_dorm_id = dest_visit_dorm_id;
        this.dest_visit_dorm_name = dest_visit_dorm_name;
        this.visitDest = visitDest;
        this.visit_time = visit_time;
        this.is_effective = 0;
    }

    public int getApplyId() {
        return applyId;
    }

    public void setApplyId(int applyId) {
        this.applyId = applyId;
    }

    public String getApplyName() {
        return applyName;
    }

    public void setApplyName(String applyName) {
        this.applyName = applyName;
    }

    public int getDest_visit_dorm_id() {
        return dest_visit_dorm_id;
    }

    public void setDest_visit_dorm_id(int dest_visit_dorm_id) {
        this.dest_visit_dorm_id = dest_visit_dorm_id;
    }

    public String getDest_visit_dorm_name() {
        return dest_visit_dorm_name;
    }

    public void setDest_visit_dorm_name(String dest_visit_dorm_name) {
        this.dest_visit_dorm_name = dest_visit_dorm_name;
    }

    public String getVisitDest() {
        return visitDest;
    }

    public void setVisitDest(String visitDest) {
        this.visitDest = visitDest;
    }

    public String getVisit_time() {
        return visit_time;
    }

    public void setVisit_time(String visit_time) {
        this.visit_time = visit_time;
    }

    public int getIs_effective() {
        return is_effective;
    }

    public void setIs_effective(int is_effective) {
        this.is_effective = is_effective;
    }

    @Override
    public String toString() {
        return "ApplyInRecord{" +
                "applyId=" + applyId +
                ", applyName='" + applyName + '\'' +
                ", dest_visit_dorm_id=" + dest_visit_dorm_id +
                ", dest_visit_dorm_name='" + dest_visit_dorm_name + '\'' +
                ", visitDest='" + visitDest + '\'' +
                ", visit_time='" + visit_time + '\'' +
                ", is_effective=" + is_effective +
                '}';
    }
}
