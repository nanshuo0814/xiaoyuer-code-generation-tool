package com.nanshuo.builder;

import com.nanshuo.bean.Constants;
import com.nanshuo.bean.FieldInfo;
import com.nanshuo.bean.TableInfo;
import com.nanshuo.utils.DateUtils;
import com.nanshuo.utils.StringUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

public class BuildPo {
    private static final Logger logger = LoggerFactory.getLogger(BuildPo.class);

    public static void execute(TableInfo tableInfo) {
        File folder = new File(Constants.PATH_PO);
        if (!folder.exists()) {
            folder.mkdirs();
        }
        File file = new File(folder, tableInfo.getBeanName() + ".java");
        OutputStream out = null;
        OutputStreamWriter osw = null;
        BufferedWriter bw = null;
        try {
            out = new FileOutputStream(file);
            osw = new OutputStreamWriter(out, "UTF-8");
            bw = new BufferedWriter(osw);
            bw.write("package " + Constants.PACKAGE_PO + ";");
            bw.newLine();
            bw.newLine();

            if (tableInfo.getHaveDate() || tableInfo.getHaveDateTime()) {

                bw.write("import " + Constants.PACKAGE_ENUMS + ".DateTimePatternEnums;");
                bw.newLine();
                bw.write("import " + Constants.PACKAGE_UTILS + ".DateUtils;");
                bw.newLine();

                bw.write(Constants.BEAN_DATE_FORMAT_CLASS);
                bw.newLine();
                bw.write(Constants.BEAN_DATE_UNFORMAT_CLASS);
                bw.newLine();

            }

            Boolean haveIgnoreBean = false;
            for (FieldInfo field : tableInfo.getFieldList()) {
                if (ArrayUtils.contains(Constants.IGNORE_BEAN_TOJSON_FIELD.split(","), field.getPropertyName())) {
                    haveIgnoreBean = true;
                    break;
                }
            }
            if (haveIgnoreBean) {
                bw.write(Constants.IGNORE_BEAN_TOJSON_CLASS);
                bw.newLine();
                bw.newLine();
            }
            bw.newLine();
            bw.write("import java.io.Serializable;");
            bw.newLine();
            if (tableInfo.getHaveBigDecimal()) {
                bw.write("import java.math.BigDecimal;");
                bw.newLine();
            }
            if (tableInfo.getHaveDate() || tableInfo.getHaveDateTime()) {
                bw.write("import java.util.Date;");
                bw.newLine();
                bw.newLine();
            }
            // 构建类注释
            BuildComment.createClassComment(bw, tableInfo.getComment());
            bw.write("public class " + tableInfo.getBeanName() + " implements Serializable{");
            bw.newLine();
            bw.newLine();
            for (FieldInfo field : tableInfo.getFieldList()) {
                BuildComment.createFieldComment(bw, field.getComment());
                if (ArrayUtils.contains(Constants.SQL_DATE_TIME_TYPES, field.getSqlType())) {
                    bw.write("\t" + String.format(Constants.BEAN_DATE_FORMAT_EXPRESSION, DateUtils.YYYYMMDDHHMMSS));
                    bw.newLine();
                    bw.write("\t" + String.format(Constants.BEAN_DATE_UNFORMAT_EXPRESSION, DateUtils.YYYYMMDDHHMMSS));
                    bw.newLine();
                }
                if (ArrayUtils.contains(Constants.SQL_DATE_TYPES, field.getSqlType())) {
                    bw.write("\t" + String.format(Constants.BEAN_DATE_FORMAT_EXPRESSION, DateUtils.YYYYMMDD));
                    bw.newLine();
                    bw.write("\t" + String.format(Constants.BEAN_DATE_UNFORMAT_EXPRESSION, DateUtils.YYYYMMDD));
                    bw.newLine();
                }
                if (ArrayUtils.contains(Constants.IGNORE_BEAN_TOJSON_FIELD.split(","), field.getPropertyName())) {
                    bw.write("\t" + String.format(Constants.IGNORE_BEAN_TOJSON_EXPRESSION, DateUtils.YYYYMMDD));
                    bw.newLine();
                }
                bw.write("\tprivate " + field.getJavaType() + " " + field.getPropertyName() + ";");
                bw.newLine();
                bw.newLine();
            }
            for (FieldInfo field : tableInfo.getFieldList()) {
                String tempField = StringUtils.upperCaseFirstLetter(field.getPropertyName());
                bw.write("\tpublic void set" + tempField + "(" + field.getJavaType() + " " + field.getPropertyName() + ") {");
                bw.newLine();
                bw.write("\t\tthis." + field.getPropertyName() + " = " + field.getPropertyName() + ";");
                bw.newLine();
                bw.write("\t}");
                bw.newLine();
                bw.newLine();

                bw.write("\tpublic " + field.getJavaType() + " get" + tempField + "() {");
                bw.newLine();
                bw.write("\t\treturn this." + field.getPropertyName() + " = " + field.getPropertyName() + ";");
                bw.newLine();
                bw.write("\t}");
                bw.newLine();
                bw.newLine();
            }

            StringBuffer toString = new StringBuffer();
            Integer index = 0;
            for (FieldInfo field : tableInfo.getFieldList()) {
                index++;
                String properName = field.getPropertyName();
                if (ArrayUtils.contains(Constants.SQL_DATE_TIME_TYPES, field.getSqlType())) {
                    properName = "DateUtils.format(" + properName + ", DateTimePatternEnums.YYYY_MM_DD_HH_MM_SS.getPattern())";
                } else if (ArrayUtils.contains(Constants.SQL_DATE_TYPES, field.getSqlType())) {
                    properName = "DateUtils.format(" + properName + ", DateTimePatternEnums.YYYY_MM_DD.getPattern())";
                }
                toString.append(field.getComment() + ":\" + (" + field.getPropertyName() + " == null ? \"空\" : " + properName + ")");
                if (index < tableInfo.getFieldList().size()) {
                    toString.append(" + ").append("\",");
                }
            }
            String toStringStr = toString.toString();
            toStringStr = "\"" + toStringStr;

            bw.write("\t@Override");
            bw.newLine();
            bw.write("\tpublic String toString() {");
            bw.newLine();
            bw.write("\t\treturn " + toStringStr + ";");
            bw.newLine();
            bw.write("\t}");
            bw.newLine();

            bw.write("}");
            bw.flush();
        } catch (Exception e) {
            logger.error("创建po失败", e);
        } finally {
            try {
                assert bw != null;
                bw.close();
                osw.close();
                out.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
    }

}
