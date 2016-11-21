#Java-反射
##什么是反射
  Java的反射就是指在运行状态中,对于任意一个类,能够知道这个类的所有属性和方法;对于任意一个对象,都能够调用它的任意属性和方法.
  
  这种动态获取信息以及动态调用对象方法的功能被称为Java的反射机制.
  
##为什么要使用反射
  静态编译:在编译时确定类型,绑定对象,即通过.实在Java代码编译成字节码之前编译器的工作.
  
  我们工作中基本上都是使用静态编译.而反射是动态编译的,在运行时才确定编译的类型,并绑定对象,最大限度发挥了java的灵活性.
  
##使用反射
###Class
  众所周知Java有个Object类,是所有Java 类的继承根源,其内声明了数个应该在所有Java类中被改写的方法:
  
  hashCode(),equals(),clone(),toString()和getClass()等.其中getClass()返回一个Class对象.
  
  Class类和Object类的关系就像小明同学和他的个人档案一样.Class类一样继承自Object,是描述类的信息的一个类.
  
  在一个类被JVM加载的时候会自动生成一个Class对象,就像一个新生儿出生就会产生与之对应的个人档案一样.
  
  Class是Reflection故事起源.针对任何您想探勘的类,唯有先为它产生一个Class对象,接下来才能经由后者唤起为数十多个的Reflection APIs.
###获取类的Class类
  可以通过三种方法获得类的Class类型(Class Type):
  ```java
    //第一种,类的class
    Class c=Student.class;
    //第二种,实例对象的getClass方法
    Class c=new Student().getClass();
    //第三种,根据类路径获取
    Class c = Class.forName("zhangff01.vo.Student");
  ```
  拿到Class类型之后,就可以通过一些方法获取类的信息(Student的代码).
  ```java
    package zhangff01.vo;
    public class Student {
    	public String name;
		private int age;
	
		public Student(){}
	
		public Student(String name,int age){
			this.name=name;
			this.age=age;
		}
		private void think(){
			System.out.println(this.name+" is thinking...");
		}
		public void study(){
			System.out.println(this.name+" is studying...");
		}
    }
  ```
###通过反射获取类的字段信息
  getDeclaredFields和getFields方法返回值是Field数组.
  
  getDeclaredFields能获取到类的所有字段,而getFields只能获取public修饰的字段.
  ```java
    try {
		Class c = Class.forName("zhangff01.vo.Student");
		Field[] fields=c.getDeclaredFields();
		for(Field field:fields){
			System.out.print("修饰符:"+Modifier.toString(field.getModifiers()));
			System.out.println("　名称:"+field.getName());
		}
     } catch (ClassNotFoundException e) {
		e.printStackTrace();
     }
  ```
  getDeclaredField和getField方法是根据字段名获得字段.
  ```java
  	Field f=c.getDeclaredField("name");
  ```
  Field.setAccessible(Boolean flag);//为true代表可以访问使用private修饰的字段.
  
  Field.get(Object obj);//参数为类的实例对象,返回字段的值.
###通过反射获取类的方法信息
  getDeclaredMethods()获取的是类声明的所有的方法,包括public、protected和private方法.
  
  getMethods()获取的是类的所有共有的方法,包括自身的和从基类继承,接口实现的所有的public方法.(注意是所有的public方法)
  
  getMethod(name, parameterTypes...);根据方法名称和参数类型获取对应的public方法.
  
  getDeclaredMethod(name, parameterTypes...);根据方法名称和参数类型获取对应的方法.
###创建对象
  当我们获取到所需类的Class对象后,可以用它来创建对象,创建对象的方法有两种:

  使用Class对象的newInstance()方法来创建该Class对象对应类的实例,但是这种方法要求该Class对象对应的类有默认的空构造器.
  
  先使用Class对象获取指定的Constructor对象,再调用Constructor对象的newInstance()方法来创建Class对象对应类的实例.
  
  通过这种方法可以选定构造方法创建实例.(其实和获取类的方法一样)
##总结
  Java通过反射机制,可以在程序运行时加载,探知和使用编译期间完全未知的类,并且可以生成相关类对象实例,从而可以调用其方法或则改变某个属性值.
  
  反射的操作都是在编译之后进行的,例如集合的泛型是在编译前进行的,控制集合的类型一致.然而集合编译后是没有泛型的(擦除),
  
  所以通过反射就可以绕过泛型,例如在ArrayList<String>集合里面就可以通过反射的方式插入String之外其他类型的数据(如int).
  
  学习反射、了解反射可以然我们对java的理解更深,更灵活.
