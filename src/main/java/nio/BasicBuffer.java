package nio;

import java.nio.IntBuffer;

public class BasicBuffer {
    public static void main(String[] args) {

        // 举例说明 Buffer 的使用
        // 创建一个 Buffer,大小为5，既可以存放5个int
        IntBuffer intBuffer = IntBuffer.allocate(5);

        // 向 Buffer 中存放数据
//        intBuffer.put(10);
//        intBuffer.put(11);
//        intBuffer.put(12);
//        intBuffer.put(13);
//        intBuffer.put(14);
        for (int i = 0; i < intBuffer.capacity(); i++) {
            intBuffer.put( i * 2);
        }

        // 从 Buffer 中读取数据
        // 将 Buffer 转换，读写切换(！！！)
        intBuffer.flip();

        while (intBuffer.hasRemaining()) {
            System.out.println(intBuffer.get());
        }
    }
}
