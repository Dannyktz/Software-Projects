
import java.util.Date;
/**
 * <br>
 * Java Project class
 * This Class used to create a object for a project


 *
 * @author Danny Kahts
 * @version 1.00, 1 Feb 2022
 */
//Class for the project
public class Project {

/**
 * <br> Attributes for all the information of the project
 *
  */
    int number;
    float fee ;
    int ERF ;
    float paid ;
    String name;
    String type;
    String address;
    Date deadline;
    boolean finalized ;


    /**
     * <br>
     * This is the Constructor
     * @param name :Sets name entered to object name
     * @param number :Sets number entered to object numbr
     * @param fee : Sets fee to object fee
     *
     * ETC for rest of information
     */
    public Project(String name,int number,float fee,float paid,int ERF,String type,String address,Date deadline,boolean f){
        this.number = number ;
        this.fee = fee ;
        this.ERF = ERF ;
        this.name = name;
        this.type = type;
        this.address =  address;
        this.deadline = deadline ;
        this.paid = paid;
        this.finalized = f;
    }


    /**Function that changes the due date of the project ,
     * @param date , the date the deadline will change to*/
    public void change_due(Date date){
        this.deadline = date;

    }

    /**Changes tha total amount paid sofar
     * @param price the new price paid */
    public void amount_paid(float price){
        this.paid = price ;
    }


    /**Function that will return the remainder of the price
    //Calculated by subracting the price paid by the fee*/
    public float getPaid() {

        return this.fee - this.paid;
    }

    public Date getDeadline(){
        return this.deadline;
    }

    /**A to string method that prints all the information about the project
     * @return The String with all information*/
    public String toString(){
        String output = "Project number : " + this.number ;
                output+= "\nProject name : " + this.name ;
                output+= "\nProject type : " + this.type ;
                output+= "\nAddress for the project : " + this.address ;
                output+= "\nERF number  : " + this.ERF ;
                output+= "\nTotal fee for project  : R" + this.fee ;
                output+= "\nAmount paid to date : R" + this.paid ;
                output+="\nDeadline date  : " + this.deadline;
                output+= "\nFinalized : " + this.finalized;

        return  output;


    }


}
