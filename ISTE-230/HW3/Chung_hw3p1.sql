-- Script for hw3.

/*
Name: Daniel Chung
Date: 02/15/19
*/

use world; 

select HeadofState from country where Code='usa';

update Country set headofstate='Donald J. Trump' where Code='usa';

select Name from country where IndepYear is null;

select Name, Continent from country where population > 1000000000 and LifeExpectancy > 70 and LifeExpectancy < 80;

select Name from country where continent='North America' or  continent='South America';