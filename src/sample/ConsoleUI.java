package sample;

import javafx.application.Application;

import java.io.*;
import java.util.Scanner;

public class ConsoleUI {
    private final static MyGymManager manager = new MyGymManager();
    private static int count = 1;

    public static void main(String[] args) throws IOException {
        Scanner input = new Scanner(System.in);
        System.out.println("=====================================================================");
        System.out.println("*********************ONLINE GYM MANAGEMENT SYSTEM********************");
        System.out.println("=====================================================================");
        System.out.println(" ");
        for (int i = 0; i < 100; i++) {
            System.out.println("  ");
            System.out.println("AVAILABLE OPTIONS");
            System.out.println("-----------------------------");
            System.out.println("(1) - Add New Member" );
            System.out.println("(2) - Delete a Member" );
            System.out.println("(3) - Print the List of Members" );
            System.out.println("(4) - Sort" );
            //System.out.println("(5) - View the List of Members" ); //get member list
            System.out.println("(5) - Search the Members" );
            System.out.println("(6) - Quit" );
            System.out.print("Enter the option : ");
            int option = input.nextInt();
            switch (option) {
                case 1:
                    addNewMember();
                    break;
                case 2:
                    deleteMember();
                    break;
                case 3:
                    manager.printListOfMember();
                    break;
                case 4:
                    manager.sort();
                    break;
                /*case 5:
                    Application.launch(ViewDetails.class, args); //Not Implemented
                    break;*/
                case 5:
                    Application.launch(SearchMember.class, args);
                    break;
                case 6:
                    System.out.println("QUIT");
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid option is entered");
            }
        }
    }


    //METHOD FOR ADDING A MEMBER====================================================
    private static void addNewMember() throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter("memberDetails.txt", true));
        Scanner input = new Scanner(System.in);

        if (count <= 100) {
            //===========================USER INPUTS==============================

            int membershipNumber = count;

            System.out.print("Enter the Membership Number : " + membershipNumber);   //getting the Membership number as a input from the user


            System.out.print("\nEnter the Member Name : ");  //getting the Member name as a input from the user
            String name = input.nextLine();
            while (!name.matches("[a-zA-Z]+")) {
                System.out.println("Please enter a valid name!!!");
                System.out.print("Enter the Member Name : ");
                name = input.nextLine();
            }

            System.out.print("Enter the Start Membership Date(dd-mm-yyyy) : ");
            String startMembershipDate = input.next();


            System.out.print("Enter the NIC Number : ");  //getting the NIC number as a input from the user
            String nicNumber = input.next();
            while (!nicNumber.matches("^([0-9]{9}[x|X|v|V]|[0-9]{12})$+")) {
                System.out.println("Invalid NIC number!!!");
                System.out.print("Enter the NIC Number : ");
                nicNumber = input.next();
            }


            System.out.print("Enter the Phone number : ");
            String phoneNumber = input.next();
            while (!phoneNumber.matches("[0-9]{10}$+")) {
                System.out.println("Invalid phone number!!!");
                System.out.print("Enter the Phone number : ");
                phoneNumber = input.next();
            }

            System.out.print("Enter the Membership Type  ((D/d) - Default Member/ (S/s) - Student Member/ (O/o)- Over 60 Member) : ");
            String options = input.next();
            DefaultMember member = null;
            while (!(options.equals("d") || options.equals("D") || options.equals("S") || options.equals("s") || options.equals("O") || options.equals("o"))){
                System.out.println("Invalid Enter the correct membership type");
                System.out.print("Enter the Membership Type  ((D/d) - Default Member/ (S/s) - Student Member/ (O/o)- Over 60 Member) : ");
                options = input.next();
            }



                switch (options) {
                    case "D":
                    case "d":
                        System.out.println("Default Member");
                        member = new DefaultMember(membershipNumber, name, startMembershipDate, nicNumber, phoneNumber);
                        break;
                    case "S":
                    case "s":
                        System.out.println("Student Member");
                        System.out.println("Fill the details given below");
                        System.out.println("\n--------------------------");
                        String schName = input.nextLine();
                        while(!schName.matches("[a-zA-Z]+")) {
                            System.out.print("Enter the School name : ");
                            schName = input.nextLine();
                        }


                        member = new StudentMember(membershipNumber,  name, startMembershipDate, nicNumber, phoneNumber, schName);
                        break;
                    case "O":
                    case "o":
                        System.out.print("Enter the age : ");
                        int age = input.nextInt();
                        while(!(age > 60 && age <100)){
                            System.out.println("Invalid!! enter the valid age");
                            System.out.print("Enter the age : ");
                            age = input.nextInt();
                        }
                        member = new Over60Member(membershipNumber, name, startMembershipDate, nicNumber, phoneNumber, age, nicNumber);
                        break;
                    default:
                        System.out.println("Invalid option is entered!!!");



                }
                manager.addNewMember(member);
                count++;
                bw.write("Membership Number : " + membershipNumber +"\t   "+ "Member Name : " + name+"\t         "
                        + "Membership type : " + options +"\t   "+ "Phone number : " + phoneNumber +"\t       "+ "NIC Number : " + nicNumber +"\t   "+ "Start Membership Date : " + startMembershipDate);
                bw.flush();
                bw.newLine();
                bw.close();

        } else {
            System.out.println("Spaces are not available for the registration");
        }



    }



    //METHOD FOR DELETING A MEMBER===========================================
    private static void deleteMember() throws IOException {
        File details = new File("memberDetails.txt");
        File data = new File("database.txt");
        BufferedReader br = new BufferedReader(new FileReader(details));
        BufferedWriter bw = new BufferedWriter(new FileWriter(data));


        Scanner input = new Scanner(System.in);
        System.out.print("Enter the Membership Number : ");
        String membershipNumber = input.next();
        String result;
        manager.deleteMember(Integer.parseInt(membershipNumber));


        while ((result = br.readLine()) != null) {

            if(result.contains(membershipNumber))
                continue;

            bw.write(result);
            bw.flush();
            bw.newLine();

        }

        br.close();
        bw.close();
        details.delete();
        data.renameTo(details);
    }


}
