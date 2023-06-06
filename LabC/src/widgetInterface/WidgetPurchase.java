/*
Abe Brege
Lab C
2/26/23
*/
package widgetInterface;
import java.text.NumberFormat;
import java.util.Scanner;

public class WidgetPurchase {

    public static void main(String[] args) {
        System.out.println("Welcome to the Widget Order Processing System."); //greeting message
        
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(); //setting up currency instances

        String customerName; //variable declarations
        String purchasedProduct;
        String productCode;
        String productDescription = null;
        double pricePerWidget = 0;
        double discountRate = 0;
        double orderSubtotal;
        double discountAmount;
        double preTaxTotal;
        double taxAmount;
        double finalTotal;
        double postTaxTotal;
        int orderNumber = 0;
        String nextOrder = "y";
        boolean validWidget = false;
        boolean validQuantity = false;
        double productQuantity = 0;
        String stateShipped;
        boolean correctState = false;
        double shippingCost = 0;
        
        while(!nextOrder.equalsIgnoreCase("n")){
            
            Scanner sc; //setting up scanner
            sc = new Scanner(System.in);
            String input;
        
            System.out.print("Enter your name: "); //getting customers name
            input = sc.nextLine();
            customerName = input;
        
     
        
            do{
                System.out.print("Enter what product you wish to purchase: "); //getting the product the customer wants to purchase
                input = sc.nextLine();
                purchasedProduct = input.toUpperCase();//making the pruchasedProduct variable the input from the user
                
                
                switch (purchasedProduct) {//switch statment that is switching the case depending on what the user has entered and then assigning the appropriate variables. 

                case "W01":
                    productCode = "W01"; //setting product code variable
                    productDescription = "Small Widget"; //setting description variable
                    pricePerWidget = .75; //setting price per widget
                    orderNumber ++; //adding one to the order number
                    validWidget = true;//stopping the loop
                    break;
         
                case "W02":     
                    productCode = "W02";
                    productDescription = "Medium Widget";
                    pricePerWidget = 1.25; 
                    orderNumber ++;
                    validWidget = true;
                    break;
                
                case "W03":
                    productCode = "W03";
                    productDescription = "Large Widget";
                    pricePerWidget = 1.50; 
                    orderNumber ++;
                    validWidget = true;
                    break;
                
                case "W04":
                    productCode = "W04";
                    productDescription = "Extra Large Widget";
                    pricePerWidget = 2.25;
                    orderNumber ++;
                    validWidget = true;
                    break;
                default: 
                validWidget = false; //continuing the loop since there was no valid product code
                System.out.println("\nIt looks like you entered an invalid product code\nPlease enter one of the following codes: W01, W02, W03, or W04\n" );
                //^^ prompting the user to enter in the right input
                break;
                }
            }while(!validWidget);//keeping them in a loop until they enter a valid product code
            
            
            
            
            do{
                System.out.print("Enter the quantity you wish to purchase: "); //getting the product the customer wants to purchase
                try{
                    input = sc.nextLine();
                    productQuantity = Double.parseDouble(input); //string to double conversion to account for values less than 1 (decimals)
                        if(productQuantity >= 1 && productQuantity <= 1000){//checking to see if the product is even in the scope to begin with to run the function                   
                            discountRate = getDiscount(productQuantity);//function runs for discount if the value is valid
                            shippingCost = getShipping(productQuantity);//function runs for shipping cost if value is valid
                            validQuantity = true;
                        }
                        else{
                            validQuantity = false;
                            System.out.println("Please enter a quantity between 1 and 1000.");//instructions on how to enter correct quantity
                            }
                    } 
                catch(NumberFormatException e){
                    System.out.println("Error! Not a valid number. Please try again. \n");
                }
                            
            }while(!validQuantity);//back through loop
            
            //making the console easier to read by not outputting this string every time the loop repeats
            System.out.print("\nWill you need shipping to: \nMichigan -- MI\nOhio -- OH\nIllinois -- IL\nIndiana -- IN\nOr Wisconsin -- WI");
            
            do{//getting the state shipped to
                System.out.print("\nEnter a two character state-code: "); //getting the the state the customer wants to ship to
                input = sc.nextLine();
                stateShipped = input.toUpperCase();//making the stateShipped variable the input from the user
                switch(stateShipped){
                    case "MI":
                        stateShipped = "MI";
                        correctState = true;
                        break;
                    
                    case "OH":
                        stateShipped = "OH";
                        correctState = true;
                        break;
                        
                    case "IL":
                        stateShipped = "IL";
                        correctState = true;
                        break;
                        
                    case "IN":
                        stateShipped = "IN";
                        correctState = true;
                        break;
                        
                    case "WI":
                        stateShipped = "WI";
                        correctState = true;
                        break;
                        
                    default:
                        correctState = false;
                        System.out.println("\nYou entered an invalid state code. Please enter one of the following:\nMI, OH, IL, IN, or WI");
                        //^letting the user know what they can and cant enter
                        break;
                }          
            }while(!correctState);

            

                
                
            orderSubtotal = productQuantity * pricePerWidget; //getting the subtotal of the order
            double taxRate = getTax(stateShipped, orderSubtotal); //using getTax to obtain tax
            discountAmount = orderSubtotal * discountRate; //getting the discount amount of the order
            preTaxTotal = orderSubtotal - discountAmount;//getting the final total before tax
            taxAmount = preTaxTotal * taxRate;//getting the total of the tax
            postTaxTotal = preTaxTotal + taxAmount;//getting the post tax total
            finalTotal = postTaxTotal + shippingCost;//getting the final amount after the discount and tax
        
            //printing order summary
            System.out.println("");
            System.out.println("ORDER SUMMARY");
            System.out.println("Order Number: " + orderNumber);
            System.out.println("Customer Name: " + customerName);
            System.out.println("Product Code: " + purchasedProduct);
            System.out.println("Product Description: " + productDescription);
            System.out.println("Quantity: " + productQuantity);
            System.out.println("");
            System.out.println("Subtotal: " + currencyFormat.format(orderSubtotal));
            System.out.println("Discount: " + currencyFormat.format(discountAmount));
            System.out.println("Subtotal (after discount): " + currencyFormat.format(preTaxTotal));
            System.out.println("Tax: " + currencyFormat.format(taxAmount));
            System.out.println("Subtotal (after tax): " + currencyFormat.format(postTaxTotal));
            System.out.println("Shipping: " + currencyFormat.format(shippingCost));
            System.out.println("Order Total: " + currencyFormat.format(finalTotal));
            System.out.println("");
        
            System.out.print("Would you like to proceed with an additional order? (y/n): ");//seeing if they want another order
            input = sc.nextLine();
            nextOrder = input;//if they say y, they will restart, if they say n, the code ends        
        }
    }
    public static double getDiscount(double input){//I made the input double because it kept saying "possible lossy conversion from double to int" when i tried to declare the method otherwise, i'll lose the points and save the headache and just make it input a double
            double discountRate = 0.0;
            double productQuantity = input;

            //below is the if statement outlining the discount rates
            if(productQuantity >= 1 && productQuantity <= 1000){//checking to see if the product is even in the scope to begin with
                if(productQuantity >= 900){//this and many more assigning discount rates to different number amounts between 1-1000
                    discountRate = .2;
                } else if(productQuantity >= 800 && productQuantity < 900){
                    discountRate = .15;
                } else if(productQuantity >= 700 && productQuantity < 800){
                    discountRate = .1;
                } else if(productQuantity >= 500 && productQuantity <700){
                    discountRate = .05;
                }else {
                    discountRate = 0.0;
                }
            }
        return discountRate;
    }
    //below is the function for shipping
    public static double getShipping(double input){
        double productQuantity = input;//declaring variables
        double shippingCost = 0;//set the cost to 0 instead of 20, the math just made more sense in my head that way
        
        if(productQuantity >= 1 && productQuantity <= 1000){//checking to see if the product is even in the scope to begin with
            if(productQuantity <= 500){
                shippingCost = 20 + 0.07 * productQuantity;//in this line and line 213 i am doing math to calculate shipping               
            } else{
                shippingCost = 30 + 0.05 * productQuantity;
            }       
        }
        return shippingCost;
    }
    
    public static double getTax(String state, double total){
        String customerState = state;
        double preTaxedTotal = total;
        double taxAmt = 0;
        //below is determining tax based upon the state they are in
        switch(customerState){
            case "MI":
                taxAmt = .06;
                break;
            case "OH":
                taxAmt = .05;
                break;
            case "IN":
                taxAmt = .045;
                break;
            case "IL":
                taxAmt = .07;
                break;  
            case "WI":
                taxAmt = .065;
                break;
            
        }
        return taxAmt;
    }

}
