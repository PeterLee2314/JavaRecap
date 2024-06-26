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

#### 1633
~~~~sql 
SELECT contest_id, ROUND(COUNT(DISTINCT user_id) * 100 / (SELECT COUNT(user_id) FROM Users),2) 
AS percentage
FROM Register GROUP BY contest_id  ORDER BY percentage DESC, contest_id;
~~~~


#### 1211
~~~~sql 
select query_name, ROUND(AVG(rating / position) ,2) as quality , ROUND(AVG( rating < 3)*100,2) as poor_query_percentage 
from Queries Where query_name is not null
group by query_name 
;
select query_name,
round(avg(cast(rating as decimal) / position), 2) as quality,
round(sum(case when rating < 3 then 1 else 0 end) * 100 / count(*), 2) as poor_query_percentage
from queries group by query_name
;

#OR use SUM(IF(rating < 3, 1, 0)) 
#OR use CASE WHEN
~~~~

#### 1193
~~~~sql
SELECT 
    DATE_FORMAT(trans_date, '%Y-%m') as month, country, 
    COUNT(id) AS trans_count , 
    SUM(IF(state = 'approved', 1,0)) as approved_count,
    SUM(amount) AS trans_total_amount ,
    SUM(IF(state = 'approved', amount, 0)) AS approved_total_amount 
from Transactions group by month,country; 
~~~~
#### 1174
~~~~sql
#SLOW (because of JOIN)
SELECT
ROUND(SUM(IF(d2.first_order = d1.customer_pref_delivery_date, 1,0))*100/COUNT(DISTINCT d1.customer_id),2)
AS immediate_percentage FROM Delivery d1
INNER JOIN
(SELECT 
    MIN(order_date) AS first_order, customer_id
    FROM Delivery GROUP BY customer_id
 ) as d2 ON d1.customer_id = d2.customer_id 
 ;
 
#FAST (JUST 2 SELECT)
SELECT ROUND(AVG(order_date = customer_pref_delivery_date)*100,2) AS immediate_percentage FROM Delivery
WHERE (customer_id,order_date) IN
(
SELECT customer_id, MIN(order_date) FROM Delivery group by customer_id
); 
~~~~

#### 550
~~~~sql
# WRONG (because there would be case that 09/10 (first time), 9/13 (second time) and 9/14(third time), but you calculate it
# should only work when consecutive is first time and second time
/*
SELECT ROUND(COUNT(*)/(SELECT COUNT(DISTINCT player_id) from Activity),2) AS fraction from Activity a1 WHERE 
EXISTS (SELECT * FROM Activity a2 WHERE a1.event_date = DATE_SUB(a2.event_date, INTERVAL 1 DAY) AND a1.player_id = a2.player_id)

select ROUND((count(*)/(select count(distinct player_id) from activity)),2) as fraction
from activity a1 join activity a2
on a1.player_id = a2.player_id
and datediff(a1.event_date,a2.event_date) = 1
*/

#Correct
SELECT ROUND(COUNT(*)/(SELECT COUNT(DISTINCT player_id) FROM Activity),2) AS fraction FROM Activity
WHERE (player_id, DATE_SUB(event_date, INTERVAL 1 DAY)) # assume the date is second time and -1 , it should match first time when consecutive 
    IN (
        SELECT player_id, MIN(event_date) FROM Activity Group By player_id #Get the Min date so it must be the first time
    )

~~~~

#### 596
~~~~sql
SELECT class FROM Courses GROUP BY class HAVING COUNT(student) >= 5 ; 
// we only use Having with aggregate function after GROUP BY
~~~~

#### 619
~~~~sql 
SELECT MAX(num) as num From
(
SELECT num from MyNumbers Group By num Having count(num) = 1
) AS unique_number;
~~~~