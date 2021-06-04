package sample;

public class Date extends DefaultMember{
    private int year;
    private int month;
    private int day;

    public Date(int membershipNumber, String name, String startMembershipDate, String nicNumber, String phoneNumber, int year, int month, int day) {
        super(membershipNumber, name, startMembershipDate, nicNumber, phoneNumber);
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public void setDay(int day) {
        if(day >= 1 && day <= 31) {
            this.day = day;
        }else{
            throw new IllegalArgumentException("Invalid day please re-enter");
        }
    }

    public int getDay() {
        return day;
    }

    public String setMonth(int month) {
        if(month >= 1 && month <= 12) {
            this.month = month;
        }else if(month >= 0 && month <= 12) {
            String[] stringMonth = {"", "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
            return stringMonth[month];
        }else {
            throw new IllegalArgumentException("Invalid day please re-enter");
        }
        return null;
    }

    public int getMonth() {
        return month;
    }

    public void setYear(int year) {
        if(year >= 1900) {
            this.year = year;
        }else{
            throw new IllegalArgumentException("Invalid year please re-enter");
        }

    }

    public int getYear() {
        return year;
    }
}
