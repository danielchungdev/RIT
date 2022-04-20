const express = require('express');
const router = express.Router();
const validation = require('../businesslayer/validation')
const DataLayer = require('../companydata/index');
const dl = new DataLayer('dec8768');

const getAllDepartments = router.get('/departments', (req, res) => {
    if(!validation.isEmpty(req.query)){
        let {company} = req.query;
        let result = dl.getAllDepartment(company);
        if (result){
            res.status(200);
            res.send(result);
        }
        else{
            res.status(300);
            res.send({"message": `Couldn't find departments on company ${company}`});
        }
    }
    else{
        res.status(500);
        res.send({"message": "Empty query parameters"})
    }
});

const getDepartment = router.get('/department', (req, res) => {
    if(!validation.isEmpty(req.query)){
        let {company, dept_id} = req.query;
        if (validation.departmentExists(company, dept_id)){
            let result = dl.getDepartment(company, dept_id);
            if (result){
                res.status(200);
                res.send(result);
            }
            else{
                res.status(300);
                res.send({"message": "Invalid search."});
            }
        }
        else{
            res.status(404);
            res.send({"message": `Department not found with department id: ${dept_id}`});
        }
    }
    else{
        res.status(500);
        res.send({"message": "Empty parameter queries"});
    }
});

const insertDepartment = router.post('/department', (req, res) => {
    if (!validation.isEmpty(req.body)){
        let {company, dept_name, dept_no, location} = req.body;
        if (validation.validateDepartmentPost(company, dept_name, dept_no, location)){
            let newDepartment = new dl.Department(company, dept_name, dept_no, location)
            let result = dl.insertDepartment(newDepartment)
            if (result){
                res.status(200)
                res.send(result)
            }
            else{
                res.status(300)
                res.send({"message": "Could not insert new department"})
            }
        }
        else{
            res.status(303);
            res.send({"message": "Incorrect information passed."})
        }
    }
    else{
        res.status(500);
        res.send({"message": "Empty body."})
    }
})

const updateDepartment = router.put('/department', (req, res) => {
    if (!validation.isEmpty(req.body)){
        let {company, dept_id, dept_name, dept_no, location} = req.body;
        if (validation.departmentExists(company, dept_id)){
            let newDepartment = new dl.Department(company, dept_name, dept_no, location, dept_id)
            let result = dl.updateDepartment(newDepartment);
            if (result){
                res.status(200);
                res.send(result)
            }
            else{
                res.status(300);
                res.send({"message": "Couldn't update department."})
            }
        }
        else{
            res.status(404);
            res.send({"message": `Department with id ${dept_id} was not found`})
        }
    }
    else{
        res.status(500)
        res.send({"message": "Empty body."})
    }
})

const deleteDepartment = router.delete('/department', (req, res) => {
    if (!validation.isEmpty(req.query)){
        let {company, dept_id} = req.query;
        if(validation.departmentExists(company, dept_id)){
            let deleteDepartment = dl.deleteDepartment(company, dept_id);
            if(deleteDepartment){
                res.status(200);
                res.send({"message": `Deleted ${deleteDepartment} deparment.`});
            }
            else{
                res.status(300);
                res.send({"message": `Could not delete department with id: ${dept_id}.`})
            }
        }
        else{
            res.status(404)
            res.send({"message": "Department not found"})
        }
    }
    else{
        res.status(500)
        res.send({"message": "Empty Query parameters."})

    }
})

module.exports = {getAllDepartments, getDepartment, insertDepartment, updateDepartment, deleteDepartment}