const express = require('express');
const router = express.Router();
const validation = require('../businesslayer/validation')
const DataLayer = require('../companydata/index');
const dl = new DataLayer('dec8768');

const getAllTimecards = router.get('/timecards', (req, res) => {
    if (!validation.isEmpty(req.query)){
        let {company, emp_id} = req.query;
        if (validation.validateTimecardsGet(company, emp_id)){
            let response = dl.getAllTimecard(emp_id);
            if (response){
                res.status(200);
                res.send(response)
            }
            else{
                res.status(404);
                res.send({"message": `No timecards where found for Employee with id: ${emp_id}.`});
            }
        }
        else{
            res.status(404);
            res.send({"message": `Employee with id: ${emp_id} was not found.`})
        }
    }
    else{
        res.status(500);
        res.send({"message": "Query parameters are empty."})
    }
});

const getTimecard = router.get('/timecard', (req, res) => {
    if(!validation.isEmpty(req.query)){
        let {company, timecard_id} = req.query;
        let getTimecard = dl.getTimecard(timecard_id)
        if (getTimecard){
            res.status(200);
            res.send(getTimecard);
        }
        else{
            res.status(300);
            res.send({"message": "Unable to get timecard."});
        }
    }
    else{
        res.status(500);
        res.send({"message": "Query parameters are empty."})
    }
});

const insertTimecard = router.post('/timecard', (req, res) => {
    let {company, emp_id, start_time, end_time} = req.body;
    if(validation.validateTimeCardPost(company, emp_id, start_time, end_time)){
        let newTimecard = new dl.Timecard(start_time, end_time, emp_id);
        let result = dl.insertTimecard(newTimecard);
        if (result){
            res.status(200)
            res.send(result)
        }
        else{
            res.status(300);
            res.send({"message": "Timecard couldn't be inserted."})
        }
    }
    else{
        res.status(500);
        res.send({"message": "Fields might be empty, or with invalid information."})
    }
})

const updateTimecard = router.put('/timecard', (req, res) => {
    let {company, timecard_id, start_time, end_time, emp_id} = req.body;
    if(validation.validateTimeCardPost(company, emp_id, start_time, end_time) && dl.getTimecard(timecard_id) !== null){
        let newTimecard = new dl.Timecard(timecard_id, start_time, end_time, emp_id);
        let result = dl.updateTimecard(newTimecard);
        if (result){
            res.status(200);
            res.send(result);
        }
        else{
            res.status(300);
            res.send({"message": "Can't update timecard"});
        }
    }
    else{
        res.status(500);
        res.send({"message": "Fields might be empty, or with invalid information."});
    }
})

const deleteTimecard = router.delete('/timecard', (req, res) => {
    let {company, timecard_id} = req.query;
    if (!validation.isEmpty(req.query)){
        let deleteTimecard = dl.deleteTimecard(timecard_id);
        if (deleteTimecard){
            res.status(200);
            res.send({"message": `Deleted ${deleteTimecard} timecard.`});
        }
        else{
            res.status(404)
            res.send({"message": `Was not able to delete timecard with id: ${timecard_id}`});
        }
    }
    else{
        res.status(500);
        res.send({"message": "Query parameters are empty!"})
    }
})

module.exports = {getAllTimecards, getTimecard, insertTimecard, updateTimecard, deleteTimecard}