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
        entityClass = GeneratorUtil.DBNameToJavaName(propertiesMap.get(tableName));
        entityName = GeneratorUtil.lower1(entityClass);
    }

    /**
     * 需要生成Abstract文件
     */
    @Override
    public void generatorWithAbstrat() throws Exception{
        // 文件内容
        StringBuilder content = new StringBuilder("package " + propertiesMap.get(package_mapper) + ";\r\n\r\n");
        // 生成import部分
        content.append("import org.springframework.stereotype.Repository;\r\n\r\n");
        content.append("import " + propertiesMap.get(common_Mapper) + ";\r\n");
        content.append("import ").append(propertiesMap.get(package_entity)).append(".").append(entityClass).append(";\r\n\r\n");
        content.append("import java.util.List;\r\nimport java.util.Map;\r\n\r\n");

        // 生成类和方法部分
        content.append("@Repository\r\n");
        content.append("public interface "+entityClass+"Mapper extends "+commonClassName+"<"+entityClass+">{\r\n\r\n");

        content.append("}");
        // 生成文件
        String fileName = entityClass+"Mapper.java";
        System.out.println("生成文件：" + this.rootPath + GeneratorUtil.packToFolder(propertiesMap.get(package_mapper)) + "/" + fileName + "...................");
        outFile(package_mapper, fileName, content.toString());
    }

    /**
     * 生成公共类文件
     * @throws Exception
     */
    @Override
    protected void generatorCommon() throws Exception {
        // 文件内容
        StringBuilder content = new StringBuilder("package " + commonPackage + ";\r\n\r\n");
        // 生成import部分
        content.append("import java.util.List;\r\nimport java.util.Map;\r\n\r\n");

        // 生成类和方法部分
        content.append("public interface "+commonClassName+"<T> {\r\n\r\n");

        // 生成Mapper方法
        content = mapperCommonMethod(content);

        content.append("}");
        // 生成文件
        String fileName = commonClassName+".java";
        System.out.println("生成文件：" + this.rootPath + GeneratorUtil.packToFolder(propertiesMap.get(common_Mapper)) + "/" + fileName + "...................");
        outFile(common_Mapper, fileName, content.toString());
    }

    /**
     * 生成CommonMapper文件的方法
     * @param content
     * @return
     */
    private StringBuilder mapperCommonMethod(StringBuilder content) {
        // 添加方法
        content.append("    public void ").append(propertiesMap.get(insert)).append("(T entity);\r\n\r\n");

        // 修改方法
        content.append("    public void ").append(propertiesMap.get(update)).append("(T entity);\r\n\r\n");

        // 删除方法
        content.append("    public void ").append(propertiesMap.get(delete)).append("(T entity);\r\n\r\n");

        // 查询方法
        content.append("    public List<T> ").append(propertiesMap.get(find))
                .append("(Map<String, Object> params);\r\n\r\n");

        return content;
    }

    /**
     * 不需要生成Abstract文件
     */
    @Override
    public void generatorWithNotAbstrat() throws Exception {
        // 生成没有Abstract文件的java文件
        generatorJavaWithNotAbstrat();
        // 生成.xml文件
        generatorMapperXml();
    }

    /**
     * 生成Mapper方法
     * @param content
     * @return
     */
    private StringBuilder mapperMethod(StringBuilder content) {
        // 添加方法
        content.append("    public void ").append(propertiesMap.get(insert)).append("(").append(entityClass)
                .append(" ").append(entityName).append(");\r\n\r\n");

        // 修改方法
        content.append("    public void ").append(propertiesMap.get(update)).append("(").append(entityClass)
                .append(" ").append(entityName).append(");\r\n\r\n");

        // 删除方法
        content.append("    public void ").append(propertiesMap.get(delete)).append("(").append(entityClass)
                .append(" ").append(entityName).append(");\r\n\r\n");

        // 查询方法
        content.append("    public List<").append(entityClass).append("> ").append(propertiesMap.get(find))
                .append("(Map<String, Object> params);\r\n\r\n");

        return content;
    }

    /**
     * 生成没有Abstract文件的java文件
     * @throws Exception
     */
    private void generatorJavaWithNotAbstrat() throws Exception{
        // 文件内容
        StringBuilder content = new StringBuilder("package " + propertiesMap.get(package_mapper) + ";\r\n\r\n");
        // 生成import部分
        content.append("import org.springframework.stereotype.Repository;\r\n\r\n");
        content.append("import ").append(propertiesMap.get(package_entity)).append(".").append(entityClass).append(";\r\n\r\n");
        content.append("import java.util.List;\r\nimport java.util.Map;\r\n\r\n");

        // 生成类和方法部分
        content.append("@Repository\r\n");
        content.append("public interface "+entityClass+"Mapper {\r\n\r\n");

        // 生成Mapper方法
        content = mapperMethod(content);

        content.append("}");
        // 生成文件
        String fileName = entityClass+"Mapper.java";
        System.out.println("生成文件：" + this.rootPath + GeneratorUtil.packToFolder(propertiesMap.get(package_mapper)) + "/" + fileName + "...................");
        outFile(package_mapper, fileName, content.toString());
    }

    /**
     * 生成.xml文件
     */
    private void generatorMapperXml() throws Exception {
        // Mapper文件xml 表头
        StringBuilder content = new StringBuilder(propertiesMap.get(mapperTop));
        content.append("<mapper namespace=\"").append(propertiesMap.get(package_mapper)).append(".").append(entityClass).append("Mapper\" >\r\n");

        // resultMap
        content.append("  <resultMap id=\"BaseResultMap\" type=\"").append(propertiesMap.get(package_entity)).append(".").append(entityClass).append("\" >\r\n");
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
        content.append("  <insert id=\""+propertiesMap.get(insert)+"\" parameterType=\""+propertiesMap.get(package_entity)+"."+entityClass+"\" >\r\n");
        if(propertiesMap.get(insertSeq) != null && !"".equals(propertiesMap.get(insertSeq))) {
            content.append("  \t<selectKey keyProperty=\""+id+"\" order=\"BEFORE\" resultType=\"java.lang."+idType+"\">\r\n");
            content.append("      SELECT "+propertiesMap.get(insertSeq)+".NEXTVAL FROM DUAL\r\n");
            content.append("  \t</selectKey>\r\n");
        }
        content.append("    insert into "+propertiesMap.get(tableName)+" (");
        int k1 = 1;
        for(String dbFieldName : dbFieldMap.keySet()) {
            if(k1>1 && (k1-1) % 3 == 0) {
                content.append("      ");
            }
            content.append(dbFieldName);
            if(k1 < dbFieldMap.size()) {
                content.append(",");
            }
            if(k1%3 == 0 && k1 != dbFieldMap.size()) {
                content.append("\r\n");
            }
            if(k1 == dbFieldMap.size()) {
                content.append(")\r\n");
            }
            k1++;
        }
        content.append("    values (");
        int k2 = 1;
        for(String dbFieldName : dbFieldMap.keySet()) {
            if(k2>1 && (k2-1) % 3 == 0) {
                content.append("      ");
            }
            content.append("#{"+GeneratorUtil.lower1(GeneratorUtil.DBNameToJavaName(dbFieldName))+",jdbcType="+dbFieldMap.get(dbFieldName)+"}");
            if(k2 < dbFieldMap.size()) {
                content.append(",");
            }
            if(k2%3 == 0 && k2 != dbFieldMap.size()) {
                content.append("\r\n");
            }
            if(k2 == dbFieldMap.size()) {
                content.append(")\r\n");
            }
            k2++;
        }
        content.append("  </insert>\r\n\r\n");

        // update
        content.append("  <update id=\""+propertiesMap.get(update)+"\" parameterType=\""+propertiesMap.get(package_entity)+"."+entityClass+"\" >\r\n");
        content.append("    update "+propertiesMap.get(tableName)+" set "+propertiesMap.get(updateVersion)+"\r\n");
        for(String dbFieldName : dbFieldMap.keySet()) {
            content.append("      <if test=\""+GeneratorUtil.lower1(GeneratorUtil.DBNameToJavaName(dbFieldName))+" != null\" >\r\n");
            content.append("        ,"+dbFieldName+" = #{"+GeneratorUtil.lower1(GeneratorUtil.DBNameToJavaName(dbFieldName))+",jdbcType="+dbFieldMap.get(dbFieldName)+"}\r\n");
            content.append("      </if>\r\n");
        }
        content.append("  \t\twhere "+GeneratorUtil.getFirstKey(dbFieldMap)+" = #{"+GeneratorUtil.lower1(GeneratorUtil.DBNameToJavaName(GeneratorUtil.getFirstKey(dbFieldMap)))+",jdbcType="+dbFieldMap.get(GeneratorUtil.getFirstKey(dbFieldMap))+"}\r\n");
        content.append("  </update>\r\n\r\n");

        // delete
        content.append("  <delete id=\""+propertiesMap.get(delete)+"\" parameterType=\""+propertiesMap.get(package_entity)+"."+entityClass+"\" >\r\n");
        content.append("    delete from "+propertiesMap.get(tableName)+"\r\n");
        content.append("    where "+GeneratorUtil.getFirstKey(dbFieldMap)+" = #{"+GeneratorUtil.lower1(GeneratorUtil.DBNameToJavaName(GeneratorUtil.getFirstKey(dbFieldMap)))+",jdbcType="+dbFieldMap.get(GeneratorUtil.getFirstKey(dbFieldMap))+"}\r\n");
        content.append("  </delete>\r\n\r\n");

        // find
        content.append("  <select id=\""+propertiesMap.get(find)+"\" parameterType=\"java.util.Map\" resultMap=\"BaseResultMap\">\r\n");
        content.append("  \tselect\r\n");
        content.append("\t\t<include refid=\"Base_Column_List\" />\r\n");
        content.append("\tfrom "+propertiesMap.get(tableName)+"\r\n");
        content.append("\t\t<trim prefix=\"WHERE\" prefixOverrides=\"AND |OR \">\r\n");
        for(String dbFieldName : dbFieldMap.keySet()) {
            content.append("\t\t      <if test=\""+GeneratorUtil.lower1(GeneratorUtil.DBNameToJavaName(dbFieldName))+" != null\" >\r\n");
            content.append("\t\t        and "+dbFieldName+" = #{"+GeneratorUtil.lower1(GeneratorUtil.DBNameToJavaName(dbFieldName))+",jdbcType=DECIMAL}\r\n");
            content.append("\t\t      </if>\r\n");
        }
        content.append("\t      </trim>\r\n");
        content.append("\torder by "+GeneratorUtil.getFirstKey(dbFieldMap)+" desc\r\n");
        content.append("  </select>\r\n");

        content.append("</mapper>");

        // 生成文件
        String fileName = entityClass+"Mapper.xml";
        System.out.println("生成文件：" + this.rootPath + GeneratorUtil.packToFolder(propertiesMap.get(package_mapper)) + "/" + fileName + "...................");
        outFile(package_mapper, fileName, content.toString());
    }
}
