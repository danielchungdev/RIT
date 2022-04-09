package com.chung.daniel;

import java.io.Console;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.glassfish.jersey.server.Uri;

import jakarta.annotation.Generated;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
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
    public Response getDepartment(@DefaultValue("dec8768") @QueryParam("company") String companyName, @QueryParam("dept_id") int id){
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
    public Response getAllDepartments(@DefaultValue("dec8768") @QueryParam("company") String inCompany){
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
    @Consumes("application/json")
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

    @Path("department")
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces("application/json")
    public Response insertDepartment(@DefaultValue("dec8768") @FormParam("company") String company, @FormParam("dept_name") String name, @FormParam("dept_no") String number, @FormParam("location") String location){
        try{
            dl = new DataLayer("dec8768");
            if(name.equals("") || number.equals("") || location.equals("")){
                return Response.status(Response.Status.BAD_REQUEST).entity("[{\"message\":\"Empty form fields.\"}]").build();
            }
            Department department = new Department(company, name, number, location);
            Department inserted = dl.insertDepartment(department);
            System.out.println(department);
            if (inserted != null){
                return Response.ok("[{message : department inserted!}]").build();
            }
            else{
                return Response.status(Response.Status.EXPECTATION_FAILED).build();
            }
        }
        catch(Exception e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
        finally{
            dl.close();
        }
    }

    @Path("department")
    @DELETE
    @Produces("application/json")
    public Response deleteDepartment(@QueryParam("company") String company, @QueryParam("dept_id") int id){
        try{
            dl = new DataLayer("dec8768");
            if(company.equals("")){
                return Response.status(Response.Status.BAD_REQUEST).entity("[{\"message\":\"Empty form fields.\"}]").build();
            }
            int res = dl.deleteDepartment(company, id);
            if (res > 0){
                return Response.ok("[{message : Department deleted}]").build();
            }
            else{
                return Response.status(Response.Status.NOT_MODIFIED).entity("[{\"message\": \"Department could not deleted\"}]").build();
            }
        }
        catch(Exception e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
        finally{
            dl.close();
        }
    }

    @Path("employee")
    @GET
    @Produces("application/json")
    public Response getEmployee(@QueryParam("company") String company, @QueryParam("emp_id") int id){
        try{
            dl = new DataLayer("dec8768");
            Employee employee = dl.getEmployee(id); 
            if (employee != null){
                String json = new Gson().toJson(employee);
                return Response.ok(json).build();
            } 
            else{
                return Response.status(Response.Status.NOT_FOUND).entity("[{\"message\": \"Employee not found\"}]").build();
            }
        }
        catch(Exception e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
        finally{
            dl.close();
        }
    }

    @Path("employees")
    @GET
    @Produces("application/json")
    public Response getAllEmployees(@QueryParam("company") String company){
        try{
            dl = new DataLayer("dec8768");
            if (company.equals("")){
                return Response.status(Response.Status.NOT_FOUND).build();
            }
            List<Employee> employees = dl.getAllEmployee(company);
            if (!employees.isEmpty()) {
                String json = new Gson().toJson(employees);
                return Response.ok(json).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).entity("[{\"message\":\""+ "No employees found\"}]").build();
            }
        }
        catch(Exception e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
        finally{
            dl.close();
        }
    }

    @Path("employee")
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces("application/json")
    public Response insertEmployee(@FormParam("company") String company, @FormParam("emp_name") String name, @FormParam("emp_no") String number, @FormParam("hire_date") String hired, @FormParam("job") String job, @FormParam("salary") Double salary, @FormParam("dept_id") int deptid, @FormParam("mng_id") int mngid){
        try{
            dl = new DataLayer("dec8768");
            Date currentDate = new Date();
            if(name.isBlank() || number.isBlank() || company.isBlank() || hired.isBlank() || job.isBlank() || salary.isNaN()){
                return Response.status(Response.Status.BAD_REQUEST).build();
            }
            java.sql.Date newDate = new java.sql.Date(new SimpleDateFormat("yyyy-MM-dd").parse(hired).getTime());
            if (!newDate.before(currentDate)){
                return Response.status(Response.Status.BAD_REQUEST).build();
            }
            Employee employee = new Employee(name, number, newDate, job, salary, deptid, mngid);
            Employee inserted = dl.insertEmployee(employee);
            if (inserted != null){
                return Response.ok("[{\"success\":" + new Gson().toJson(inserted) + "}]").build();
            }
            else{
                return Response.status(Response.Status.EXPECTATION_FAILED).entity("[{\"message\": \"couldn't insert employee\"}]").build();
            }
        }
        catch(Exception e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
        finally{
            dl.close();
        }
    }

    @Path("employee")
    @PUT
    @Consumes("application/json")
    @Produces("application/json")
    public Response updateEmployee(String body){
        System.out.println(body);
        try{
            dl = new DataLayer("dec8768");
            JsonElement jsonel = new Gson().fromJson(body, JsonElement.class);
            JsonObject json = jsonel.getAsJsonObject();
            System.out.println(json);
            int empid = json.get("emp_id").getAsInt();
            String name = json.get("emp_name").getAsString();
            String empNo = json.get("emp_no").getAsString();
            java.sql.Date newDate = new java.sql.Date(new SimpleDateFormat("yyyy-MM-dd").parse(json.get("hire_date").getAsString()).getTime());
            String job = json.get("job").getAsString();
            double salary = json.get("salary").getAsDouble();
            int deptid = json.get("dept_id").getAsInt();
            int mngid = json.get("mng_id").getAsInt();
            Employee employee = new Employee(empid, name, empNo, newDate, job, salary, deptid, mngid);
            Employee res = dl.updateEmployee(employee);
            if(res != null){
                String resJson = new Gson().toJson(res);
                String composed = "[{\"success\":" + resJson + "}]";
                return Response.ok(composed).build();
            }
            else{
                return Response.status(Response.Status.NOT_MODIFIED).entity("[{\"message\": \"could not update employee\"}]").build();
            }
        }
        catch(Exception e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
        finally{
            dl.close();
        }
    }

    @Path("employee")
    @DELETE
    @Produces("application/json")
    public Response delteEmployee(@QueryParam("company") String company, @QueryParam("emp_id") int id){
        try{
            dl = new DataLayer("dec8768");
            if (company.isBlank() || id == 0){
                return Response.status(Response.Status.BAD_REQUEST).entity("[{\"message\": \"Empty fields\"}]").build();
            }
            int res = dl.deleteEmployee(id);
            if (res > 0){
                return Response.ok("[{\"message\": \"Deleted employee\"}]").build();
            }
            else{
                return Response.status(Response.Status.EXPECTATION_FAILED).entity("[{\"message\" : \"Couldn't delete employee\"}]").build();
            }
        }
        catch(Exception e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
        finally{
            dl.close();
        }
    }

    @Path("timecard")
    @GET
    @Produces("application/json")
    public Response getTimecard(@QueryParam("company") String company, @QueryParam("timecard_id") int id){
        try{
            dl = new DataLayer("dec8768");
            if (company.isEmpty() || id == 0){
                return Response.status(Response.Status.BAD_REQUEST).entity("[{\"message\": \"Empty fields\"}]").build();
            }
            Timecard timecard = dl.getTimecard(id);
            if (timecard != null){
                return Response.ok(new Gson().toJson(timecard)).build();
            }
            else{
                return Response.status(Response.Status.NOT_FOUND).entity("{[\"message\": \"Timecard not found\"]}").build();
            }
        }
        catch(Exception e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
        finally{
            dl.close();
        }
    }

    @Path("timecards")
    @GET
    @Produces("application/json")
    public Response getAllTimecards(@QueryParam("company") String company, @QueryParam("emp_id") int id){
        try{
            dl = new DataLayer("dec8768");
            if (company.isBlank() || id == 0){
                return Response.status(Response.Status.BAD_REQUEST).entity("[{\"message\": \"Empty fields.\"}]").build();
            }
            List<Timecard> timecards = dl.getAllTimecard(id);
            if (!timecards.isEmpty()){
                return Response.ok(new Gson().toJson(timecards)).build();
            }
            else{
                return Response.status(Response.Status.NOT_FOUND).entity("[{\"message\": \"No timecards found.\"}]").build();
            }
        }
        catch(Exception e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
        finally{
            dl.close();
        }
    }

    @Path("timecard")
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces("application/json")
    public Response insertTimecard(@FormParam("company") String company, @FormParam("emp_id") int id, @FormParam("start_time") String startTime, @FormParam("end_time") String endTime){
        try{
            dl = new DataLayer("dec8768");
            java.sql.Timestamp newStartTime = new java.sql.Timestamp(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(startTime).getTime());
            java.sql.Timestamp newEndTime = new java.sql.Timestamp(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(endTime).getTime());
            Timecard timecard = new Timecard(newStartTime, newEndTime, id);
            System.out.println(timecard);
            Timecard res = dl.insertTimecard(timecard);
            if (res != null){
                String strjson = new Gson().toJson(res);
                return Response.ok("[{\"success\":" + strjson + "}]").build();
            }
            else{
                return Response.status(Response.Status.EXPECTATION_FAILED).entity("[{\"message\":\"Couldn't insert timecard\"}]").build();
            }
        }
        catch(Exception e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
        finally{
            dl.close();
        }
    }

    @Path("timecard")
    @PUT
    @Consumes("application/json")
    @Produces("application/json")
    public Response updateTimecard(String body){
        try{
            dl = new DataLayer("dec8768");
            JsonElement jsonel = new Gson().fromJson(body, JsonElement.class);
            JsonObject json = jsonel.getAsJsonObject();
            System.out.println(json);
            int timecard_id = json.get("timecard_id").getAsInt();
            java.sql.Timestamp start_time = new java.sql.Timestamp(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(json.get("start_time").getAsString()).getTime());
            java.sql.Timestamp end_time = new java.sql.Timestamp(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(json.get("end_time").getAsString()).getTime());
            int emp_id = json.get("emp_id").getAsInt();
            System.out.println(start_time.toString() + end_time.toString());
            Timecard timecard = new Timecard(timecard_id, start_time, end_time, emp_id);
            System.out.println(timecard);
            Timecard res = dl.updateTimecard(timecard);
            if (res != null){
                return Response.ok(new Gson().toJson(timecard)).build();
            }
            else{
                return Response.status(Response.Status.NOT_FOUND).entity("[{\"message\": \"Couldn't update timecard.\"}]").build();
            }
        }
        catch (Exception e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
        finally{
            dl.close();
        }
    }

    @Path("timecard")
    @DELETE
    @Produces("application/json")
    public Response deleteTimecard(@QueryParam("company") String company, @QueryParam("timecard_id") int id){
        try{
            dl = new DataLayer(company);
            int timecard = dl.deleteTimecard(id);
            if (timecard > 0){
                return Response.ok(new Gson().toJson(timecard)).entity("[{\"message\": \"Timecard has been deleted.\"}]").build();
            }
            else{
                return Response.status(Response.Status.NOT_FOUND).entity("[{\"message\": \"timecard not found.\"}]").build();
            }
        }
        catch( Exception e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
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