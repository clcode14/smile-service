package org.flightythought.smile.appserver.common.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;

public class SmsUtils {
    /**
     * 发送验证码
     *
     * @param phone
     * @param code
     */
    public static void sendVerifyCode(String phone, String code) {
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "LTAIHNajEEHzHjpn", "2QBXYZuj5w2mkILficTO8B1dLgIM0t");
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");
        request.putQueryParameter("RegionId", "cn-hangzhou");
        request.putQueryParameter("PhoneNumbers", phone);
        request.putQueryParameter("SignName", "笑美健康");
        request.putQueryParameter("TemplateCode", "SMS_162880071");
        request.putQueryParameter("TemplateParam", "{\"code\":\"" + code + "\"}");
        try {
            CommonResponse response = client.getCommonResponse(request);
            JSONObject parse = (JSONObject) JSON.parse(response.getData());
            if (parse.get("Code").equals("OK")) {
                return;
            }

            throw new RuntimeException(response.getData());

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
//
//    public static void main(String[] args) {
//        sendVerifyCode("18550133770", "2356");
//    }
}
