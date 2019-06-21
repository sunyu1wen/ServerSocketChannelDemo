import java.io.IOException;
import java.io.Reader;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import  java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.sql.Connection;
import java.util.Iterator;

/**
 * @author Bocai
 * @version V1.0
 * @ClassName: ${file_name}
 * @Description: ${todo}(用一句话描述该文件做什么)
 * @Date ${date} ${time}
 */
public class Listener extends Thread {
  /*  Selector selector;
    Reader[] readers;
    int robin;
    int readNum ;

    volatile boolean running = true;

    Listener(int port)throws IOException {
        ServerSocketChannel serverChannel = ServerSocketChannel.open();
        serverChannel.configureBlocking(false);
        serverChannel.socket().bind(new InetSocketAddress(port), 150);
        selector = Selector.open();
        serverChannel.register(selector, SelectionKey.OP_ACCEPT);
        readNum = 10;
        readers = new Reader[readNum];
        for(int i = 0; i < readNum; i++) {
            readers[i] = new Reader(i);
            readers[i].start();
        }
    }

    public void  run(){
        while (running){
            try {
                selector.select();
                Iterator<SelectionKey> it = selector.selectedKeys().iterator();
                while(it.hasNext()) {
                    SelectionKey key = it.next();
                    it.remove();
                    if(key.isValid()) {
                        if(key.isAcceptable()) {
                            doAccept(key);
                        }
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
    public void doAccept(SelectionKey selectionKey) throws IOException {
        ServerSocketChannel serverSocketChannel = (ServerSocketChannel) selectionKey.channel();
        SocketChannel socketChannel;
        while((socketChannel = serverSocketChannel.accept()) != null ){
            socketChannel.configureBlocking(false);
            socketChannel.socket().setTcpNoDelay(true);
            socketChannel.socket().setKeepAlive(true);
        }

        Reader reader = getReader();
        try{
            reader.startAdd();
            SelectionKey readKey = reader.registerChannel(socketChannel);
            Connection c = new Connection(socketChannel);
            readKey.attach(c);

        }catch (Exception e){

        }finally {
            reader.finishAdd();
        }




    }
    public Reader getReader() {
        if(robin == Integer.MAX_VALUE) {
            robin = 0;
        }
        return readers[(robin ++) % readNum];
    }*/
}
