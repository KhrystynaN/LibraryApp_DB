select count(*) from books;


SELECT count(*) from users;

select count(*) from book_borrow
where is_returned=0;

select * from book_categories;

select name from book_categories;

select name,isbn,year,author,description from books
where name = 'Agile Testing';

select * from users
where email = 'librarian56@library';

select status from users where email='joshua.huel@yahoo.com';