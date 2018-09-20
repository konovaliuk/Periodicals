> ## System of Periodicals 

### Description

**Administrator** maintains a catalog of **periodicals**.<br />
The **reader** can **subscribe** by pre-selecting the **periodicals** from the catalog.<br />
The system calculates the amount for payment and registers the **payment**.

------------------

### Technologies
* JSP + JSTL
* Servlets
* JDBC
* Log4J
* JUnit
* Mockito
------------------

### Software requirements
* jdk 1.8.0_181
* Apache Tomcat 9.0.10
* MySQL Server 8.0
------------------

### Installation
* Clone, fork or download the source code from this Github page
* Create database from file: \src\main\resources\periodicals.sql
* Run mvn clean install
* Copy Periodicals.war from target folder and paste it to the /TOMCAT/webapps folder.
------------------

### Launch
* Run Tomcat server using script /TOMCAT/bin/startup.bat
* Connect to the application at http://localhost:8080/

