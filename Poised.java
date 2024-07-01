

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.*;
/**
 * <br>
 * Java project manager application
 * This Class project is used to capture , store and change information af on Project
 * It could be used to track past orders, the price and information about people
 * Working on the project
 *
 * @author Danny Kahts
 * @version 2.00, 17 Feb 2022
 */
public class Poised {


    //Main static class

    /**
     * Main Class that will run the project.
     * Variables an Arrays for all the Object types.
     * Scanner Initiated .
     * Empty Strings of Objects so that run won't fail.
     * @param
     */
    public static void main(String[] args) {


        Project[] projects ;
        Person[] people ;
        //Initiate looper variable
        //Initiate Person objects for the people
        //Initiate the scanner for user input.
        boolean bmenu =false;
        Person Contractor, Architect,Customer;
        Project User;
        Scanner sc = new Scanner(System.in);

        Contractor = new Person("","","","","",0);
        Customer = new Person("","","","","",0);
        Architect = new Person("","","","","",0);

        /**
         * <br>
         * Call the functions to set the arrays with Objects.
         */
        projects= ReadProjects();
        people = ReadPeople();


        while (bmenu==false) {

            /**
             * <br>
             * Ask user for choice input.
             */
            try {
                System.out.println("1:Add new project\n
                2:Select or update project\n3:See list of project that still need to be Completed" +
                        "\n4:See projects that are past due date\n5.Exit");
                int out = sc.nextInt();


                /**
                 * <br>
                 * IF user chose add new project-
                 * Call the functions to Add new Project,Customer,Contractor and Architect.
                 * Add new Project to array
                 */
                if (out == 1) {

                    //Let the User enter the project details ( Call the function)


                    //Call the function that the Customers information can be entered
                    System.out.println("Enter details of Customer : ");
                    Customer = Poised.get_Per("Customer", projects.length + 1);
                    people = Person_add(people, Customer);

                    //Call the function so that the Contractor's information can be entered
                    System.out.println("Enter details of Contractor: ");
                    Contractor = Poised.get_Per("Contractor", projects.length + 1);
                    people = Person_add(people, Contractor);

                    System.out.println("Enter details of Architect: ");
                    Architect = Poised.get_Per("Architect", projects.length + 1);
                    people = Person_add(people, Architect);

                    System.out.println("Enter details of project ");
                    User = Poised.Initialize_Project(projects.length + 1);



                    /**
                     * <br>
                     * If Project of User was not enter
                     * If the name has spaces in , take the last name and set project name to
                     * -Project type and Customer Surname
                     * Add the new project to array
                     */

                    if (User.name == null) {

                        if (Customer.name.contains(" ")) {
                            String[] newS = Customer.name.split("\\\\s+");
                            String surname = newS[newS.length - 1];
                            User.name = User.type + " " + surname;
                        } else {
                            Customer.name = User.type + " " + User.name;
                        }
                    }

                    projects = Proj_add(projects, User);


                    toFile(projects, people);


                    /**
                     * <br>
                     * If user chose to select project.
                     * Print all the projects
                     * Ask User if they want to select with name or number
                     *
                     */
                } else if (out == 2) {

                    long milis = System.currentTimeMillis();
                    User = new Project("", 0, 0, 0, 0, "", "", new Date(milis), false);

                    try {

                        for (int i = 0; i < projects.length; i++) {
                            System.out.println(projects[i]);
                            System.out.println("------------------------------");
                        }
                        System.out.println("1: Enter Project name\n2: Enter project number ");
                        int project_type = sc.nextInt();

                        /**
                         * <br>
                         * If user chose to name.
                         * Search the name in the project list and set the Object to correct Project and People.
                         *
                         */
                        if (project_type == 1) {
                            Scanner n = new Scanner(System.in);
                            System.out.println("ENTER PROJECT NAME:");
                            String name_entered = n.nextLine();
                            boolean found = false;

                            for (int i = 0; i < people.length; i++) {


                                String Test = projects[(people[i].project_num) - 1].name;


                                if (name_entered.toLowerCase().contains(Test.toLowerCase()) == true) {
                                    Architect = people[i];
                                    Contractor = people[i - 1];
                                    Customer = people[i - 2];
                                    User = projects[Customer.project_num - 1];
                                    found = true;
                                }
                            }
                            if (found == false) {
                                throw new RuntimeException();
                            }

                            /**
                             * <br>
                             * If user chose to enter Number.
                             * Take user input for number
                             * Search for the Number in people array
                             * If found set the correct Projects and people to the Objects
                             *
                             */
                        } else if (project_type == 2) {

                            Scanner n = new Scanner(System.in);
                            System.out.println("SELECT PROJECT NUMBER :");
                            int option = n.nextInt();

                            if (option==1){
                                User = projects[0];
                                Architect= people[2];
                                Contractor = people[1];
                                Customer = people[0];
                            }
                            else{

                            for (int i = 0; i < people.length; i++) {
                                if ((people[i].project_num == option)) {
                                    Architect = people[i];
                                    Contractor = people[i - 1];
                                    Customer = people[i - 2];
                                    User = projects[Customer.project_num - 1];
                                }
                            }

                         }
                        }
                        else
                            throw new RuntimeException();

                        /**
                         * <br>
                         * If no errors occurred then Call the new menu , to change information.
                         *
                         */
                        O_menu(Contractor, Customer, Architect, User);
                    } catch (Exception e) {
                        System.out.println("There was an error with your input");
                    }
                } else if (out == 3) {

                    System.out.println("Projects that still need to be Completed");

                    for (int i = 0; i < projects.length; i++) {
                        if (projects[i].finalized == false) {
                            System.out.println(projects[i]);
                            System.out.println("------------------------------");
                        }
                    }
                    /**
                     * <br>
                     * If User chose 4 Take the Current date , then loop through the projects
                     * If the project date is before current day , print that project information.
                     */
                } else if (out == 4) {

                    long milis = System.currentTimeMillis();
                    Date current = new Date(milis);
                    System.out.println("------------------------------");


                    for (int i = 0; i < projects.length; i++) {
                        Date deadline_date = projects[i].getDeadline();


                        if (deadline_date.before(current)) {
                            System.out.println(projects[i]);
                            System.out.println("------------------------------");
                        }
                    }

                    /**
                     * <br>
                     * If User chose 5 ,stop the loop and print all projects to file.
                     * iF no options were selected throw new exception
                     */
                } else if (out == 5) {
                    toFile(projects, people);
                    System.out.println("See you next time ! ");
                    bmenu = true;


                } else
                    throw new RuntimeException("No option selected");

            }catch (Exception e){
                System.out.println("There was an error with your input");
            }finally {
                toFile(projects,people);
            }

        }


        /**
         * <br>
         Call the toFile function that will print all the projedt
         */

        toFile(projects,people);
    }

