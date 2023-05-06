package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Student;
import com.example.demo.service.StudentService;
import com.example.demobook.model.Orders;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
public class StudentController {
 @Autowired
StudentService studService;
 
 //to fetch all table details
 @Tag(name="Get",description="get data")
 @GetMapping(value="/fetchStudents")
 public List<Student> getAllStudents()
 {
	 List<Student> studList=studService.getAllStudents();
	 return studList;
 }

//to save Students

 @PostMapping(value="/saveStudent")
 public Student saveStudent(@RequestBody Student s)
 {
	 return studService.saveStudent(s);
 }
 
 
 //@PutMapping(value="/updateStudent")
 //public Student updateStudent(@RequestBody Student s)
 //{
//	 return studService.saveStudent(s);
// }
 
 //only updates records available in table if not available then it will be null but does not create new records
 
 @PutMapping(value="/updateStudent/{rno}")
 public Student updateStudent(@RequestBody Student s,@PathVariable int rno)
 {
	 return studService.updateStudent(s,rno);
 }
 
 
 @DeleteMapping("/deleteStudent/{rno}")
	 public void deleteStudent(@PathVariable("rno") int regno)
	 {
		 studService.deleteStudent(regno);
	 }

@GetMapping(value="/getStudent/{rno}")
public Student getStudent(@PathVariable("rno") int regno)
{
	return studService.getStudent(regno);
}
// sorting//

//    http://localhost:8080/sortStudents/name

@GetMapping("/sortStudents/{field}")
public List<Student>sortStudents(@PathVariable String field)
{
	return studService.sortStudents(field); 
}

///pagination

//     http://localhost:8080/pageStudents/0/1

@GetMapping("/pagingStudents/{offset}/{pageSize}")
public List<Student> pagingStudents(@PathVariable int offset,@PathVariable int pageSize)
{
	return studService.pagingStudents(offset,pageSize);
}


//pagination and sorting

@GetMapping("/pagingAndSortingStudents/{offset}/{pageSize}/{field}")
public List<Student> pagingAndSortingEmployees(@PathVariable int offset,@PathVariable int pageSize,@PathVariable String field)
{
	return studService.pagingAndSortingEmployees(offset,pageSize,field);
}

//JPA Query
//http://localhost:8080/fetchStudentsByNamePrefix?prefix=a
@GetMapping("/fetchStudentsByNamePrefix")
public List<Student> fetchStudentsByNamePrefix(@RequestParam String prefix)
{
	  return studService.fetchStudentsByNamePrefix(prefix);
}

//http://localhost:8080/fetchStudentsByNameSuffix?suffix=n

@GetMapping("/fetchStudentsByNameSuffix")
public List<Student> fetchStudentsByNameSuffix(@RequestParam String suffix)
{
	return studService.fetchStudentsByNameSuffix(suffix);
}

//http://localhost:8080/fetchStudentsByDepartment/ECE/Minu
@GetMapping("/fetchStudentsByDepartment/{dept}/{name}")
public List<Student> fetchStudentsByDepartment(@PathVariable 
		String dept,@PathVariable String name)
{
	return studService.getStudentsByDepartment(dept, name);
}
@DeleteMapping("/deleteStudentByName/{name}")
public String deleteStudentByName(@PathVariable String name)
{
int result=studService.deleteStudentByName(name)	;
if(result >0)
	return "Student record deleted";
else
	return "Problem occured while deleting";
}
@PutMapping("/update/{name}/{department}")

public String update(@PathVariable String name,@PathVariable String department)

{

	int result=studService.update(name,department);

	if(result>0)

		return "Updated Successfully";

	else

		return "Problem occur while updating";

}
//NATIVE QUERY
@GetMapping("/getByName/{name}")
public List<Student> getStudentByName(@PathVariable String name)
{
return studService.getStudentByName(name);
}
}