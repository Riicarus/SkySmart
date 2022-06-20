package com.skyline.skysmart.device.service.interfaces;

import com.skyline.skysmart.device.data.bo.interfaces.IPresetBO;

/**
 * [FEATURE INFO]<br/>
 * preset service interface
 *
 * @author Skyline
 * @create 2022/6/17 16:07
 * @since 1.0.0
 */
public interface IPresetService {

    /**
     * cache default presets to redis when project load
     */
    void cacheDefaultPresets();

    /**
     * get default preset from redis by preset name
     *
     * @param presetName String
     * @return IPresetBO
     */
    IPresetBO getDefaultPreset(String presetName);

}
