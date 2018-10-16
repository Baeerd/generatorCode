package generator;

import util.GeneratorUtil;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.ResourceBundle;

public abstract class GeneratorCommon {

    /**
     * config.properties 中的属性key
     */
    public String jdbcName = "jdbcName";
    public String jdbcUrl = "jdbcUrl";
    public String jdbcUser = "jdbcUser";
    public String jdbcPassword = "jdbcPassword";

    // 公共方法名称
    public String find = "find";
    public String insert = "insert";
    public String insertSeq = "insertSeq";
    public String update = "update";
    public String delete = "delete";

    // 表数据及包数据
    public String tableName = "tableName";
    public String package_controller = "package_controller";
    public String package_service = "package_service";
    public String package_mapper = "package_mapper";
    public String package_entity = "package_entity";

    // 公共继承类名
    public String common_entity = "common_entity";
    public String entityField = "entityField";
    public String common_Mapper = "common_Mapper";
    public String common_service = "common_service";
    public String common_serviceImpl = "common_serviceImpl";
    public String common_controller = "common_controller";

    // mapper文件头
    public String mapperTop = "mapperTop";
    public String updateVersion = "updateVersion";

    /**
     *  config.properties文件中的信息
     */
    public Map<String, String> propertiesMap = new LinkedHashMap<>();

    /**
     * 数据集合
     * key ： 实体类属性名称
     * value ： 实体类属性类型
     */
    public Map<String, String> fieldMap = new LinkedHashMap<>();

    /**
     * 数据库名称集合
     */
    public Map<String, String> dbFieldMap = new LinkedHashMap<>();

    public String rootPath;

    // 实体类名
    public String entityClass;

    // 实体类名（首字母小写）
    public String entityName;

    // 公共类包名
    public String commonPackage;

    // 公共类类名
    public String commonClassName;

    /**
     * 初始化创建文件IO流方法
     */
    public void initIO() {
        File directory = new File("");// 参数为空
        try {
            rootPath = directory.getCanonicalPath() + "/";
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 初始化配置文件数据
     */
    public void initProperties() {
        // 创建对象时获取配置文件信息
        ResourceBundle resource = ResourceBundle.getBundle("generator/config");
        //获取key对应的value值
        propertiesMap.put(jdbcName, resource.getString(jdbcName));
        propertiesMap.put(jdbcUrl, resource.getString(jdbcUrl));
        propertiesMap.put(jdbcUser, resource.getString(jdbcUser));
        propertiesMap.put(jdbcPassword, resource.getString(jdbcPassword));

        // 公共方法名称
        propertiesMap.put(find, resource.getString(find));
        propertiesMap.put(insert, resource.getString(insert));
        propertiesMap.put(insertSeq, resource.getString(insertSeq));
        propertiesMap.put(update, resource.getString(update));
        propertiesMap.put(delete, resource.getString(delete));

        // 表数据及包数据
        propertiesMap.put(tableName, resource.getString(tableName));
        propertiesMap.put(package_controller, resource.getString(package_controller));
        propertiesMap.put(package_service, resource.getString(package_service));
        propertiesMap.put(package_mapper, resource.getString(package_mapper));
        propertiesMap.put(package_entity, resource.getString(package_entity));

        // 公共继承类名
        propertiesMap.put(common_entity, resource.getString(common_entity));
        propertiesMap.put(entityField, resource.getString(entityField));
        propertiesMap.put(common_Mapper, resource.getString(common_Mapper));
        propertiesMap.put(common_service, resource.getString(common_service));
        propertiesMap.put(common_serviceImpl, resource.getString(common_serviceImpl));
        propertiesMap.put(common_controller, resource.getString(common_controller));

        // mapper文件头
        propertiesMap.put(mapperTop, resource.getString(mapperTop));
        propertiesMap.put(updateVersion, resource.getString(updateVersion));
        System.out.println("初始化配置信息....." + propertiesMap);
    }

    public void outFile(String packageName, String fileName, String content) throws  Exception{
        String filePath = "";
        if(fileName.contains("Impl.java")) {
            filePath = this.rootPath + GeneratorUtil.packToFolder(propertiesMap.get(packageName)+".impl");
        } else if(packageName.contains("common")) {
            filePath = this.rootPath + GeneratorUtil.packToFolder(commonPackage);
        }else {
            filePath = this.rootPath + GeneratorUtil.packToFolder(propertiesMap.get(packageName));
        }
        File folder = new File(filePath);
        if(!folder.exists() && !folder.isDirectory()) {
            folder.mkdirs();
        }
        PrintStream out = new PrintStream(filePath + "/" + fileName);
        // 记录原输出路径
        PrintStream reOut = System.out;
        if(out != null) {
            System.setOut(out);
        }
        System.out.println(content);
        // 还原System.out输出路径
        System.setOut(reOut);
    }

    /**
     * 生成文件方法
     * @param commonFlag
     */
    public void generator(String commonFlag) {
        try {
            // 判断是否需要生成Abstract文件
            if (propertiesMap.get(commonFlag) != null && !propertiesMap.get(commonFlag).equals("")) {
                // 初始化公共类包名及类名
                String commonConfig = propertiesMap.get(commonFlag);
                commonPackage = commonConfig.substring(0, commonConfig.lastIndexOf("."));
                commonClassName = commonConfig.substring(commonConfig.lastIndexOf(".")+1);
                // 需要生成Abstract文件
                generatorWithAbstrat();
                // 生成公共类文件
                generatorCommon();
            } else {
                // 不需要生成Abstract文件
                generatorWithNotAbstrat();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 生成公共类文件
     * @throws Exception
     */
    protected abstract void generatorCommon() throws Exception;

    /**
     * 需要生成Abstract文件
     * @throws Exception
     */
    protected abstract void generatorWithAbstrat() throws Exception;

    /**
     * 不需要生成Abstract文件
     * @throws Exception
     */
    protected abstract void generatorWithNotAbstrat() throws Exception;

}
