package generator;

import util.GeneratorUtil;

import java.util.Map;

/**
 * 生成service层文件(EntityService.java, EntityServiceImpl.java, AbstratService.java, AbstratServiceImpl.java)
 */
public class GeneratorService extends GeneratorCommon{


    public GeneratorService(Map<String, String> propertiesMap, Map<String, String> fieldMap, Map<String, String> dbFieldMap) {
        this.propertiesMap = propertiesMap;
        this.fieldMap = fieldMap;
        this.dbFieldMap = dbFieldMap;
        initIO();
        entityClass = GeneratorUtil.DBNameToJavaName(propertiesMap.get(tableName));
        entityName = GeneratorUtil.lower1(entityClass);
    }

    /********************需要生成Abstract文件*************************************************/
    /**
     * 需要生成Abstract文件
     * @throws Exception
     */
    @Override
    public void generatorWithAbstrat() throws Exception{
        // 生成service文件
        serviceWithAbstrat();
        // 生成serviceImpl文件
        serviceImplWithAbstrat();
    }

    /**
     * 生成带公共类的service文件
     */
    private void serviceWithAbstrat() throws Exception{
        // 文件内容
        StringBuilder content = new StringBuilder("package " + commonPackage + ";\r\n\r\n");
        // 生成import部分
        content.append("import " + propertiesMap.get(common_service) + ";\r\n");
        content.append("import ").append(propertiesMap.get(package_entity)).append(".").append(entityClass).append(";\r\n\r\n");
        content.append("import java.util.List;\r\nimport java.util.Map;\r\n\r\n");

        content.append("public interface "+entityClass+"Service extends "+commonClassName+"<"+entityClass+">{\r\n\r\n");

        content.append("}");

        // 生成文件
        String fileName = entityClass+"Service.java";
        System.out.println("生成文件：" + this.rootPath + GeneratorUtil.packToFolder(propertiesMap.get(package_service)) + "/" + fileName + "...................");
        outFile(package_service, fileName, content.toString());
    }

    /**
     * 生成带公共类的serviceImpl文件
     */
    private void serviceImplWithAbstrat() throws Exception{
        // 文件内容
        StringBuilder content = new StringBuilder("package " + propertiesMap.get(package_service) + ".impl;\r\n\r\n");
        // 生成import部分
        content.append("import org.springframework.stereotype.Service;\r\n\r\n");

        content.append("import "+propertiesMap.get(common_serviceImpl)+";\r\n");
        content.append("import ").append(propertiesMap.get(package_entity)).append(".").append(entityClass).append(";\r\n");
        content.append("import "+propertiesMap.get(package_mapper)+"."+entityClass+"Mapper;\r\n");
        content.append("import "+propertiesMap.get(package_service)+"."+entityClass+"Service;\r\n\r\n");

        content.append("import java.util.List;\r\nimport java.util.Map;\r\n\r\n");

        content.append("@Service\r\n");
        content.append("public class "+entityClass+"ServiceImpl extends "+commonClassName+"Impl<"+entityClass+"> implements "+entityClass+"Service {\r\n\r\n");

        content.append("}");
        // 生成文件
        String fileName = entityClass+"ServiceImpl.java";
        System.out.println("生成文件：" + this.rootPath + GeneratorUtil.packToFolder(propertiesMap.get(package_service)+".impl") + "/" + fileName + "...................");
        outFile(package_service, fileName, content.toString());
    }

    /********************生成公共类文件*************************************************/
    /**
     * 生成公共类文件
     * @throws Exception
     */
    @Override
    protected void generatorCommon() throws Exception {
        // 生成公共service文件
        serviceCommon();
        // 生成公共serviceImpl文件
        serviceImplCommon();
    }

    /**
     * 生成公共service文件
     */
    private void serviceCommon() throws Exception{
        // 文件内容
        StringBuilder content = new StringBuilder("package " + commonPackage + ";\r\n\r\n");
        // 生成import部分
        content.append("import java.util.List;\r\nimport java.util.Map;\r\n\r\n");

        content.append("public interface "+commonClassName+"<T> {\r\n\r\n");

        // 生成方法
        content = serviceCommonMethod(content);

        content.append("}");

        // 生成文件
        String fileName = commonClassName+".java";
        System.out.println("生成文件：" + this.rootPath + GeneratorUtil.packToFolder(propertiesMap.get(common_service)) + "/" + fileName + "...................");
        outFile(common_service, fileName, content.toString());
    }

    /**
     * 生成公共类Service的公共方法
     * @param content
     * @return
     */
    private StringBuilder serviceCommonMethod(StringBuilder content) {
        // 添加
        content.append("    public void "+propertiesMap.get(insert)+"(T entity);\r\n\r\n");
        // 修改
        content.append("    public void "+propertiesMap.get(update)+"(T entity);\r\n\r\n");
        // 删除
        content.append("    public void "+propertiesMap.get(delete)+"(T entity);\r\n\r\n");
        // 查询
        content.append("    public List<T> "+propertiesMap.get(find)+"(Map<String, Object> params);\r\n\r\n");
        return content;
    }

