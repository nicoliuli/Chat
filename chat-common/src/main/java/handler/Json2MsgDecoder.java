package handler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import model.chat.ChatMsg;
import model.chat.MsgType;

/**
 * 将JSON转化为POJO
 */
public class Json2MsgDecoder extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof String) {
            String jsonMsg = (String) msg;
            JSONObject jsonObject = JSON.parseObject(jsonMsg);
            if(jsonObject.containsKey("msgType")){
                Integer msgType = jsonObject.getInteger("msgType");
                if(msgType == MsgType.MSGTYPE_CHAT){
                    ChatMsg chatMsg = JSON.parseObject(jsonMsg, ChatMsg.class);
                    ctx.fireChannelRead(chatMsg);
                }
            }

        }
    }
}