package util;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GeneratorUtil {
    /**
     * 将数据库名称转换成驼峰命名
     * @param DBName
     * @return
     */
    public static String DBNameToJavaName(String DBName) {
        StringBuilder javaName = new StringBuilder();
        String[] nameList = DBName.split("_");
        for (String name : nameList) {
            javaName.append(name.substring(0,1).toUpperCase()).append(name.substring(1).toLowerCase());
        }
        return javaName.toString();
    }

    /**
     * 将数据库类型转换成java类型
     * @param DBType
     * @return
     */
    public static String  DBTypeToJavaType(String DBType) {
        String javaType = "";
        switch (DBType) {
            case "VARCHAR2" : javaType = "String"; break;
            case "VARCHAR" : javaType = "String"; break;
            case "NUMBER" : javaType = "Long"; break;
            case "DATE" : javaType = "Date"; break;
            case "NUMBER_" : javaType = "BigDecimal"; break;
            default:javaType = "String";break;
        }
        return javaType;
    }

    /**
     * 将第一个字母变成小写
     * @param javaName
     * @return
     */
    public static String lower1(String javaName) {
        return javaName.substring(0,1).toLowerCase() + javaName.substring(1);
    }

    /**
     * 包路径转换成文件路径
     * @param packStr
     * @return
     */
    public static String packToFolder(String packStr) {
        String folderPath = "";
        if(packStr != null && !"".equals(packStr)) {
            folderPath = packStr.replaceAll("\\.", "/");
        }
        return folderPath;
    }

    /**
     * 获取Map中第一个key
     * @return
     */
    public static String getFirstKey(Map<String,String> map) {
        String key="";
        for (Map.Entry<String, String> entry : map.entrySet()) {
            key = entry.getKey();
            if (key != null) {
                break;
            }
        }
        return key;
    }
}
