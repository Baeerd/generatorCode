package generator;

import util.GeneratorUtil;

import java.util.Map;

/**
 * 生成controller层文件(EntityController.java, AbstratEntityController.java)
 */
public class GeneratorController extends GeneratorCommon{

    public GeneratorController(Map<String, String> propertiesMap, Map<String, String> fieldMap, Map<String, String> dbFieldMap) {
        this.propertiesMap = propertiesMap;
        this.fieldMap = fieldMap;
        this.dbFieldMap = dbFieldMap;
        initIO();
        entityClass = GeneratorUtil.DBNameToJavaName(propertiesMap.get(tableName));
        entityName = GeneratorUtil.lower1(entityClass);
    }

    /**
     * TODO 需要生成Abstract文件
     * @throws Exception
     */
    @Override
    protected void generatorWithAbstrat() throws Exception {
        // 文件内容
        StringBuilder content = new StringBuilder("package " + propertiesMap.get(package_controller) + ";\r\n\r\n");
        // 生成import部分
        content.append("import org.springframework.context.annotation.Scope;\r\n");
        content.append("import org.springframework.web.bind.annotation.RequestMapping;\r\n");
        content.append("import org.springframework.stereotype.Controller;\r\n\r\n");

        content.append("import " + propertiesMap.get(common_controller) + ";\r\n");
        content.append("import ").append(propertiesMap.get(package_entity)).append(".").append(entityClass).append(";\r\n\r\n");

        content.append("import java.util.List;\r\nimport java.util.Map;\r\n\r\n");

        content.append("@Controller\r\n");
        content.append("@Scope(\"prototype\")\r\n");
        content.append("@RequestMapping(\"/"+entityName+"\")\r\n");
        content.append("public class "+entityClass+"Controller extends "+commonClassName+"<"+entityClass+">{\r\n\r\n");

        content.append("}");

        // 生成文件
        String fileName = entityClass+"Controller.java";
        System.out.println("生成文件：" + this.rootPath + GeneratorUtil.packToFolder(propertiesMap.get(package_controller)) + "/" + fileName + "...................");
        outFile(package_controller, fileName, content.toString());
    }

    /**
     * TODO 生成公共类文件
     * @throws Exception
     */
    @Override
    protected void generatorCommon() throws Exception {
        // 文件内容
        StringBuilder content = new StringBuilder("package " + commonPackage + ";\r\n\r\n");
        // 生成import部分
        content.append("import ").append(propertiesMap.get(package_entity)).append(".").append(entityClass).append(";\r\n");
        content.append("import "+propertiesMap.get(package_service)+"."+entityClass+"Service;\r\n\r\n");
        content.append("import java.util.List;\r\nimport java.util.Map;\r\n\r\n");

        content.append("import org.springframework.web.bind.annotation.RequestMapping;\r\n\r\n");

        content.append("public class "+commonClassName+"<T> {\r\n\r\n");

        // 生成方法
        content = controllerMethod(content);

        content.append("}");

        // 生成文件
        String fileName = commonClassName+".java";
        System.out.println("生成文件：" + this.rootPath + GeneratorUtil.packToFolder(propertiesMap.get(common_controller)) + "/" + fileName + "...................");
        outFile(common_controller, fileName, content.toString());
    }

    /**
     * 生成方法
     * @param content
     * @return
     */
    private StringBuilder controllerMethod(StringBuilder content) {

        // 添加
        content.append("    @RequestMapping(\"/"+propertiesMap.get(insert)+"\")\r\n");
        content.append("    public void "+propertiesMap.get(insert)+"("+entityClass+" "+entityName+") {\r\n");
        content.append("        "+entityName+"Service."+propertiesMap.get(insert)+"("+entityName+");\r\n");
        content.append("    }\r\n\r\n");

        // 修改
        content.append("    @RequestMapping(\"/"+propertiesMap.get(update)+"\")\r\n");
        content.append("    public void "+propertiesMap.get(update)+"("+entityClass+" "+entityName+") {\r\n");
        content.append("        "+entityName+"Service."+propertiesMap.get(update)+"("+entityName+");\r\n");
        content.append("    }\r\n\r\n");

        // 删除
        content.append("    @RequestMapping(\"/"+propertiesMap.get(delete)+"\")\r\n");
        content.append("    public void "+propertiesMap.get(delete)+"("+entityClass+" "+entityName+") {\r\n");
        content.append("        "+entityName+"Service."+propertiesMap.get(delete)+"("+entityName+");\r\n");
        content.append("    }\r\n\r\n");

        // 查询
        content.append("    @RequestMapping(\"/"+propertiesMap.get(find)+"\")\r\n");
        content.append("    public List<"+entityClass+"> "+propertiesMap.get(find)+"(Map<String, Object> params) {\r\n");
        content.append("        return "+entityName+"Service."+propertiesMap.get(find)+"(params);\r\n");
        content.append("    }\r\n\r\n");

        return content;
    }

    /**
     * 不需要生成Abstract文件
     * @throws Exception
     */
    @Override
    protected void generatorWithNotAbstrat() throws Exception {
        // 文件内容
        StringBuilder content = new StringBuilder("package " + propertiesMap.get(package_controller) + ";\r\n\r\n");
        // 生成import部分
        content.append("import org.springframework.beans.factory.annotation.Autowired;\r\n");
        content.append("import org.springframework.context.annotation.Scope;\r\n");
        content.append("import org.springframework.web.bind.annotation.RequestMapping;\r\n");
        content.append("import org.springframework.stereotype.Controller;\r\n\r\n");
        content.append("import ").append(propertiesMap.get(package_entity)).append(".").append(entityClass).append(";\r\n");
        content.append("import "+propertiesMap.get(package_service)+"."+entityClass+"Service;\r\n\r\n");
        content.append("import java.util.List;\r\nimport java.util.Map;\r\n\r\n");

        content.append("@Controller\r\n");
        content.append("@Scope(\"prototype\")\r\n");
        content.append("@RequestMapping(\"/"+entityName+"\")\r\n");
        content.append("public class "+entityClass+"Controller {\r\n\r\n");

        content.append("    @AutoWired\r\n");
        content.append("    private "+entityClass+"Service "+entityName+"Service;\r\n\r\n");

        // 生成方法
        content = controllerMethod(content);

        content.append("}");

        // 生成文件
        String fileName = entityClass+"Controller.java";
        System.out.println("生成文件：" + this.rootPath + GeneratorUtil.packToFolder(propertiesMap.get(package_controller)) + "/" + fileName + "...................");
        outFile(package_controller, fileName, content.toString());
    }

}
