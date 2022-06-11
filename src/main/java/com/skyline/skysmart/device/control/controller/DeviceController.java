package com.skyline.skysmart.device.control.controller;

/**
 * [FEATURE INFO]<br/>
 * abstract device controller
 *
 * @author Skyline
 * @create 2022/6/10 17:57
 * @since 1.0.0
 */
public abstract class DeviceController {

    /**
     * execute method according to instruction
     *
     * @param instruction String
     */
    public abstract void execute(String instruction);

}
