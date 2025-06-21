package Daniel_Niv;

import Daniel_Niv.Exceptions.AlreadyExistException;
import Daniel_Niv.Exceptions.CollegeException;
import Daniel_Niv.Exceptions.IlegalMemberAddedException;


import java.io.Serializable;
import java.util.ArrayList;


public class Commission <T extends Lecturer>  implements Cloneable, Serializable {
    private String commissionName;
    private Lecturer headOfCommission;
    private ArrayList <T> commissionTeam=new ArrayList<>();
    private Class<?> allowedType = null;



    public Commission(String commisonName,Lecturer headOfCommision){
        setCommissionName(commisonName);
        setHeadOfCommission(headOfCommision);
    }
    public void add(T lecturer)throws CollegeException {
        if (allowedType == null) {
            allowedType = lecturer.getClass();
        } else if (!lecturer.getClass().equals(allowedType)) {
            throw new IlegalMemberAddedException(lecturer.getName());
        }
        commissionTeam.add(lecturer);
    }
    public void remove(T lecturer){
        commissionTeam.remove(lecturer);
    }
    public void IsLecturerNotInCommissionTeam(Lecturer l1) throws AlreadyExistException {
        if(commissionTeam.isEmpty()){
            return;
        }
        if(commissionTeam.contains(l1)){
            throw new AlreadyExistException(l1.getName());
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

    public ArrayList<T> getCommissionTeam() {
        return commissionTeam;
    }

    public int getNumOfCommissionMembers() {
        return commissionTeam.size();
    }
    public void setCommissionName(String commissionName) {
        this.commissionName = commissionName;
    }

    @Override
    public Commission clone() throws CloneNotSupportedException {
        Commission copy = (Commission) super.clone();
        copy.commissionTeam = new ArrayList<>();
        for (Lecturer l : this.commissionTeam) {
            copy.commissionTeam.add(l.clone());
        }

        copy.headOfCommission = this.headOfCommission.clone();

        return copy;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("commission Name='").append(commissionName).append('\'')
                .append(", commission Team=[");
        if(commissionTeam.isEmpty()){
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