    /**
     *
     * Calls New Menu
     * <br>
     * When user Chose to Select or Update a project
     * -This function will print the new menu with all options.
     *
     * This function was refactored from v1.
     * @since version 1.00
     * @throws
     */
    private static void O_menu(Person Contractor, Person Customer,Person Architect, Project User) {
        boolean bloop =false;

        while (bloop ==false) {
            try {
                Scanner in = new Scanner(System.in);
                //Ask the user for their option in the menu
                System.out.println("");
                System.out.println("Please Chose Option" +
                        "\n1.Finalise project" +
                        "\n2.Change the due date of project" +
                        "\n3.Change the total amount of the fee paid to date" +
                        "\n4.Change detail of contractor" +
                        "\n5.Display all Details of People  "+
                        "\n6.Back");
                int option = in.nextInt();


                /**If the User chose 1 , Get the amount the user still needs to pay
                //If the price is not fully payed , print a invoice with the Customers information and the total price left
                //Make the finalized variable true
                //If price is 0 or smaller then Print that the full amount has been payed*/
                if (option == 1) {
                    float total_price = User.getPaid();

                    if ((total_price > 0)&&(User.finalized==false)){
                        System.out.println("");
                        System.out.println("----INVOICE----");
                        System.out.println(Customer);
                        System.out.println("Outstanding Amount : R" + total_price);
                        System.out.println("---------------");
                        User.finalized = true;
                        Finalize(User);

                    } else if (User.finalized = false );
                        User.finalized = true;
                        System.out.println("Already Finalized ! ");
                }

                /**Ask the user for a new date
                Call the user classe's change_due function , print out the new information.
                 If there was an error it will go to the catch*/
                else if (option == 2) {

                    long milis = System.currentTimeMillis();
                    Date current = new Date(milis);
                    System.out.println("------------------------------");

                    Scanner dui = new Scanner(System.in);
                    System.out.println("Please enter new due date :(dd/MM/yyyy)");
                    String new_date = dui.nextLine();
                    Date duiDate = new SimpleDateFormat("dd/MM/yyyy").parse(new_date);

                        if (duiDate.before(current)) {
                            System.out.println("Date already passed");
                            System.out.println("------------------------------");
                            throw new RuntimeException();
                        }
                    User.change_due(duiDate);
                    System.out.println("-------------------");
                    System.out.println(User);
                    System.out.println("-------------------");

                }

                /**if User chose 3 then ask the user the new amount they payed for the project
                //Change the Project's Class amount with the function amount_paid
                //Print the new information of the Project Class*/
                else if (option == 3) {
                    System.out.println("Enter new amount paid : ");
                    float new_paid = in.nextFloat();

                    /**
                     * If the new amount is less ,less than 0 or more than fee raise exception .
                     */
                    if ((new_paid> User.paid)||(new_paid<0)||(new_paid>User.fee)){
                        throw new Exception("");
                    }
                    else{
                        User.amount_paid(new_paid);
                        System.out.println("----------------");
                        System.out.println(User);
                    }


                }

                /**<br>
                 * If User chose 4 then Ask user what contact information they would like to change
                 * If user chose email, ask user for new email and call chane_email function and enter inputted value as parameter to change Contractors info
                 *  If user chose phone , ask user for new phone number , call the change_number function and change the phone number*/
                else if (option == 4) {

                    System.out.println("Options : " +
                            "\n1 : Change email : " +
                            "\n2 : Change phone number : ");

                    int changeChoise = in.nextInt();



                    if (changeChoise == 1) {

                        System.out.println("New Email : ");
                        String person_email = in.next();

                        //If new email does not contain an @ sign then raise exception
                        if (person_email.contains("@")==false){
                            System.out.println("Does not contain @ Sign");
                            throw new RuntimeException("");
                        }
                        else
                            Contractor.change_email(person_email);

                    }
                    //If user chose phone , ask user for new phone number , call the change_number function and change the phone number
                    else if (changeChoise == 2) {
                        System.out.println("New phone number : ");
                        String person_phone = in.next();


                        //If the length of the number is not 10 or 12(2 spaces) raise exception
                        if ((person_phone.length()!=10)){
                            System.out.println("Number entered not valid!");
                            throw new RuntimeException();
                        }
                        else if ((person_phone.contains(" ") || (person_phone.length() != 12))) {
                            System.out.println(("Length of number not valid"));
                            throw new RuntimeException();
                        }
                        Contractor.change_number(person_phone);

                    }
                    //if 1 or 2 wasn't selected print that the number is not valid
                    else
                        throw new RuntimeException("List out of bound");;


                    //print the information of the contractor
                    System.out.println(Contractor);
                }

                //If user chose 5 , Make bLoop true (Stop loop and program will stop)
                else if (option == 5) {
                    System.out.println("----Customer----");
                    System.out.println(Customer);
                    System.out.println("");
                    System.out.println("----Contractor----");
                    System.out.println(Contractor);
                    System.out.println("");
                    System.out.println("----Architect----");
                    System.out.println(Architect);
                }
                else if (option==6){
                    bloop = true;
                }else
                    //Throw in Exception because list will be out of bound.
                    throw new RuntimeException();


            }catch (Exception e){
                System.out.println("There was an error with your input");
            }
        }
    }
    /**
     *
     * Adds New Project
     * <br>
     * Method is used to add a new Project to the array with Projects stored inside.
     *
     * @param projects Array with all the project Objects stored inside
     * @param new_p Project Object with information about the new projecy
     * @return A new array with the new Projects information in.
     *
     * @since version 2.00
     * @throws
     */

