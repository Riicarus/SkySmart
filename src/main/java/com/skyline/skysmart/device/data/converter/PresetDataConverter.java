package com.skyline.skysmart.device.data.converter;

import com.skyline.skysmart.device.data.bo.impls.PresetBO;
import com.skyline.skysmart.device.data.bo.interfaces.IPresetBO;
import com.skyline.skysmart.device.data.dao.PresetDAO;
import org.springframework.stereotype.Component;

/**
 * [FEATURE INFO]<br/>
 * preset data converter
 *
 * @author Skyline
 * @create 2022/6/17 16:09
 * @since 1.0.0
 */
@Component
public class PresetDataConverter {

    private static final PresetDataConverter INSTANCE = new PresetDataConverter();

    private PresetDataConverter() {}

    public static PresetDataConverter getConverter() {
        return INSTANCE;
    }

    /**
     * construct IPresetBO from PresetDAO
     *
     * @param presetDAO PresetDAO
     * @return IPresetBO
     */
    public IPresetBO constructPresetBO(PresetDAO presetDAO) {
        IPresetBO presetBO = new PresetBO();
        presetBO.mapPresetDAO(presetDAO);
        presetBO.mapProperties(presetDAO.getProperties());
        return presetBO;
    }



}
