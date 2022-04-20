const express = require('express');
const router = express.Router();
const validation = require('../businesslayer/validation')
const DataLayer = require('../companydata/index');
const dl = new DataLayer('dec8768');

const getAllEmployee = router.get('/employees', (req, res) => {
    let {company} = req.query;
    if (!validation.isEmpty(req.query)){
        let result = dl.getAllEmployee(company);
        if (result){
            res.status(200);
            res.send(result)
        }
        else{
            res.status(404);
            res.send({"message": `No employees found for company ${company}`});
        }
    }
    else{
        res.status(400);
        const response = {
            error: "Company query parameter is empty!"
        }
        res.send(response);
    }
});

const getEmployeeById = router.get('/employee', (req, res) => {
    if (!validation.isEmpty(req.query)){
        res.status(200);
        res.send(dl.getEmployee(req.query.emp_id));
    }
    else{
        res.status(400);
        const response = {
            error: "Emp_id query parameter is empty!"
        }
        res.send(response)
    } 
});

const insertEmployee = router.post('/employee', (req, res) => {
    let {company, emp_name, emp_no, hire_date, job, salary, dept_id, mng_id} = req.body;
    if (validation.validateEmployeePost(company, emp_name, emp_no, hire_date, job, salary, dept_id, mng_id)){
        let insertEmployee = new dl.Employee(emp_name, emp_no, hire_date, job, parseFloat(salary), parseInt(dept_id), parseInt(mng_id));
        let result = dl.insertEmployee(insertEmployee);
        if (result !== null){
            res.status(200);
            res.send(result);
        }
        else{
            res.status(400);
            res.send({"message": "Failed to insert."});
        }
    }
    else{
        res.status(500);
        res.send({"message": "Invalid inputs."});
    }
});

const updateEmployee = router.put('/employee', (req, res) => {
    let {company, emp_id, emp_name, emp_no, hire_date, job, salary, dept_id, mng_id} = req.body;
    if (validation.validateEmployeePut(company, emp_id, emp_name, emp_no, hire_date, job, salary, dept_id, mng_id)){
        let updatedEmployee = new dl.Employee(emp_name, emp_no, hire_date, job, parseFloat(salary), parseInt(dept_id), parseInt(mng_id), parseInt(emp_id));
        let result = dl.updateEmployee(updatedEmployee);
        if (result !== null){
            res.status(200);
            res.send(result);
        }
        else{
            res.status(400);
            res.send({"message": `Unable to update employee with id ${emp_id}.`});
        }
    }
    else{
        res.status(500);
        res.send({"message": "Invalid inputs."})
    }
});

const deleteEmployee = router.delete('/employee', (req, res) => {
    let {company, emp_id} = req.query;
    if (validation.validateEmployeeDelete(company, emp_id)){
        let deleteEmployee = dl.deleteEmployee(emp_id);
        if (deleteEmployee){
            res.status(200);
            res.send({"message": `Employee of id ${emp_id} has successfully been deleted.`, "rows": deleteEmployee})
        }
        else{
            res.status(400);
            res.send({"message": `Couldn't delete employee of id ${emp_id}`});
        }
    }
    else{
        res.status(500);
        res.send({"message": "Empty parameters."})
    }
});

module.exports = {getAllEmployee, getEmployeeById, insertEmployee, updateEmployee, deleteEmployee}