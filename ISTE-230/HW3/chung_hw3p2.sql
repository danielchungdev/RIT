-- Homework 3 part 2 script.
/*
Name: Daniel Chung.
Date: February 16.
*/

INSERT INTO contact_info
(contactID,
firstname,
middleinitial,
lastname,
suffix_description,
title_description,
jobtitle,
department,
email,
url,
IMaddress,
phone_number,
phonetype_description,
birthday,
notes,
companyname,
street1,
street2,
city,
state_province,
zip_postalcode,
country_region,
company_url,
company_phone)
VALUES
(2,
'Daniel',
'E',
'Chung',
'I',
'Mr.',
'Student',
'ISTE',
'dec8768@rit.edu',
'http://www.twitch.tv/pikachungg',
'Chung',
'585-766-7536',
'Personal',
'1999-01-23',
'Started as Student of RIT on August 2017.',
null,
'6000 Reynolds Drive',
'Global Village 404',
'Rochester',
'NY',
'14623-825',
'USA',
null,
null);

alter table contact_info add [column] Nickname char(20);

alter table contact_info add Nickname char(20) default 'To be determined';

alter table contact_info MODIFY firstname varchar(15) NOT NULL;
alter table contact_info MODIFY lastname varchar(15) NOT NULL;

update contact_info set nickname='Bill' where contactID=1;

delete from contact_info where companyname='Concor International, Inc.';