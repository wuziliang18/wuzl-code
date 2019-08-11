package org.family.learn.netty4;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.embedded.EmbeddedChannel;

import org.family.learn.netty4.handler.FixedLengthDecoder;
import org.junit.Assert;
import org.junit.Test;

public class TestFixedLengthDecoder {
	@Test  
    public void testFramesDecoded() {  
		ByteBuf buf=Unpooled.buffer();
		for(int i=0;i<10;i++){
			buf.writeByte(i);
		}
		ByteBuf input = buf.duplicate();  
		EmbeddedChannel channel=new EmbeddedChannel(new FixedLengthDecoder(3));
		Assert.assertTrue(channel.writeInbound(input));
//		channel.finish();//加上不对
		Assert.assertEquals(buf.readBytes(3), channel.readInbound());//会报错 因为数据被完全读取的话  bytebuf会关闭 把9改成10就可以
		Assert.assertEquals(buf.readBytes(3), channel.readInbound());
		Assert.assertEquals(buf.readBytes(3), channel.readInbound());
		Assert.assertNull(channel.readInbound());
	}

}
