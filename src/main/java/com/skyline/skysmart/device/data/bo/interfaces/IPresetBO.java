package com.skyline.skysmart.device.data.bo.interfaces;

import com.skyline.skysmart.device.data.dao.PresetDAO;
import com.skyline.skysmart.device.data.dto.InstructionUnit;

import java.util.LinkedHashMap;
import java.util.Queue;

/**
 * [FEATURE INFO]<br/>
 * preset bo interface
 *
 * @author Skyline
 * @create 2022/6/17 12:01
 * @since 1.0.0
 */
public interface IPresetBO {

    /**
     * set preset dao
     *
     * @param presetDAO PresetDAO
     */
    void mapPresetDAO(PresetDAO presetDAO);

    /**
     * get preset dao
     *
     * @return PresetDAO
     */
    PresetDAO getPresetDAO();

    /**
     * bind instructionUnitQueue of preset
     *
     * @param instructionUnitJson String
     */
    void mapInstructionUnitQueue(String instructionUnitJson);


    /**
     * bind instructionUnitQueue of preset
     *
     * @param instructionUnitQueue Queue
     */
    void mapInstructionUnitQueue(Queue<InstructionUnit> instructionUnitQueue);

    /**
     * set PresetDAO's instructionUnitQueue String
     *
     * @param instructionUnitJson String
     */
    void setInstructionUnitJson(String instructionUnitJson);

    /**
     * set PresetDAO's properties String
     *
     * @param instructionUnitQueue Queue
     */
    void setInstructionUnitJson(Queue<InstructionUnit> instructionUnitQueue);

    /**
     * get instructionUnitQueue of preset
     *
     * @return Queue, instructionUnitQueue
     */
    Queue<InstructionUnit> getInstructionUnitQueue();

    /**
     * set preset name
     *
     * @param name String
     */
    void setPresetName(String name);

    /**
     * get preset name
     *
     * @return String
     */
    String getPresetName();

    /**
     * assert preset not empty
     */
    void assertPresetDAONotEmpty();

    /**
     * assert properties not empty
     */
    void assertInstructionUnitQueueNotEmpty();
}
