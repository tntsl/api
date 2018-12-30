package com.demo.api.common.util;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;

public class ValidResultUtils {

    private ValidResultUtils() {
        throw new IllegalStateException("ValidResultUtils class");
    }

    /**
     * 将hibernate validate校验结果转字符串
     *
     * @param result
     * @return
     */
    public static String resultsToString(BindingResult result) {
        if (result != null) {
            List<FieldError> errors = result.getFieldErrors();
            StringBuilder stringBuilder = new StringBuilder();
            for (FieldError error : errors) {
                stringBuilder.append(error.getField() + ":" + error.getDefaultMessage() + ";");
            }
            return stringBuilder.toString();
        }
        return "";
    }

    /**
     * 将hibernate validate 校验结果转json
     *
     * @param result
     * @return
     */
    public static String resultsToJson(BindingResult result) {
        if (result != null) {
            List<FieldError> errors = result.getFieldErrors();
            return GsonUtils.toJson(errors);
        }
        return "[]";
    }
}
