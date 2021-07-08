## Overview
Webrock is a webservices framework to help programmers to avoid burden of writting thousands of class and writting a lot of code inside that classes instead of that what users can do are they can create a package create different different annotations each for specific purpose to avoid writting meaningless and a lot of code

## How to Use
Download apache tomcat server for java
Clone this repo Copy and paste this folder inside tomcat's folder
edit web.xml according to your project need(How to edit web.xml is written below)
create a package structure inside classes folder (For demo examples I have create a package named as bobby/foo, bobby/foo inside classes folder)
create a class inside package and compile it accoording to latest jdk
for compilation write this line in command prompt (javac -classpath {path of tomcat lib}\*;{path of lib folder inside WEB-INF}\*;{path upto classes folder};. Classname.java)
start tomcat server by running startup batch file inside tomcat's bin folder and send request from either browser or REST Client(I have written a demo example below which will help how to use annotations and how to send request)

## Specify your service pattern and package name to scan inside web.xml file located in WEB-INF folder
Refer web.xml File inside WEB-INF folder

## Useful Annotations which will help programmers
- @Path("/pathPattern") applicable for both class as well as method level 
- @AutoWired(name="propname") this annotation will help when user wants to inject data in any property from request or session or servletContext(Property level)
- @Forward(urlPattern) this annotation will forward request to url pattern(Method level)
- @OnStartup(priority) the method annotated with this annotation will invoked according to priority number
- @Get() to specify a service is of get type (class and both method level the method or class which is not annotated with this annotation will be application for both get and post efaultly)
- @Post() to specify a service is of post type (class and both method level the method or class which is not annotated with this annotation will be application for both get and post efaultly)
- @RequestParameter(name="paramname") this annotation is used to get data coming from query string in get request directly inside a method parameter
- @InjectRequestParameter("paramname") this annotation is used to wrap data coming from query string to a corresponding property specified
- @PathVariable() this annotation will help to get data coming along with request uri inside a method parameter
- @InjectApplicationDirectory this annotation is used to get wrapper of ApplicationDirectory class inside a property specified
- @InjectApplicationScope this annotation is used to inject application scope(ServletContext) wrapper inside a service as a Field
- @InjectSessionScope this annotation is used to inject Session scope(HttpSession) wrapper inside a service as a Field
- @InjectRequestScope this annotation is used to inject Request scope(HttpServletRequest) wrapper inside a service as a Field

## Precuations to take
Do not change code of classes present inside a package started with com,Right now I haven't made any jar file for this project so one has to use classes located inside package started with com

## Sample Code Examples
## Example 1)
## package - bobby/test

package bobby.test;
import com.thinking.machines.webrock.annotations.*;
import com.thinking.machines.webrock.injections.*;
@Path("/student")
@Get
@InjectSessionScope
@InjectRequestScope
@InjectApplicationScope
public class StudentService
{
private SessionScope sessionScope;
private RequestScope requestScope;
private ApplicationScope applicationScope;
public void setSessionScope(SessionScope sessionScope) //inversion of control
{
System.out.println("Set Session Scope Invoked");
this.sessionScope=sessionScope;
}
public void setRequestScope(RequestScope requestScope)
{
System.out.println("Set Request Scope Invoked");
this.requestScope=requestScope;
}
public void setApplicationScope(ApplicationScope applicationScope)
{
System.out.println("Set Application Scope Invoked");
this.applicationScope=applicationScope;
}
@Path("/addStudent")
@Forward("/employee/addEmployee")
public void addStudent()
{
System.out.println("Add Student Invoked");
}
@Path("/setSessionTest")
public void setSessionTest()
{
System.out.println("Set session scope Test");
if(sessionScope==null)
{
System.out.println("Session Scope is Null");
}
if(sessionScope!=null) this.sessionScope.setAttribute("name","Saksham");
}
@Path("/getSessionTest")
public void getSessionTest()
{
System.out.println("Get session scope Test");
System.out.println(this.sessionScope.getAttribute("name"));
}
@Path("/setApplicationTest")
public void setApplicationTest()
{
System.out.println("Set application scope Test");
if(applicationScope==null)
{
System.out.println("Application Scope is Null");
}
if(applicationScope!=null) this.applicationScope.setAttribute("salary",1000);
}
@Path("/getApplicationTest")
public void getApplicationTest()
{
System.out.println("Get application scope Test");
System.out.println(this.applicationScope.getAttribute("salary"));
}
@Path("/setRequestTest")
public void setRequestTest()
{
System.out.println("Set Request Test Invoked");
this.requestScope.setAttribute("rollNumber","0701CS161049");
System.out.println(this.requestScope.getAttribute("rollNumber"));
}
@Path("/test")
@Forward("/test.jsp")
@OnStartup(priority=2)
public void test()
{
if(this.sessionScope!=null) System.out.println(this.sessionScope.getAttribute("designation"));
System.out.println("Test From Student Service");
}
@Path("/test2")
@OnStartup(priority=3)
public void test2()
{
System.out.println("Test 2 From Student Service");
}
@Path("/test3")
@OnStartup(priority=1)
@Forward("/student/test")
public void test3()
{
if(sessionScope!=null) this.sessionScope.setAttribute("designation","CSE");
System.out.println("Test 3 From Student Service");
}
}



