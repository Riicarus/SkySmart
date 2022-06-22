package com.skyline.skysmart.core.controller;

import com.skyline.skysmart.auth.shiro.AssertPermission;
import com.skyline.skysmart.core.enums.RedisKeyPrefix;
import com.skyline.skysmart.core.response.ResponseResult;
import com.skyline.skysmart.core.service.interfaces.IMailService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * [FEATURE INFO]<br/>
 * Controller request of verification.<br/>
 * Here is suggest to use Kafka to send verification code.
 *
 * @author Skyline
 * @create 2022/6/16 15:20
 * @since 1.0.0
 */
@Api(tags = {"VerificationController"})
@RestController
public class VerificationController {

    private IMailService mailService;
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    public void setMailService(IMailService mailService) {
        this.mailService = mailService;
    }

    @Autowired
    public void setRedisTemplate(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @ApiOperation("send email binding verification code")
    @GetMapping("/verification/emailBinding")
    public ResponseResult<String> getBindEmailVeryCode(
            @RequestParam("uid") String uid,
            @RequestParam("email") String email
    ) {
        AssertPermission.single("user:*:" + uid);

        String code = getCode();
        String title = "Email Binding Verification";
        String content = "<h2>Thanks for using SkySmart</h2>" +
                "<p>This is your verification code: </p><span>" + code +
                "</span>";

        mailService.sendSimpleMail(email, title, content);

        // store to redis
        String prefix = RedisKeyPrefix.BIND_EMAIL_VERYCODE.getKeyPrefix();
        String key = prefix + uid;
        redisTemplate.opsForValue().set(key, code, 10, TimeUnit.MINUTES);

        return ResponseResult.success("Send verification succeeded!");
    }

    @ApiOperation("send reset password verification code")
    @GetMapping("/verification/passChange")
    public ResponseResult<String> getChangeSecondPassVeryCode(
            @RequestParam("uid") String uid,
            @RequestParam("email") String email
    ) {
        String code = getCode();
        String title = "Change Password Verification";
        String content = "<h2>Thanks for using SkySource</h2>" +
                "<p>This is your verification code: </p><span>" + code +
                "</span>";

        mailService.sendSimpleMail(email, title, content);

        // store to redis
        String prefix = RedisKeyPrefix.SET_PASS_VERYCODE.getKeyPrefix();
        String key = prefix + uid;
        redisTemplate.opsForValue().set(key, code, 10, TimeUnit.MINUTES);

        return ResponseResult.success("Send verification succeeded!");
    }

    @ApiOperation("send reset password verification code")
    @GetMapping("/verification/emailLogin")
    public ResponseResult<String> getEmailLoginVeryCode(
            @RequestParam("email") String email
    ) {
        String code = getCode();
        String title = "Email Login Verification";
        String content = "<h2>Thanks for using SkySource</h2>" +
                "<p>This is your verification code: </p><span>" + code +
                "</span>";

        mailService.sendSimpleMail(email, title, content);

        // store to redis
        String prefix = RedisKeyPrefix.EMAIL_LOGIN_VERYCODE.getKeyPrefix();
        String key = prefix + email;
        redisTemplate.opsForValue().set(key, code, 10, TimeUnit.MINUTES);

        return ResponseResult.success("Send verification succeeded!");
    }

    /**
     * generate verification code with 6 bytes
     * @return String
     */
    private String getCode() {
        String numbers = "1234567890";
        StringBuilder builder = new StringBuilder();
        Random random = new Random();
        for(int i = 0; i < 6; i++) {
            int index = random.nextInt(10);
            builder.append(numbers.charAt(index));
        }
        return builder.toString();
    }

}
