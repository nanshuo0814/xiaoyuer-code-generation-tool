package com.nanshuo.builder;

import com.nanshuo.bean.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class BuildBase {

    private static final Logger logger = LoggerFactory.getLogger(BuildBase.class);

    public static void execute() {
        List<String> headerInfoList = new ArrayList<String>();
        //生成Date枚举
        headerInfoList.add("package " + Constants.PACKAGE_ENUMS);
        build(headerInfoList, "DateTimePatternEnums", Constants.PATH_ENUMS);

        headerInfoList.clear();
        headerInfoList.add("package " + Constants.PACKAGE_UTILS);
        build(headerInfoList, "DateUtils", Constants.PATH_UTILS);
        //生成baseMapper
        headerInfoList.clear();
        headerInfoList.add("package " + Constants.PACKAGE_MAPPERS);
        build(headerInfoList, "BaseMapper", Constants.PATH_MAPPERS);

        //生成PageSize枚举
        headerInfoList.clear();
        headerInfoList.add("package " + Constants.PACKAGE_ENUMS);
        build(headerInfoList, "PageSize", Constants.PATH_ENUMS);

        //生成SimplePage枚举
        headerInfoList.clear();
        headerInfoList.add("package " + Constants.PACKAGE_QUERY);
        headerInfoList.add("import " + Constants.PACKAGE_ENUMS + "." + "PageSize");
        build(headerInfoList, "SimplePage", Constants.PATH_QUERY);

        //生成baseQuery枚举
        headerInfoList.clear();
        headerInfoList.add("package " + Constants.PACKAGE_QUERY);
        build(headerInfoList, "BaseQuery", Constants.PATH_QUERY);

        //生成PaginationResultVo枚举
        headerInfoList.clear();
        headerInfoList.add("package " + Constants.PACKAGE_VO);
        build(headerInfoList, "PaginationResultVO", Constants.PATH_VO);

        //生成BusinessException枚举
        headerInfoList.clear();
        headerInfoList.add("package " + Constants.PACKAGE_EXCEPTION);
        headerInfoList.add("import " + Constants.PACKAGE_ENUMS+ ".ResponseCodeEnum");
        build(headerInfoList, "BusinessException", Constants.PATH_EXCEPTION);

        //生成ResponseCodeEnum枚举
        headerInfoList.clear();
        headerInfoList.add("package " + Constants.PACKAGE_ENUMS);
        build(headerInfoList, "ResponseCodeEnum", Constants.PATH_ENUMS);

        //生成ABaseController枚举
        headerInfoList.clear();
        headerInfoList.add("package " + Constants.PACKAGE_CONTROLLER);
        headerInfoList.add("import " + Constants.PACKAGE_ENUMS + ".ResponseCodeEnum");
        headerInfoList.add("import " + Constants.PACKAGE_VO + ".ResponseVO");
        build(headerInfoList, "ABaseController", Constants.PATH_CONTROLLER);

        //生成ResponseVO枚举
        headerInfoList.clear();
        headerInfoList.add("package " + Constants.PACKAGE_VO);
        build(headerInfoList, "ResponseVO", Constants.PATH_VO);

        //生成AGlobalExceptionHandlerController枚举
        headerInfoList.clear();
        headerInfoList.add("package " + Constants.PACKAGE_CONTROLLER);
        headerInfoList.add("import " + Constants.PACKAGE_ENUMS + ".ResponseCodeEnum");
        headerInfoList.add("import " + Constants.PACKAGE_VO + ".ResponseVO");
        headerInfoList.add("import " + Constants.PACKAGE_EXCEPTION + ".BusinessException");
        build(headerInfoList, "AGlobalExceptionHandlerController", Constants.PATH_CONTROLLER);

        //生成StringTools枚举
        headerInfoList.clear();
        headerInfoList.add("package " + Constants.PACKAGE_UTILS);
        headerInfoList.add("import " + Constants.PACKAGE_EXCEPTION + ".BusinessException");
        build(headerInfoList, "StringTools", Constants.PATH_UTILS);

        //生成CreateImageCode(图片验证码工具类)
        headerInfoList.clear();
        headerInfoList.add("package " + Constants.PACKAGE_UTILS);
        build(headerInfoList, "CreateImageCode", Constants.PATH_UTILS);

    }

    private static void build(List<String> headerInfoList, String fileName, String outPutPath) {
        File folder = new File(outPutPath);
        if (!folder.exists()) {
            folder.mkdirs();
        }
        File file = new File(outPutPath, fileName + ".java");
        OutputStream out = null;
        OutputStreamWriter osw = null;
        BufferedWriter bw = null;

        InputStream is = null;
        InputStreamReader isr = null;
        BufferedReader br = null;
        try {
            out = new FileOutputStream(file);
            osw = new OutputStreamWriter(out, "utf-8");
            bw = new BufferedWriter(osw);
            is = BuildBase.class.getClassLoader().getResourceAsStream("template/" + fileName + ".txt");
            assert is != null;
            isr = new InputStreamReader(is, "utf-8");
            br = new BufferedReader(isr);
            for (String head : headerInfoList) {
                bw.write(head + ";");
                bw.newLine();
            }

            String lineInfo = null;
            while ((lineInfo = br.readLine()) != null) {
                bw.write(lineInfo);
                bw.newLine();
            }
            bw.flush();
        } catch (Exception e) {
            logger.error("生成基础类：{},失败：", fileName, e);
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
                if (isr != null) {
                    isr.close();
                }
                if (is != null) {
                    is.close();
                }
                if (bw != null) {
                    bw.close();
                }
                if (osw != null) {
                    osw.close();
                }
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
