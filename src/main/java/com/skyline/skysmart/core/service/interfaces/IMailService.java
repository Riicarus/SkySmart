package com.skyline.skysmart.core.service.interfaces;

/**
 * [FEATURE INFO]<br/>
 * Mail service interface
 *
 * @author Skyline
 * @create 2022/6/16 15:25
 * @since 1.0.0
 */
public interface IMailService {

    /**
     * send simple email
     *
     * @param receiver String, receiver's email
     * @param title    String, email title
     * @param content  String, email content
     */
    void sendSimpleMail(String receiver, String title, String content);

}
