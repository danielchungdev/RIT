var builder = WebApplication.CreateBuilder(args);

List<Product> products = new List<Product>();

products.Add(new Product("Apples", 3.99));
products.Add(new Product("Peaches", 4.05));
products.Add(new Product("Pumpkin", 13.99));
products.Add(new Product("Pie", 8.00));

for (var i = 0; i < products.Count; i++){
    Console.WriteLine(products[i]);
}

// Add services to the container.
// Learn more about configuring Swagger/OpenAPI at https://aka.ms/aspnetcore/swashbuckle
builder.Services.AddEndpointsApiExplorer();
builder.Services.AddSwaggerGen();

var app = builder.Build();

// Configure the HTTP request pipeline.
if (app.Environment.IsDevelopment())
{
    app.UseSwagger();
    app.UseSwaggerUI();
}

app.UseHttpsRedirection();


app.MapGet("/services/products", () =>{
    return products;
}).WithName("getNames");

app.MapGet("/services/products/cheapest", async () => {
    var index = 0; 
    for (var i = 1; i < products.Count; i++){
        if (products[index].price > products[i].price){
            index = i;
        }
    }
    return products[index];
}).WithName("getCheapest");

app.MapGet("/services/products/costliest", async () => {
    var index = 0; 
    for (var i = 1; i < products.Count; i++){
        if (products[index].price < products[i].price){
            index = i;
        }
    }
    return products[index];
}).WithName("getCostliest");

app.MapGet("/services/products/{name}", async (string name) => {
    var index = 0;
    var found = false;
    for (var i = 0; i < products.Count; i++){
        if (products[i].name.ToLower() == name.ToLower()){
            index = i;
            found = true;
        }
    }
    if (found){
        return products[index].price.ToString();
    }
    else{
        return "Not found";
    }
}).WithName("getPrice");

app.Run();