package com.skyline.skysmart.device.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * [FEATURE INFO]<br/>
 * Light properties, different device has different properties<br/>
 * Use properties to get LightDAO's properties json String,
 * to adapt to unpredictable device's properties<br/>
 * When using related services, check whether the properties exists first<br/>
 *
 * @author Skyline
 * @create 2022/6/11 19:58
 * @since 1.0.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LightProperties {

    // device last start time, device should offer the start time when it is manually started
    private Long lastStartTime;

    // device last close time, device should offer the close time when it is manually closed
    private Long lastCloseTime;

    // device work time
    private Long workTime;

    // light color
    private String color;

    // next start time, need one timer on device to prepare start
    private Long startTime;

    // next close time
    private Long closeTime;

}
