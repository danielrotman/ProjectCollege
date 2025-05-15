package Daniel_Niv;

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

    public void IsLecturerNotInCommissionTeam(Lecturer l1) throws AlreadyExistException {
        if(commissionTeam==null){
            return;
        }
        if (commissionTeam.length != 0) {
            for (int i = 0; i < commissionTeam.length; i++) {
                if (commissionTeam[i].equals(l1)) {
                    throw new AlreadyExistException(l1.getName());
                }
            }
        }
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
        sb.append("commission Name='").append(commissionName).append('\'')
                .append(", commission Team=[");
        if(numOfCommissionMembers==0){
            sb.append("the commission is empty!");

        }
        else {
            boolean first=true;
            for (Lecturer lecturer : commissionTeam) {
                if (lecturer !=null) {
                    if(!first) {
                        sb.append(",");
                    }
                    sb.append(lecturer.getName());
                    first=false;
                }
            }
        }

        sb.append("], head Of Commission= ").append(headOfCommission.getName())
                .append(".");
        return sb.toString();
    }
}
