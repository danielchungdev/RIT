const DataLayer = require('../companydata/index');
const dl = new DataLayer('dec8768');

const isEmpty = (obj) => {
    return JSON.stringify(obj) === "{}";
}

const validateEmployeePost = (company, name, number, hiredate, job, salary, dept_id, mng_id) => {
    if(company === undefined ||name === undefined|| job === undefined || salary === undefined || parseInt(dept_id) < 1 || parseInt(mng_id) < 1){
        return false;
    }
    return true;
}

const validateEmployeePut = (company, emp_id, emp_name, emp_no, hire_date, job, salary, dept_id, mng_id) => {
    if (company === undefined || emp_id === undefined || emp_name === undefined || emp_no === undefined || hire_date === undefined || job === undefined || salary === undefined || dept_id === undefined || mng_id === undefined){
        return false;
    }
    return true;
}

const validateEmployeeDelete = (company, emp_id) => {
    if(company === undefined || emp_id === undefined){
        return false;
    }
    return true;
}

const validateTimecardsGet = (company, emp_id) => {
    let res = false
    if (employeeExists(emp_id) && company === "dec8768"){
        res = true
    }
    return res;
}

const employeeExists = (emp_id) => {
    let employees = dl.getAllEmployee(company);
    let res = false
    employees.forEach( employee => {
        if (employee.emp_id === parseInt(emp_id)){
            res = true
        }
    })
    return res;

}

const validateTimeCardPost = (company, emp_id, start_time, end_time) => {
    let dateStartTime = new Date(start_time); 
    let dateEndTime = new Date(end_time);
    if (company === undefined || company !== "dec8768" || emp_id === undefined || start_time === undefined || end_time === undefined){
        return false
    }
    if (!employeeExists){
        return false
    }
    if (isWeekend(dateStartTime) || isWeekend(dateEndTime)){
        return false
    }
    if (isOutsideHours(dateStartTime) && isOutsideHours(dateEndTime)){
        return false
    }
    if (dateEndTime < dateStartTime){
        return false
    }
    return true
};

const isWeekend = (date_time) => {
    if (date_time.getDay() === 6 || date_time.getDay === 0){
        return true
    }
    return false;
}

const isOutsideHours = (date_time) => {
    if (date_time.getHours() <= 8 && date_time.getHours >= 18){
        return true;
    }
    return false;
}

const departmentExists = (company, dept_id) => {
    let allDepts = dl.getAllDepartment(company);
    let result = false;
    console.log(allDepts)
    allDepts.forEach( department => {
        if (department.dept_id === parseInt(dept_id)){
            result = true
        }
    })
    console.log(result)
    return result;
}

const validateDepartmentPost = (company, dept_name, dept_no, location) => {
    let result = true
    if (company === undefined || dept_name === undefined || dept_no === undefined || location === undefined){
        result = false
    }
    let departments = dl.getAllDepartment("dec8768");
    departments.forEach(department => {
        if (department.dept_no === dept_no){
            result = false
        }
    })
    return result
}



module.exports = {
    isEmpty, validateEmployeePost, validateEmployeePut, 
    validateEmployeeDelete, validateTimecardsGet, validateTimeCardPost, 
    departmentExists, validateDepartmentPost
};