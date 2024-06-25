#### SQL Concept
### MYSQl Left Join (Origin from A + Intercept of A and B [Inclusive] OR exclude Intercept of A and B[Exclusive])
Left Join will put null value on not existing record, put everything in the left table
Table: A
id,name
Table: B
id, unique_id

if A.id is not find on B.id, it will create NULL on unique_id
```
select eu.unique_id, e.name from Employees e 
LEFT JOIN
EmployeeUNI eu on e.id = eu.id;
```
The LEFT JOIN keyword returns all records from the left table (table1), and the matching records from the right table (table2). 
The result is 0 records from the right side, if there is no match.

#### Inner Join (only display record when A match B, eg only A.id match B.id) , Inner Join =  JOIN (Natural Join)

![img_1.png](JoinTable.png)

the join will match every row from TableA with every row from TableB , if NO ON part
eg SELECT * FROM TableA  INNER JOIN TableB;

Else you specific it will have only matching result
SELECT * FROM TableA INNER JOIN TableB ON TableA.id = TableB.id;

#### 3-CTE(Common Table Expression)
Imagine you're cooking a meal. Before you start cooking, you gather all your ingredients and chop them up. 
That's what a CTE does in SQL. It's like preparing your ingredients separately before you start cooking.
```
WITH SaleDetails AS (
    SELECT 
        s.sale_id,
        p.product_name,
        s.year,
        s.price
    FROM 
        Sales s
    JOIN 
        Product p ON s.product_id = p.product_id
)
SELECT 
    product_name,
    year,
    price
FROM 
    SaleDetails;
```

#### SUBDATE() DATEDIFF()
Date -1 is not same as SUBDATE(Date, INTERVAL 1 DAY) why?
for example 1998-10-01 -1 should be 1998-9-31, HOWEVER the actual date should be 1998-9-30 , Therefore use SUBDATE or DATEDIFF 
follow with INTERVAL 1 DAY


#### function
any function must start with() without space eg SUM() // correct , SUM () // wrong

#### GROUP BY ( together with function eg AVG, SUM, COUNT)
SELECT COUNT(CustomerID), Country FROM Customers GROUP BY Country; // it mean we group same Country and COUNT how the row of Customer ID into number

If group by Student ID , so we will only combine when exactly match the Student ID and nothing else 
eg 
id = 1, subject = Math, 
id = 1, subject = English
It will merge together, and got unexpected result
RESULT: id = 1, subject = English OR id = 1, subject = Math

If group by student_id, subject_name 
the row of record will group follow (student_id + subject_name)
It will not merge together, unless both student_id and subject_name is same
id = 1, subject = Math, mark = 50
id = 1, subject = English, mark = 60
id = 1, subject = Math, mark = 79
id = 1, subject = English, mark = 120
id = 1, subject = PE, mark = 120
Similarly, if dont have aggregate +Function like Sum,Count, it will have unexpected result
eg we have SUM(mark) then we have reslt as
id = 1, subject = Math, mark = 129
id = 1, subject = English, mark = 180
id = 1, subject = PE, mark = 120

#### use > < operator on select column
Case 1 Select * from X where A > 3 (only A > 3 row allow)
Case 2 Select A > 3 from X (it will also have new column that A > 3 is 1 if true, 0 if else)
if rating < 3 returns either 1 and 0 for each group, why count(rating < 3) does not work?
SInce rating< 3 always return 1 or 0, count(rating <3) will stay the same and will be the number of all rows since it is always fed an non-null value.
Count only exclude null values for a given column
eg COUNT(0) is 6, COUNT(1) is 6 (as there is 6 row on the table), so COUNT accept any value except null COUNT(null)

#### Date (Java prefer DATE_FORMAT than EXTRACT)
%Y, %M ,%D => output: 2018, Decemeber , 18th
%y, %m ,%d => output: 18, 12 , 18
DATE_FORMAT(date, '%Y') // if date is 2018/06/04 => output: 2018 
YEAR(date) // get 2018
EXTRACT( YEAR_MONTH FROM `date` ) 