#NIO入门
##为什么要使用NIO?
NIO的创建目的是为了让Java程序员可以实现高速I/O而无需编写自定义的本机代码.

NIO将最耗时的I/O操作(即填充和提取缓冲区)转移回操作系统,因而可以极大地提高速度.
##通道(Channel)和缓冲区(Buffer)
通道和缓冲区对象是NIO的核心对象,几乎每一个I/O操作都要用到它们.

通道是对原I/O中流的模拟,到任何目的地(或来自任何地方)的所有数据都必须通过一个Channel对象.

一个Buffer对象实际上是一个容器对象,发送给一个通道的所有数据都必须首先放到缓冲区中;同样从通道读取的任何数据都要读到缓冲区中.

###什么是缓冲区？

Buffer是一个对象,它包含一些要写入或者刚读取的数据.在NIO中,所有的数据都是用缓冲哪个区处理的.在读取数据时,它是直接读取到缓冲区中的;

在写入数据时,它是写入到缓冲区中的.任何时候访问NIO中的数据,都是将数据放入到缓冲区中的.
###缓冲区类型
最常用的缓冲区类型是ByteBuffer.一个ByteBuffer可以在其底层字节数组上进行get/set操作(字节的获取和设置).

在NIO中,每一种基本的Java类型(除了boolean布尔类型)都有一种缓冲区类型:

ByteBuffer,ShortBuffer,IntBuffer,LongBuffer,FloatBuffer,DoubleBuffer,CharBuffer.

每一个Buffer类都是Buffer接口的一个实例
