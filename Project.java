//Class for the project
public class Project {

    //Attributes for all the information of the project
    int number;
    float fee ;
    int ERF ;
    float paid ;
    String name;
    String type;
    String address;
    String deadline;
    boolean finalized ;


    //Initiate the class to the correct variables
    public Project(String name,int number,float fee,float paid,int ERF,String type,String address,String deadline){
        this.number = number ;
        this.fee = fee ;
        this.ERF = ERF ;
        this.name = name;
        this.type = type;
        this.address =  address;
        this.deadline = deadline ;
        this.paid = paid;
        this.finalized = false;
    }


    //Function that changes the due date of the project , takes in a String as a parameter
    public void change_due(String date){
        this.deadline = date;

    }

    //Changes tha total amount paid sofar , float as a parameter
    public void amount_paid(float price){
        this.paid = price ;
    }


    //Function that will return the remainder of the price
    //Calculated by subracting the price paid by the fee
    public float getPaid() {
        float remain = this.fee - this.paid;

        return remain;
    }

    public String toString(){
        String output = "Project number : " + this.number ;
                output+= "\nProject name : " + this.name ;
                output+= "\nProject type : " + this.type ;
                output+= "\nAddress for the project : " + this.address ;
                output+= "\nERF number  : " + this.ERF ;
                output+= "\nTotal fee for project  : R" + this.fee ;
                output+= "\nAmount paid to date : " + this.paid ;
                output+="\nDeadline date  : " + this.deadline;

        return  output;


    }


}
