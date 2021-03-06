package com.ab.crawl.util;
/**
 * 实现对Java配置文件Properties的读取、写入与更新操作
 */
import java.io.*;
import java.util.Properties;
import java.util.Set;


/**
 * @author
 * @version
 */
public class SetSystemProperty {
    //属性文件的路径
    private static String absolutePath = new File("./").getAbsolutePath();
    static String profilepath="\\crawl.properties";
    /**
     * 采用静态方法
     */
    private static Properties props = new Properties();
    static {

        try {
            System.out.println(absolutePath+profilepath);

            props.load(new FileInputStream(absolutePath+profilepath));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.exit(-1);
        } catch (IOException e) {
            System.exit(-1);
        }
    }

    /**
     * 读取属性文件中相应键的值
     * @param key
     *            主键
     * @return String
     */
    public static String getKeyValue(String key) {
        return props.getProperty(key);
    }

    /**
     * 根据主键key读取主键的值value
     * @param filePath 属性文件路径
     * @param key 键名
     */
    public static String readValue(String filePath, String key) {
        Properties props = new Properties();
        try {
            InputStream in = new BufferedInputStream(new FileInputStream(
                    filePath));
            props.load(in);
            String value = props.getProperty(key);
            System.out.println(key +"键的值是："+ value);
            return value;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 更新（或插入）一对properties信息(主键及其键值)
     * 如果该主键已经存在，更新该主键的值；
     * 如果该主键不存在，则插件一对键值。
     * @param keyname 键名
     * @param keyvalue 键值
     */
    public static void writeProperties(String keyname,String keyvalue) {
        try {
            // 调用 Hashtable 的方法 put，使用 getProperty 方法提供并行性。
            // 强制要求为属性的键和值使用字符串。返回值是 Hashtable 调用 put 的结果。
            OutputStream fos = new FileOutputStream(profilepath);
            props.setProperty(keyname, keyvalue);
            // 以适合使用 load 方法加载到 Properties 表中的格式，
            // 将此 Properties 表中的属性列表（键和元素对）写入输出流
            props.store(fos, "Update '" + keyname + "' value");
        } catch (IOException e) {
            System.err.println("属性文件更新错误");
        }
    }

    /**
     * 更新properties文件的键值对
     * 如果该主键已经存在，更新该主键的值；
     * 如果该主键不存在，则插件一对键值。
     * @param keyname 键名
     * @param keyvalue 键值
     */
    public static void updateProperty(String keyname,String keyvalue) {

        try {
            BufferedInputStream buf = new BufferedInputStream(SetSystemProperty.class.getResourceAsStream(absolutePath+profilepath));
            props.load(new FileInputStream(profilepath));
            // 调用 Hashtable 的方法 put，使用 getProperty 方法提供并行性。
            // 强制要求为属性的键和值使用字符串。返回值是 Hashtable 调用 put 的结果。
            OutputStream fos = new FileOutputStream(profilepath);
            props.setProperty(keyname, keyvalue);
            // 以适合使用 load 方法加载到 Properties 表中的格式，
            // 将此 Properties 表中的属性列表（键和元素对）写入输出流
            props.store(fos, "Update '" + keyname + "' value");
        } catch (IOException e) {
            System.err.println("属性文件更新错误");
        }
    }


    public static void updateProperties(String keyname,String keyvalue) {

        try {
            BufferedInputStream buf = new BufferedInputStream(SetSystemProperty.class.getResourceAsStream(absolutePath+profilepath));
            props.load(new FileInputStream(absolutePath+profilepath));
           OutputStream fos = new FileOutputStream(absolutePath+profilepath);
            BufferedOutputStream by = new BufferedOutputStream(fos);
            props.setProperty(keyname, keyvalue);
            props.store(by, "Update '" + keyname + "' value");
            buf.close();
        } catch (IOException e) {
            System.out.println(e);
            System.err.println("属性文件更新错误");
        }
    }



    //测试代码
    public static void main(String[] args) {
        String rootUrl =  PropertiesConfig.INSTANCE.getProPerties("dir");
        System.out.println(getKeyValue("stopTyphoon"));
        SetSystemProperty.updateProperties("stopTyphoon","999");

    }


}
