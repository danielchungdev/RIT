
public class Product {
    public string name {get; set;}
    public double price {get; set;}
    public Product(string productName, double productPrice){
        this.name = productName;
        this.price = productPrice;
    }
}