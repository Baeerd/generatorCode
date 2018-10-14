package generator;

import util.GeneratorUtil;

import java.sql.*;
import java.util.*;

public class GeneratorJava extends GeneratorCommon{

    public static void main(String[] args) {
        GeneratorJava generatorJava = new GeneratorJava();
        generatorJava.generator();
    }

    public GeneratorJava() {
        super();
        initProperties();
    }

    /**
     * 初始化配置文件数据
     */
    private void initProperties() {
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
        System.out.println("初始化配置信息....." + propertiesMap);
    }

    /**
     * 生成代码
     */
    private void generator() {
        // 根据数据库配置获取所查表数据
        try {
            fieldMap = generatorData();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 生成实体类文件(Entity.java, AbstraEntity.java)参数：表名，dataList
        GeneratorEntity generatorEntity = new GeneratorEntity(propertiesMap, fieldMap, GeneratorUtil.DBNameToJavaName(propertiesMap.get(tableName)));
        generatorEntity.generator();
        // 生成mapper层文件(EntityMapper.java, EntityMapper.xml, AbstratEntityMapper.java)
        GeneratorMapper generatorMapper = new GeneratorMapper(propertiesMap, fieldMap, GeneratorUtil.DBNameToJavaName(propertiesMap.get(tableName)));
        generatorMapper.generator();
        // 生成service层文件(EntityService.java, EntityServiceImpl.java, AbstratService.java, AbstratServiceImpl.java)
        // 生成controller层文件(EntityController.java, AbstratEntityController.java)

    }

    /**
     * 根据数据库配置信息，连接数据库，对应表所有属性及类型
     * @return
     */
    private Map<String,String> generatorData() throws Exception {
        Map<String, String> fieldMap = new LinkedHashMap<>();

        Class.forName(propertiesMap.get(jdbcName));
        String url = propertiesMap.get(jdbcUrl);
        String user = propertiesMap.get(jdbcUser);
        String password = propertiesMap.get(jdbcPassword);

        Connection conn = DriverManager.getConnection(url, user, password);

        String sql = "select * from " + propertiesMap.get(tableName);
        PreparedStatement stmt;
        try {
            stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery(sql);
            ResultSetMetaData data = rs.getMetaData();
            for (int i = 1; i <= data.getColumnCount(); i++) {
                // 获得所有列的数目及实际列数
                int columnCount = data.getColumnCount();
                // 获得指定列的列名
                String columnName = data.getColumnName(i);
                // 获得指定列的列值
                int columnType = data.getColumnType(i);
                // 获得指定列的数据类型名
                String columnTypeName = data.getColumnTypeName(i);
                // 所在的Catalog名字
                String catalogName = data.getCatalogName(i);
                // 对应数据类型的类
                String columnClassName = data.getColumnClassName(i);
                // 在数据库中类型的最大字符个数
                int columnDisplaySize = data.getColumnDisplaySize(i);
                // 默认的列的标题
                String columnLabel = data.getColumnLabel(i);
                // 获得列的模式
                String schemaName = data.getSchemaName(i);
                // 某列类型的精确度(类型的长度)
                int precision = data.getPrecision(i);
                // 小数点后的位数
                int scale = data.getScale(i);
                // 获取某列对应的表名
                String tableName = data.getTableName(i);
                // 是否自动递增
                boolean isAutoInctement = data.isAutoIncrement(i);
                // 在数据库中是否为货币型
                boolean isCurrency = data.isCurrency(i);
                // 是否为空
                int isNullable = data.isNullable(i);
                // 是否为只读
                boolean isReadOnly = data.isReadOnly(i);
                // 能否出现在where中
                boolean isSearchable = data.isSearchable(i);
                /*System.out.println(columnCount);
                System.out.println("获得列" + i + "的字段名称:" + columnName);
                System.out.println("获得列" + i + "的类型,返回SqlType中的编号:"+ columnType);
                System.out.println("获得列" + i + "的数据类型名:" + columnTypeName);
                System.out.println("获得列" + i + "所在的Catalog名字:"+ catalogName);
                System.out.println("获得列" + i + "对应数据类型的类:"+ columnClassName);
                System.out.println("获得列" + i + "在数据库中类型的最大字符个数:"+ columnDisplaySize);
                System.out.println("获得列" + i + "的默认的列的标题:" + columnLabel);
                System.out.println("获得列" + i + "的模式:" + schemaName);
                System.out.println("获得列" + i + "类型的精确度(类型的长度):" + precision);
                System.out.println("获得列" + i + "小数点后的位数:" + scale);
                System.out.println("获得列" + i + "对应的表名:" + tableName);
                System.out.println("获得列" + i + "是否自动递增:" + isAutoInctement);
                System.out.println("获得列" + i + "在数据库中是否为货币型:" + isCurrency);
                System.out.println("获得列" + i + "是否为空:" + isNullable);
                System.out.println("获得列" + i + "是否为只读:" + isReadOnly);
                System.out.println("获得列" + i + "能否出现在where中:"+ isSearchable);*/

                // 添加进fieldMap中，key=列名，value=列类型
                fieldMap.put(GeneratorUtil.DBNameToJavaName(columnName),
                        GeneratorUtil.DBTypeToJavaType(scale > 0 ? columnTypeName+"_" : columnTypeName));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("表" + propertiesMap.get(tableName) + "的所有属性及类型：" + fieldMap);
        return fieldMap;
    }
}