    private static Project[] Proj_add(Project[] projects,Project new_p) {
        Project[] t = new Project[projects.length + 1];
        int i;
        for(i = 0; i < projects.length; i++) {
            t[i] = projects[i];
        }
        t[i] = new_p;

        return t;
    }

    /**
     *
     * <</h >Finalize project
     * <br>
     * The methods Takes a Project and sets the Finalized to true
     * Then it prints that Objects information to a textfile

     * @param project Person Object array with all information of the People.
     * @since version 2.00
     * @throws
     */


    private static void Finalize(Project project){

        String w="";
        try {
            FileWriter fw = new FileWriter("Completed.txt", true);
            BufferedWriter bw = new BufferedWriter(fw);

            w+= project.name + ","+ project.number + "," + project.fee + "," +project.paid ;
            w+= "," + project.ERF + ","+project.type+"," + project.address + "," + project.deadline + "," + project.finalized+ "\n";;

            bw.write(w);
            bw.newLine();
            bw.close();
        }catch (Exception e){
            System.out.println("Error");
        }



    }

    /**
     *
     * Adds New Person
     * <br>
     * Method is used to add a new person to the array with people stored inside.
     *
     * @param person Array with all the Person Objects stored inside
     * @param new_p Person Object with infromation about new person
     * @return Person array with new Persons information with
     *
     * @since version 2.00
     */
    private static Person[] Person_add(Person[] person, Person new_p) {

        Person[] t = new Person[person.length + 1];
        int i;
        for(i = 0; i < person.length; i++) {
            t[i] = person[i];
        }
        t[i] = new_p;

        return t;
    }

