package com.skyline.skysmart.auth.shiro;

import com.skyline.skysmart.core.enums.ResultCode;
import com.skyline.skysmart.core.exception.Asserts;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

/**
 * [FEATURE INFO]<br/>
 * Authentication
 *
 * @author Skyline
 * @create 2022/6/10 16:06
 * @since 1.0.0
 */
public class AssertPermission {

    public static void single(String permission) {
        Subject subject = SecurityUtils.getSubject();
        if (!subject.isPermitted(permission)) {
            Asserts.fail(ResultCode.NO_PERMISSION);
        }
    }

    public static void multiOr(String[] permissions) {
        Subject subject = SecurityUtils.getSubject();
        boolean[] hasPermissions = subject.isPermitted(permissions);
        boolean hasPermission = false;
        for (boolean b : hasPermissions) {
            if (b) {
                hasPermission = true;
                break;
            }
        }
        if (!hasPermission) {
            Asserts.fail(ResultCode.NO_PERMISSION);
        }
    }

    public static void multiAnd(String[] permissions) {
        Subject subject = SecurityUtils.getSubject();
        boolean[] hasPermissions = subject.isPermitted(permissions);
        boolean hasPermission = true;
        for (boolean b : hasPermissions) {
            if (!b) {
                hasPermission = false;
                break;
            }
        }
        if (!hasPermission) {
            Asserts.fail(ResultCode.NO_PERMISSION);
        }
    }

}
