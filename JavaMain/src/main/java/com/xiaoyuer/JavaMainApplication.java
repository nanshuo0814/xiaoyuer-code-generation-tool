package com.xiaoyuer;

import com.xiaoyuer.bean.TableInfo;
import com.xiaoyuer.builder.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class JavaMainApplication {
    private static final Logger logger = LoggerFactory.getLogger(BuildTable.class);
    public static void main(String[] args) {
        logger.info("开始生成代码,start...");
        BuildBase.execute();
        List<TableInfo> tableInfoList = BuildTable.getTables();
        for (TableInfo tableInfo : tableInfoList) {
            BuildPo.execute(tableInfo);
            BuildQuery.execute(tableInfo);
            BuildMapper.execute(tableInfo);
            BuildMapperXml.execute(tableInfo);
            BuildService.execute(tableInfo);
            BuildServiceImpl.execute(tableInfo);
            BuildController.execute(tableInfo);
        }
        logger.info("生成代码完成,end...");
    }
}
