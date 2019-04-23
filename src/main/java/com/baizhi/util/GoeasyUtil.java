package com.baizhi.util;

import io.goeasy.GoEasy;

public class GoeasyUtil {
    public static void sendMessage(String channel,String json){
        GoEasy goEasy = new GoEasy( "http://rest-hangzhou.goeasy.io", "BC-2b66fbf505a54de1a1ca0b060dc1be20");
        //goEasy.publish("my_channel","Hello, GoEasy!");
        goEasy.publish(channel,json);
    }
    public static void sendMessage(String json){
        sendMessage("my_channel",json);
    }
}
