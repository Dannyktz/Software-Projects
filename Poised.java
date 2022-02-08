
import java.nio.file.attribute.PosixFileAttributes;
//import scanner to take users input

import java.util.Scanner ;


public class Poised {

    //Main static class
    public static void main(String[] args) {

        //Initiate looper variable
        //Initiate Person objects for the people
        //Initiate the scanner for user input.
        boolean bLoop = false;
        Scanner in = new Scanner(System.in);
        Person Contractor, Architect,Customer;



        System.out.println("Enter details of project ");

        //Let the User enter the project details ( Call the function)
        Project User = Poised.i_proj();

        //Call the function that the Customers information can be entered
        System.out.println("Enter details of Customer : ");
         Customer =Poised.get_Per("Customer");

         //Call the function so that the Contractor's information can be entered
        System.out.println("Enter details of Contractor: ");
         Contractor = Poised.get_Per("Contractor");

         //While loop is false(this allows the user to return to the home menu and only close the program on input)
        while (bLoop==false){

            //Ask the user for their option in the menu
            System.out.println("");
            System.out.println("Please Chose Option" +
                    "\n1.Finalise project" +
                    "\n2.Change the due date of project" +
                    "\n3.Change the total amount of the fee paid to date" +
                    "\n4.Change detail of contractor"+
                    "\n5.Exit " );
            int Option = in.nextInt();


            //If the User chose 1 , Get the amount the user still needs to pay
            //If the price is not fully payed , print a invoice with the Customers information and the total price left
            //Make the finalized variable true
            //If price is 0 or smaller then Print that the full amount has been payed
            if (Option==1){
                float total_price = User.getPaid();

                if (total_price>0){
                    System.out.println("----INVOICE----");
                    System.out.println(Customer);
                    System.out.println("Outstanding Amount : R" + total_price);
                    User.finalized = true;

                }
                else
                    System.out.println("Amount already paid ! ");
            }


            else if (Option==2){
                String n_date= "" ;
                System.out.println("please enter new due date : ");
                n_date = in.next();
                System.out.println(n_date);

            }

            //if User chose 3 then ask the user the new amount they payed for the project
            //Change the Project's Class amount with the function amount_paid
            //Print the new information of the Project Class
            else if (Option==3){
                System.out.println("Enter new amount paid : ");
                float new_paid = in.nextFloat();
                User.amount_paid(new_paid);
                System.out.println(User);
        }

            //If User chose 4 then Ask user what contact information they would like to change
            else if (Option==4) {

                System.out.println("Options : " +
                        "\n1 : Change email : " +
                        "\n2 : Change phone number : ");

                int C_option = in.nextInt();


                //If user chose email, ask user for new email and call chane_email function and enter inputted value as parameter to change Contractors info
                if (C_option==1){
                    System.out.println("New Email : ");
                    String c_email = in.next();
                    Contractor.change_email(c_email);

                }
                //If user chose phone , ask user for new phone number , call the change_number function and change the phone number
                else if (C_option==2){
                    System.out.println("New phone number : ");
                    String c_phone = in.next();
                    Contractor.change_number(c_phone);

                }

                //if 1 or 2 wasn't selected print that the number is not valid
                else
                    System.out.println("Number not valid !");


                //print the information of the contractor
                System.out.println(Contractor);
            }

            //If user chose 5 , Make bLoop true (Stop loop and program will stop)
            else if (Option==5){
                bLoop= true;
                System.out.println("See you next time !");
            }
            else
                System.out.println("No option selected");


        }

        }

        //Private function that takes in the type of user(Contractor...)
        //Ask for all the input of the user
        //Initiate a new Person class and return that class

    private static Person get_Per(String user){
        Scanner cus = new Scanner(System.in);
        String t_user = user;



        System.out.println("Name of " + t_user+": ");
        String c_name = cus.next();

        System.out.println("Phone number of  "+ t_user+": ");
        String c_phone = cus.next();

        System.out.println("Email of  "+ t_user+": ");
        String c_mail = cus.next();

        System.out.println("Address of "+ t_user+": ");
        String c_address = cus.next();

        Person NewPers = new Person(c_name,c_mail,t_user,c_phone,c_address);

        return  NewPers;
    }



    //Function that will ask the user Questions about the Project
    //Take the users input on all information
    //Initiate all the information to the Project class
    //Return the Person class with the new info in
    private static Project i_proj(){
        Scanner sc = new Scanner(System.in);

        System.out.println("Name of project : ");
        String name = sc.nextLine();

        System.out.println("type of project(ex.House , building) : ");
        String type = sc.nextLine();

        System.out.println("Address of project : ");
        String address = sc.nextLine();


        System.out.println("Number of project : ");
        int number = sc.nextInt();

        System.out.println("Fee of the project : ");
        int fee = sc.nextInt();

        System.out.println("ERF number of project : ");
        int ERF = sc.nextInt();

        System.out.println("Amount paid to date : ");
        int paid = sc.nextInt();

        System.out.println("Deadline of project : ");
        String deadline = sc.next();



        Project User = new Project(name,number,fee,paid,ERF,type,address,deadline);

        return User;


    }





        }







