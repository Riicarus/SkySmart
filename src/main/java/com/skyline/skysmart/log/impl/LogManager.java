package com.skyline.skysmart.log.impl;

import com.alibaba.fastjson.JSON;
import com.skyline.skysmart.log.ILogManager;
import org.springframework.stereotype.Service;

/**
 * [FEATURE INFO]<br/>
 * log manager
 *
 * @author Skyline
 * @create 2022/7/26 21:53
 * @since 1.0.0
 */
@Service
public class LogManager implements ILogManager {
    @Override
    public void logInfo(Object object) {
        System.out.println(JSON.toJSONString(object));
    }

    @Override
    public void logWarn(Object object) {

    }

    @Override
    public void logDebug(Object object) {

    }

    @Override
    public void logError(Object object) {

    }
}
