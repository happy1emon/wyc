package com.xg.servicepassengeruser.service;

import com.xg.internalcommon.dto.ResponseResult;

public interface UserService {

    ResponseResult loginOrRegister(String passengerPhone);

    ResponseResult selectUserByPassengerPhone(String passengerPhone);
}
