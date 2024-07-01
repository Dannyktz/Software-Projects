/**
 * <br>
 * Java Person class
 * This Class used to create a person object
 * Includes : Contractor , Customer , Architect


 *
 * @author Danny Kahts
 * @version 1.00, 1 Feb 2022
 */
public class Person {

    /**<br>
     * Attributes for all the information of Person
    type is contractor.architect or customer*/
    String type;
    String name;
    String phone_m;
    String email;
    String address;
    int project_num;


    ///Initiate the variables entered to the class
    /**<br>
     * Constuctor for information given
     * Sets the object to correct values
     *
     * @param name Name of person
     * @param email Email of person
     *
     * ETC for phone,proj num and address
     */
    public Person(String name,String email,String type,String phone_m,String address,int project_num){
        this.name = name;
        this.email = email;
        this.type = type;
        this.phone_m = phone_m;
        this.address = address;
        this.project_num = project_num;

    }

    //public class that takes a String and replaces the number with the String
    public void change_number(String phone){
    this.phone_m = phone;}

    //public class that takes a String and replaces the email with the entered String
    public void change_email(String mail){
        this.email = mail;}

    //ToString that returns a String with all the information of the clas
    public String toString() {
        String output = "Name: " + name;
        output+= "\nphone number: " + phone_m;
        output+= "\nEmail: " + email;
        output+= "\nAddress : " + address;

        return  output;
    }
}
