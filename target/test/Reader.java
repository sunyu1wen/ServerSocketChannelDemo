import main.Log;

import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
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
public class Reader extends Thread {
   /* Log LOG = new Log();
    Selector readSelector;
    boolean adding;

    Reader(int i) throws IOException {
        setName("Reader-" + i);
        this.readSelector = Selector.open();
        LOG.debug("Starting Reader-" + i + "...");
    }
    @Override
    public void run() {
        while (true){//running boolean
            try{
                readSelector.select();
                while (adding){
                    synchronized(this) {
                        this.wait(1000);
                    }
                    Iterator<SelectionKey> it = readSelector.selectedKeys().iterator();
                    while(it.hasNext()) {
                        SelectionKey key = it.next();
                        it.remove();
                        if(key.isValid()) {
                            if(key.isReadable()) {
                                doRead(key);
                            }
                        }
                    }
                }


            }catch (Exception e){

            }

        }
    }

    public void doRead(SelectionKey selectionKey) throws Exception{
        Connection c = (Connection) selectionKey.attachment();
        if(c == null) {
            return;
        }
        int n;
        try {
            n = c.readAndProcess();
        } catch (IOException e) {
            LOG.debug(e.getMessage());
            n = -1;
        } catch (Exception e) {
            LOG.debug(e.getMessage());
            n = -1;
        }
        if(n == -1) {
            c.close();
        }
    }

    public SelectionKey registerChannel(SocketChannel channel) throws IOException {
        return channel.register(readSelector, SelectionKey.OP_READ);
    }

    public void startAdd() {
        adding = true;
        readSelector.wakeup();
    }

    public synchronized void finishAdd() {
        adding = false;
        this.notify();
    }
}
*/
}
