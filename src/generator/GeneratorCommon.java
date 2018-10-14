package generator;

import util.GeneratorUtil;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.LinkedHashMap;
import java.util.Map;

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

    public String rootPath;

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

    public void outFile(String packageName, String fileName, String content) throws  Exception{
        String filePath = this.rootPath + GeneratorUtil.packToFolder(propertiesMap.get(packageName));
        File folder = new File(filePath);
        if(!folder.exists() && !folder.isDirectory()) {
            folder.mkdirs();
        }
        PrintStream out = new PrintStream(filePath + "/" + fileName + ".java");
        // 记录原输出路径
        PrintStream reOut = System.out;
        if(out != null) {
            System.setOut(out);
        }
        System.out.println(content);
        // 还原System.out输出路径
        System.setOut(reOut);
    }

}
