const express = require('express');
const router = express.Router();
const port = 8080; 
const app = express();
const cors = require('cors');

const employees = require('./routes/employee');
const timecards = require('./routes/timecard');
const departments = require('./routes/department');
const companies = require('./routes/company');

app.use(cors());
app.use(express.urlencoded({extended: false}))
app.use(router);

/**
 * @description Employee routes
 */
app.get('/employees', employees.getAllEmployee);
app.get('/employee', employees.getEmployeeById);
app.post('/employee', employees.insertEmployee);
app.put('/employee', employees.updateEmployee);
app.delete('/employee', employees.deleteEmployee);

/**
 * @description Timecard routes;
 */
app.get('/timecards', timecards.getAllTimecards);
app.get('/timecard', timecards.getTimecard);
app.post('/timecard', timecards.insertTimecard);
app.put('/timecard', timecards.updateTimecard);
app.delete('/timecard', timecards.deleteTimecard);

/**
 * @description Departments routes
 */
app.get('/departments', departments.getAllDepartments);
app.get('/department', departments.getDepartment);
app.post('/department', departments.insertDepartment);
app.put('/department', departments.updateDepartment);
app.delete('/department', departments.deleteDepartment);


app.delete('/company', companies.deleteCompany);


app.listen(port || 8080, () => {
    console.log(`Server running at port: ${port}`);
});