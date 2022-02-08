#Import math module to to use in further code
#Import the tabulate module to display a table
import math
from tabulate import tabulate
#Function that takes a string and return the Position of the comma
def pos(input):
    Pos = input.find(',')
    return Pos


# Main class that will initiate the info into the correct variables
# Class will be used for each product
#>cost >country >product >quantity >price >Value
class Shoe(object):


    # #initiate the info to the class
    def __init__(self,info):
        self.info = info

    # Function that reads the info from the line to the correct variables , that are seperated by commas
    # strip the \n to make it easier to work with the lines
    def read_data(self):

        
            
        try:
                input = self.info
                Pos = pos(input)
                self.country = input[0:Pos]
                input = input[Pos+1::]
                self.code = input[0:pos(input)]
                input = input[pos(input)+1::]
                self.product = input[0:pos(input)]
                input = input[pos(input)+1::]
                self.cost = int(input[0:pos(input)])
                input = input[pos(input)+1::]
                self.quantity =int(input.strip('\n'))


        # If there are any exceptions display an error message to the user
        except Exception:
            print("An error has accurred")
    
    # A function that worksout the value of the product and set the value to a variable
    def value_per_item(self):
        value = self.cost* self.quantity
        self.value = value

    # A function that updates the info variable when another variable has been changed
    def update(self):
        self.info = f"{self.country},{self.code},{self.product},{self.cost},{self.quantity}\n"
        

    




# Have a list where the objects will be stored in
list=[]

# Have a function what opens the file and initiates each line of the file to a object and append the object to a list
# call the Read the data function that initaites the line to correct variables
# Call the value per item function that will calculate and add the value variable
# Append the new object
def file_list():
    with open('inventory.txt',"r+") as f:
        next(f)
        for line in f:
            new = Shoe(line)
            new.read_data()
            new.value_per_item()
            list.append(new)


# Have a file update variable that emptys the textfile and writes the info backto the textfile after working with the data
# #Truncate clears the textfile
def file_update():
    with open('inventory.txt',"r+") as f:
        f.truncate(0)
        f.write("Country,Code,Product,Cost,Quantity\n")
        for obj in list:
            f.write(obj.info)
            
            
# Function that searches for the lowest quantity and return that index
# Append the quantity of each obj to a new list
# Determine the minimum value and the index of the mini
# Return the index with the lowest value   
def lowest_q():
    qList = []
 
    for obj in list:
        qList.append(obj.quantity)

    mini = min(qList)
    min_index = qList.index(mini)
    return min_index

# Function that searches for the highest quantity and return that index
# Append the quantity of each obj to a new list
# Determine the max value and the index of the max
# Return the index with the max value
def highest_q():
    qList = []

    for obj in list:
        qList.append(obj.quantity)

    maxi = max(qList)
    max_index = qList.index(maxi)
    return max_index

# A function that takes the code as a parameter
# Have a boolean for if the code is found
# search through the list of objects for a object with the same code as the parameter
# If code found then Make found true and return the info of that object
# if found is false return Not found to user
def search_c(code):
    Found =False
    for obj in list:
        if obj.code ==code:
            Found = True
            return obj.info
            
    
    if Found==False:
        return "Not found"
    

# Call file list that reads from the textfile to the lists of objects
file_list()

# while true try that the usere enters a value
# if there are exceptions print a error to the user
while True:
    try:
        value = int(input('''\t1:Search product
        2:Product with the lowest quantity
        3:Product with the highest quantity
        4:Represent all the data in table
        Value : '''))
        break

    except Exception:
        print("input invalid")


# if value is 1 then ask user to enter code of a product
# call the search_c function to search the code
if value==1:
    name = input("Please enter product code : ")
    print(search_c(name))

# if value entered is 2 then call lowest_q function then print the info of the list with the lowest quantity
elif value==2:
    index = lowest_q()
    print(list[index].info)
    
    # while True try that user enteres a amount
    # if Valueerrors then print that value entered is wrong
    while True:
        try:
            new_value = int(input("Please enter amount you would like to restock to : "))
            break
        except ValueError:
            print("Value entered is incorrect")

        
    # finally if no errors : change the quantity of the object
    # call update function that will update the objects info
    # print the new value
        finally:
            list[index].quantity = new_value
            list[index].update()
            print(list[index].info)

# if user entered 3 then call highest_q function that will return the index of highest quantity 
# print the info of that list
elif value==3:
    
    index = highest_q()
    print(list[index].info)
    
    # While true try that user entered the new price of the product 
    # if there are exceptions print the error
    # If no errors were found Change the cost of the object,if there is no (S) in die line then append the (S) to the product (S stand for sale)
    # update the object
    # print the new information to the user
    while True:
        try:
           new_cost = int(input("Please enter the sale price of the product : "))
           break
        except Exception:
            print("Value entered is invalid")
        finally:
            list[index].cost = new_cost
            if not(list[index].product.find("(S)")>0):
                list[index].product += "(S)"
            list[index].update()
            print(list[index].info)


# if the user chose 4 then initiate the header of the table
# have a list for the table >table[]
# for the length of the list with obj in append the information to the table list
# add value to the tValue
# append the total of all product to the end
# print the table with all the products
elif value==4:
    header = ["Country","Code","Product","Cost","Quantity","Value"] 
    table= []
    sale = []
    tValue = 0

    for i in range(len(list)):
        table.append([list[i].country,list[i].code,list[i].product,f"R{list[i].cost}",list[i].quantity,f"R{list[i].value}"])
        tValue += list[i].value
        
        ##if item is up for sale append to new list
        if list[i].product.find('(S)')>0:
            sale.append([list[i].country,list[i].code,list[i].product,f"R{list[i].cost}",list[i].quantity,f"R{list[i].value}"])

    
    ##appent the total and the items up for sale
    ##print graph to user
    table.append(["TOTAL :","","","","",f"R{tValue}"])
    table.append(["Items for sale"])
    print(tabulate(table+sale,headers= header))
    

# Else print that the option was not found
else:
    print("That is not one of the options !")

    


##update the textfile with this function
file_update()








