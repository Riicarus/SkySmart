package com.skyline.skysmart.device.data.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * [FEATURE INFO]<br/>
 * the minim unit of instruction's param
 *
 * @author Skyline
 * @create 2022/6/22 21:39
 * @since 1.0.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InstructionUnit {

    private String action;

    private List<String> params;
}
