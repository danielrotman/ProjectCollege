package Part2;

import java.util.Arrays;

public class Commission {
    private String commissionName;
    private Lecturer[]commissionTeam;
    private int numOfCommissionMembers;
    private Lecturer headOfCommission;


    public Commission(String commisonName,Lecturer headOfCommision){
        setCommissionName(commisonName);
        setHeadOfCommission(headOfCommision);
    }
    public Commission(Commission commission2){
        this(commission2.commissionName,commission2.headOfCommission);
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
    public void addToCommissionTeam(Lecturer l1){
    if(numOfCommissionMembers==0){
        commissionTeam=new Lecturer[2];
    }
    else if (numOfCommissionMembers == commissionTeam.length){
        commissionTeam = Arrays.copyOf(commissionTeam, commissionTeam.length * 2);
    }
        commissionTeam[numOfCommissionMembers++]=l1;
    }
    public void removeLectFromCommission(Lecturer l1){
        if(numOfCommissionMembers==0){
            commissionTeam=new Lecturer[2];
        }
        else if (numOfCommissionMembers == commissionTeam.length){
            commissionTeam = Arrays.copyOf(commissionTeam, commissionTeam.length * 2);
        }
        commissionTeam[numOfCommissionMembers--]=null;
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Commission{")
                .append("commissionName='").append(commissionName).append('\'')
                .append(", commissionTeam=[");

        for (int i = 0; i < numOfCommissionMembers; i++) {
            sb.append(commissionTeam[i]);
            if (i < numOfCommissionMembers - 1) {
                sb.append(", ");
            }
        }
        sb.append("], headOfCommission=").append(headOfCommission)
                .append("}\n");
        return sb.toString();
    }
}
