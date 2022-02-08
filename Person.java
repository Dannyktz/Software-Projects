//public class Person for all the type of people(Contractor , Architect , Customer)
public class Person {

    //Attributes for all the information of Person
    //type is contractor.architect or customer
    String type;
    String name;
    String phone_m;
    String email;
    String address;


    ///Initiate the variables entered to the class
    public Person(String name,String email,String type,String phone_m,String address){
        this.name = name;
        this.email = email;
        this.type = type;
        this.phone_m = phone_m;
        this.address = address;

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
