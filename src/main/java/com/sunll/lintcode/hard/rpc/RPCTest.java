package com.sunll.lintcode.hard.rpc;

import java.io.IOException;
import java.net.InetSocketAddress;

/**
 * <p>desc: </p>
 *
 * @author sunliangliang 2019-08-15 08:10
 * @version 1.0
 */
public class RPCTest {
    public static void main(String[] args) throws IOException {
        new Thread(new Runnable() {
            public void run() {
                try {
                    Server serviceServer = new ServerImpl(8088);
                    serviceServer.register(HelloService.class, HelloServiceImpl.class);
                    serviceServer.start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        HelloService service = Client.getRemoteProxyObj(HelloService.class, new InetSocketAddress("localhost", 8088));
        service.sqyHello("sunll");
    }
}
