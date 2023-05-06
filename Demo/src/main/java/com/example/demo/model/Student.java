package com.example.demo.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;


@Entity
public class Student {
 @Id
 private int regno;
 //@Override
//public String toString() {
	//return "Student [regno=" + regno + ", name=" + name + ", department=" + department + ", email=" + email + "]";
//}
//public Student() {
	//super();
//}
//public Student(int regno, String name, String department, String email) {
	//super();
	//this.regno = regno;
	//this.name = name;
	//this.department = department;
	//this.email = email;
//}
private String name;
 private String department;
 private String email;
public int getRegno() {
	return regno;
}
public void setRegno(int regno) {
	this.regno = regno;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getDepartment() {
	return department;
}
public void setDepartment(String department) {
	this.department = department;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
 
}
