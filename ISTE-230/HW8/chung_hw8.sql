/*
Daniel Chung
HW8
4/15/2019
*/

SELECT DISTINCT statecode from employer;

select Employer.companyname, Interview.salaryoffered, employer.division, employer.statecode from employer inner join interview on employer.companyname = interview.companyname;

select state.statecode, state.description from state left join employer on state.statecode = employer.statecode where employer.statecode IS NULL;

select distinct companyname, minhrsoffered from interview;

select * from state where description like '__a%' or description like '__e%' or description like '__i%' or description like '__o%' or description like '__u%';

select quarter.qtrcode, quarter.location, state.description from quarter inner join state on quarter.location = state.statecode;

select state.description, employer.companyname from state left join employer on state.statecode = employer.statecode;
