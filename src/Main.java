import java.text.ParseException;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import model.Course;
import model.Student;
import service.CourseService;
import service.StudentService;
import util.PrinterHelper;
//Changes
public class Main
{

    public static void main( String[] args ) throws ParseException
    {
        StudentService studentService = new StudentService();
        CourseService courseService = new CourseService();
        Scanner scanner = new Scanner( System.in );
        int option = 0;
        do
        {
            PrinterHelper.showMainMenu();
            
            option = scanner.nextInt();
            switch ( option )
            {
                case 1:
                	
                    try{
                    	registerStudent( studentService, scanner );
                    	
                    }
                    catch (Exception e) {
						// TODO: handle exception
                    	System.err.println("Please Enter Valid input");
					}
                    
                    break;
                case 2:
                    findStudent( studentService, scanner );
                    break;
                case 3:
                    gradeStudent( studentService, scanner );
                    break;
                case 4:
                    enrollStudentToCourse( studentService, courseService, scanner );
                    break;
                case 5:
                    showStudentsSummary( studentService, scanner );
                    break;
                case 6:
                    showCoursesSummary( courseService, scanner );
                    break;
                case 7:
                	setGradeOfStudent(scanner,studentService);
                	
                case 8:
                	findPassedCourses(scanner, studentService);
                	
            }
        }
        while ( option != 9 );
    }

    private static void enrollStudentToCourse( StudentService studentService, CourseService courseService,
                                               Scanner scanner )
    {
        System.out.println( "Insert student ID" );
        String studentId = scanner.next();
        Student student = studentService.findStudent( studentId );
        if ( student == null )
        {
            System.out.println( "Invalid Student ID" );
            return;
        }
        System.out.println( student );
        System.out.println( "Insert course ID" );
        String courseId = scanner.next();
        Course course = courseService.getCourse( courseId );
        if ( course == null )
        {
            System.out.println( "Invalid Course ID" );
            return;
        }
      //  System.out.println( course );
        courseService.enrollStudent( courseId, student );
        studentService.enrollToCourse( studentId, course );
        System.out.println( "Student with ID: " + studentId + " enrolled successfully to " + courseId );

    }

    private static void showCoursesSummary( CourseService courseService, Scanner scanner )
    {
        courseService.showSummary();
    }

    private static void showStudentsSummary( StudentService studentService, Scanner scanner )
    {
        studentService.showSummary();
    }

    private static void gradeStudent( StudentService studentService, Scanner scanner ) throws ParseException
    {
    	
    	System.out.println("Please Enter student Id :");
    	String studentid = scanner.next();
    	try {
    		Student student=studentService.findStudent(studentid);
        	
        	if(!student.equals(null)) { /// student  = null ;--- > stuent.equals(null) -- > true .  -- false
        	
        		if(student.getAverage()>=90) {
            		System.out.println("Student has A grade");
            	}
            	else if(student.getAverage()>=60) {
            		System.out.println("Student has B grade :");
            	}
            	else if(student.getAverage()>=40)
            	{
            		System.out.println("Student has C grade");
            	}
            	else if(student.getAverage()>0) {
            		System.out.println("Student is fail");
            	}
        	}	
		} catch (Exception e) {
			System.err.println("Please Register Student first");
			 registerStudent(  studentService, scanner );
		}
    	

    }

    private static void findStudent( StudentService studentService, Scanner scanner ) 
    {
        System.out.println( "Enter student ID: " );
        String studentId = scanner.next();
        Student student = studentService.findStudent( studentId );
        if ( student != null )
        {
            System.out.println( "Student Found: " );
            System.out.println( student );
        }
        else
        {
            System.out.println( "Student with Id = " + studentId + " not found" );
           
        }
    }

    private static void registerStudent( StudentService studentService, Scanner scanner )
        throws ParseException
    {
        Student student = PrinterHelper.createStudentMenu( scanner );
        studentService.subscribeStudent( student );
    }
    
    public static void calculateAverageGrade(Scanner scanner ,CourseService courseService,StudentService studentService) { 
    	  
    	
    	
    	 System.out.println("Enter the course code:");
    	    String courseCode = scanner.nextLine();
    	    Course course = courseService.getCourse(courseCode);
    	    if (course != null) {
    	        double averageGrade = studentService.calculateAverageGradeForCourse(course);
    	        System.out.println("The average grade for course " + courseCode + " is: " + averageGrade);
    	    } else {
    	        System.out.println("Course not found.");
    	    }
    	
    	
    }
    
    public static void setGradeOfStudent(Scanner scanner , StudentService studentservice) {
    	
    	
    	System.out.println("Please Enter student id");
    	String sid = scanner.next();
    	
    	System.out.println("Please Enter course ");
    	String courseCode = scanner.next();
    	studentservice.setGradeOfStudent(sid,courseCode,scanner);
    	
    	
    	
    }
    
    private static void findPassedCourses(Scanner sc,StudentService service) {
		
    	System.out.println("Please Enter Student id");
    	String studentid = sc.next();
    	Student student = service.findStudent(studentid);
    	
    	if(!student.equals(null)) {
    		
    		
    		List<Course> list=student.findPassedCourses();
    		
    		for(Course c : list) {
    			
    			System.out.println(c);
    			
    		}
    		
    	}
    	else System.out.println("Did not find Student...");
    	
    	
    	
    	
    	
    	

	}
    
    
    
    
    
}
