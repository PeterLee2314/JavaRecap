### JPA (Java Persistence API)
What is JPA? We are working with data
the moment we close the application, data will gone => so we need to save the data in database
BUT, How do we save the Data? Objects into Database(row column) 
SO, we need ORM (Object Relational Mapping) => A class structure and variables will become table row and column (mapping)
Problem is, To achieve ORM, we need some tool (eg Hibernate, iBatis, TopLink)
Another Problem is, What If i want to use other ORM tool? (eg from Hibernate to iBatis)
SO, we introduce JPA (common standard & specification) => Thats why we have Spring Boot JPA (jakarta.persistence)
Why we need hibernate if we have JPA? its just a specification, hibernate for implementation
Java, RDBMS, JDBC, Hibernate

#### how to achieve JPA?
src/main/resources/META-INF/xxx.xml
```
EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
EntityManager em = emf.createEntityManager();

Alien a = em.find(Alien.class, 101);

persistence.xml

<?xml version="1.0" encoding="UTF-8"?>

<persistence xmlns="http://java.sun.com/xml/ns/persistence"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://java.sun.com/xml/ns/persistence
http://java.sun.com/xml/ns/persistence/persistence_2_0.xsd"
	version="2.0">
	<persistence-unit name="pu">
		<properties>
			<property name="javax.persistence.jdbc.driver"
				value="com.mysql.cj.jdbc.Driver" />
			<property name="javax.persistence.jdbc.url"
				value="jdbc:mysql://localhost:3306/neon" />
			<property name="javax.persistence.jdbc.user" value="root" />
			<property name="javax.persistence.jdbc.password" value="password" />
			
			<property name="hibernate.show_sql"  value="true"/>
			<property name="hibernate.hbm2ddl.auto"  value="update"/>
			
			
		</properties>
	</persistence-unit>
</persistence>
```
After define App.java and xml , we need to put Annotation inside the class object

em.getTransaction().begin();
em.persist(a);
em.getTransaction().commit();