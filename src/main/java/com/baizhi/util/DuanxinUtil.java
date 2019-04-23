package com.baizhi.util;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class DuanxinUtil {
    public static void sendMessage(String code,String phoneNum,HttpSession session){
        final List<HttpSession> sessions = new ArrayList<>();
        sessions.add(session);
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "LTAILNvQEPKLKslf", "jLfYSu03p16lS03EO47NmwvfDFmL6a");
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        //request.setProtocol(ProtocolType.HTTPS);
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");
        request.putQueryParameter("RegionId", "cn-hangzhou");
        request.putQueryParameter("PhoneNumbers", phoneNum);
        request.putQueryParameter("SignName", "达酷玻");
        request.putQueryParameter("TemplateCode", "SMS_163436675");
        request.putQueryParameter("TemplateParam", "{\"code\":'"+code+"'}");
        System.out.println("==------------->"+code);
        session.setAttribute("phoneCode",code);
        try {
            CommonResponse response = client.getCommonResponse(request);
            System.out.println(response.getData());
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }
    public static void sendMessage(String phoneNum, HttpSession session){
        sendMessage(getCode(),phoneNum,session);
    }
    public static String getCode(){
        String code = String.valueOf((int) (1000 + Math.random() * (9999 - 1000 + 1)));
        return code;
    }
}
