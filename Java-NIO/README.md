#NIO
##NIO概述
NIO主要有一下几个核心组成:Channel,Buffer,Selector
###Channel和Buffer
基本上,所有的IO在NIO中都从一个Channel开始.Channel有点象流,数据可以从Channel读到Buffer中,也可以从Buffer写到Channel中.

![image](https://github.com/zhangff01/Java-Summary/blob/master/Java-NIO/Channel%26Buffer.png)
###Selector
如果一个应用打开了多个Channel,向Selector注册Channel,然后调用它的select()方法,当其中有Channel准备就绪时,

线程就可以处理这些先准备就绪的事件(多路复用模式).

![image](https://github.com/zhangff01/Java-Summary/blob/master/Java-NIO/Selector%26Channel.png)
##Channel(通道)
NIO中的通道有点类似于IO中的流,但有些不同:

1.通道是双向的,既可以读取数据也可以写数据,而流是单向的.

2.通道中的数据总是要先读到一个Buffer,或者总是要从一个Buffer中写入(看上图).

3.通道可以异步地读写.

Java NIO中最重要的通道的实现:

FileChannel:从文件中读写数据.

DatagramChannel:通过UDP读写网络中的数据.

SocketChannel:通过TCP读写网络中的数据.

ServerSocketchannel:监听新进来的TCP连接,像Web服务器那样.对每一个新进来的连接都会创建一个SocketChannel.

###read()和write()方法-读写是相对Channel来说的
Channel.read():从通道里读取数据到Buffer缓存区内,对Buffer来说是write.
Channel.write():向通道里面写入数据,对Buffer来说是read.
##Buffer(缓冲区)
Buffer用于和NIO通道进行交互.数据是从通道读入缓冲区,从缓冲区写入到通道中的.

缓冲区本质上是一块可以写入数据,然后可以从中读取数据的内存.这块内存被包装成Buffer对象,并提供了一组方法,用来方便的访问该块内存.

###Buffer有三个重要的属性:capacity,limit和position.

capacity:作为一个内存块,Buffer有一个固定的大小值capacit.你只能往里写capacity个byte,long,char等类型.

一旦Buffer满了,需要将其清空(通过读数据或者清除数据)才能继续写数据往里写数据.

limit:在写模式下,Buffer的limit表示你最多能往Buffer里写多少数据.写模式下,limit等于Buffer的capacity.

当Buffer切换到读模式时,limit被设置成已写数据的数量,这个值在写模式下就是position,表示你最多能读到多少数据.

position:写数据到Buffer中时,position表示当前的位置.初始的position值为0.当一个byte,long等数据写到Buffer后,

position会向前移动到下一个可插入数据的Buffer单元.position最大可为capacity–1.

当将Buffer从写模式切换到读模式,position会被重置为0.从Buffer的position处读取数据,position向前移动到下一个可读的位置直到limit位置处。

###使用Buffer读写数据一般遵循以下四个步骤:

1.写入数据到Buffer.

2.调用flip()方法.

3.从Buffer中读取数据.

4.调用clear()或者compact()方法.

当向Buffer中写入数据时,Buffer会记录写入数据的大小,一旦要读取数据,需要通过flip()方法将Buffer从写模式切换到读模式.

一旦读完了所有的数据,就需要清空缓冲区,让它可以再次被写入.有两种方式能清空缓冲区:调用clear()或compact()方法.

clear()方法会重置了position和limit的位置.

compact()方法只会清除已经读过的数据.任何未读的数据都被移到缓冲区的起始处,新写入的数据将放到缓冲区未读数据的后面.
###Buffer的类型
有以下Buffer类型:ByteBuffer,ShortBuffer,IntBuffer,LongBuffer,CharBuffer,FloatBuffer,DoubleBuffer,MappedByteBuffer.
###创建Buffer
每一个Buffer对象都有allocate(int capacity)方法用于创建Buffer.
```java
  CharBuffer buf = CharBuffer.allocate(1024);//创建一个可存储1024个字符的CharBuffer
```
###向Buffer中写数据和从Buffer中读数据
写数据到Buffer有两种方式:1.通过Channel.read(buffer)方法写到Buffer;2.通过Buffer的put()方法写到Buffer里.

同样从Buffer读数据也有两种方式:1。通过Channel.write(buffer)方法从Buffer中读数据;2.通过Buffer的get()方法.
###flip()方法
flip方法将Buffer从写模式切换到读模式.调用flip()方法会将position设回0,并将limit设置成之前position的值.
