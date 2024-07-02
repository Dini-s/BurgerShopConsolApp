import java.util.Scanner;

public class BurgerShop {
    public static CustomeList list=new CustomeList();
    //Variable
    //public static int orderId = 0;
    public static final double UNITPRICE = 500.00;
    public static final int PREPARING = 0;
    public static final int DELIVERED = 1;
    public static final int Cancel = 2;

    public static void main(String args[]) {
        homePage();
    }
    public static void homePage() {
        do {
            System.out.println("-------------------------------------------------------------------------");
            System.out.println("|\t\t\t\tiHungry Burger\t\t\t\t|");
            System.out.println("-------------------------------------------------------------------------\n");
            System.out.println("[1] Place Order\t\t\t[2] Search Best Customer");
            System.out.println("[3] Search Order\t\t[4] Search Customer");
            System.out.println("[5] View Orders\t\t\t[6] Update Order Details");
            System.out.println("[7] Exit");

            Scanner input = new Scanner(System.in);


            System.out.print("\nEnter an option to continue > ");
            char option = input.next().charAt(0);

            switch (option) {
                case '1':
                    clearConsole();
                    placeOrder();
                    break;
                case '2':
                    clearConsole();
                    searchBestCustomer();
                    break;
                case '3':
                    clearConsole();
                    searchOrder();
                    break;
                case '4':
                    clearConsole();
                    searchCustomer();
                    break;
                case '5':
                    clearConsole();
                    viewDetails();
                    break;
                case '6':
                    clearConsole();
                    updateOrder();
                    break;
                case '7':
                    exit();
                    break;
            }
        } while (true);
    }
    //generate order Id
    public static String getOrderId() {

        if(list.size()==0){
            return "O0001";
        }
        String lastOrdId=list.getLast().getId();
        int number=Integer.parseInt(lastOrdId.split("O")[1]);
        number++;
        return String.format("O%04d",number);


    }
    public static boolean isValid(String phone){
        if(phone.length()==10 || phone.charAt(0)==0){
            return true;
        }
        return false;
    }
    public static int getQuantity() {

        Scanner scan = new Scanner(System.in);

        System.out.print("Enter Burger Quantity - ");
        int qty = scan.nextInt();

        while (true) {
            if (qty <= 0) {
                System.out.println("Invalid Quantitiy.Please Enter it Again");
                continue;
            } else {
                break;
            }
        }
        return qty;
    }

    //Calculate Total Price
    public static double calcTotal(int qty) {

        return qty * UNITPRICE;
    }

