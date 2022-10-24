java  内存操作








## JAVA直接内存
### 直接内存概述
    不是虚拟机运行时数据区的一部分，也不是《Java虚拟机规范》
    中定义的内存区域。直接内存是在Java堆外的、直接向系统申请的内存区间。

    来源于NIO，通过存在堆中的DirectByteBuffer操作Native内存。
    通常，访问直接内存的速度会优于Java堆，即读写性能高。
    因此出于性能考虑，读写频繁的场合可能会考虑使用直接内存。
    Java的NIO库允许Java程序使用直接内存，用于数据缓冲区

### 非直接缓存区
    使用IO读写文件，需要与磁盘交互，需要由用户态切换到内核态。在内核态时，需要两份内存存储重复数据，效率低。



### 直接缓存区
    使用NIO时，操作系统划出的直接缓存区可以被java代码直接访问，只有一份。NIO适合对大文件的读写操作。
    也可能导致OutOfMemoryError异常

    Exception in thread "main" java.lang.OutOfMemoryError: Direct buffer memory 
    at java.nio.Bits.reserveMemory(Bits.java:693)
    at java.nio.DirectByteBuffer.<init>(DirectByteBuffer.java:123)
    at java.nio.ByteBuffer.allocateDirect(ByteBuffer.java:311)
    at com.atguigu.java.BufferTest2.main(BufferTest2.java:20)













