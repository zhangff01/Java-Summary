Java - JVM

JVM内存分代策略
Java虚拟机根据对象存活的周期不同，把堆内存（heap）划分为几块，一般分为新年代（Young）、老年代（Old）和永久代（Permanent）（hotspot虚拟机这么实现的），这就是JJVM的内存分代策略。


为什么要分代？
堆内存（heap）是虚拟机管理的内存中最大的一块，也是垃圾回收最频繁的一块区域，所有的对象实例都存放在堆内存中。给堆内存分代是为了提高对象内存分配和垃圾回收的效率。


新创建的对象会在新生代中分配内存，经过多次GC之后依然存活下来的对象存放在老年代中，静态属性、类信息等存放在永久代中，新生代的对象存活时间短，所以GC比较频繁，老年代中对象生命周期长，GC频率就比较低，永久代一般不会进行GC。


新生代（Young）
HotSpot将新生代划分为三块，一块较大的Eden空间和两块较小的Survior空间，默认比例为8:1:1。划分的目的是因为hotspot采用复制算法来回收新生代。
新生成的对象在Eden区分配（大对象除外，大对象直接进入老年代），当Eden区没有足够的空间进行分配，虚拟机将发起一次Minor GC。
GC开始时，对象只会存在于Eden空间和From Survior区域，To Survior是空的，在GC进行时，Eden区域中存活的对象都会被复制到To Survior区,而在From Survior区域中的对象，存活下来的并且年龄值（之GC的存活次数）达到年龄阀值（默认为15）的对象会被移到老年代中，没达到的会被复制到To Survior区。
接着清空Eden区和From Survior区，然后交换From Survior和To Survior的角色，这样一次GC完成之后，Enden区和To Survior区域是空的。
From Survior存放上次GC存活下来的对象。

老年代（Old）
在新生代中，经历多次GC，对象年龄值达到年龄阀值的对象会被移到老年代中，老年代GC频率比较低。

永久代（Permanment）
永久代是HotSpot实现JVM中才有的，而其他的JVM实现里面并没有永久代的说法，而且在Java 8里面已经去掉了永久代，取而代之的是元空间（Metaspace）——与堆不相连的本地内存。-XX:MaxPermSize失去了意义，取而代之的是-XX:MaxMetaspaceSize。

Minor GC 和 Full GC的区别
新生代GC（Minor GC）：Minor GC指发生在新生代的GC
老年代GC（Full GC/Major GC）：Full GC指发生在老年代的GC，出现了Full GC一般会伴随着至少一次的Minor GC（老年代的对象大部分是Minor GC过程中从新生代进入老年代）