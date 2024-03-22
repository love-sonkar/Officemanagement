# Office management

This is a java console based office management project.

## Installation process

## Please make a database in your system using mysql

```bash
create database officemanagement;
use officemanagement;
```

## Create admin table for your project using
```bash
create table admin values(id int, username varchar(255), password varchar(255));
```
## Insert data for your admin

```bash
insert into admin values(1, "your username", "your password");
```

After Creating database and tables go to Hibernate.cfg.xml file and change username and password according to your database configuration. 

That's it for the setup

## Run project
Go to your App.java file run your Project

## Project Features

<h2>Admin</h2>
<h4> 1. Admin Login Function</h4>
<h4> 2. Your can see Employee Clients and Project list</h4>
<h4> 3. Admin can READ, CREATE, UPDATE and DELETE Project as well as Clients</h4>
<h4> 4. Admin can change Password for security</h4>
<h4> 4. Admin can Approve, Reject and Assign Projects to any employee </h4>
--------------------------------------------------------------------------

<h2>Employee</h2>
<h4> 1. Employee Login Function</h4>
<h4> 2. Employee can see Assign projects and Update Project Status </h4>
<h4> 3. Employee can UPDATE and DELETE own account </h4>
<h4> 4. Employee can change Password for security</h4>
--------------------------------------------------------------------------

<h2>Client</h2>
<h4> 1. Sign up and Login function</h4>
<h4> 2. After successfully login client can create project and wait for admin to approve once the project Approved client can see projects Update </h4>
<h4> 3. Client can READ, CREATE, UPDATE and DELETE Project </h4>
<h4> 4. And Change Password Functionality</h4>
--------------------------------------------------------------------------

<h3>Know more about this project please clone this repo and run the Project. </h3>
## Thankyou for using this project