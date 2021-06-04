package sample;


import javafx.scene.control.Label;

import java.io.*;
import java.util.*;


public class MyGymManager implements GymManager {
    private ArrayList<DefaultMember> memberList = new ArrayList<DefaultMember>();  //creating a list to store member details

    @Override
    public void addNewMember(DefaultMember member) {  //creating method to add members in the list
        if (memberList.size() < 100) {
            memberList.add(member);
            for (int i = 0; i < 1; i++) {
                //Displaying the outputs=====================================================
                System.out.print(member.getName() + " " + "Details are successfully added\n");
                System.out.println("No of occupied spaces : " + memberList.size());
                System.out.println("No of available spaces : " + (100 - memberList.size()));
            }
        } else {
            System.out.println("There are no free spaces available");   //creating a message when there is no available places
        }


    }

    @Override
    public boolean deleteMember(int membershipNumber) {  //creating a method to delete member details
        boolean flag = false;
        for (DefaultMember member : memberList) {
            if (Objects.equals(member.getMembershipNumber(), membershipNumber)) {
                flag = true;
                memberList.remove(member);
                //displaying the outputs========================================================================
                System.out.println("Member with the membership number " + membershipNumber + " Successfully removed");
                System.out.println("No of occupied slots : " + memberList.size());
                System.out.println("No of free slots: " + (100 - memberList.size()));
                //testing whether the member is instance of Student member or Over 60 member or default member
                if (member instanceof StudentMember) {
                    System.out.println("Member type is: StudentMember");
                } else if (member instanceof Over60Member) {
                    System.out.println("Member type is: Over60Member");
                } else {
                    System.out.println("Member type is: DefaultMember");
                }
                break;
            }
        }
        if (!flag) {
            System.out.println(membershipNumber + "is not found"); //If the details display the message
        }
        return false;
    }

    @Override
    public void printListOfMember() {  // Print the details of members
        for (DefaultMember member : memberList) {
            System.out.println("===========================================");
            System.out.println("Membership Number : " + member.getMembershipNumber() + " ");
            if (member instanceof StudentMember) {    //to test member is instance of Membership types(StudentMember,Over60Member,DefaultMember)
                System.out.println("Member type is : StudentMember");
                System.out.println("School Name:" + ((StudentMember) member).getSchoolName());
            } else if (member instanceof Over60Member) {
                System.out.println("Member type is : Over60Member");
                System.out.println("Age :" + ((Over60Member) member).getAge());
            } else {
                System.out.println("Member type is: DefaultMember");
            }
            //Printing the details of members ====================================
            System.out.println("First Name is :" + member.getName() + " ");
            System.out.println("NIC Number is :" + member.getPhoneNumber() + " ");
            System.out.println("Contact Number is :" + member.getNicNumber() + " ");
            System.out.println("Membership start date is :" + member.getStartMembershipDate());

        }
        if (memberList.size() == 0) {
            System.out.println("Empty List");  //If the details display the message
        }
    }

    @Override   //sorting member list
    public List<DefaultMember> sort() {

        //sorting the member names in ascending order
        ArrayList<String> memberData = new ArrayList<>();

        //get the names to the arraylist
        for (int i = 0; i < memberList.size(); i++) {
            String name = memberList.get(i).getName();
            memberData.add(name);
        }

        //array to store member names
        String[] array = memberData.toArray(new String[]{});

        //sorting names in ascending order
        BubbleSort.sort(array, true);

        //convert array to lsit
        List<String> sortList = Arrays.asList(array);

        //match member names with object
        for (String element : sortList) {
            for (DefaultMember o : memberList) {
                if (element.equals(o.getName())) {
                    System.out.println(o.toString());
                }
            }
        }

        return memberList;
    }


