const express = require('express');
const router = express.Router();
const validation = require('../businesslayer/validation')
const DataLayer = require('../companydata/index');
const dl = new DataLayer('dec8768');

const getAllEmployee = router.get('/employees', (req, res) => {
    if (!validation.isEmpty(req.query)){
        res.status(200);
        res.send(dl.getAllEmployee(req.query.company));
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
    
});

module.exports = {getAllEmployee, getEmployeeById}