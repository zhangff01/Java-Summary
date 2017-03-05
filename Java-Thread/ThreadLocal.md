ThreadLocal
#介绍
ThreadLocal,怎么理解,翻译过来就是线程本地变量的意思,在多线程中,有些变量我们不想让每个线程的修改而影响到其他线程对该变量的使用,可以考虑使用ThreadLocal.
#实现
ThreadLocal<T>的API:
- public void set(T value):将值放入线程局部变量中.
- public T get():从线程局部变量中获取值.
- public void remove():从线程局部变量中移除值(有助于JVM垃圾回收).
- protected T initialValue():返回线程局部变量中的初始值(默认为null).

为什么initialValue()方法是protected的呢？就是为了提醒程序员们,这个方法是要你们来实现的,请给这个线程局部变量一个初始值吧.
