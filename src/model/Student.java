package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Student extends Person implements Evaluation
{
    private double average;

    private final List<Course> courses = new ArrayList<>();

    private final Map<String, Course> approvedCourses = new HashMap<>();

    public Student( String id, String name, String email, Date birthDate )
    {
        super( id, name, email, birthDate );
    }

    public void enrollToCourse( Course course )
    {
        //TODO implement this method
    	
    	if(!approvedCourses.containsKey(course.getCode())) {
    		
    		
    		//approvedCourses.
    		System.out.println("Perticullary couse is not available in the has map so i am makin new id whith"+course.getCode());
    		approvedCourses.put(course.getCode(),course);
    		
    	}
    	else System.out.println("All ready course is enrolled");
	
    	
    }

    public void registerApprovedCourse( Course course )
    {
        approvedCourses.put( course.getCode(), course );
    }

    public boolean isCourseApproved( String courseCode )
    {
        //TODO implement this method
    	if(approvedCourses.containsKey(courseCode)) {
    		return true;
    	}
        return false;
    }

    // CHALLENGE IMPLEMENTATION: Read README.md to find instructions on how to solve. 
    public List<Course> findPassedCourses( Course course )
    {
        //TODO implement this method
        return null;
    }

    public boolean isAttendingCourse( String courseCode )
    {
        //TODO implement this method
    	
    	if(approvedCourses.containsKey(courseCode)) {
    		return true;
    	}
    	
        return false;
    }

    @Override
    public double getAverage()
    {
        return average;
    }

    @Override
    public List<Course> getApprovedCourses()
    {
        //TODO implement this method
    	
    	if(!approvedCourses.isEmpty()) {
    		//Set<String> key = approvedCourses.keySet();
    		
    		for (Map.Entry<String, Course> entry : approvedCourses.entrySet()) {
				String key = entry.getKey();
				Course val = entry.getValue();
				courses.add(val);
				
			}
    		return courses;
    		
    	}
    	
        return null;
    }

    @Override
    public String toString()
    {
        return "Student {" + super.toString() + "}";
    }
}