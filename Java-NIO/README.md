#NIO
##NIO概述
NIO主要有一下几个核心组成:Channel,Buffer,Selector
###Channel和Buffer
基本上,所有的IO在NIO中都从一个Channel开始.Channel有点象流,数据可以从Channel读到Buffer中,也可以从Buffer写到Channel中.
###Selector
如果一个应用打开了多个Channel,向Selector注册Channel,然后调用它的select()方法,当其中的Channel准备就绪时,

线程就可以处理这些事件(多路复用模式).
