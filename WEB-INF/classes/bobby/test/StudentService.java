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