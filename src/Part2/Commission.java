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
        commissionTeam[numOfCommissionMembers++]=l1;
    }
    else {
        for(int i=0;i<commissionTeam.length;i++) {
            if (commissionTeam[i] == null) {
                commissionTeam[i] = l1;
                numOfCommissionMembers++;
                return;
            }
        }
        commissionTeam = Arrays.copyOf(commissionTeam, commissionTeam.length * 2);
        commissionTeam[numOfCommissionMembers++] = l1;

    }
    }
    public void removeLectFromCommission(Lecturer l1){
        if(numOfCommissionMembers==0){
            System.out.println("there is no Lecturers in this commission");
            return;
        }
        else{
            for(int i=0;i<commissionTeam.length;i++){
                if(commissionTeam[i]!=null){
                if(l1.getId().equals(commissionTeam[i].getId())) {
                    commissionTeam[i] = null;
                    numOfCommissionMembers--;
                }
                }
            }
        }

    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Commission{")
                .append("commissionName='").append(commissionName).append('\'')
                .append(", commissionTeam=[");
        if(numOfCommissionMembers==0){
            sb.append("the commission is empty!");

        }
        else {
            for (Lecturer lecturer : commissionTeam) {
                if (lecturer == null) {
                    sb.append("");
                } else {
                    sb.append(lecturer);
                    sb.append(", ");
                }
            }
        }

        sb.append("], headOfCommission=").append(headOfCommission)
                .append("}\n");
        return sb.toString();
    }
}
