package com.roc.admin.backend.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.roc.admin.backend.aop.log.OperationRecord;
import com.roc.admin.backend.constant.ResponseCode;
import com.roc.admin.backend.constant.ResponseData;
import com.roc.admin.backend.dao.entity.RbacUser;
import com.roc.admin.backend.dao.service.IRbacUserService;
import com.roc.admin.backend.utils.JWTUtils;
import lombok.extern.slf4j.Slf4j;
import org.omg.CORBA.Object;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @Description
 * @Author: Zhang Peng
 * @Date: 2024/5/12
 */
@Slf4j
@RestController
@RequestMapping("/login")
public class LoginController {

    @Resource
    private IRbacUserService userService;


    /**
     * SpEL tips:
     * if there are more than one variable, use "+" split them, otherwise spel cannot parse
     * e.g. func = "#test(" + "#user.getUserEmail)", the spel parse it as "test("app@gmail.com")
     * if you want to add some string, the string should be surrounded by ''
     * e.g. value = "'user:'+#user.getUserEmail", the spel parse it as "user:app@gmail.com
     */
    @OperationRecord(value = "'user:'+#user.getUserEmail", func = "#getUser(" + "#user.getUserEmail)")
    @PostMapping(value = "/sessionDemo")
    public ResponseData<Object> sessionLogin(HttpServletRequest request, @RequestBody RbacUser user) {
        LambdaQueryWrapper<RbacUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(RbacUser::getUserEmail, user.getUserEmail());
        RbacUser userFromDb = userService.getOne(queryWrapper, true);

        if (userFromDb != null && userFromDb.getUserPasswd().equals(user.getUserPasswd())) {
            request.getSession().setAttribute("user", user);
            return ResponseData.success();
        } else {
            return ResponseData.fail(ResponseCode.FAIL);
        }
    }

    @PostMapping(value = "/tokenDemo")
    public ResponseData<String> tokenLogin(@RequestBody RbacUser user) {
        LambdaQueryWrapper<RbacUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(RbacUser::getUserEmail, user.getUserEmail());
        RbacUser userFromDb = userService.getOne(queryWrapper, true);

        if (userFromDb != null && userFromDb.getUserPasswd().equals(user.getUserPasswd())) {
            String token = JWTUtils.getToken(String.valueOf(userFromDb.getUserEmail()));
            return ResponseData.success(token);
        } else {
            return ResponseData.fail(ResponseCode.FAIL);
        }
    }


    @PostMapping(value = "/json")
    public String test(@RequestBody String json) {
        log.info(json);
        return json;
    }


    @PostMapping(value = "/logout")
    public String logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return "log out";
    }
}
