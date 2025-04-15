package Part2;

public class Commission {
    private String commissionName;
    private Lecturer[]commissionTeam;
    private Lecturer headOfCommission;


    public Commission(String commisonName,Lecturer headOfCommision){
        setCommissionName(commisonName);
        setHeadOfCommission(headOfCommision);
    }

    public Lecturer getHeadOfCommission() {
        return headOfCommission;
    }

    public void setHeadOfCommission(Lecturer headOfCommission) {
        this.headOfCommission=headOfCommission;
    }

    public String getCommissionName() {
        return commissionName;
    }

    public void setCommissionName(String commissionName) {
        this.commissionName = commissionName;
    }
}
