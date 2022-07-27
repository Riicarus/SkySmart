package com.skyline.skysmart.log;

/**
 * [FEATURE INFO]<br/>
 * log manager interface
 *
 * @author Skyline
 * @create 2022/7/26 16:13
 * @since 1.0.0
 */
public interface ILogManager {

    void logInfo(Object object);

    void logWarn(Object object);

    void logDebug(Object object);

    void logError(Object object);

}
