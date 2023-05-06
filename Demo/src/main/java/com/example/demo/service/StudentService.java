package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.example.demo.Repository.StudentRepository;
import com.example.demo.model.Student;
import com.example.demobook.model.Orders;

import jakarta.transaction.Transactional;

@Service
public class StudentService {
	@Autowired
StudentRepository studRepository;
	
	//getMapping fetchingAll
 public List<Student> getAllStudents()
 {
	 List<Student> studList=studRepository.findAll();
	 return studList; 
}
 
 //postMapping
 
 public Student saveStudent(Student s)
 {
	 Student obj=studRepository.save(s);
	 return obj;
	 //return studRepository.save(s);
 }
 
 //putMapping if record not available then creates new record
 
 //public Student updateStudent(Student s)
 //{
//	 Student obj=studRepository.save(s);
	// return obj;
	 //return studRepository.save(s);
 //}
 
 //putMapping if record not available returns null
 
 public Student updateStudent(Student s,int rno)
 {
	 Optional<Student> optional=studRepository.findById(rno);
	 Student obj=null;
	 if(optional.isPresent())
	 {
		 obj=optional.get();
		 studRepository.save(s);
	 }
	 return obj;
 }
 
 //deleteMapping
 
 public void deleteStudent(int regno)
 {
	 studRepository.deleteById(regno);
 }
 
 //getMapping to find particular column
 
 
 public Student getStudent(int regno)
 {
	 Student s=studRepository.findById(regno).get();
	 return s;
 }
 // sorting//
 
 public List<Student> sortStudents(String field)
 {
	//ascending findAll(sort sort) and no change in controller method for both//
	 //return studRepository.findAll(Sort.by(field));
	 
	 //descending findAll(Sort.by(Direction,field))
	 return studRepository.findAll(Sort.by(Direction.DESC, field));
 }
 
 
 //pagination
 
 public List<Student> pagingStudents(int offset,int pageSize)
 {
	 Pageable paging=PageRequest.of(offset,pageSize);
	 Page<Student> studData=studRepository.findAll(paging);
	 List<Student>studList=studData.getContent();
	 return studList;
 }
 
 
 //pagination and sorting
 
 public List<Student> pagingAndSortingEmployees(int offset,int pageSize,String field) 
 {
	Pageable paging = PageRequest.of(offset, pageSize).withSort(Sort.by(field));
	Page<Student> stud=studRepository.findAll(paging);
	return stud.getContent();
 }
 
 //JPA QUERY
 
 public List<Student> fetchStudentsByNamePrefix(String prefix)
 {
	  return studRepository.findByNameStartingWith(prefix);
 }
 
 public List<Student> fetchStudentsByNameSuffix(String suffix)
 {
	 return studRepository.findByNameEndingWith(suffix);
 }
 
 public List<Student> getStudentsByDepartment(String dept,String name)
 {
	  return studRepository.getStudentsByDepartment(dept, name);
 }
 @Transactional
 public int deleteStudentByName(String name)
 {
 	return studRepository.deleteStudentByName(name);
 }
 @Transactional
 public int update(String name,String department)
 {
	 return studRepository.UpdateStudentByName(name,department);
 }
//native
public List<Student> getStudentByName(String name)
{
	return studRepository.getStudentByName(name);
}
}
