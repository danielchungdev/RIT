var products = new List<Product>();

var builder = WebApplication.CreateBuilder(args);

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

app.MapGet("/", () => "hello world");

app.MapGet("/Product/{id}", (int id) => {
    var found = products.Find( (product) => {
        return product.id == id;
    });
});

app.MapPost("/", (Product product) => {
    bool isValid = !String.IsNullOrWhiteSpace(product.Name) && product.Name.Length < 40 ? true : false;
    if (product == null || !isValid){
        return Results.BadRequest();
    }
    product.id = 6; 
    return Results.Created($"Product/{product.id}", product);
});

app.MapPut("/", (int id, Product product) => {
    bool isValid = !String.IsNullOrWhiteSpace(product.Name) && product.Name.Length < 40 ? true : false;
    if (product == null || !isValid){
        return Results.BadRequest();
    }
    if (!products.Exists(x=>x.id == id)){
        return Results.NotFound();
    }
    return Results.NoContent();
});

app.MapDelete("/product/{id}", (int Id) => {
    if (!products.Exists(x=>x.id == Id)){
        return Results.NotFound();
    }
    return Results.NoContent();
});

//If properties of an object are private, we can't 
// return the object or you will get an empty json. 
//So need to do is build a dictionary and return that.

app.MapGet("/Product2/{id}", (int ID) => {
    Product product = products.Find(x=>x.id == ID);
    if (product == null){
        return Results.NotFound();
    }
    var returnProd = new Dictionary<String,object>{
        {"id", product.id},
        {"name", product.Name}
    };
    return Results.Ok(returnProd);
});

app.Run();