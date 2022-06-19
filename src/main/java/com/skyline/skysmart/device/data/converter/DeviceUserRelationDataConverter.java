package com.skyline.skysmart.device.data.converter;

import com.skyline.skysmart.device.data.bo.impls.DeviceBO;
import com.skyline.skysmart.device.data.bo.impls.DeviceUserRelationBO;
import com.skyline.skysmart.device.data.bo.interfaces.IDeviceUserRelationBO;
import com.skyline.skysmart.device.data.bo.interfaces.IPresetBO;
import com.skyline.skysmart.device.data.dao.DeviceUserRelationDAO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * [FEATURE INFO]<br/>
 * device user relation data converter
 *
 * @author Skyline
 * @create 2022/6/17 16:09
 * @since 1.0.0
 */
@Component
public class DeviceUserRelationDataConverter {

    private static final DeviceUserRelationDataConverter INSTANCE = new DeviceUserRelationDataConverter();

    private DeviceUserRelationDataConverter() {}

    public static DeviceUserRelationDataConverter getConverter() {
        return INSTANCE;
    }

    /**
     * construct IDeviceUserRelationDAO from DeviceUserRelationDAO, DeviceBO, ArrayList of PresetBO
     *
     * @param deviceUserRelationDAO DeviceUserRelationDAO
     * @param deviceBO DeviceBO
     * @param presetBOList ArrayList of PresetBO
     * @return IDeviceUserRelationBO
     */
    public IDeviceUserRelationBO constructDeviceUserRelationBO(DeviceUserRelationDAO deviceUserRelationDAO, DeviceBO deviceBO, ArrayList<IPresetBO> presetBOList) {
        IDeviceUserRelationBO deviceUserRelationBO = new DeviceUserRelationBO();
        deviceUserRelationBO.mapDeviceUserRelationDAO(deviceUserRelationDAO);
        deviceUserRelationBO.setDeviceInfo(deviceBO);

        HashMap<String, HashMap<String, String>> presetMap = new HashMap<>();
        for (IPresetBO presetBO : presetBOList) {
            presetMap.put(presetBO.getPresetName(), presetBO.getProperties());
        }
        deviceUserRelationBO.setDevicePreset(presetMap);

        return deviceUserRelationBO;
    }

}
