### SQL Leetcode
#### 1934 
~~~~sql
SELECT s.user_id, ROUND(IFNULL(SUM(CASE WHEN action = 'timeout' THEN 0 WHEN action = 'confirmed' THEN 1 END)/ COUNT(action),0),2) AS confirmation_rate from Signups AS s
LEFT JOIN Confirmations ON s.user_id = Confirmations.user_id  group by s.user_id;

# People Solution
/*
select s.user_id, round(avg(if(c.action="confirmed",1,0)),2) as confirmation_rate
from Signups as s left join Confirmations as c on s.user_id= c.user_id group by user_id;

*/
~~~~


#### 620 
~~~~sql 
SELECT id, movie, description, rating FROM Cinema where description != 'boring' AND mod(id,2) = 1 order by rating desc;
~~~~

#### 1251
Wrong, because in general, left join will return null. HOWEVER, some product_id without UnitsSold record will not display null value
Because the WHERE condition is after the JOIN, when JOIN we filter out some product_id without the condition(purchase_date BETWEEN start_date AND end_date)
THEREFORE, use AND instead (ON ... AND ... AND)  NOT(ON... WHERE ... AND)

~~~~sql
SELECT p.product_id, IFNULL(ROUND(SUM(p.price*u.units)/ SUM(u.units),2),0) AS average_price 
from Prices p LEFT JOIN UnitsSold u 
ON u.product_id = p.product_id WHERE purchase_date BETWEEN start_date AND end_date GROUP BY p.product_id
;
~~~~
Correct (Key point, IFNULL and "AND" before purchase_date)
~~~~sql 
# Write your MySQL query statement below
SELECT p.product_id, IFNULL(ROUND(SUM(p.price*u.units)/ SUM(u.units),2),0) AS average_price 
from Prices p LEFT JOIN UnitsSold u 
ON u.product_id = p.product_id AND purchase_date BETWEEN start_date AND end_date GROUP BY p.product_id
;
~~~~

#### 1075
~~~~sql 
SELECT project_id, ROUND(AVG(experience_years),2) AS average_years FROM Employee e, Project p 
WHERE e.employee_id = p.employee_id
group by project_id;
~~~~

~~~~sql 

~~~~

~~~~sql 

~~~~