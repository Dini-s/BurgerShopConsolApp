public class Customer {

    private String customerId;
    private String customerName;
    private String ordersId;
    private int quantity;
    private double prices;
    private int orderStatus;

    public Customer(String customerId,String customerName,String ordersId,int quantity,double prices,int orderStatus){
        this.customerId=customerId;
        this.customerName=customerName;
        this.ordersId=ordersId;
        this.quantity=quantity;
        this.prices=prices;
        this.orderStatus=orderStatus;

    }
    public String getPhone(){
        return customerId;
    }
    public String getName(){
        return customerName;
    }
    public String getId(){
        return ordersId;
    }
    public int getQty(){
        return quantity;
    }
    public double getPrice(){
        return prices;
    }
    public int getStatus(){
        return orderStatus;
    }
    public void setQty(int Quantity){
        this.quantity=Quantity;
    }
    public void setPrices(double prices){
        this.prices=prices;
    }
    public void setStatus(int orderStatus){
        this.orderStatus=orderStatus;
    }

}
