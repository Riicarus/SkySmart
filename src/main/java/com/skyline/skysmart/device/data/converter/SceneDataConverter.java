package com.skyline.skysmart.device.data.converter;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.skyline.skysmart.core.enums.ResultCode;
import com.skyline.skysmart.core.exception.Asserts;
import com.skyline.skysmart.device.data.bo.impls.SceneBO;
import com.skyline.skysmart.device.data.bo.interfaces.ISceneBO;
import com.skyline.skysmart.device.data.dao.SceneDAO;
import com.skyline.skysmart.device.data.dto.InstructionUnit;
import com.skyline.skysmart.device.data.dto.SceneAddParam;
import com.skyline.skysmart.device.util.InstructionUtils;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * [FEATURE INFO]<br/>
 * scene data converter
 *
 * @author Skyline
 * @create 2022/6/22 20:39
 * @since 1.0.0
 */
@Component
public class SceneDataConverter {

    private static final SceneDataConverter INSTANCE = new SceneDataConverter();

    private SceneDataConverter() {}

    public static SceneDataConverter getConverter() {
        return INSTANCE;
    }

    /**
     * construct SceneDAO from SceneAddParam
     *
     * @param sceneAddParam SceneAddParam
     * @return SceneDAO
     */
    private SceneDAO constructSceneDAO(SceneAddParam sceneAddParam) {
        if (sceneAddParam == null) {
            return new SceneDAO();
        }

        SceneDAO sceneDAO = new SceneDAO();
        sceneDAO.setUuid(UUID.randomUUID().toString());
        sceneDAO.setUid(sceneAddParam.getUid());
        sceneDAO.setName(sceneAddParam.getName());
        sceneDAO.setActive(true);

        HashMap<String, Queue<InstructionUnit>> rawDeviceInstructionMap = JSONObject.parseObject(sceneAddParam.getInstructionsJson().toString(), new TypeReference<HashMap<String, Queue<InstructionUnit>>>(){});

        Queue<String> instructionQueue = new LinkedList<>();
        for (Map.Entry<String, Queue<InstructionUnit>> entry : rawDeviceInstructionMap.entrySet()) {
            String instruction = InstructionUtils.generate(entry.getKey(), entry.getValue());
            instructionQueue.add(instruction);
        }

        sceneDAO.setInstructionsJson(JSONObject.toJSONString(instructionQueue));

        return sceneDAO;
    }

    /**
     * construct ISceneBO from SceneDAO
     *
     * @param sceneDAO SceneDAO
     * @return ISceneBO
     */
    public ISceneBO constructSceneBO(SceneDAO sceneDAO) {
        ISceneBO sceneBO = new SceneBO();
        sceneBO.mapperSceneDAO(sceneDAO);
        sceneBO.mapInstructions(sceneDAO.getInstructionsJson());
        return sceneBO;
    }

    /**
     * construct ISceneBO from SceneAddParam
     *
     * @param sceneAddParam SceneAddParam
     * @return ISceneBO
     */
    public ISceneBO constructSceneBO(SceneAddParam sceneAddParam) {
        ISceneBO sceneBO = new SceneBO();
        SceneDAO sceneDAO = constructSceneDAO(sceneAddParam);

        return constructSceneBO(sceneDAO);
    }

}
