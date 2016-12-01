#Java内部类
可以将一个类定义在另外一个类的内部,这就是内部类
##为什么要使用内部类
  在《Think in java》中有这样一句话:
  
  使用内部类最吸引人的原因是:每个内部类都能独立地继承一个(接口的)实现,所以无论外围类是否已经继承了某个(接口的)实现,对于内部类都没有影响.
  
  但是使用内部类还能够为我们带来如下特性(摘自《Think in java》):
  
  1.内部类可以用多个实例,每个实例都有自己的状态信息,并且与其他外围对象的信息相互独立.
  
  2.在单个外围类中,可以让多个内部类以不同的方式实现同一个接口,或者继承同一个类.
  
  3.创建内部类对象的时刻并不依赖于外围类对象的创建.
  
  4.内部类并没有令人迷惑的“is-a”关系,他就是一个独立的实体.
  
  5.内部类提供了更好的封装,除了该外围类，其他类都不能访问.
##内部类
  广泛意义上的内部类一般来说包括以下四种:成员内部类，局部内部类，匿名内部类和静态内部类.
###成员内部类
  成员内部类定义于一个类的内部,相当于一个成员变量.形如下面的形式:
  ```java
  class Circle{
    double radius=0;
    public Circle(double radius){
      this.radius=radius;
    }
    //内部类
    class Draw(){
      public void drawShape(){
        System.out.println("drawshape...");
      }
    }
  }
  ```
  成员内部类是最普通的内部类,相当于外部类的一个成员变量.所以他是可以无限制的访问外围类的所有成员属性和方法,尽管是private的.
  
  但是外围类要访问内部类的成员属性和方法则需要通过内部类实例来访问.
  
  如果要访问外部类的同名成员,需要以下面的形式进行访问: 外部类.this.成员变量 和 外部类.this.成员方法. 
  
  在外部类中如果要访问成员内部类的成员,必须先创建一个成员内部类的对象,再通过指向这个对象的引用来访问,看下面的例子:
```java
  public class CarClass {
	private String name;
	private int price;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	//成员内部类
	class Engine{
		private float displacement;
		
		public float getDisplacement() {
			return displacement;
		}
		public void setDisplacement(float displacement) {
			this.displacement = displacement;
		}
		//内部类的构造函数
		public Engine(){}
		public Engine(float displacement){
			this.displacement=displacement;
		}
		public void info(){
			System.out.println("displacement:"+this.displacement+"L");
		}
		
	}
	//外部类的构造函数
	public CarClass(){}
	public CarClass(String name,int price){
		this.name=name;
		this.price=price;
	}
	//外部类的方法
	public void carInfo(Engine engine){
		System.out.println(this.name+" is "+this.price+"RMB");
		System.out.println("and it\'s displacement:"+engine.displacement+"L");
	}
  	//获取内部类的实例
  	public Engine getEngineInstance(){
		return new Engine();
	}
}
...
public class InnerClassTest {

	public static void main(String[] args) {
		CarClass cc=new CarClass("Volkswagon cc",20);
    		//第一种:通过外部类的实例new一个内部类
		CarClass.Engine ccengine=cc.new Engine(3.0f);
    		//第二种:通过外部类的方法获取内部类的实例
    		CarClass.Engine engine=cc.getEngineInstance();
		cc.carInfo(ccengine);
		ccengine.info();
	}

}
//运行结果:
Volkswagon cc is 20RMB
and it's displacement:3.0L
displacement:3.0L
```
在成员内部类中要注意两点,第一:成员内部类中不能存在任何static的变量和方法;

第二:成员内部类是依附于外围类的,所以只有先创建了外围类才能够创建内部类.
###局部内部类
有这样一种内部类,它是嵌套在方法和作用于内的,对于这个类的使用主要是应用与解决比较复杂的问题,

想创建一个类来辅助我们的解决方案,到那时又不希望这个类是公共可用的,所以就产生了局部内部类.

局部内部类和成员内部类一样被编译,只是它的作用域发生了改变,它只能在该方法和属性中被使用,出了该方法和属性就会失效.
###匿名内部类
说到匿名内部类我首先想到的就是生成一个新的线程的代码:
```java
    Thread tr=new Thread(new Runnable(){
			@Override
			public void run() {
				System.out.println("it\'s a thread");
		}});
		tr.start();
```
匿名内部类是没有访问修饰符的,只要一个类是抽象的或是一个接口,就可以使用匿名内部类

(个人理解就是临时继承一个抽象类或者实现一个接口调用)
###静态内部类
静态内部类也是定义在另一个类里面的类,只不过在类的前面多了一个关键字static来修饰.

静态内部类是不需要依赖外部类的,这点和类的静态成员属性有点类似,并且它不能使用外部类的非static成员变量或者方法

(这点很好理解,如果允许访问外部类的非static成员就会产生矛盾,因为外部类的非static成员必须依附于具体的对象).
```java
class Outter{
		public Outter(){}
		static class inner(){}
}
//调用方式
...
Outter.inner oi=new Outter.inner();
...

```

     
