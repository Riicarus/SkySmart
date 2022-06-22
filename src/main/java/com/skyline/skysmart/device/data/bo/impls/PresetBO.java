package com.skyline.skysmart.device.data.bo.impls;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.skyline.skysmart.core.enums.ResultCode;
import com.skyline.skysmart.core.exception.Asserts;
import com.skyline.skysmart.device.data.bo.interfaces.IPresetBO;
import com.skyline.skysmart.device.data.dao.PresetDAO;
import com.skyline.skysmart.device.data.dto.InstructionUnit;

import java.util.Queue;

/**
 * [FEATURE INFO]<br/>
 * PresetBO
 *
 * @author Skyline
 * @create 2022/6/17 12:05
 * @since 1.0.0
 */
public class PresetBO implements IPresetBO {

    private PresetDAO presetDAO;
    private Queue<InstructionUnit> instructionUnitQueue;

    /**
     * set preset dao
     *
     * @param presetDAO PresetDAO
     */
    @Override
    public void mapPresetDAO(PresetDAO presetDAO) {
        this.presetDAO = presetDAO;
    }

    /**
     * get preset dao
     *
     * @return PresetDAO
     */
    @Override
    public PresetDAO getPresetDAO() {
        assertPresetDAONotEmpty();
        return this.presetDAO;
    }

    /**
     * bind instructionUnitQueue of preset
     *
     * @param instructionUnitJson String
     */
    @Override
    public void mapInstructionUnitQueue(String instructionUnitJson) {
        this.instructionUnitQueue = JSONObject.parseObject(instructionUnitJson, new TypeReference<Queue<InstructionUnit>>(){});
    }

    /**
     * bind instructionUnitQueue of preset
     *
     * @param instructionUnitQueue Queue
     */
    @Override
    public void mapInstructionUnitQueue(Queue<InstructionUnit> instructionUnitQueue) {
        this.instructionUnitQueue = instructionUnitQueue;
    }

    /**
     * set PresetDAO's instructionUnitQueue String
     *
     * @param instructionUnitJson String
     */
    @Override
    public void setInstructionUnitJson(String instructionUnitJson) {
        assertPresetDAONotEmpty();
        this.presetDAO.setInstructionUnitJson(instructionUnitJson);
    }

    /**
     * set PresetDAO's instructionUnitQueue String
     *
     * @param instructionUnitQueue Queue
     */
    @Override
    public void setInstructionUnitJson(Queue<InstructionUnit> instructionUnitQueue) {
        assertPresetDAONotEmpty();
        String instructionUnitJson = JSONObject.toJSONString(instructionUnitQueue);
        this.presetDAO.setInstructionUnitJson(instructionUnitJson);
    }

    /**
     * get instructionUnitQueue of preset
     *
     * @return Queue, instructionUnitQueue
     */
    @Override
    public Queue<InstructionUnit> getInstructionUnitQueue() {
        assertInstructionUnitQueueNotEmpty();
        return this.instructionUnitQueue;
    }

    /**
     * set preset name
     *
     * @param name String
     */
    @Override
    public void setPresetName(String name) {
        assertPresetDAONotEmpty();
        this.presetDAO.setName(name);
    }

    /**
     * get preset name
     *
     * @return String
     */
    @Override
    public String getPresetName() {
        assertPresetDAONotEmpty();
        return presetDAO.getName();
    }

    /**
     * assert preset not empty
     */
    @Override
    public void assertPresetDAONotEmpty() {
        if (this.presetDAO == null) {
            Asserts.fail(ResultCode.NULL);
        }
    }

    /**
     * assert instructionUnitQueue not empty
     */
    @Override
    public void assertInstructionUnitQueueNotEmpty() {
        if (instructionUnitQueue == null || instructionUnitQueue.isEmpty()) {
            Asserts.fail(ResultCode.NULL);
        }
    }
}
