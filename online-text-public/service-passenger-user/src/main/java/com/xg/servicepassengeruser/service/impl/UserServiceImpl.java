package com.xg.servicepassengeruser.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xg.internalcommon.constant.CommonStatusEnum;
import com.xg.internalcommon.dto.ResponseResult;
import com.xg.internalcommon.dto.PassengerUser;
import com.xg.servicepassengeruser.mapper.PassengerUserMapper;
import com.xg.servicepassengeruser.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private PassengerUserMapper passengerUserMapper;

    @Override
    public ResponseResult loginOrRegister(String passengerPhone) {
        System.out.println("user service");
        //根据手机号查询用户信息
        Map<String,Object> map=new HashMap<>();
        map.put("passenger_phone",passengerPhone);
        List<PassengerUser> passengerUsers=passengerUserMapper.selectByMap(map);
        //判断用户信息是否存在
        if (passengerUsers.size()==0){
            PassengerUser passengerUser=new PassengerUser();
            passengerUser.setPassengerName("张三");
            passengerUser.setPassengerPhone(passengerPhone);
            passengerUser.setPassengerGender(0);
            passengerUser.setState(0);
            LocalDateTime now= LocalDateTime.now();
            passengerUser.setGmtCreate(now);
            passengerUser.setGmtModified(now);
            passengerUserMapper.insert(passengerUser);
        }
        //如果不存在，插入用户信息

        return ResponseResult.success();
    }

    @Override
    public ResponseResult selectUserByPassengerPhone(String passengerPhone) {
        QueryWrapper<PassengerUser> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("passenger_phone",passengerPhone);
        List<PassengerUser> passengerUser = passengerUserMapper.selectList(queryWrapper);
        if (passengerUser.size()==0){
            return ResponseResult.fail(CommonStatusEnum.USER_NOT_EXISTS);
        }
        else {
            PassengerUser passengerUser1 = passengerUser.get(0);
            return ResponseResult.success(passengerUser1);
        }
    }
}
