package com.skyline.skysmart.device.data.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;

/**
 * [FEATURE INFO]<br/>
 * DeviceControlRequest
 *
 * @author Skyline
 * @create 2022/6/19 12:13
 * @since 1.0.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeviceControlRequest {

    private String url;
    private HashMap<String, String> params = new HashMap<>();

    public void setInstruction(String instruction) {
        this.params.put("instruction", instruction);
    }

}
