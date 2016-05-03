package utils;

import java.util.UUID;

/**
 * Created by RZ on 4/20/16.
 */
public class FileUploadUtils {

    public static String getRealname(String name) {
        int index = name.lastIndexOf("/");
        return name.substring(index+1);
    }

    public static String getUUIDName(String realName) {
        int index = realName.lastIndexOf(".");
        if(index!=-1) {
            return UUID.randomUUID()+realName.substring(index);
        }else {
            return UUID.randomUUID().toString();
        }
    }

    public static String getRandomDirs(String uuidName){
        int hashCode = uuidName.hashCode();
        int a = hashCode & 0xf;
        int b = (hashCode>>4) & 0xf;
        return "/"+a+"/"+b;
    }
}
