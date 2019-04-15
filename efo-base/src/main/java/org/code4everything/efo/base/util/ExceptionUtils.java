package org.code4everything.efo.base.util;

import org.code4everything.boot.exception.BootException;
import org.code4everything.boot.exception.ExceptionFactory;
import org.code4everything.boot.exception.ExceptionThrower;
import org.code4everything.boot.message.VerifyCodeUtils;
import org.code4everything.efo.base.constant.ErrorCode;
import org.springframework.http.HttpStatus;

import java.util.regex.Pattern;

/**
 * @author pantao
 * @since 2019-04-15
 */
public class ExceptionUtils {

    private static final Pattern PASSWORD_PATTERN = Pattern.compile("^.{6,20}$");

    private static final Pattern USERNAME_PATTERN = Pattern.compile("^[a-zA-Z][a-zA-Z0-9]{3,9}$");

    private static final ExceptionThrower THROWER = ExceptionThrower.getInstance();

    private ExceptionUtils() {}

    public static void throwIf(boolean result, int code, String msg) {
        THROWER.throwIf(result, ExceptionFactory.exception(code, msg, HttpStatus.OK));
    }

    public static void throwIfFalse(boolean result, int code, String msg) {
        THROWER.throwIf(!result, ExceptionFactory.exception(code, msg, HttpStatus.OK));
    }

    public static <T extends BootException> void throwIf(boolean result, int code, String msg, Class<T> clazz) {
        THROWER.throwIf(result, ExceptionFactory.exception(code, msg, HttpStatus.OK, clazz));
    }

    public static void checkCode(String email, String code) {
        throwIfFalse(VerifyCodeUtils.validate(email, code, true), ErrorCode.CODE_ERROR, "验证码不正确");
    }

    public static void checkPassword(String password) {
        throwIfFalse(PASSWORD_PATTERN.matcher(password).matches(), ErrorCode.ILLEGAL_PASSWORD, "密码格式不合法");
    }

    public static void checkUsername(String username) {
        throwIfFalse(USERNAME_PATTERN.matcher(username).matches(), ErrorCode.ILLEGAL_USERNAME, "用户名格式不合法");
    }
}