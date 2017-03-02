#代理模式
##介绍
代理模式是指使用代理对象完成用户请求,屏蔽用户对真实对象的访问.
##动态代理
一般讲到代理模式都会讲动态代理,在Java中动态代理的实现有两种:
- 使用jdk动态代理.InvocationHandler接口和Proxy类实现(都位于reflect反射包下).
- 使用cglib动态代理.MethodInterceptor接口和Enhancer类实现.

##举例和实现
老是说概念可能不太好理解,就举个简单的例子吧:

现在基本上人人都有微信号(代理对象),我们可以通过微信号与其他人进行交流而又可以避免泄露隐私等考虑.

在这个例子中,微信号可以看做是代理对象,我们每个人是真实对象.
###代码实现
####使用jdk动态代理实现
真实对象和接口:
```java
package com.zhangff01;

/**
 * @description 我们想要代理的真实对象接口
 * @author zhangff01
 */
public interface IPerson {
	public void sayHello();
}
...
/**
 * @description 我们想要代理的真实对象
 * @author zhangff01
 */
public class Programer implements IPerson {

	@Override
	public void sayHello() {
		System.out.println("I am a Coder,Hello World!");
	}

}
```
代理对象:
```java
/**
 * @description 我们实际使用的代理类
 * @author Administrator
 */
public class Wechat implements InvocationHandler {
	private IPerson person;//我们希望使用的类
	
	public Wechat(IPerson person){
		this.person=person;
	}
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {

		System.out.println("代理之前的自定义操作");
		
		method.invoke(person, args);//这里是反射的方法
		
		System.out.println("代理之后的自定义操作");
		
		return null;
	}

}
```
测试代码:
```java
/**
 * @description 测试
 * @author zhangff01
 */
public class Test {

	public static void main(String[] args) {
		IPerson person=new Programer();
		InvocationHandler handler=new Wechat(person);
		
		IPerson real_person=(IPerson) Proxy.newProxyInstance(handler.getClass().getClassLoader(), 
				person.getClass().getInterfaces(), handler);
		
		real_person.sayHello();

	}

}
...输出结果
代理之前的自定义操作
I am a Coder,Hello World!
代理之后的自定义操作
```
实现了通过微信号与其他人打招呼的功能.
####使用cglib动态代理实现
```java
/**
 * @description 我们想要代理的真实对象
 * @author zhangff01
 */
public class Coder {
	
	public void sayHello(){
		System.out.println("Hello World!");
	}
	
}
```
```java
import java.lang.reflect.Method;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;
/**
 * @description 我们实际使用的是这个代理对象
 * @author zhangff01
 */
public class Wechat implements MethodInterceptor {
	//cglib的Enhancer类,可以生成类
	private Enhancer enhancer=new Enhancer();
	//获取真实对象
	public Object getProxy(Object object){
		//设置需要创建的类
		enhancer.setSuperclass(object.getClass());
		enhancer.setCallback(this);
		return enhancer.create();//生成真实的类
	}
	/**
	 * 代理方法-我们不仅可以使用到真实对象的方法,还可以在调用前后做一些操作
	 */
	@Override
	public Object intercept(Object o, Method method, Object[] arg,
			MethodProxy proxy) throws Throwable {
		System.out.println("开始代理方法...");
		Object obj=proxy.invokeSuper(o, arg);
		System.out.println("结束代理方法...");
		return obj;
	}

}
```
```java
/**
 * @description 测试代码
 * @author zhangff01
 */
public class Test {
	public static void main(String[] args){
		Wechat wechat=new Wechat();
		Coder coder=(Coder) wechat.getProxy(new Coder());
		coder.sayHello();
	}
}
...输出结果
开始代理方法...
Hello World!
结束代理方法...
```
##总结和感悟
###总结
- jdk实现动态需要被代理对象实现一个接口,jdk动态代理是根据反射来实现的.
- cglib实现动态代理不需要被代理对象实现接口,直接就可以代理,采用继承代理.
- 一般在代理调用次数不是特别高的情况下,jdk的代理性能高于cglib代理.

###感悟
那么代理模式有什么作用呢,从上面的代码中我们可以看到在代理对象Wechat中我们队真实对象的方法进行了拓展.

在调用真实对象的方法之前或者之后我们都可以做一些其他的处理,那么你想到了什么？Spring的AOP编程.

Spring的AOP就是用代理模式来实现的,我们这里直接在代理对象里面组装了真实对象,而Spring是通过容器管理并注入真实对象到对应的代理对象中的.

jdk动态代理和cglib动态代理在Spring AOP模块中均有使用.
