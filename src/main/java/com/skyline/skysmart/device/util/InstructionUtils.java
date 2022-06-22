package com.skyline.skysmart.device.util;

import com.skyline.skysmart.core.enums.ResultCode;
import com.skyline.skysmart.core.exception.Asserts;
import com.skyline.skysmart.device.data.dto.InstructionUnit;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * [FEATURE INFO]<br/>
 * Generate device instruction which is used to provide a way to control device method invoking.
 *
 * @author Skyline
 * @create 2022/6/10 23:35
 * @since 1.0.0
 */
public class InstructionUtils {

    public static final String UNIT_SEPARATOR = "&";
    public static final String K_V_LINKER = "=";
    public static final String PARAM_SEPARATOR = ",";

    /**
     * generate device instruction
     *
     * @param deviceId String, unique id of device
     * @param unitQueue Queue, queue of instruction units
     * @return String, instruction
     */
    public static String generate(String deviceId, Queue<InstructionUnit> unitQueue) {
        StringBuilder sb = new StringBuilder();

        sb.append(deviceId);

        for (InstructionUnit instructionUnit : unitQueue) {

            sb.append(UNIT_SEPARATOR).append(instructionUnit.getAction()).append(K_V_LINKER);

            List<String> params = instructionUnit.getParams();
            if (params == null || params.isEmpty()) {
                Asserts.fail(ResultCode.FAILED);
            }

            for (int i = 0; i < params.size(); i++) {
                String param = params.get(i);

                if (i == 0) {
                    sb.append(param);
                } else {
                    sb.append(PARAM_SEPARATOR).append(param);
                }
            }
        }


        return sb.toString();
    }

    /**
     * parse sub instruction to Queue of unit
     *
     * @param instruction String, sub instruction of instruct sent to device controller, which contains instruction units
     * @return Queue, unit queue
     */
    public static Queue<InstructionUnit> parse(String instruction) {
        Queue<InstructionUnit> unitQueue = new LinkedList<>();

        String[] unitStrs = instruction.split(UNIT_SEPARATOR);
        for (String unitStr : unitStrs) {
            // "K=V" ==> [Action, Params]
            String[] k_v_parts = unitStr.split(K_V_LINKER);
            String action = k_v_parts[0];
            // Params => [param_1, param_2, ...]
            String[] params = k_v_parts[1].split(PARAM_SEPARATOR);

            InstructionUnit unit = new InstructionUnit(action, Arrays.asList(params));
            unitQueue.add(unit);
        }

        return unitQueue;
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
        String[] strs = instruction.split(UNIT_SEPARATOR);
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
        String[] strs = instruction.split(UNIT_SEPARATOR);
        return instruction.substring(strs[0].length() + 1);
    }

}