    /**
     * TODO 生成公共serviceImpl文件
     */
    private void serviceImplCommon() {

    }

    /********************不需要生成Abstract文件*************************************************/
    /**
     * 不需要生成Abstract文件
     * @throws Exception
     */
    @Override
    public void generatorWithNotAbstrat() throws Exception{
        // 生成service文件
        serviceWithNotAbstrat();
        // 生成serviceImpl文件
        serviceImplWithNotAbstrat();
    }

    /**
     * 生成service文件
     */
    private void serviceWithNotAbstrat() throws Exception{
        // 文件内容
        StringBuilder content = new StringBuilder("package " + propertiesMap.get(package_service) + ";\r\n\r\n");
        // 生成import部分
        content.append("import ").append(propertiesMap.get(package_entity)).append(".").append(entityClass).append(";\r\n\r\n");
        content.append("import java.util.List;\r\nimport java.util.Map;\r\n\r\n");

        content.append("public interface "+entityClass+"Service {\r\n\r\n");

        // 生成方法
        content = serviceMethod(content);

        content.append("}");

        // 生成文件
        String fileName = entityClass+"Service.java";
        System.out.println("生成文件：" + this.rootPath + GeneratorUtil.packToFolder(propertiesMap.get(package_service)) + "/" + fileName + "...................");
        outFile(package_service, fileName, content.toString());
    }

    /**
     * 生成serviceImpl文件
     */
    private void serviceImplWithNotAbstrat() throws Exception{
        // 文件内容
        StringBuilder content = new StringBuilder("package " + propertiesMap.get(package_service) + ".impl;\r\n\r\n");
        // 生成import部分
        content.append("import org.springframework.beans.factory.annotation.Autowired;\r\n");
        content.append("import org.springframework.stereotype.Service;\r\n\r\n");
        content.append("import ").append(propertiesMap.get(package_entity)).append(".").append(entityClass).append(";\r\n");
        content.append("import "+propertiesMap.get(package_mapper)+"."+entityClass+"Mapper;\r\n");
        content.append("import "+propertiesMap.get(package_service)+"."+entityClass+"Service;\r\n\r\n");
        content.append("import java.util.List;\r\nimport java.util.Map;\r\n\r\n");

        content.append("@Service\r\n");
        content.append("public class "+entityClass+"ServiceImpl implements "+entityClass+"Service {\r\n\r\n");
        content.append("    @Autowired\r\n");
        content.append("    private "+entityClass+"Mapper "+entityName+"Mapper;\r\n\r\n");

        // 生成方法
        content = serviceImplMethod(content);

        content.append("}");
        // 生成文件
        String fileName = entityClass+"ServiceImpl.java";
        System.out.println("生成文件：" + this.rootPath + GeneratorUtil.packToFolder(propertiesMap.get(package_service)+".impl") + "/" + fileName + "...................");
        outFile(package_service, fileName, content.toString());
    }

    /**
     * 生成Service方法
     * @param content
     * @return
     */
    private StringBuilder serviceMethod(StringBuilder content) {
        // 添加
        content.append("    public void "+propertiesMap.get(insert)+"("+entityClass).append(" ").append(entityName+");\r\n\r\n");
        // 修改
        content.append("    public void "+propertiesMap.get(update)+"("+entityClass).append(" ").append(entityName+");\r\n\r\n");
        // 删除
        content.append("    public void "+propertiesMap.get(delete)+"("+entityClass).append(" ").append(entityName+");\r\n\r\n");
        // 查询
        content.append("    public List<"+entityClass+"> "+propertiesMap.get(find)+"(Map<String, Object> params);\r\n\r\n");
        return content;
    }

    /**
     * 生成ServiceImpl方法
     * @param content
     * @return
     */
    private StringBuilder serviceImplMethod(StringBuilder content) {

        // 添加
        content.append("    public void "+propertiesMap.get(insert)+"("+entityClass+" "+entityName+") {\r\n");
        content.append("        "+entityName+"Mapper.insert("+entityName+");\r\n");
        content.append("    }\r\n\r\n");

        // 修改
        content.append("    public void "+propertiesMap.get(update)+"("+entityClass+" "+entityName+") {\r\n");
        content.append("        "+entityName+"Mapper.update("+entityName+");\r\n");
        content.append("    }\r\n\r\n");

        // 删除
        content.append("    public void "+propertiesMap.get(delete)+"("+entityClass+" "+entityName+") {\r\n");
        content.append("        "+entityName+"Mapper.delete("+entityName+");\r\n");
        content.append("    }\r\n\r\n");

        // 查询
        content.append("    public List<"+entityClass+"> "+propertiesMap.get(find)+"(Map<String, Object> params) {\r\n");
        content.append("        return "+entityName+"Mapper.find(params);\r\n");
        content.append("    }\r\n\r\n");

        return content;
    }
}
