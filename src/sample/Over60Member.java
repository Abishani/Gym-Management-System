package sample;

public class Over60Member extends DefaultMember{
    private int age;

    /*public Over60Member(int membershipNumber, String name, String startMembershipDate, String nicNumber, String phoneNumber, int age, String nicNum) {
        super(membershipNumber, name, startMembershipDate, nicNumber, phoneNumber);
        this.age = age;
    }*/

    public Over60Member(int membershipNumber, String name, String startMembershipDate, String nicNumber, String phoneNumber, int age, String number) {
        super(membershipNumber, name, startMembershipDate, nicNumber, phoneNumber);
        this.age = age;
    }

    //===========================================================================================================================================================================


    //getters and setters
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        while(true)
            try {
                if(age >= 60) {
                    this.age = age;
                }
                break;
            } catch (Exception e) {
                System.out.println("Invalid !!! Age must be 60 and over");
                continue;
            }

    }
}
