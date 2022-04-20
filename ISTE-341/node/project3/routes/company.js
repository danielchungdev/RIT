const express = require('express');
const router = express.Router();
const validation = require('../businesslayer/validation')
const DataLayer = require('../companydata/index');
const dl = new DataLayer('dec8768');

const deleteCompany = router.delete('/company', (req, res) => {
    if(!validation.isEmpty(req.query)){
        let {company} = req.query;
        let result = dl.deleteCompany(company);
        if (result){
            res.status(200)
            res.send({"message": `Deleted company: ${company}`})
        }
        else{
            res.status(404)
            res.send({"message": "Unable to delete company."})
        }
    }
    else{
        res.status(500)
        res.send({"message": "Empty query parameters for company."})
    }
})

module.exports = {deleteCompany}