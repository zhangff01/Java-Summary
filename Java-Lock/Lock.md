#Lock锁
##介绍
在jdk 1.5之前只有一种锁,就是使用synchronized来实现锁的功能.

在jdk 1.5之后增加了Lock接口(及其实现类)来实现锁的功能.
##详解
首先Lock是一个接口,查看jdk源码可以看到在Lock接口中定义了以下方法:
- void lock():请求获取锁,会一直请求直到获取锁.
- void lockInterruptible:同lock方法一样,但是会优先处理Interrupted.
- boolean trylock():获取锁返回true.
- boolean trylock(long time,Timeunit unit):在指定时间内获取锁,返回true,第二个参数是时间单位.
- void unlock():释放锁.
- Condition newCondition():返回绑定在此Lock实例上的Conditiond对象.

##ReentrantLock(可重入锁)和ReentrantReadWriteLock(读写锁)
首先ReentrantLock和ReentrantReadWriteLock都是独占锁,即同一时间只有一个线程获取锁.

**公平锁/不公平锁**:
在公平锁的模式下,线程按照他们请求的顺序来获取锁,而不公平锁模式下线程获取锁的方式是随机的.
###ReentrantLock
查看源码可以看到ReentrantLock有一个内部类Sync,Sync内部类继承了AbstractQueuedSynchronizer抽象类,

ReentrantLock的大部分功能都在Sync中实现了.另外还有NofairSync(不公平锁)和FairSync(公平锁)继承于Sync类实现锁的公平/不公平竞争.

所以ReentrantLock支撑两种锁模式,公平锁和不公平锁.ReentrantLock有两个构造函数,默认无参的为不公平锁模式,参数为true为公平锁模式.
###和ReentrantReadWriteLock
和ReentrantReadWriteLock的代码结构和ReentrantLock一样,ReentrantReadWriteLock提供两种锁:读取锁readLock和写入锁writeLock.
获取读取锁/写入锁:
```java
ReentrantReadWriteLock rwLock=new ReentrantReadWriteLock();
Lock rLock=rwLock.readLock();
LOck wLock=rwLock.writeLock();
...使用
...
try{
  rLock.lock();
  ...
}finally{
  rLock.unlock();
}
...
```
##区别
- Synchronized锁是在jvm层面上实现的,而Lock锁是用代码实现.
- Synchronized锁在发生异常时会自动释放锁,但是Lock锁在发生异常时不会,所以在使用Lock锁时最好在finally块中lock.unlock()释放锁.
- 在竞争不是很激烈的情况下Synchronized性能是优于Lock的,反之Lock性能更好.
