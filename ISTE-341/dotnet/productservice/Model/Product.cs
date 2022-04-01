public class Product {
    public int id {get; set;}
    public string Name {get; set;}

    public override string ToString()
    {
        return "ID: " + id + " Name: " + Name;
    }
}
