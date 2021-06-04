package sample;


import java.util.Objects;

public class DefaultMember implements Comparable<DefaultMember>{
    private int membershipNumber;
    private String name;
    private String startMembershipDate;
    private String nicNumber;
    private String phoneNumber;
    private String membershipType;

    public DefaultMember(int membershipNumber, String name, String startMembershipDate, String phoneNumber,String nicNumber) {
        super();
        this.membershipNumber = membershipNumber;
        this.name = name;
        this.startMembershipDate = startMembershipDate;
        this.phoneNumber = phoneNumber;
        this.nicNumber = nicNumber;

    }

    public DefaultMember() {

    }

    public int getMembershipNumber() {
        return membershipNumber;
    }

    public void setMembershipNumber(int membershipNumber) {
        this.membershipNumber = membershipNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStartMembershipDate() {
        return startMembershipDate;
    }

    public void setStartMembershipDate(String startMembershipDate) {
        this.startMembershipDate = startMembershipDate;
    }

    public String getNicNumber() {
        return nicNumber;
    }

    public void setNicNumber(String nicNumber) {
        this.nicNumber = nicNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getMembershipType() {
        return membershipType;
    }

    public void setMembershipType(String membershipType) {
        this.membershipType = membershipType;
    }

    @Override
    public int compareTo(DefaultMember o) {
        return this.getName().compareTo(o.getName());
    }
    public DefaultMember(int membershipNumber, String name, String startMembershipDate, String phoneNumber,String nicNumber,String membershipType) {
        super();
        this.membershipNumber = membershipNumber;
        this.name = name;
        this.startMembershipDate = startMembershipDate;
        this.nicNumber = nicNumber;
        this.phoneNumber = phoneNumber;
        this.membershipType = membershipType;
    }
    @Override
    public String toString() {
        return "Member{" +
                "membershipNumber=" + membershipNumber +
                ", name='" + name + '\'' +
                ", startMembershipDate='" + startMembershipDate + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", phoneNumber='" + nicNumber + '\'' +
                '}';
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DefaultMember)) return false;
        DefaultMember member = (DefaultMember) o;
        return membershipNumber == (member.membershipNumber) &&
                name.equals(member.name) &&
                startMembershipDate.equals(member.startMembershipDate) &&
                phoneNumber.equals(member.phoneNumber) &&
                nicNumber.equals(member.nicNumber) &&
                Objects.equals(membershipType, member.membershipType);
    }
    @Override
    public int hashCode() {
        return Objects.hash(membershipNumber, name, startMembershipDate, phoneNumber, membershipType,nicNumber);
    }
}

