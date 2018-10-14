package generator;

import util.GeneratorUtil;

import java.util.Map;

/**
 * 生成mapper层文件(EntityMapper.java, EntityMapper.xml, AbstratEntityMapper.java)
 */
public class GeneratorMapper extends GeneratorCommon{

    public GeneratorMapper(Map<String, String> propertiesMap, Map<String, String> fieldMap, Map<String, String> dbFieldMap) {
        this.propertiesMap = propertiesMap;
        this.fieldMap = fieldMap;
        this.dbFieldMap = dbFieldMap;
        initIO();
    }

    /**
     * 生成mapper层文件(EntityMapper.java, EntityMapper.xml, AbstratEntityMapper.java)
     */
    public void generator() {
        try {
            // 判断是否需要生成Abstract文件common_Mapper
            if (propertiesMap.get(common_Mapper) != null && !propertiesMap.get(common_Mapper).equals("")) {
                // 需要生成Abstract文件
                generatorWithAbstrat();
            } else {
                // 不需要生成Abstract文件
                generatorWithNotAbstrat();
            }
            // 生成.xml文件
            generatorMapperXml();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 需要生成Abstract文件
     */
    private void generatorWithAbstrat() {

    }

    /**
     * 不需要生成Abstract文件
     */
    private void generatorWithNotAbstrat() throws Exception {
        // 文件内容
        StringBuilder content = new StringBuilder("package " + propertiesMap.get(package_mapper) + ";\r\n\r\n");
        // 生成import部分
        content.append("import ").append(propertiesMap.get(package_entity)).append(".").append(tableName).append(";\r\n\r\n");
        content.append("import java.util.List;\r\nimport java.util.Map;\r\n\r\n");

        // 生成类和方法部分
        content.append("@Repository\r\n");
        content.append("public interface "+GeneratorUtil.DBNameToJavaName(propertiesMap.get(tableName))+"Mapper {\r\n\r\n");

        // 添加方法
        content.append("    public void ").append(propertiesMap.get(insert)).append("(").append(GeneratorUtil.DBNameToJavaName(propertiesMap.get(tableName)))
                .append(" ").append(GeneratorUtil.lower1(tableName)).append(");\r\n\r\n");

        // 修改方法
        content.append("    public void ").append(propertiesMap.get(update)).append("(").append(GeneratorUtil.DBNameToJavaName(propertiesMap.get(tableName)))
                .append(" ").append(GeneratorUtil.lower1(tableName)).append(");\r\n\r\n");

        // 删除方法
        content.append("    public void ").append(propertiesMap.get(delete)).append("(").append(fieldMap.get(GeneratorUtil.getFirstKey(fieldMap)))
                .append(" ").append(GeneratorUtil.lower1(GeneratorUtil.getFirstKey(fieldMap))).append(");\r\n\r\n");

        // 查询方法
        content.append("    public List<").append(GeneratorUtil.DBNameToJavaName(propertiesMap.get(tableName))).append("> ").append(propertiesMap.get(find))
                .append("(Map<String, Object> params);\r\n}");

        // 生成文件
        String fileName = GeneratorUtil.DBNameToJavaName(propertiesMap.get(tableName))+"Mapper.java";
        System.out.println("生成文件：" + this.rootPath + GeneratorUtil.packToFolder(propertiesMap.get(package_mapper)) + "/" + fileName + "...................");
        outFile(package_mapper, fileName, content.toString());
    }

    /**
     * 生成.xml文件
     */
    private void generatorMapperXml() throws Exception {
        // Mapper文件xml 表头
        StringBuilder content = new StringBuilder(propertiesMap.get(mapperTop));
        content.append("<mapper namespace=\"").append(propertiesMap.get(package_mapper)).append(".").append(GeneratorUtil.DBNameToJavaName(propertiesMap.get(tableName))).append("Mapper\" >\r\n");

        // resultMap
        content.append("  <resultMap id=\"BaseResultMap\" type=\"").append(propertiesMap.get(package_entity)).append(".").append(GeneratorUtil.DBNameToJavaName(propertiesMap.get(tableName))).append("\" >\r\n");
        int i = 1;
        String id = "";
        String idType = "";
        for(String dbFieldName : dbFieldMap.keySet()) {
            if(i == 1) {
                id = GeneratorUtil.lower1(GeneratorUtil.DBNameToJavaName(dbFieldName));
                idType = fieldMap.get(dbFieldName);
                content.append("    <id column=\""+dbFieldName+"\" property=\""+GeneratorUtil.lower1(GeneratorUtil.DBNameToJavaName(dbFieldName))+"\" jdbcType=\""+dbFieldMap.get(dbFieldName)+"\" />\r\n");
                i++;
                continue;
            }
            content.append("    <result column=\""+dbFieldName+"\" property=\""+GeneratorUtil.lower1(GeneratorUtil.DBNameToJavaName(dbFieldName))+"\" jdbcType=\""+dbFieldMap.get(dbFieldName)+"\" />\r\n");
        }
        content.append("  </resultMap>\r\n\r\n\r\n");

        // Base_Column_List
        content.append("  <sql id=\"Base_Column_List\" >\r\n");
        int j = 1;
        for(String dbFieldName : dbFieldMap.keySet()) {
            if((j+7) % 8 == 0) {
                content.append("    ");
            }
            content.append(dbFieldName);
            if(j < dbFieldMap.size()) {
                content.append(",");
            }
            if(j%8 == 0 || j == dbFieldMap.size()) {
                content.append("\r\n");
            }
            j++;
        }
        content.append("  </sql>\r\n\r\n\r\n");

        // insert
        content.append("  <insert id=\""+propertiesMap.get(insert)+"\" parameterType=\""+propertiesMap.get(package_entity)+"."+GeneratorUtil.DBNameToJavaName(propertiesMap.get(tableName))+"\" >\r\n");
        if(propertiesMap.get(insertSeq) != null && !"".equals(propertiesMap.get(insertSeq))) {
            content.append("  \t<selectKey keyProperty=\""+id+"\" order=\"BEFORE\" resultType=\"java.lang."+idType+"\">\r\n");
            content.append("      SELECT "+propertiesMap.get(insertSeq)+".NEXTVAL FROM DUAL\r\n");
            content.append("  \t</selectKey>\r\n");
        }
        content.append("    insert into "+propertiesMap.get(tableName)+" (");
        int k = 1;
        for(String dbFieldName : dbFieldMap.keySet()) {
            if(k>1 && (k-1) % 3 == 0) {
                content.append("      ");
            }
            content.append(dbFieldName);
            if(k < dbFieldMap.size()) {
                content.append(",");
            }
            if(k%3 == 0 && k != dbFieldMap.size()) {
                content.append("\r\n");
            }
            if(k == dbFieldMap.size()) {
                content.append(")\r\n");
            }
            k++;
        }

        // update
        // delete
        // find

        // 生成文件
        String fileName = GeneratorUtil.DBNameToJavaName(propertiesMap.get(tableName))+"Mapper.xml";
        System.out.println("生成文件：" + this.rootPath + GeneratorUtil.packToFolder(propertiesMap.get(package_mapper)) + "/" + fileName + "...................");
        outFile(package_mapper, fileName, content.toString());
    }
}
