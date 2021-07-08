package bobby.member;
import com.thinking.machines.webrock.annotations.*;
@Path("/member")
@Get
public class MemberService
{
@Path("/addMember")
@Forward("/index.jsp")
public void addMember()
{
System.out.println("Add Member Invoked");
}
@Path("/updateMember")
public void updateMember()
{
System.out.println("Update Member Invoked");
}
}