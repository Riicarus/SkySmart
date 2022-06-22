package com.skyline.skysmart.device.service.impls;

import com.skyline.skysmart.device.control.DeviceControlCenter;
import com.skyline.skysmart.device.data.dto.DeviceInternetInfo;
import com.skyline.skysmart.device.service.interfaces.IDeviceControlService;
import com.skyline.skysmart.device.util.InstructionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * [FEATURE INFO]<br/>
 * device control service
 *
 * @author Skyline
 * @create 2022/6/19 23:31
 * @since 1.0.0
 */
@Service
public class DeviceControlService implements IDeviceControlService {

    private DeviceControlCenter deviceControlCenter;

    @Autowired
    public void setDeviceControlCenter(DeviceControlCenter deviceControlCenter) {
        this.deviceControlCenter = deviceControlCenter;
    }

    /**
     * register device, save device's internet information to redis
     *
     * @param info DeviceInternetInfo
     */
    @Override
    public void register(DeviceInternetInfo info) {
        deviceControlCenter.register(info);
    }

    /**
     * turn on service to work status at the setting time
     *
     * @param deviceId String
     * @param time     Long, timestamp
     */
    @Override
    public void turnOn(String deviceId, Long time) {
        time = (time == null) ? 0 : time;

        HashMap<String, String> params = new HashMap<>();
        params.put("on", time.toString());

        String instruction = InstructionUtils.generate(deviceId, params);
        deviceControlCenter.doDispatch(instruction);
    }

    /**
     * turn off service to sleep status at the setting time
     *
     * @param deviceId String
     * @param time     Long, timestamp
     */
    @Override
    public void turnOff(String deviceId, Long time) {
        time = (time == null) ? 0 : time;

        HashMap<String, String> params = new HashMap<>();
        params.put("off", time.toString());

        String instruction = InstructionUtils.generate(deviceId, params);
        deviceControlCenter.doDispatch(instruction);
    }

    /**
     * handle multi instructions
     *
     * @param deviceId String
     * @param params   HashMap, contains operations and their param
     */
    @Override
    public void handleMulti(String deviceId, HashMap<String, String> params) {
        String instruction = InstructionUtils.generate(deviceId, params);
        deviceControlCenter.doDispatch(instruction);
    }
}
