package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import service.CourseService;

public class Student extends Person implements Evaluation
{
    private double average;

    private final List<Course> courses = new ArrayList<>();
    
    private  Map<Course,Integer> courseGrades = new HashMap<Course, Integer>();
    
    private final Map<String, Course> approvedCourses = new HashMap<>();


	public void setAverage(double average) {
		this.average = average;
	}
	
	public int getCourseGrade(Course course) {
		
		return courseGrades.get(course);
		
	}
	
	public void calculateAverage() {
		double totalgrade= 0 ;
		int courseCount = 0 ;
		
		for (Map.Entry<Course, Integer> entry : courseGrades.entrySet()) {
			
			Integer val = entry.getValue();
			totalgrade+=val;
			courseCount++;
			
		}
	        setAverage(totalgrade/courseCount);
		
	}

	

    public Student( String id, String name, String email, Date birthDate )
    {
        super( id, name, email, birthDate );
        
        
        
    }

    public void enrollToCourse( Course course )
    {
        //TODO implement this method
    	
    	if(!approvedCourses.containsKey(course.getCode())) {
    		
    		
    		//approvedCourses.
    		System.out.println("This course is not taken by student so i am making enroll to student : "+course.getCode());
    		approvedCourses.put(course.getCode(),course);
    		
    		
    	}
    	else System.out.println("Allready course is enrolled");
	
    	
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
    public List<Course> findPassedCourses()
    {
        //TODO implement this method
    	
    		    	
    	if(!approvedCourses.isEmpty()) {
    		List<Course> passedcourse = new ArrayList<Course>();
        	
        	Set<Course> keys=courseGrades.keySet();
        	
        	for(Course key : keys) {
        		
        		if(courseGrades.get(key)>50) {
        														//courses.add(approvedCourses.get(key));     // Method 1 
        			courses.add(key);                          // For this purpose we set the key of course grade as object of course it will save time of searching 
        		}    		
        	}
        	
        	// Another method for Iterate
//        	for(Map.Entry<Course,Integer> e : courseGrades.entrySet()) {       		
//        		courses.add(e.getKey());
//        		
//        	}
        	return courses;
    	}  	
        return null;
    }
    
    
    public void setGrade(String courseCode,int grade) {
    	
    	
    	if (isCourseApproved(courseCode)) {
    	    Course course = approvedCourses.get(courseCode);
    	     courseGrades.put(course, grade);
    	     
    	        System.out.println("Grade updated");
    	    System.out.println(courseGrades.get(course));
    	    
    	} else {
    	    System.err.println("Course not approved.");
    	}

    	
    	
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
    	calculateAverage();
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
   

	

	public List<Course> getCourses() {
		return courses;
	}

    @Override
    public String toString()
    {
        return "Student {" + super.toString() + "}";
    }
}