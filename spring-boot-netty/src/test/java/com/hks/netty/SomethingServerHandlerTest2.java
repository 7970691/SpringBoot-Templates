package com.hks.netty;

import com.hks.netty.server.handler.SomethingServerHandler;
import io.netty.channel.embedded.EmbeddedChannel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.InetSocketAddress;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author heks
 * @since
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SomethingServerHandlerTest2 {
    @Autowired
    private SomethingServerHandler somethingServerHandler;

    @Test
    public void channelRead() {
        EmbeddedChannel channel = new EmbeddedChannel(somethingServerHandler);
        channel.connect(new InetSocketAddress("127.0.0.1", 10) );

        String res1 = channel.readOutbound();
        assertThat(res1, is("Your channel key is embedded\r\n"));
        
        channel.writeInbound("embedded::test");

        String res2 = channel.readOutbound();
        assertThat(res2, is("test\n\r"));
    }
}