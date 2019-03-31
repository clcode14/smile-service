package org.flightythought.smile.appserver.common.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * .
 */
public class PhoneUtil {

    public static boolean isMobileNO(String mobiles) {
        String chinaMobile = "^134[0-8]\\d{7}$|^(?:13[5-9]|147|15[0-27-9]|178|18[2-478])\\d{8}$"; //Add 178 移动方面最新答复
        Pattern isChinaMobile = Pattern.compile(chinaMobile);
        Matcher matcher1 = isChinaMobile.matcher(mobiles);

        if(matcher1.matches()) {
            return true;
        }

        String chinaUnion = "^(?:13[0-2]|145|15[56]|17[56]|18[56])\\d{8}$";//联通
        Pattern isChinaUnion = Pattern.compile(chinaUnion);
        Matcher matcher2 = isChinaUnion.matcher(mobiles);

        if(matcher2.matches()) {
            return true;
        }

        String chinaTelcom = "^(?:133|153|173|177|18[019])\\d{8}$"; //^1349\d{7}$ 1349号段 电信方面没给出答复，视作不存在
        Pattern isChinaTelcom = Pattern.compile(chinaTelcom);
        Matcher matcher3 = isChinaTelcom.matcher(mobiles);

        if(matcher3.matches()) {
            return true;
        }

        String otherTelphone = "^(?:170|171)\\d{8}$";//其他
        Pattern isOtherTelphone = Pattern.compile(otherTelphone);
        Matcher matcher4 = isOtherTelphone.matcher(mobiles);

        if(matcher4.matches()) {
            return true;
        }

        return false;
    }

    public static void main(String[] args) {
        System.out.println(isMobileNO("17506529249"));
    }

}
