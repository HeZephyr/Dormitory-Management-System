package team.family.dbs.bean;

public class Academy {
    private int academy_id;
    private String academy;
    private int is_effective = 0;

    public Academy() {
    }

    public Academy(int academy_id, String academy) {
        this.academy_id = academy_id;
        this.academy = academy;
        this.is_effective = 0;
    }

    public int getIs_effective() {
        return is_effective;
    }

    public void setIs_effective(int is_effective) {
        this.is_effective = is_effective;
    }

    public int getAcademy_id() {
        return academy_id;
    }

    public void setAcademy_id(int academy_id) {
        this.academy_id = academy_id;
    }

    public String getAcademy() {
        return academy;
    }

    public void setAcademy(String academy) {
        this.academy = academy;
    }

    @Override
    public String toString() {
        return "Academy{" +
                "academy_id=" + academy_id +
                ", academy='" + academy + '\'' +
                '}';
    }
}
