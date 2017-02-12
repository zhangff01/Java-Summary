#Servlet
##概述
Servlet首先是一个接口,源码如下:
```java
public interface Servlet
{
    public abstract void init(ServletConfig servletconfig) throws ServletException;
    public abstract ServletConfig getServletConfig();
    public abstract void service(ServletRequest servletrequest,ServletResponse servletresponse)
            throws ServletException, IOException;
    public abstract String getServletInfo();
    public abstract void destroy();
}
```
定义了5个方法:init,getServletConfig,service,getServletInfo和destory.

然后GenericServlet类提供了servlet接口的基本实现,是通用的,不特定于任何协议的Servlet实现.

而HttpServlet继承自GenericServlet类并提供了具体于Http的实现.,所以我们定义的Servlet只需要继承HttpServlet父类即可.

当一个Servlet被实例化后,包容器自动去调用固定的方法首先是init()初始化Servlet对象,该方法在整个生命周期只执行一次.

然后每次发出Http请求时都会执行service()方法,最后当Servlet销毁时调用destroy().

HttpServlet中的service方法:
```java
public void service(ServletRequest req,ServletResponse res) throws ServletException,IOException{
  HttpServletRequest request;
  HttpServletResponse response;
  try{
    request=(HttpServletRequest) req;
    response=(HttpServletResponse) res;
  }catch(ClassCastException e){
    throw new ServletException("non-HTTP request or response");
  }
  service(request,response);//调用下面的service方法.
}
protect void service(HttpServletRequest req,HttpServletResponse resp) throws ServletException,IOException{
  ...
  String method=req.getMethod();
  if(method.equals(METHOD_GET)){
    ...
    doGet();
  }else if(method.equals(METHOD_POST)){
    ...
    doPost();
  }
  //该service方法中,首先获得到请求的方法名,然后根据方法名调用对应的doXXX方法.
}
```
Http请求刚刚进来的时候实际上只是一个HTTP请求报文,容器会自动将这个Http请求报文包装成一个HttpServletRequest对象,

并且自动调用HttpServlet的service()方法来解析这个Http请求,service()方法会解析http请求行,并根据请求的method来决定是执行doGet还是doPost方法.
##创建一个Servlet
1.扩展HttpServlet抽象类.

2.覆盖HttpServlet的部分方法,如覆盖doGet()或doPost()方法.

3.通过HttpServletRequest对象处理数据和逻辑.

4.通过HttpServletReponse对象对请求作出响应.
