package com.xiaoyuer.builder;

import com.xiaoyuer.bean.Constants;
import com.xiaoyuer.bean.FieldInfo;
import com.xiaoyuer.bean.TableInfo;
import com.xiaoyuer.utils.JsonUtils;
import com.xiaoyuer.utils.PropertiesUtils;
import com.xiaoyuer.utils.StringUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BuildTable {
    private static final Logger logger = LoggerFactory.getLogger(BuildTable.class);
    private static Connection conn = null;
    private static final String SQL_SHOW_TABLE_STATUS = "show table status";
    private static final String SQL_SHOW_FULL_FIELDS = "show full fields from %s";
    private static final String SQL_SHOW_INDEX = "show index from %s";

    static {
        String url = PropertiesUtils.getString("db.url");
        String driverName = PropertiesUtils.getString("db.driver.name");
        String username = PropertiesUtils.getString("db.username");
        String password = PropertiesUtils.getString("db.password");
        try {
            Class.forName(driverName);
            conn = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException e) {
            logger.error("ClassNotFoundException,数据库连接失败！", e);
            throw new RuntimeException(e);
        } catch (SQLException e) {
            logger.error("SQLException,数据库连接失败！", e);
            throw new RuntimeException(e);
        }
    }

    public static List<TableInfo> getTables() {
        PreparedStatement ps = null;
        ResultSet rs = null;

        List<TableInfo> tableInfoList = new ArrayList<TableInfo>();

        try {
            ps = conn.prepareStatement(SQL_SHOW_TABLE_STATUS);
            rs = ps.executeQuery();
            TableInfo tableInfo = null;
            while (rs.next()) {
                String tableName = rs.getString("name");
                String comment = rs.getString("comment");

                String beanName = tableName;
                if (Constants.IGNORE_TABLE_PREFIX) {
                    beanName = tableName.substring(beanName.indexOf("_") + 1);
                }
                beanName = processFiled(beanName, true);
                tableInfo = new TableInfo();
                tableInfo.setTableName(tableName);
                tableInfo.setBeanName(beanName);
                tableInfo.setComment(comment);
                tableInfo.setBeanParamName(beanName + Constants.SUFFIX_BEAN_QUERY);
                readFieldInfo(tableInfo);
                getKeyIndexInfo(tableInfo);
            }
            tableInfoList.add(tableInfo);
        } catch (Exception e) {
            logger.error("Exception,获取表信息失败！", e);
        } finally {
            try {
                assert rs != null;
                rs.close();
                ps.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return tableInfoList;
    }

    private static void readFieldInfo(TableInfo tableInfo) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<FieldInfo> fieldInfoList = new ArrayList<FieldInfo>();
        List<FieldInfo> fieldExtendList = new ArrayList<FieldInfo>();

        try {
            ps = conn.prepareStatement(String.format(SQL_SHOW_FULL_FIELDS, tableInfo.getTableName()));
            rs = ps.executeQuery();
            Boolean haveDateTime = false;
            Boolean havaDate = false;
            Boolean havaBigDecimal = false;
            while (rs.next()) {
                String field = rs.getString("field");
                String comment = rs.getString("comment");
                String type = rs.getString("type");
                String extra = rs.getString("extra");
                if (type.indexOf("(") > 0) {
                    type = type.substring(0, type.indexOf("("));
                }
                String propertyName = processFiled(field, false);

                FieldInfo fieldInfo = new FieldInfo();
                fieldInfoList.add(fieldInfo);

                fieldInfo.setFieldName(field);
                fieldInfo.setComment(comment);
                fieldInfo.setSqlType(type);
                fieldInfo.setAutoIncrement("auto_increment".equalsIgnoreCase(extra));
                fieldInfo.setPropertyName(propertyName);
                fieldInfo.setJavaType(processJavaType(type));
                if (ArrayUtils.contains(Constants.SQL_DATE_TIME_TYPES, type)) {
                    haveDateTime = true;
                }
                if (ArrayUtils.contains(Constants.SQL_DATE_TYPES, type)) {
                    havaDate = true;
                }
                if (ArrayUtils.contains(Constants.SQL_DECIMAL_TYPES, type)) {
                    havaBigDecimal = true;
                }
                if (ArrayUtils.contains(Constants.SQL_STRING_TYPES, type)) {

                    FieldInfo fuzzyField = new FieldInfo();
                    fuzzyField.setJavaType(fieldInfo.getJavaType());
                    fuzzyField.setPropertyName(propertyName + Constants.SUFFIX_BEAN_QUERY_FUZZY);
                    fuzzyField.setFieldName(fieldInfo.getFieldName());
                    fuzzyField.setSqlType(type);
                    fieldExtendList.add(fuzzyField);
                }

                if (ArrayUtils.contains(Constants.SQL_DATE_TIME_TYPES, type) || ArrayUtils.contains(Constants.SQL_DATE_TYPES, type)) {

                    FieldInfo timeStartField = new FieldInfo();
                    timeStartField.setJavaType("String");
                    timeStartField.setPropertyName(propertyName + Constants.SUFFIX_BEAN_QUERY_TIME_START);
                    timeStartField.setFieldName(fieldInfo.getFieldName());
                    timeStartField.setSqlType(type);
                    fieldExtendList.add(timeStartField);

                    FieldInfo timeEndField = new FieldInfo();
                    timeEndField.setJavaType("String");
                    timeEndField.setPropertyName(propertyName + Constants.SUFFIX_BEAN_QUERY_TIME_END);
                    timeEndField.setFieldName(fieldInfo.getFieldName());
                    timeEndField.setSqlType(type);
                    fieldExtendList.add(timeEndField);
                }

            }
            tableInfo.setHaveDateTime(haveDateTime);
            tableInfo.setHaveDate(havaDate);
            tableInfo.setHaveBigDecimal(havaBigDecimal);
            tableInfo.setFieldList(fieldInfoList);
            tableInfo.setFieldExtendList(fieldExtendList);

        } catch (Exception e) {
            logger.error("读取表失败！", e);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static List<FieldInfo> getKeyIndexInfo(TableInfo tableInfo) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<FieldInfo> fieldInfoList = new ArrayList<FieldInfo>();
        try {
            Map<String, FieldInfo> tempMap = new HashMap<String, FieldInfo>();
            for (FieldInfo fieldInfo : tableInfo.getFieldList()) {
                tempMap.put(fieldInfo.getPropertyName(), fieldInfo);
            }
            ps = conn.prepareStatement(String.format(SQL_SHOW_INDEX, tableInfo.getTableName()));
            rs = ps.executeQuery();
            while (rs.next()) {
                String keyName = rs.getString("key_name");
                Integer nonUnique = rs.getInt("non_unique");
                String columnName = rs.getString("column_name");
                if (nonUnique == 1) {
                    continue;
                }
                List<FieldInfo> keyFieldList = tableInfo.getKeyIndexMap().get(keyName);
                if (keyFieldList == null) {
                    keyFieldList = new ArrayList<FieldInfo>();
                    tableInfo.getKeyIndexMap().put(keyName, keyFieldList);
                }
                for (FieldInfo fieldInfo : tableInfo.getFieldList()) {
                    if (fieldInfo.getFieldName().equals(columnName)) {
                        keyFieldList.add(fieldInfo);
                    }
                }
            }
        } catch (Exception e) {
            logger.error("读取索引失败！", e);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return fieldInfoList;
    }

    public static String processFiled(String field, Boolean upperCaseFirstLetter) {
        StringBuilder sb = new StringBuilder();
        String[] fields = field.split("_");
        sb.append(upperCaseFirstLetter ? StringUtils.upperCaseFirstLetter(fields[0]) : fields[0]);
        for (int i = 1, len = fields.length; i < len; i++) {
            sb.append(StringUtils.upperCaseFirstLetter(fields[i]));
        }
        return sb.toString();
    }

    private static String processJavaType(String type) {
        if (ArrayUtils.contains(Constants.SQL_INTEGER_TYPE, type)) {
            return "Integer";
        } else if (ArrayUtils.contains(Constants.SQL_LONG_TYPE, type)) {
            return "Long";
        } else if (ArrayUtils.contains(Constants.SQL_STRING_TYPES, type
        )) {
            return "String";
        } else if (ArrayUtils.contains(Constants.SQL_DATE_TIME_TYPES, type) || ArrayUtils.contains(Constants.SQL_DATE_TYPES, type)) {
            return "Date";
        } else if (ArrayUtils.contains(Constants.SQL_DECIMAL_TYPES, type)) {
            return "BigDecimal";
        } else {
            throw new RuntimeException("无法识别的mysql类型" + type);
        }
    }

}