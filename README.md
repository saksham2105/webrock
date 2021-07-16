## Overview
Webrock is a webservices framework to help programmers to avoid burden of writting thousands of classes instead of that what users can do are they can create a package create different - different annotations each for specific purpose to avoid writting meaningless and monotonous code(See sample Examples)

## How to Use
* Download [Apache Tomcat](https://tomcat.apache.org/download-90.cgi) server for java
* Clone this repo Copy and paste this folder inside tomcat's folder
* edit web.xml according to your project need(How to edit web.xml is written below)
* create a package structure inside classes folder (For demo examples I have create a package named as bobby/foo, bobby/foo inside classes folder)
* create a class inside package and compile it accoording to latest jdk
* for compilation write this line in command prompt (javac -classpath {path of tomcat lib}\*;{path of lib folder inside WEB-INF}\*;{path upto classes folder};. Classname.java)
* start tomcat server by running startup batch file inside tomcat's bin folder and send request from either browser or REST Client(I have written a demo example below which will help how to use annotations and how to send request)

## Specify your service pattern and package name to scan inside web.xml file located in WEB-INF folder
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
