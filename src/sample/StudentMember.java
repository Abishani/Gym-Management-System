package sample;

public class StudentMember extends DefaultMember {
    private String schoolName;

   /* public StudentMember(int membershipNumber, String memName, String name, String startMembershipDate, String nicNumber, String phoneNumber, String schoolName) {
        super(membershipNumber, name, startMembershipDate, nicNumber, phoneNumber);
        this.schoolName = schoolName;
    }*/

    public StudentMember(int membershipNumber, String name, String startMembershipDate, String nicNumber, String phoneNumber, String schoolName) {
        super(membershipNumber, name, startMembershipDate, nicNumber, phoneNumber);
        this.schoolName = schoolName;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }
}
