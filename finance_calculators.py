import math   ## import math to use math.pow

### Let the user enter if they want a bond or investment and save it to proper variable 
### use /t and /n to make the layout and spacing of the output as given .

chosen =input("""Choose either 'investment' or 'bond' from the menu below to proceed:\n
\ninvestment\t - to calculate the amount of intterest you'll earn on interest
\nbond\t\t - to calculate the amount you'll have to pay on a home loan\nEnter Value :""")


## if the input is an investment , gather all the information and store it to the correct variable
## Divide i_rate by 100 to get the decimal value of the rate
## Ask the user if they want simple or compound interest and save it to the correct variable

if chosen.lower() == "investment":  
    amount = float(input("Amount you are depositing"))
    i_rate = float(input("Enter your interest rate in %"))
    i_rate = i_rate/100
    i_year = int(input("Howmany years would you like to invest?"))
    interest = input("Simple or Compound interest?")

    ### IF the interest is simple then use calculations to see what the investment will be after the amount of years entered
    ##Round to 2 decimals and print the amount of years and the total of the investment

    if (interest.lower() == 'simple') or (interest.lower() == 'simple interest'):
        Total = amount*(1+(i_rate)*i_year)
        Total = round(Total,2)
        print(f"in {i_year} years your investment will be : R{Total}")
    
    ##Use elif if compound is selected to determine the value of the investment after the chosen amount of years
    ##Round to 2 decimals and print the amount of years and the total of the investment

    elif (interest.lower() == 'compound') or (interest.lower() == 'compound interest'):
        Total = amount*math.pow((1+i_rate),i_year)
        Total = round(Total,2)
        print(f"in {i_year} years your investment will be : R{Total}")
    
     ##If simple or compound was not entered print an error statement .
    else:
        print("simple or compound not entered , please restart program and enter correct form of interest ")


    ##If the bond option was chosen ask the user a series of questions and save them to the correct variable
    ##b_rate divide by 100 and 12 to get monthly rate
    ##use calculations to determine the price the user has to pay for the amount of months .
    ##Print the amount and months to the user.

elif chosen.lower() == "bond":
    b_value = float(input("Current Value of the house?")) 
    b_rate = float(input("Enter your interest rate in %"))
    b_rate= (b_rate/100)/12
    b_months = int(input("Number of months planned to repay bond"))
    Total = (b_rate*b_value)/(1-(1+b_rate)**(-b_months))   ##(i.P)/(1 - (1+i)^(-n))

    Total = round(Total,2)
    print(f"You will have to pay R{Total} for {b_months} months ")
 
    #Use else to display error message if bond or investment was not entered.

else:
    print("You have not selected one of the options ")