    /**
     *
     * Read People
     * <br>
     * The methods Writes all the information about the all Projects and People to a textfile.
     * The Textfile will then be used to read from again later.
     *
     * @param project Array with all the Projects Objects stored inside
     * @param person Array with all the Person Objects stored inside
     *
     * @since version 2.00
     * @throws
     */
    public static void toFile(Project[] project,Person[] person){

    try {
    File x = new File("People.txt");
    FileWriter writer = new FileWriter(x);
    String w = "" ;

    for (int i=0;i<person.length;i++){
         w+= person[i].name + ","+ person[i].email + ","+ person[i].type + "," + person[i].phone_m + "," + person[i].address + "," + person[i].project_num+ "\n";

    }
    writer.write(w);
    writer.close();

    }catch (Exception e){
    System.out.println("An error has occurred");
    }

        try {
            File x = new File("C:Projects.txt");
            FileWriter writer = new FileWriter(x);
            String w = "" ;

            for (int i=0;i<project.length;i++){
                DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                String date =dateFormat.format(project[i].deadline);
                w+= project[i].name + ","+ project[i].number + "," + project[i].fee + "," +project[i].paid ;
                w+= "," + project[i].ERF + ","+project[i].type+"," + project[i].address + "," + date+ "," + project[i].finalized+ "\n";;

            }
            writer.write(w);
            writer.close();

        }catch (Exception e){
            System.out.println("An error has occurred");
        }





    }

    /**
     *
     * Read Projects
     * <br>
     * The methods Reads all the information about Projects to an Project array
     *
     * Reads the information from a textfile,Creates a new object and Store the object to an Array.
     *
     * @return Project Object array with all information of the projects.
     * @since version 2.00
     * @throws
     */

    public static Project[] ReadProjects(){

        ArrayList<Project> projects= new ArrayList<Project>();
        Project[] test = projects.toArray(new Project[0]);

        try {
            BufferedReader in = new BufferedReader(
                    new FileReader("Projects.txt"));
            String str;

            while ((str = in.readLine())!= null) {
                String[] ar=str.split(",");
                boolean f;

                if (ar[8].contains("false")==true){
                    f = false;
                }
                else
                    f=true ;


                Date date1=  new SimpleDateFormat("dd/MM/yyyy").parse(ar[7]);
                Project Read = new Project(ar[0],Integer.parseInt(ar[1]),Float.parseFloat(ar[2]),Float.parseFloat(ar[3]),Integer.parseInt(ar[4]),ar[5],ar[6],date1,f);
                projects.add(Read);

            }

           test = projects.toArray(new Project[0]);
            in.close();

        } catch (Exception e) {
            System.out.println("File Read Error");
        }

        return test;
    }

    /**
     *
     * Read People
     * <br>
     * The methods Reads all the information about People for the project to an array
     * of the Person Object type.
     * Reads the information from a textfile,Creates a new object and Store the object to an Array.
     *
     * @return Person Object array with all information of the People.
     * @since version 2.00
     */


