package main;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * @author Bocai
 * java 纯nio使用 serverSocketChannel与socketChannel 最简单的例子，没有使用select，多线程
 * @version V1.0
 * @ClassName: ${file_name}
 * @Description: ${todo}(serverSocketChannel 是服务器端，监听端口，等待链接)
 * @Date ${date} ${time}
 */
public class Server {

    public static  void  main(String[] args) throws IOException {
        //serverSocketChannel 是服务器端，监听端口，等待链接
        Log log = new Log();
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.socket().bind(new InetSocketAddress(8066));
        while (true){
            SocketChannel socketChannel = serverSocketChannel.accept();
            System.out.println(socketChannel);
            System.out.println("conn.........."+socketChannel.getRemoteAddress().toString());
            ByteBuffer buf  = ByteBuffer.allocate(128);
            buf.clear();
            int len = socketChannel.read(buf);
            log.debug(buf.toString());
            log.debug(String.valueOf(len));
            int count = 0;
            String content = "";
            while (len>0){
                count += len;
                buf.flip();
                while (buf.position()< buf.limit()){
                    content += (char) buf.get();
                }
                log.debug("接收到的内容："+content);
                if(buf.limit()<buf.capacity())
                {
                    break;
                }
                buf.clear();
                len = socketChannel.read(buf);
            }
            log.debug("has buf "+count+" ..............");
            buf.clear();
            log.debug("----output-开始组装出参--");
            StringBuilder sendStr = new StringBuilder();
            sendStr.append("Http/1.1 200 Ok\r\n");
            sendStr.append("Content-Type:text/html;charset=UTF-8\r\n");
            sendStr.append("\r\n");
            sendStr.append("<html><head><title>显示报文</title></head><body>hi,world"+ content+"</body></html>");
            buf = ByteBuffer.wrap(sendStr.toString().getBytes("UTF-8"));
            socketChannel.write(buf);
            socketChannel.close();
        }
    }
}
