package bobby.foo;
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
@AutoWired(name="xyz")
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
emp.setName("Kalu");
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
emp.setName("Raju");
if(this.applicationScope.getAttribute("xyz")!=null)
{
Employee e=(Employee)this.applicationScope.getAttribute("xyz");
System.out.println(e.getName());
}
this.applicationScope.setAttribute("xyz",emp);
System.out.println("Get Third Employee Chala ");
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