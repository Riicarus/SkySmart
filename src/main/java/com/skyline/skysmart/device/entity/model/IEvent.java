package com.skyline.skysmart.device.entity.model;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * [FEATURE INFO]<br/>
 * event interface, some function queue in one device
 *
 * @author Skyline
 * @create 2022/7/26 11:02
 * @since 1.0.0
 */
public interface IEvent {

    /**
     * get event id
     *
     * @return String, event id
     */
    String getId();

    /**
     * get event detail description
     *
     * @return String, event detail
     */
    String getName();

    /**
     * get all functions in this event
     *
     * @return ArrayList, all functions of this event
     */
    ArrayList<IFunction> getFunctions();

    /**
     * functions cooperate as one event
     *
     * @param functions IFunction
     */
    void setFunctions(ArrayList<IFunction> functions);

    void addFunction(IFunction function);

    /**
     * may have some expanded value
     *
     * @return HashMap, expanded values
     */
    HashMap<String, String> getExpands();

    void setExpands(HashMap<String, String> expands);

}
