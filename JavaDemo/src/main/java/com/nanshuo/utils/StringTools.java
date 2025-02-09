package com.nanshuo.utils;

import com.nanshuo.exception.BusinessException;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 字符串工具
 *
 * @author nanshuo
 * @date 2023/11/15 22:37
 */
public class StringTools {

    /**
     * 检查参数
     *
     * @param param 参数
     * @throws BusinessException 业务异常
     */
    public static void checkParam(Object param) throws BusinessException {
        try {
            Field[] fields = param.getClass().getDeclaredFields();
            boolean notEmpty = false;
            for (Field field : fields) {
                String methodName = "get" + StringTools.upperCaseFirstLetter(field.getName());
                Method method = param.getClass().getMethod(methodName);
                Object invoke = method.invoke(param);
                if (invoke instanceof String && !StringTools.isEmpty(invoke.toString()) || invoke != null && !(invoke instanceof String)) {
                    notEmpty = true;
                    break;
                }
            }
            if (!notEmpty) {
                throw new BusinessException("多参数更新、删除,必须有非空条件");
            }

        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException("校验参数是否为空失败");
        }
    }

    /**
     * 是否为空
     *
     * @param str str
     * @return boolean
     */
    public static boolean isEmpty(String str) {
        if (null == str || str.isEmpty() || "null".equals(str) || "\u0000".equals(str)) {
            return true;
        } else return str.trim().isEmpty();
    }

    /**
     * 首字母大写
     *
     * @param field
     * @return {@code String}
     */
    public static String upperCaseFirstLetter(String field) {
        if (isEmpty(field)) {
            return field;
        }
        // 如果第一个字母是大写,第二个字母不是大写
        if (field.length() > 1 && Character.isUpperCase(field.charAt(1))) {
            return field;
        }
        return field.substring(0, 1).toUpperCase() + field.substring(1);
    }
}
