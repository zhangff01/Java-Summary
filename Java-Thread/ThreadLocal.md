ThreadLocal
#介绍
ThreadLocal,怎么理解,翻译过来就是线程本地变量的意思,在多线程中,有些变量我们不想让每个线程的修改而影响到其他线程对该变量的使用,可以考虑使用ThreadLocal.

**如果你需要在多个进程之间通信,则使用同步机制,如果需要隔离多个线程之间的共享冲突,则需要使用ThreadLocal.**
#实现
ThreadLocal是通过map来实现的,查看源代码我们可以看看实现:
```java
  public void set(T value) {
        Thread t = Thread.currentThread();
        ThreadLocalMap map = getMap(t);
        if (map != null)
            map.set(this, value);
        else
            createMap(t, value);
  }
  ...
  public T get() {
        Thread t = Thread.currentThread();
        ThreadLocalMap map = getMap(t);
        if (map != null) {
            ThreadLocalMap.Entry e = map.getEntry(this);
            if (e != null)
                return (T)e.value;
        }
        return setInitialValue();
   }
   ...
   ThreadLocalMap getMap(Thread t) {
        return t.threadLocals;
   }
   ...
   void createMap(Thread t, T firstValue) {
        t.threadLocals = new ThreadLocalMap(this, firstValue);
   }
```
在这个方法内部我们看到,首先通过getMap(Thread t)方法获取一个和当前线程相关的ThreadLocalMap(每个Thread都有一个ThreadLocalMap类实例threadLocals).

ThreadLocalMap是ThreadLocal类的一个静态内部类,它实现了键值对的设置和获取.

每个线程中都有一个独立的ThreadLocalMap副本,它所存储的值,只能被当前线程读取和修改.Map的键为线程对象,值为变量值.

ThreadLocal<T>的API:
- public void set(T value):将值放入线程局部变量中.
- public T get():从线程局部变量中获取值.
- public void remove():从线程局部变量中移除值(有助于JVM垃圾回收).
- protected T initialValue():返回线程局部变量中的初始值(默认为null).

为什么initialValue()方法是protected的呢？就是为了提醒程序员们,这个方法是要你们来实现的,请给这个线程局部变量一个初始值吧.

所以一般这么使用:
```java
private static ThreadLocal<T> tl=new ThreadLocal<T>(){
  @override
  protected T initialValue(){
    return T;
  }
};
```
赋予初始值,要不然只要不进行set操作,get得到的一直是null.