    public static Person[] ReadPeople(){

        ArrayList<Person> projects= new ArrayList<Person>();
        Person[] test = projects.toArray(new Person[0]);

        try {
            BufferedReader in = new BufferedReader(
                    new FileReader("People.txt"));
            String str;

            while ((str = in.readLine())!= null) {
                String[] ar=str.split(",");

                Person Read = new Person(ar[0],ar[1],ar[2],ar[3],ar[4],Integer.parseInt(ar[5]));

                projects.add(Read);

            }

            test = projects.toArray(new Person[0]);


        }
        catch (Exception e) {
            System.out.println("Error 101");
        }

        return test;
    }


    /**
     *
     * Person Capture.
     * <br>
     * The methods Asks User information about the Person and Returns the Person Object
     * Questions Asked: Name,email,number,Address
     *
     * @param user the type of user for project (Contractor,Architect)
     * @return Person Object with all information stored
     * @since version 1.00
     */
    private static Person get_Per(String user,int proj_num) {


        //Added a try catch in a while loop to determine that the user enters the correct input
        //Initiate Person class as empty , sothat return function does not fail
        boolean scan = true;
        Person NewPers = new Person("", "", "", "", "",0);
        String person_name;

        while (scan == true) {
            try {
                Scanner cus = new Scanner(System.in);
                String t_user = user;

                System.out.println("Name of " + t_user + ": ");
                person_name = cus.nextLine();

                System.out.println("Phone number of  " + t_user + ": ");
                String person_phone = cus.next();

                //If the length of the number is not 10 or 12(2 spaces) then raise exception
                if ((person_phone.length()!=10)) {
                    System.out.println(("Length of number not valid"));
                    throw new RuntimeException();
                }

                System.out.println("Email of  " + t_user + ": ");
                String person_mail = cus.next();

                if (person_mail.contains("@")==false){
                    System.out.println("Email not valid , does not contain @ sign");
                    throw new RuntimeException();
                }


                Scanner c = new Scanner(System.in);
                System.out.println("Address of " + t_user + ": ");
                String person_address = c.nextLine();

                NewPers = new Person(person_name, person_mail, t_user, person_phone, person_address,proj_num);

                //It will Return the correct Person if there are no errors.(The loop will stop)
                return NewPers;

                //If there are exceptions print to user
            } catch (Exception e) {
                System.out.println("There was an error with your input");
            }
        }
        return NewPers;
    }



    /**
     *
     * Project Capture.
     * <br>
     * The methods Asks User information about the Project and Returns the Object
     * Test the information in a try to make sure input is correct
     *
     * @param number the first value
     * @return Project Object with information
     * @since version 1.00
     * @throws
     */
    private static Project Initialize_Project(int number){

        //Initiate boolean that scans and empty Project class sothat return function wont fail
        boolean bScan = true;
        Calendar cal = Calendar.getInstance();
        Date date = cal.getTime();

        Project User = new Project("",0 , 0, 0, 0, "", "", date,false);

        while (bScan==true) {
            try {

                //Initiate the scanner
                Scanner sc = new Scanner(System.in);

                System.out.println("Name of project : ");
                String name = sc.nextLine();

                System.out.println("type of project(ex.House , building) : ");
                String type = sc.nextLine();

                System.out.println("Address of project : ");
                String address = sc.nextLine();


                //If any of the integers are less than 0 raise an exception

                if (number<0 )
                    throw new RuntimeException();

                System.out.println("Fee of the project : ");
                int fee = sc.nextInt();

                if (fee<0)
                    throw new RuntimeException();

                System.out.println("ERF number of project : ");
                int ERF = sc.nextInt();

                if (ERF<0 )
                    throw new RuntimeException();

                System.out.println("Amount paid to date : ");
                int paid = sc.nextInt();

                if ((paid<0 )||(paid>fee)) {
                    System.out.println("Paid is less than 0 or more than fee");
                    throw new RuntimeException();
                }

                Scanner dui = new Scanner(System.in);
                System.out.println("Please enter new due date :(dd/MM/yyyy)");
                String n_date = dui.nextLine();
                Date duiDate = new SimpleDateFormat("dd/MM/yyyy").parse(n_date);

                long milis = System.currentTimeMillis();
                Date current = new Date(milis);


                if (duiDate.before(current)) {
                    System.out.println("Date already passed");
                    System.out.println("------------------------------");
                    throw new RuntimeException();
                }


                System.out.println("You have successfully entered your project");


                User = new Project(name, number, fee, paid, ERF, type, address, duiDate,false);

                return User;
            } catch (Exception e) {
                System.out.println("There was an error with your input");
            }

        }

            return User;
    }




        }







