--Daniel Chung HW9
USE book;

select CONCAT(city, ', ' ,statecode) as Location, count(StateCode) as Count from publisher group by CONCAT(city, ', ' ,statecode) order by count(StateCode);

select book.title, count(bookreview.rating) as 'Total Ratings', MIN(Rating) as 'Low', MAX(rating) as 'High', cast(avg(rating) as decimal(4,2)) as 'Average' from book
left join bookreview on book.isbn = bookreview.isbn group by book.title order by count(bookreview.rating) desc;

select publisher.name as 'Publisher Name', count(publisher.name) as 'Book Count' from publisher left join book on publisher.PublisherID=book.PublisherID
group by publisher.name having count(publisher.name) > 2 order by count(publisher.name) desc;

select title, length(title) as 'Length', SUBSTR(title, INSTR(title, 'Bill') + 4, length(title)) as 'After Bill' from book group by title having title
like '%Bill%' order by length(SUBSTR(title, INSTR(title, 'Bill') + 4, length(title))) desc;

select distinct book.title from book right join ownersbook on book.isbn=ownersbook.isbn;
