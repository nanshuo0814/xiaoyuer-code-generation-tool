package com.nanshuo.builder;

import com.nanshuo.bean.Constants;
import com.nanshuo.bean.FieldInfo;
import com.nanshuo.bean.TableInfo;
import com.nanshuo.utils.StringUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.List;

public class BuildQuery {
    private static final Logger logger = LoggerFactory.getLogger(BuildPo.class);

    public static void execute(TableInfo tableInfo) {
        File folder = new File(Constants.PATH_QUERY);
        if (!folder.exists()) {
            folder.mkdirs();
        }
        String className = tableInfo.getBeanName() + Constants.SUFFIX_BEAN_QUERY;
        File file = new File(folder, className + ".java");
        OutputStream out = null;
        OutputStreamWriter osw = null;
        BufferedWriter bw = null;
        try {
            out = new FileOutputStream(file);
            osw = new OutputStreamWriter(out, "UTF-8");
            bw = new BufferedWriter(osw);
            bw.write("package " + Constants.PACKAGE_QUERY + ";");
            bw.newLine();
            bw.newLine();
            if (tableInfo.getHaveBigDecimal()) {
                bw.write("import java.math.BigDecimal;");
                bw.newLine();
            }
            if (tableInfo.getHaveDate() || tableInfo.getHaveDateTime()) {
                bw.write("import java.util.Date;");
                bw.newLine();
            }

            bw.newLine();
            // 构建类注释
            BuildComment.createClassComment(bw, tableInfo.getComment() + "查询对象");
            bw.write("public class " + className + " extends BaseQuery {");
            bw.newLine();

            for (FieldInfo field : tableInfo.getFieldList()) {
                BuildComment.createFieldComment(bw, field.getComment());
                bw.write("\tprivate " + field.getJavaType() + " " + field.getPropertyName() + ";");
                bw.newLine();
                bw.newLine();
                // String类型的参数
                if (ArrayUtils.contains(Constants.SQL_STRING_TYPES, field.getSqlType())) {
                    String propertyName = field.getPropertyName() + Constants.SUFFIX_BEAN_QUERY_FUZZY;
                    bw.write("\tprivate " + field.getJavaType() + " " + propertyName + ";");
                    bw.newLine();
                    bw.newLine();

                }
                if (ArrayUtils.contains(Constants.SQL_DATE_TIME_TYPES, field.getSqlType()) || ArrayUtils.contains(Constants.SQL_DATE_TYPES, field.getSqlType())) {
                    bw.write("\tprivate String " + field.getPropertyName() + Constants.SUFFIX_BEAN_QUERY_TIME_START + ";");
                    bw.newLine();
                    bw.newLine();
                    bw.write("\tprivate String " + field.getPropertyName() + Constants.SUFFIX_BEAN_QUERY_TIME_END + ";");
                    bw.newLine();
                    bw.newLine();

                }
            }

            buildGetSet(bw, tableInfo.getFieldList());
            buildGetSet(bw, tableInfo.getFieldExtendList());

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

    private static void buildGetSet(BufferedWriter bw, List<FieldInfo> fieldInfoList) throws Exception {
        for (FieldInfo field : fieldInfoList) {
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

    }
}
