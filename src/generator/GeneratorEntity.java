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

    public GeneratorEntity(Map<String, String> propertiesMap, Map<String, String> fieldMap, Map<String, String> dbFieldMap) {
        this.propertiesMap = propertiesMap;
        this.fieldMap = fieldMap;
        this.dbFieldMap = dbFieldMap;
        initIO();
        entityClass = GeneratorUtil.DBNameToJavaName(propertiesMap.get(tableName));
        entityName = GeneratorUtil.lower1(entityClass);
    }

    /**
     * 需要生成AbstractEntity文件
     */
    @Override
    public void generatorWithAbstrat() {

    }

    /**
     * 不需要生成AbstractEntity文件
     */
    @Override
    public void generatorWithNotAbstrat() throws Exception {
        // 文件内容
        StringBuilder content = new StringBuilder("package " + propertiesMap.get(package_entity) + ";\r\n\r\n");
        // 生成import部分
        content.append("import java.math.BigDecimal;\r\nimport java.util.Date;\r\n\r\n");
        // 生成类和属性部分
        content.append("public class "+entityClass+" {\r\n\r\n");
        for(String dbFieldName : dbFieldMap.keySet()) {
            //     private Long sid;
            content.append("    private ").append(fieldMap.get(dbFieldName)).append(" ").append(GeneratorUtil.lower1(GeneratorUtil.DBNameToJavaName(dbFieldName))).append(";\r\n\r\n");
        }
        // 生成set、get部分
        for(String dbFieldName : dbFieldMap.keySet()) {
             /*    public Long getSid() {
                        return sid;
                    }

                    public void setSid(Long sid) {
                        this.sid = sid;
                    }
              */
            content.append("    public ").append(fieldMap.get(dbFieldName)).append(" ").append("get").append(GeneratorUtil.DBNameToJavaName(dbFieldName)).append("() {\r\n");
            content.append("        return ").append(GeneratorUtil.lower1(GeneratorUtil.DBNameToJavaName(dbFieldName))).append(";\r\n");
            content.append("    }\r\n\r\n");

            content.append("    public void set").append(GeneratorUtil.DBNameToJavaName(dbFieldName)).append("(").append(fieldMap.get(dbFieldName)).append(" ").append(GeneratorUtil.lower1(GeneratorUtil.DBNameToJavaName(dbFieldName))).append(") {\r\n");
            content.append("        this.").append(GeneratorUtil.lower1(GeneratorUtil.DBNameToJavaName(dbFieldName))).append(" = ").append(GeneratorUtil.lower1(GeneratorUtil.DBNameToJavaName(dbFieldName))).append(";\r\n");
            content.append("    }\r\n\r\n");
        }
        content.append("}");
        // 生成文件
        String fileName = entityClass+".java";
        System.out.println("生成文件：" + this.rootPath + GeneratorUtil.packToFolder(propertiesMap.get(package_entity)) + "/" + fileName + "...................");
        outFile(package_entity, fileName, content.toString());
    }

}
