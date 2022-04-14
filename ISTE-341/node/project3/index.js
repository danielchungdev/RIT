const express = require('express');
const router = express.Router();
const port = 8080; 
const app = express();
const cors = require('cors');

const employees = require('./routes/employee')

app.use(cors());
app.use(express.urlencoded({extended: false}))
app.use(router);

app.get('/employees', employees.getAllEmployee);
app.get('/employee', employees.getEmployeeById);

app.listen(port || 8080, () => {
    console.log(`Server running at port: ${port}`);
});