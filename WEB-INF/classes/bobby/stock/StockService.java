package bobby.stock;
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