    public static void placeOrder(){
        Scanner scan=new Scanner(System.in);
        char choice;

        L1:while(true){

            System.out.println("--------------------------------------------------------------------------------");
            System.out.println("|                               PLACE ORDER                                    |");
            System.out.println("--------------------------------------------------------------------------------");

            System.out.println("\n\n");

            String orderId=getOrderId();


            System.out.println("ORDER ID - " + orderId);
            System.out.println("=======================");
            String name="";
            String phone="";

            while(true){
                System.out.println("Enter CustomerID(phone No.) :");
                phone=scan.next();

                if(isValid(phone)){
                    if(list.isContains(phone)){
                        System.out.println("Customer Name : "+list.getObject(phone).getName());
                        name=list.getObject(phone).getName();
                        break;
                    }
                    else{
                        System.out.print("Customer Name: ");
                        name=scan.next();
                    }

                }
                else{
                    System.out.println("Invalid cutomerId.Try again....");
                    continue ;
                }
                break ;
            }
            int qty = getQuantity();
            double price = calcTotal(qty);

            System.out.println("Total Value - " + price);
            while (true) {
                System.out.print("Do you want to place Order(Y-Yes/N-No)? - ");
                choice = scan.next().charAt(0);

                if (Character.toUpperCase(choice) != 'Y' && Character.toUpperCase(choice) != 'N') {
                    System.out.println("Invalid Choice.Please Enter Correct choice!..");
                    continue;
                } else {
                    break;
                }
            }
            if (Character.toUpperCase(choice) == 'Y') {

                list.add(new Customer(phone, name, orderId, qty, price, PREPARING));

                System.out.println("\t\t\t Your order is entered to the system successfully...\n\n");
            } else {
                break L1;
            }
            System.out.print("Do you want to place another order (Y-Yes/N-No)?");
            choice = scan.next().charAt(0);

            if (Character.toUpperCase(choice) == 'Y') {
                continue L1;
            } else {
                break L1;
            }


        }
    }
    //Search Order
    public static void searchOrder(){
        Scanner scan = new Scanner(System.in);

        System.out.println(" --------------------------------------------------------------------------------");
        System.out.println("|                       SEARCH ORDER DETAILS                                     |");
        System.out.println(" --------------------------------------------------------------------------------");

        while(true) {
            System.out.print("Enter Order ID : ");
            String id = scan.next();

            if (!list.isContains(id)) {
                while (true) {
                    System.out.print("\nInvalid Order ID.Do you want to try it again (Y-Yes/N-No)? ");
                    char option = scan.next().charAt(0);


                    if (Character.toUpperCase(option) == 'Y') {
                        System.out.print("\nInvalid Option .Try Again... ");
                    } else {
                        return;
                    }
                }
            } else {
                System.out.println("---------------------------------------------------------------------------------------------");
                System.out.printf("|%-10s %-15s %-10s %-13s %-17s %-15s|\n", "OrderID", "CustomerID", "Name", "Quantitiy", "OrderValue", "OrderStatus");
                System.out.println("----------------------------------------------------------------------------------------------");
                System.out.printf("|%-10s %-15s %-10s %-13d %-17.2f %-15s|\n", list.getObject(id).getId(), list.getObject(id).getPhone(), list.getObject(id).getName(), list.getObject(id).getQty(), list.getObject(id).getPrice(), list.getObject(id).getStatus());
                System.out.println("-------------------------------------------------------------------------------------------BE---");

                System.out.print("Do you want to Search another order Details (Y-Yes / N-No)?");
                char option2 = scan.next().charAt(0);

                if (Character.toUpperCase(option2) == 'Y') {
                    continue;
                } else {
                    return;
                }
            }
        }
    }
    //View Details
    public static void viewDetails() {
        Scanner scan = new Scanner(System.in);

        L1:
        while (true) {
            //clearConsole();
            System.out.println(" --------------------------------------------------------------------------------");
            System.out.println("|                       VIEW ORDER DETAILS                                       |");
            System.out.println(" --------------------------------------------------------------------------------\n");

            System.out.println("[1] Delivered Order\n[2] Preparing Order\n[3] Cancel Oredr");

            while(true) {

                System.out.print("Enter an Option >");
                int op = scan.nextInt();

                if (op > 0 && op < 4) {

                    switch (op) {
                        case 1:
                            deliveredOrder();
                            break;
                        case 2:
                            preparingOrder();
                            break;
                        case 3:
                            cancelOrder();
                            break;
                        default:
                            break L1;
                    }
                } else {
                    System.out.print("Invalid Option.Do you want to try again(Y-Yes/N-No) ?");
                    char op1 = scan.next().charAt(0);

                    if (Character.toUpperCase(op1) == 'Y') {
                        continue L1;
                    } else {
                        break L1;
                    }
                }
                break;
            }
        }
    }
    //delivered Order
    public static void deliveredOrder(){
        while(true){
            //clearConsole();
            Scanner scan=new Scanner(System.in);
            System.out.println("---------------------------------------------------------------------------------");
            System.out.println("|                         DELIVERED ORDERS                                       |");
            System.out.println("---------------------------------------------------------------------------------\n");
            System.out.println("---------------------------------------------------------------------------------");
            System.out.printf("%15s %15s %10s %15s %15s\n","OrderID","CustomerID","Name","Quantity","OrderValue");
            System.out.println("---------------------------------------------------------------------------------");

            for(int i=0;i<list.size();i++){
                if(list.get(i).getStatus()==1){
                    System.out.printf("%15s %15s %10s %15d %15f",list.get(i).getId(),list.get(i).getPhone(),list.get(i).getName(),list.get(i).getQty(),list.get(i).getPrice());
                    System.out.println("---------------------------------------------------------------------------------");
                }
            }
            System.out.print("Do you want to go to home Page(Y/N)?");
            char op=scan.next().charAt(0);

            if(Character.toUpperCase(op)=='Y'){
                return ;
            }
            else{
                continue;
            }
        }
    }
    //preparing order
    public static void preparingOrder(){

        while(true){
            //clearConsole();
            Scanner scan=new Scanner(System.in);
            System.out.println("---------------------------------------------------------------------------------");
            System.out.println("|                         Preparing Order                                       |");
            System.out.println("---------------------------------------------------------------------------------\n");
            System.out.println("---------------------------------------------------------------------------------");
            System.out.printf("%15s %15s %10s %15s %15s\n","OrderID","CustomerID","Name","Quantity","OrderValue");
            System.out.println("---------------------------------------------------------------------------------");

            for(int i=0;i< list.size();i++){
                if(list.get(i).getStatus()==0){
                    System.out.printf("%15s %15s %10s %15d %15f\n",list.get(i).getId(),list.get(i).getPhone(),list.get(i).getName(),list.get(i).getQty(),list.get(i).getPrice());
                    System.out.println("---------------------------------------------------------------------------------");
                }
            }
            System.out.print("Do you want to go to home Page(Y/N)?");
            char op=scan.next().charAt(0);

            if(Character.toUpperCase(op)=='Y'){
                return ;
            }
            else{
                continue;
            }
        }
    }
    //cancel Order
    public static void cancelOrder(){
        L1:while(true){
            //clearConsole();
            Scanner scan=new Scanner(System.in);
            System.out.println("---------------------------------------------------------------------------------");
            System.out.println("|                        Cancel Order                                       |");
            System.out.println("---------------------------------------------------------------------------------\n");
            System.out.println("---------------------------------------------------------------------------------");
            System.out.printf("%15s %15s %10s %15s %15s\n","OrderID","CustomerID","Name","Quantity","OrderValue");
            System.out.println("---------------------------------------------------------------------------------");

            for(int i=0;i< list.size();i++){
                if(list.get(i).getStatus()==2){
                    System.out.printf("%15s %15s %10s %15d %15f\n",list.get(i).getId(),list.get(i).getPhone(),list.get(i).getName(),list.get(i).getQty(),list.get(i).getPrice());
                    System.out.println("---------------------------------------------------------------------------------");
                }
            }
            System.out.print("Do you want to go to home Page(Y/N)?");
            char op=scan.next().charAt(0);

            if(Character.toUpperCase(op)=='Y'){
                return ;
            }
            else{
                continue L1;
            }
        }
    }
    //update order
    public static void updateOrder(){

        Scanner scan=new Scanner(System.in);

        System.out.println(" --------------------------------------------------------------------------------");
        System.out.println("|                       UPDATE ORDER DETAILS                                      |");
        System.out.println(" --------------------------------------------------------------------------------\n");

        while(true){

            System.out.println("Enter order Id - ");
            String id=scan.next();

            if(list.getObject(id).getStatus()==1){
                System.out.println("This Order is already delivered...You can not update this order..");
            }
            else if(list.getObject(id).getStatus()==2){
                System.out.println("This Order is already cancelled...You can not update this order..");
            }
            else{
                System.out.printf("%s %10s %5s\n","OrderID","-",list.getObject(id).getId());
                System.out.printf("%s %5s %5s\n","CustoerID","-",list.getObject(id).getPhone());
                System.out.printf("%s %12s %5s\n","Name","-",list.getObject(id).getName());
                System.out.printf("%s %8s %5d\n","Quantity","-",list.getObject(id).getQty());
                System.out.printf("%s %5s %5f\n","OrderValue","-",list.getObject(id).getPrice());
                System.out.printf("%s %2s %5d\n","OrderStatus","-",list.getObject(id).getStatus());

                System.out.println("What do you want to update ?");

                System.out.printf("%15s\n%15s\n","(01) Quantity","(02) Status");

                System.out.print("Enter your option -");
                int op=scan.nextInt();
                switch(op){

                    case 1:
                        qtyUpdate(list.get(id));break;
                    case 2:
                        statusUpdate(list.get(id));break;

                }

                System.out.print("Do you want to update another order details(Y/N):");
                char option=scan.next().charAt(0);

                if(Character.toUpperCase(option)=='Y'){
                    continue;
                }
                else{
                    break;
                }
            }
        }
    }
    //Quatity update
    public static void qtyUpdate(int i){
        Scanner scan=new Scanner(System.in);

        System.out.println("Quantity Update");
        System.out.println("================\n\n");

        System.out.printf("%s %10s %5s\n","OrderID","-",list.get(i).getId());
        System.out.printf("%s %5s %5s\n","CustoerID","-",list.get(i).getPhone());
        System.out.printf("%s %12s %5s\n","Name","-",list.get(i).getName());

        System.out.print("Enter your quantity update value - ");
        int qty=scan.nextInt();

        list.get(i).setQty(qty);
        list.get(i).setPrices(qty*UNITPRICE);


        System.out.printf("%15s\n","Update order quantity Success fully....");

        System.out.println("new order quantity - "+qty);
        System.out.print("new order value - "+list.get(i).getPrice());

    }
    //status update
    public static void statusUpdate(int i){
        Scanner scan=new Scanner(System.in);

        System.out.println("Quantity Update");
        System.out.println("================\n\n");

        System.out.printf("%s %10s %5s\n","OrderID","-",list.get(i).getId());
        System.out.printf("%s %5s %5s\n","CustomerID","-",list.get(i).getPhone());
        System.out.printf("%s %12s %5s\n","Name","-",list.get(i).getName());

        System.out.printf("%15s\n%15s\n%15s\n","(0)Cancel","(1)Preparing","(3)Delivered");

        System.out.print("Enter new order Status - ");
        int status=scan.nextInt();

        list.get(i).setStatus(status);


        System.out.printf("%15s\n","Update order quantity Success fully....");

        System.out.println("new order Status - "+list.get(i).getStatus());

    }
    //get best Customer
    public static void searchBestCustomer(){
        Scanner scan=new Scanner(System.in);
        while(true) {

            System.out.println("--------------------------------------------------------------------------------");
            System.out.println("|                             BEST Customer                                    |");
            System.out.println("--------------------------------------------------------------------------------");

            sortByPrice();

            System.out.println("Do you want to go to back to main menu?(Y/N) >");
            char op=scan.next().charAt(0);
            if(Character.toUpperCase(op)=='Y'){
                break;
            }
            else{
                continue;
            }

        }
    }
    //sorted pricecs for get bestCustomer
    public static Customer[] removeDuplicate(){

        boolean isExist;

        Customer[] temp1=new Customer[list.size()];

        for(int i=0;i< list.size();i++){
            temp1[i]=list.get(i);
        }


        Customer[] temp2=new Customer[0];

        for(int i=0;i< temp1.length;i++){
            isExist=false;
            double tot=0;
            for(int j=0;j<temp2.length;j++){
                if(temp2[j].getPhone().equals(list.get(i).getPhone())){
                    isExist=true;
                    temp2[j].setPrices(temp2[j].getPrice()+temp1[i].getPrice());
                }
            }
            if(!isExist){
                Customer[] temp3=new Customer[temp2.length+1];
                for(int j=0;j<temp2.length;j++){
                    temp3[j]=temp2[j];
                }
                temp3[temp3.length-1]=temp1[i];
                temp2=temp3;
            }

        }
        return temp2;
    }
    //sort object by price
    public static void sortByPrice(){
        Customer[] temp1=removeDuplicate();
        for(int i=0;i<temp1.length;i++){
            for(int j=0;j<temp1.length-1;j++){
                if(temp1[j].getPrice()<temp1[j+1].getPrice()){
                    Customer tempVar=temp1[j];
                    temp1[j]=temp1[j+1];
                    temp1[j+1]=tempVar;
                }
            }
        }
        //print sorted array
        System.out.println("-----------------------------------------------------------");
        System.out.printf("%-15s %-20s %s\n", "CustomerID", "Name", "Total");
        System.out.println("-----------------------------------------------------------");

        for(int i=0;i<temp1.length;i++){
            System.out.printf("%-15s %-20s %.2f\n",temp1[i].getPhone(),temp1[i].getName(),temp1[i].getPrice());
            System.out.println("-----------------------------------------------------------");
        }
    }
    //search customer details
    public static void searchCustomer() {

        L1:
        while (true) {
            Scanner scan = new Scanner(System.in);
            System.out.println(" --------------------------------------------------------------------------------");
            System.out.println("|                       SEARCH CUSTOMER DETAILS                                  |");
            System.out.println(" --------------------------------------------------------------------------------");

            System.out.print("Enter customer ID - ");
            String phone = scan.next();

            if (!list.isContains(phone)) {
                System.out.println("This customer ID is not added yet...");


                System.out.println("Do you want to search another customer details(Y/N) :");
                char op = scan.next().charAt(0);

                if (Character.toUpperCase(op) == 'Y') {
                    continue L1;
                } else {
                    break L1;
                }

            } else {
                System.out.println("Customer ID\t - " + list.getObject(phone).getPhone());
                System.out.println("Name\t\t- " + list.getObject(phone).getName());
                System.out.println();
                System.out.println("Customer Order Details");
                System.out.println("======================");
                System.out.println("");
                System.out.println("-----------------------------------------------------");
                System.out.printf("%-10s %-20s %-15s\n", "OrderID", "Order_Quantity", "Total_value");
                System.out.println("-----------------------------------------------------");

                for (int i = 0; i < list.size(); i++) {
                    if (phone.equals(list.get(i).getPhone())) {
                        System.out.printf("%-10s %-20s %-15s\n", list.get(i).getId(), list.get(i).getQty(), list.get(i).getPrice());
                    }
                }
                System.out.println("Do you want to search another customer details(Y/N) :");
                char op = scan.next().charAt(0);

                if (Character.toUpperCase(op) == 'Y') {
                    continue L1;
                } else {
                    break L1;
                }
            }
        }
    }

    //exit method
    public static void exit(){

        System.exit(0);
    }

    //clear Console
    public static void clearConsole(){
        try {
            final String os = System.getProperty("os.name");
            if (os.contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (final Exception e) {
            e.printStackTrace();

        }
    }




}
