package generator;

import util.GeneratorUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.List;
import java.util.Map;

/**
 * 生成实体类文件(Entity.java, AbstraEntity.java)
 */
public class GeneratorEntity extends GeneratorCommon{

    public GeneratorEntity(Map<String, String> propertiesMap, Map<String, String> fieldMap, String tableName) {
        this.fieldMap = fieldMap;
        this.propertiesMap = propertiesMap;
        this.tableName = tableName;
        initIO();
    }

    /**
     * 生成实体类文件(Entity.java, AbstraEntity.java)
     */
    public void generator() {
        try {
            // 判断是否需要生成AbstractEntity文件common_entity
            if (propertiesMap.get(common_entity) != null && !propertiesMap.get(common_entity).equals("")) {
                // 需要生成AbstractEntity文件
                generatorWithAbstrat();
            } else {
                // 不需要生成AbstractEntity文件
                generatorWithNotAbstrat();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 需要生成AbstractEntity文件
     */
    private void generatorWithAbstrat() {

    }

    /**
     * 不需要生成AbstractEntity文件
     */
    private void generatorWithNotAbstrat() throws Exception {
        // 文件内容
        StringBuilder content = new StringBuilder("package " + propertiesMap.get(package_entity) + ";\r\n\r\n");
        // 生成import部分
        content.append("import java.math.BigDecimal;\r\nimport java.util.Date;\r\n\r\n");
        // 生成类和属性部分
        content.append("public class "+tableName+" {\r\n\r\n");
        for(String fieldName : fieldMap.keySet()) {
            //     private Long sid;
            content.append("    private ").append(fieldMap.get(fieldName)).append(" ").append(GeneratorUtil.lower1(fieldName)).append(";\r\n\r\n");
        }
        // 生成set、get部分
        for(String fieldName : fieldMap.keySet()) {
             /*    public Long getSid() {
                        return sid;
                    }

                    public void setSid(Long sid) {
                        this.sid = sid;
                    }
              */
            content.append("    public ").append(fieldMap.get(fieldName)).append(" ").append("get").append(fieldName).append("() {\r\n");
            content.append("        return ").append(GeneratorUtil.lower1(fieldName)).append(";\r\n");
            content.append("    }\r\n\r\n");

            content.append("    public void set").append(fieldName).append("(").append(fieldMap.get(fieldName)).append(" ").append(GeneratorUtil.lower1(fieldName)).append(") {\r\n");
            content.append("        this.").append(GeneratorUtil.lower1(fieldName)).append(" = ").append(GeneratorUtil.lower1(fieldName)).append(";\r\n");
            content.append("    }\r\n\r\n");
        }
        content.append("}");
        // 生成文件
        String fileName = tableName;
        System.out.println("生成文件：" + this.rootPath + GeneratorUtil.packToFolder(propertiesMap.get(package_entity)) + "/" + fileName + ".java...................");
        outFile(package_entity, fileName, content.toString());
    }

}
