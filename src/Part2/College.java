package Part2;

public class College {
    private Lecturer[] allLectrures;
    private int numOfLectrures;
    private Commission[]allCommissions;
    private int numOfCommissions;
    private Department[]allDepts;
    private int numOfDepts;
    private String collegeName;

    public College(int numOfLectrures, int numOfCommissions, int numOfDepts, String collegeName) {
        this.numOfLectrures = numOfLectrures;
        this.numOfCommissions = numOfCommissions;
        this.numOfDepts = numOfDepts;
        this.collegeName = collegeName;
    }

    public College(String collegeName) {
        this(0,0,0,collegeName);
    }

    public boolean addLecturer(Lecturer l1){
        if(numOfLectrures==0){
            allLectrures=new Lecturer[2];
            allLectrures[numOfLectrures++]=l1;
        }
        else{
            for(int i=0;i<numOfLectrures;i++){
                if(allLectrures[i].getId().equals(l1.getId())){
                    return false;
                }
                else{
                    allLectrures[numOfLectrures++]=l1;
                }
            }
        }
        return true;
    }

    public boolean addCommision(Commission commission){
        if (commission.getHeadOfCommission().getDegree()== Lecturer.eDegree.Bachelor&&commission.getHeadOfCommission().getDegree()!=Lecturer.eDegree.Master) {
            return false;
        }
        if(numOfCommissions==0){
            allCommissions=new Commission[2];
            allCommissions[numOfCommissions++]=commission;
        }
        else{
            for(int i=0;i<numOfCommissions;i++){
                if(allCommissions[i].getCommissionName().equals(commission.getCommissionName())){
                    return false;
                }
                else{
                    allCommissions[numOfCommissions++]=commission;
                }
            }
        }
        return true;
    }
}
