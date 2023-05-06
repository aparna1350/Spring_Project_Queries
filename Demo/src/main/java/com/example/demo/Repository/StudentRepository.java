package com.example.demo.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Student;
import com.example.demobook.model.Orders;

@Repository
public interface StudentRepository extends JpaRepository<Student,Integer>
{
	//positional parameter
		@Query("select s from Student s where s.department=?1 and s.name=?2" )
		public List<Student> getStudentsByDepartment(String dept,String name);
	
		//native query
		@Query("select s from Student s where s.name=:name")
		public List<Student> getStudentByName(String name);
	
	
	//DML
	@Modifying
	@Query("delete from Student s where s.name=?1")
	public int deleteStudentByName(String name);
	
	@Modifying
	@Query("update Student e set e.name=?1 where e.department=?2")
	public int UpdateStudentByName(String name,String department);
	
	List<Student> findByNameStartingWith(String prefix);
	List<Student> findByNameEndingWith(String suffix);
	List<Student> findByDepartment(String dept);
}
