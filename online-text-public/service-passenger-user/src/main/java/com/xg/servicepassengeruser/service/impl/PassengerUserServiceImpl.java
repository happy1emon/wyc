package com.xg.servicepassengeruser.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xg.servicepassengeruser.domain.PassengerUser;
import com.xg.servicepassengeruser.service.PassengerUserService;
import com.xg.servicepassengeruser.mapper.PassengerUserMapper;
import org.springframework.stereotype.Service;

/**
* @author junxuan
* @description 针对表【passenger_user】的数据库操作Service实现
* @createDate 2023-03-06 15:22:07
*/
@Service
public class PassengerUserServiceImpl extends ServiceImpl<PassengerUserMapper, PassengerUser>
    implements PassengerUserService{

}




