package main;

import java.net.InetSocketAddress;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;

/**
 * @author Bocai
 * @version V1.0
 * @ClassName: ${file_name}
 * @Description: ${todo}(用一句话描述该文件做什么)
 * @Date ${date} ${time}
 */
public class Client {
    public static void main(String[] args) throws Exception {
//        String url = "ifeve.com";
//        String url = "www.baidu.com";
//        String url = "blog.csdn.net";
        String url2 = "github.com";
        String url = "localhost";
        Log log = new Log();

        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress(url, 8066));
       log.debug("conn..............");

        while (true) {
            log.debug("whild.................");
            if (socketChannel.isConnected()) {
                log.debug("pending.....................");
                //发送http
                StringBuilder sendStr = new StringBuilder();
                sendStr.append("GET / HTTP/1.1\r\n");
                sendStr.append("Host: " + url + "\r\n");
                //gzip压缩
                //sendStr.append("Accept-Encoding: gzip, deflate, br\r\n");
                //http协议，需要空一行
                sendStr.append("\r\n");
                sendStr.append("test content BoCai");

                ByteBuffer buf = ByteBuffer.wrap(sendStr.toString().getBytes("UTF-8"));
                int bytesWrite = socketChannel.write(buf);
                log.debug(bytesWrite);
                buf.clear();
                Integer length  = socketChannel.write(buf);
                String content = "";
                for(Integer i =0;i<length;i++ ){
                    content+=(char)buf.get(i);
                }
                log.debug(content);

                int read = 0;
                ByteBuffer buffer = ByteBuffer.allocate(1024);
                while ((read = socketChannel.read(buffer)) != -1) {
                    {
                        log.debug("read " + read + ".....................");
                        buffer.flip();
                        while (buffer.position() < buffer.limit()) {
                            //不能解析中文
                            //System.out.print((char) buffer.get());
                            //解析中文，不知道是否有个别乱码，缓存刚好截断utf8中文（三个字节）？
                            log.debug(Charset.forName("UTF-8").newDecoder().decode(buffer).toString());
                        }
                        //#todo 需要计算body长度，缓存没满不一定读完数据,html文档没读完
                        if (buffer.limit() < buffer.capacity()) {
                            break;
                        }
                        buffer.clear();
                    }
                }
            }
            socketChannel.close();
            break;
        }
    }
}
