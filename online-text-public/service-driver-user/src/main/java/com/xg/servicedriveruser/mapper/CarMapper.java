package com.xg.servicedriveruser.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xg.internalcommon.dto.Car;
import org.apache.ibatis.annotations.Mapper;

/**
* @author junxuan
* @description 针对表【car】的数据库操作Mapper
* @createDate 2023-03-29 15:37:33
* @Entity .dto.Car
*/
@Mapper
public interface CarMapper extends BaseMapper<Car> {

}




