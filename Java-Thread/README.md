#Java-多线程
##线程的生命周期和五种基本状态
###五种基本状态:

####新建状态(New):当线程对象创建后,即进入了新建状态,例如:Thread tr=new Thread(new Runnable(){...});

####就绪状态(Runnable):

   当调用线程对象的start方法后(tr.start()),线程就进入了就绪状态.

   就绪状态只是代表该线程可以去被CPU调度执行,并不是说执行了tr.start()此线程立即就会执行.

####运行状态(Running):CPU开始执行处于就绪状态的线程,此时的线程进入运行状态.

####阻塞状态(Blocked):

   处于运行状态中的由于某种原因,暂时放弃对CPU的使用权,停止执行,此时进入阻塞状态,

   直到其进入到就绪状态,才有机会再次被CPU调用以进入到运行状态.

根据阻塞产生的原因不同,阻塞状态又可以分为三种:

1.等待阻塞:运行状态中的线程调用wait方法,使本线程进入到等待阻塞状态.

2.同步阻塞:线程在获取synchronized同步锁失败(因为锁被其它线程所占用),它会进入同步阻塞状态.

3.通过调用线程的sleep()或join()或发出了I/O请求时,线程会进入到阻塞状态.
####死亡状态(Dead):线程执行完了或者因异常退出了run()方法,该线程结束生命周期.
###线程的生命周期如下图:
![image](https://github.com/zhangff01/Java-Summary/blob/master/Java-Thread/Thread.png)
##线程的创建和启动
###1.继承Thread类,重写该类的run()方法
```java
package zhangff01;

public class MyThread extends Thread {
	@Override
	public void run(){
		for(int i=0;i<3;i++){
			System.out.println(i);
		}
	}
}
...
public class MainThread {
	public static void main(String[] args) {
		Thread tr=new MyThread();
		tr.start();
		System.out.println("...end");
	}
}
//运行结果:
...end
0
1
2
```
###2.实现Runnable接口并重写run()方法,创建实现类的实例并作为参数传递给Thread构造函数来创建Thread实例对象.
```java
package zhangff01;
public class MyThread2 implements Runnable {
	@Override
	public void run() {
		for(int i=0;i<3;i++){
			System.out.println(i);
		}
	}
}
...
public class MainThread {
	public static void main(String[] args) {
		Thread tr2=new Thread(new MyThread2());
		tr2.start();
		System.out.println("...end");
	}
}
//运行结果:
...end
0
1
2
```
##线程的方法
###yield()方法
当调用线程的yield()方法时,此线程会从运行状态转换为就绪状态,因为yield()方法只能让同级的线程有执行的机会,

所以接下来cpu接下来调度哪个线程是随机的,可能会出现A线程调用了yield()方法后,接下来CPU仍然调度了A线程的情况.
###sleep()方法(Thread类的静态方法)
使当前线程(即调用该方法的线程)暂停执行一段时间,会进入阻塞状态,让其他线程有机会继续执行.

但它并不释放对象锁,也就是说如果有synchronized同步块,其他线程仍然不能访问共享数据.

sleep()可以使低优先级的线程得到执行的机会,当然也可以让同优先级,高优先级的线程有执行的机会.

sleep()调用之后当指定的时间到了又会自动恢复运行状态.
###join()
等待该方法的线程执行完毕后再往下继续执行,有一种感觉就是程序像单线程一样顺序执行.
###wait()和notify()、notifyAll()----(这三个是Object类的方法)
我们怎么在代码里使用wait()呢？因为wait()并不是Thread类下的函数,正确的方法是对在多线程间共享的那个Object来使用wait

所以必须在synchronized语句块内使用.而且是永远在while循环而不是if语句中使用wait.

wait()方法使当前线程暂停执行并释放对象锁标示,让其他线程可以进入synchronized数据块,当前线程被放入对象等待池中(等待阻塞状态).

当调用notify()方法后,将从对象的等待池中移走一个任意的线程并放到锁标志等待池中.

即同步阻塞状态(只有锁标志等待池中线程能够获取锁标志),获取锁标志则进入就绪状态.

如果锁标志等待池中没有线程,则notify()不起作用.notifyAll()则从对象等待池中移走所有等待那个对象的线程并放到锁标志等待池中.
