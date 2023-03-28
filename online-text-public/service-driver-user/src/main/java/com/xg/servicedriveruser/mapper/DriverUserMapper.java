package com.xg.servicedriveruser.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xg.internalcommon.dto.DriverUser;
import org.apache.ibatis.annotations.Mapper;

/**
* @author junxuan
* @description 针对表【driver_user】的数据库操作Mapper
* @createDate 2023-03-28 12:26:09
* @Entity .dto.DriverUser
*/
@Mapper
public interface DriverUserMapper extends BaseMapper<DriverUser> {

}




