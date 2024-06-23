package com.roc.admin.backend.aop.log;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.roc.admin.backend.dao.entity.RbacUser;
import com.roc.admin.backend.dao.service.IRbacUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @Description
 * @Author: Zhang Peng
 * @Date: 2024/6/21
 */
@Component
public class OperationRecordFunc {
    @Autowired
    private IRbacUserService userService;

    @Autowired
    private static IRbacUserService staticUserService;

    @PostConstruct
    public void init() {
        staticUserService = userService;
    }

    //spel self-design method must be static
    public static String test(String input) {
        return "test-" + input;
    }

    public static RbacUser getUser(String email) {
        LambdaQueryWrapper<RbacUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(RbacUser::getUserEmail, email);
        RbacUser userFromDb = staticUserService.getOne(queryWrapper, false);
        return userFromDb;

    }
}
