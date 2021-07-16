## Overview
Webrock is a webservices framework to help programmers to avoid burden of writting thousands of classes instead of that what users can do are they can create a package create different - different annotations each for specific purpose to avoid writting meaningless and monotonous code(See sample Examples)

## How to Use
* Download [Apache Tomcat](https://tomcat.apache.org/download-90.cgi) server for java
* Clone this repo Copy and paste this folder inside tomcat's folder
* edit web.xml according to your project need(Refer web.xml file for demo examples)
* create a package structure inside classes folder (For demo examples I have create a package named as bobby/foo, bobby/foo inside classes folder)
* create a class inside package and compile it according to latest jdk
* Paste TMWebRock.jar and gson jar inside libs folder of WEB-INF
* start tomcat server by running startup batch file inside tomcat's bin folder and send request from either browser or REST Client(I have written a demo example below which will help how to use annotations and how to send request)
* For Compilation Write -> javac -classpath c:\tomcatFolder\lib\*;c:\tomcatFolder\webapps\project\WEB-INF\lib\*;
c:\tomcatFolder\webapps\project\WEB-INF\classes;. ClassName.java

* For Testing Open your browser or Any RestClient Tool
type localhost:portNumber/project/servicePattern/stock/updateStock in address bar 
* for post requests if there is Some data pass it in Body from rest tools
* for query string pass it in url too

## Specify your service pattern and SERVICE_PACKAGE_PREFIX to scan Classes on server startup inside web.xml file
Refer web.xml File inside WEB-INF folder

## Useful Annotations which will help programmers
* @Path("/pathPattern") applicable for both class as well as method level 
* @AutoWired(name="propname") this annotation will help when user wants to inject data in any property from request or session or servletContext(Property level)
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

## Sample Code Examples
*Example 1-
Related to OnStartup,forward and get Feature
```sh
package com.project.demo;
import com.thinking.machines.webrock.annotations.*;
@Path("/stock")
public class StockService
{
@Path("/addStock")
@Get
@Forward("/index.jsp")
@OnStartup(priority=1)
public void addStock()
{
System.out.println("Add Stock Invoked");
}
@Path("/updateStock")
@Get
@Forward("/stock/addStock")
public void updateStock()
{
System.out.println("Update Stock Invoked");
}
}
```

*Example 2- Related to Autowired,Scopes
```sh
//Pojo Employee Class
package com.project.demo;
public class Employee 
{
private Integer code;
private String name;
public Employee()
{
this.code=0;
this.name=null;
}
public void setCode(Integer code)
{
this.code=code;
}
public Integer getCode()
{
return this.code;
}
public void setName(String name)
{
this.name=name;
}
public String getName()
{
return this.name;
}
}

package com.project.demo;
import com.thinking.machines.webrock.annotations.*;
import com.thinking.machines.webrock.injections.*;
@Path("/employee")
@InjectApplicationScope
public class EmployeeService
{
private ApplicationScope applicationScope;
public void setApplicationScope(ApplicationScope applicationScope)
{
this.applicationScope=applicationScope;
System.out.println("Application Scope invoked for employee service "+applicationScope);
}
@AutoWired(name="xyz") // if there exists some key with name xyz in session or application or request scope it will inject that object in second employee
private Employee secondEmployee;
@AutoWired(name="test")
private Employee employee;
@Path("/addEmployee")
@Get
@Forward("/member/addMember")
public void addEmployee()
{
System.out.println("Add Employee Invoked");
}
@Path("/getSecondEmployee")
@Get
public void getSecondEmployee()
{
Employee emp=new Employee();
emp.setCode(101);
emp.setName("Suresh");
if(this.applicationScope.getAttribute("test")!=null)
{
Employee e=(Employee)this.applicationScope.getAttribute("test");
System.out.println(e.getName());
}
this.applicationScope.setAttribute("test",emp);
System.out.println("Get Second Employee Chala ");
}
@Path("/printSecondEmployee")
@Get
@Forward("/index.jsp")
public void printSecondEmployee()
{
System.out.println("Print Second Employee Invoked");
if(this.employee!=null) System.out.println(this.employee.getCode()+","+this.employee.getName());
}
@Path("/getThirdEmployee")
@Get
public void getThirdEmployee()
{
Employee emp=new Employee();
emp.setCode(102);
emp.setName("Mohan");
if(this.applicationScope.getAttribute("xyz")!=null)
{
Employee e=(Employee)this.applicationScope.getAttribute("xyz");
System.out.println(e.getName());
}
this.applicationScope.setAttribute("xyz",emp);
System.out.println("Get Third Employee Invoked ");
}
@Path("/printThirdEmployee")
@Get
@Forward("/index.jsp")
public void printThirdEmployee()
{
System.out.println("Print Third Employee Invoked");
if(employee==null)
{
System.out.println("First if is null");
}
if(this.secondEmployee!=null) System.out.println(this.secondEmployee.getCode()+","+this.secondEmployee.getName());
}
}
```
