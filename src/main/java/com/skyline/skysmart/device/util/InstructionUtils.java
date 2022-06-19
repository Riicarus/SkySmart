package com.skyline.skysmart.device.util;

import com.skyline.skysmart.core.enums.ResultCode;
import com.skyline.skysmart.core.exception.Asserts;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * [FEATURE INFO]<br/>
 * Generate device instruction which is used to provide a way to control device method invoking.
 *
 * @author Skyline
 * @create 2022/6/10 23:35
 * @since 1.0.0
 */
public class InstructionUtils {

    public static final String PARAM_SEPARATOR = "&";
    public static final String K_V_LINKER = "=";

    /**
     * generate device instruction
     *
     * @param deviceId String, unique id of device
     * @param params HashMap, param of key and value
     * @return String, instruction
     */
    public static String generate(String deviceId, HashMap<String, String> params) {
        StringBuilder sb = new StringBuilder();

        sb.append(deviceId);

        for (Map.Entry<String, String> entry : params.entrySet()) {
            sb.append(PARAM_SEPARATOR).append(entry.getKey()).append(K_V_LINKER).append(entry.getValue());
        }

        return sb.toString();
    }

    /**
     * parse sub instruction to HashMap params
     *
     * @param instruction String, sub instruction of instruct sent to device controller, which contains params
     * @return HashMap, params
     */
    public static LinkedHashMap<String, String> parse(String instruction) {
        LinkedHashMap<String, String> params = new LinkedHashMap<>();

        String[] strs = instruction.split(PARAM_SEPARATOR);
        for (String str : strs) {
            String[] parts = str.split(K_V_LINKER);
            params.put(parts[0], parts[1]);
        }

        return params;
    }

    /**
     * get deviceId from instruction
     *
     * @param instruction String
     * @return String, deviceId
     */
    public static String getDeviceId(String instruction) {
        if (!StringUtils.hasLength(instruction)) {
            Asserts.fail(ResultCode.NULL);
        }
        String[] strs = instruction.split(PARAM_SEPARATOR);
        return strs[0];
    }

    /**
     * get sub instruction from instruction
     *
     * @param instruction String
     * @return String, sub instruction
     */
    public static String getSubInstruction(String instruction) {
        if (!StringUtils.hasLength(instruction)) {
            Asserts.fail(ResultCode.NULL);
        }
        String[] strs = instruction.split(PARAM_SEPARATOR);
        return instruction.substring(strs[0].length() + 1);
    }

}
