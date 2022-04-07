package com.chung.daniel;

import java.util.List;

import org.glassfish.jersey.server.Uri;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import companydata.*;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("CompanyServices")
public class MyResource {
    public DataLayer dl; 

    @Context
    UriInfo uriInfo;
    public MyResource(){
    }

    //TODO
    @Path("company")
    @DELETE
    @Produces("application/json")
    public Response deleteDepartments(){
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("[{\"error\":\"Exception from data layer\"}]").build();
    }

    @Path("department")
    @GET
    @Produces("application/json")
    public Response getDepartment(@DefaultValue("1") @QueryParam("company") String companyName, @QueryParam("departmentid") int id){
        try{
            dl = new DataLayer("dec8768");
            Department department = dl.getDepartment(companyName, id);
            if (department != null){
                String json = new Gson().toJson(department);
                return Response.ok(json).build();
            }
            else{
                return Response.status(Response.Status.NOT_FOUND).entity("[{\"message\": \"Department not found\"}]").build();
            }
        }
        catch(Exception e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("[{\"error\":\"Exception from data layer\"}]").build();
        }
        finally{
            dl.close();
        }
    }

    @Path("departments")
    @GET
    @Produces("application/json")
    public Response getAllDepartments(@DefaultValue("1") @QueryParam("company") String inCompany){
        try{
            dl= new DataLayer("dec8768");
            List<Department> departments = departments = dl.getAllDepartment(inCompany);
            if (!departments.isEmpty()) {
                String json = new Gson().toJson(departments);
                return Response.ok(json).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).entity("[{\"message\":\""+ "No departments found\"}]").build();
            }
        }
        catch(Exception e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("[{\"error\":\"Exception from data layer\"}]").build();
        }
        finally {
            dl.close();
        }
    }

    @Path("department")
    @PUT
    @Produces("application/json")
    public Response putDepartment(String body){
        try{
            dl = new DataLayer("dec8768");
            JsonElement jsonel = new Gson().fromJson(body, JsonElement.class);
            JsonObject json = jsonel.getAsJsonObject();
            System.out.println(body);
            System.out.println(json.toString());
            String company = json.get("company").getAsString();
            int departmentid = json.get("dept_id").getAsInt();
            String departmentName = json.get("dept_name").getAsString();
            String departmentNumber = json.get("dept_no").getAsString();
            String location = json.get("location").getAsString();
            Department department = new Department(departmentid, company, departmentName, departmentNumber, location);
            Department res = dl.updateDepartment(department);

            if(res != null){
                return Response.ok("[{\"message\": \"Department updated\"}]").build();
            }
            else{
                return Response.status(Response.Status.NOT_MODIFIED).entity("[{\"message\": \"Department not updated\"}]").build();
            }
        }
        catch(Exception e){
            JsonObject json = new Gson().fromJson(body, JsonObject.class);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(json.toString()).build();
        }
        finally{
            dl.close();
        }
    }
    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt() {
        return "Got it!";
    }
}