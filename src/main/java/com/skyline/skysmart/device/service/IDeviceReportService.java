package com.skyline.skysmart.device.service;

import com.skyline.skysmart.device.entity.message.IDeviceReportMessage;

/**
 * [FEATURE INFO]<br/>
 * device report service interface
 *
 * @author Skyline
 * @create 2022-8-4 21:43
 * @since 1.0.0
 */
public interface IDeviceReportService {

    /**
     * handle device report message
     *
     * @param deviceReportMessage IDeviceReportMessage
     */
    void handleReport(IDeviceReportMessage deviceReportMessage);

}
