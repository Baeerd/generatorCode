package generator;

import util.GeneratorUtil;

import java.util.Map;

/**
 * 生成mapper层文件(EntityMapper.java, EntityMapper.xml, AbstratEntityMapper.java)
 */
public class GeneratorMapper extends GeneratorCommon{

    public GeneratorMapper(Map<String, String> propertiesMap, Map<String, String> fieldMap, String tableName) {
        this.propertiesMap = propertiesMap;
        this.fieldMap = fieldMap;
        this.tableName = tableName;
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
        content.append("public interface "+tableName+"Mapper {\r\n\r\n");

        // 添加方法
        content.append("    public void ").append(propertiesMap.get(insert)).append("(").append(tableName)
                .append(" ").append(GeneratorUtil.lower1(tableName)).append(");\r\n\r\n");

        // 修改方法
        content.append("    public void ").append(propertiesMap.get(update)).append("(").append(tableName)
                .append(" ").append(GeneratorUtil.lower1(tableName)).append(");\r\n\r\n");

        // 删除方法
        content.append("    public void ").append(propertiesMap.get(delete)).append("(").append(fieldMap.get(GeneratorUtil.getFirstKey(fieldMap)))
                .append(" ").append(GeneratorUtil.lower1(GeneratorUtil.getFirstKey(fieldMap))).append(");\r\n\r\n");

        // 查询方法
        content.append("    public List<").append(tableName).append("> ").append(propertiesMap.get(find))
                .append("(Map<String, Object> params);\r\n}");

        // 生成文件
        String fileName = tableName+"Mapper";
        System.out.println("生成文件：" + this.rootPath + GeneratorUtil.packToFolder(propertiesMap.get(package_mapper)) + "/" + fileName + ".java...................");
        outFile(package_mapper, fileName, content.toString());
    }

    /**
     * 生成.xml文件
     */
    private void generatorMapperXml() {
        
    }
}