    @Override
    public void search(String searchValue, Label lbl) {
        DefaultMember[] members = memberList.toArray(new DefaultMember[]{});
        ArrayList<DefaultMember> arrayList = new ArrayList<>();
        File file = new File("searchMemberDetails.txt");
        try {
            Scanner scanner = new Scanner(file);


            while (scanner.hasNext()) {
                int membershipNumber = scanner.nextInt();
                String memName = scanner.next();
                String startMembershipDate = scanner.next();
                String nicNum = scanner.next();
                String phoneNumber = scanner.next();
                String schoolName = scanner.next();
                int memAge = scanner.nextInt();
                String membershipType = scanner.next();


                DefaultMember member = null;
                //inserting data into the table
                if (membershipType.equals("student")) {
                    member = new StudentMember(membershipNumber, memName,startMembershipDate,nicNum, phoneNumber, schoolName);

                } else if (membershipType.equals("over60")) {
                    member = new Over60Member(membershipNumber, memName, startMembershipDate, phoneNumber, membershipType, memAge,nicNum);

                } else {
                    member = new DefaultMember(membershipNumber, memName, startMembershipDate, phoneNumber, membershipType,nicNum);
                }
                arrayList.add(member);
            }


            String memName;
            int id;

            for (DefaultMember member : arrayList) {
                System.out.println(member.toString());
                if (searchValue.equals(member.getName()) || searchValue == String.valueOf(member.getMembershipNumber())) {
                    lbl.setText(member.toString());
                    break;
                } else {
                    lbl.setText("not found");
                }
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    @Override   //storing the member details
    public void save() {
        DefaultMember[] save = memberList.toArray(new DefaultMember[]{});
        File file = new File("memberDetails.txt");
        BufferedWriter output;


        try (FileWriter fileWriter = new FileWriter(file)) {
            output = new BufferedWriter(fileWriter);


            int q = memberList.size();
            for (int a = 0; a < memberList.size(); a++) {
                String schoolName;
                int age;

                if (memberList.get(a) instanceof StudentMember) {
                    schoolName = ((StudentMember) memberList.get(a)).getSchoolName();
                    output.write(
                            " Membership Number : " + memberList.get(a).getMembershipNumber()
                                    + " Name : " + memberList.get(a).getName()
                                    + " startMembershipDate : " + memberList.get(a).getStartMembershipDate()
                                    + " phoneNumber : " + memberList.get(a).getPhoneNumber()
                                    + " schoolName : " + ((StudentMember) memberList.get(a)).getSchoolName()
                                    + " NIC Name : " + memberList.get(a).getNicNumber()
                    );

                } else if (memberList.get(a) instanceof Over60Member) {
                    age = ((Over60Member) memberList.get(a)).getAge();
                    output.write(
                            " Membership Number : " + memberList.get(a).getMembershipNumber()
                                    + " Name : " + memberList.get(a).getName()
                                    + " startMembershipDate : " + memberList.get(a).getStartMembershipDate()
                                    + " phoneNumber : " + memberList.get(a).getPhoneNumber()
                                    + " Age : " + ((Over60Member) memberList.get(a)).getAge()
                                    + " NIC Name : " + memberList.get(a).getNicNumber()
                    );

                } else {
                    output.write(
                            " Membership Number : " + memberList.get(a).getMembershipNumber()
                                    + " Name : " + memberList.get(a).getName()
                                    + " startMembershipDate : " + memberList.get(a).getStartMembershipDate()
                                    + " phoneNumber : " + memberList.get(a).getPhoneNumber()
                                    + " NIC Name : " + memberList.get(a).getNicNumber()
                    );
                }


                System.out.println("saved successfully");

                output.flush();
                output.newLine();
            }

        } catch (IOException e) {
            System.out.println("No data to save");
        }
    }

    public void saveForSearch() {
        DefaultMember[] save = memberList.toArray(new DefaultMember[]{});
        File file = new File("searchMemberDetails.txt");
        BufferedWriter fileOut;

        try (FileWriter fileWriter = new FileWriter(file)) {
            fileOut = new BufferedWriter(fileWriter);


            int q = memberList.size();
            for (int a = 0; a < memberList.size(); a++) {
                String schoolName;
                int age;

                if (memberList.get(a) instanceof StudentMember) {
                    schoolName = ((StudentMember) memberList.get(a)).getSchoolName();
                    fileOut.write(
                            memberList.get(a).getMembershipNumber()
                                    + " " + memberList.get(a).getName()
                                    + " " + memberList.get(a).getStartMembershipDate()
                                    + " " + memberList.get(a).getPhoneNumber()
                                    + " " + memberList.get(a).getNicNumber()
                                    + " " + ((StudentMember) memberList.get(a)).getSchoolName()
                                    + " 0"
                                    + " student");

                } else if (memberList.get(a) instanceof Over60Member) {
                    age = ((Over60Member) memberList.get(a)).getAge();
                    fileOut.write(
                            memberList.get(a).getMembershipNumber()
                                    + " " + memberList.get(a).getName()
                                    + " " + memberList.get(a).getStartMembershipDate()
                                    + " " + memberList.get(a).getPhoneNumber()
                                    + " " + memberList.get(a).getNicNumber()
                                    + " null"
                                    + " " + ((Over60Member) memberList.get(a)).getAge()
                                    + " over60");

                } else {
                    fileOut.write(
                            memberList.get(a).getMembershipNumber()
                                    + " " + memberList.get(a).getName()
                                    + " " + memberList.get(a).getStartMembershipDate()
                                    + " " + memberList.get(a).getNicNumber()
                                    + " " + memberList.get(a).getPhoneNumber()
                                    + " null 0 default");
                }

                System.out.println("Details are saved successfully");

                fileOut.flush();    ///file.flush is use to filter the details to
                fileOut.newLine();
            }

        } catch (IOException e) {
            System.out.println("No data to save");
        }
    }

    //searching member details
    public static <T> int searchRecord(T[] array, T searchValue) {
        for (int i = 0; i < array.length; i++) {
            if (array[i].equals(searchValue)) {
                return i;
            }
        }
        return -1;
    }
}
