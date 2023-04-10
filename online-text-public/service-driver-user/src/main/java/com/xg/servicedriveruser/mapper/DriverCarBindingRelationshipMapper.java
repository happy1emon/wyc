package com.xg.servicedriveruser.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xg.internalcommon.dto.DriverCarBindingRelationship;
import org.apache.ibatis.annotations.Mapper;

/**
* @author junxuan
* @description 针对表【driver_car_binding_relationship】的数据库操作Mapper
* @createDate 2023-03-29 17:08:54
* @Entity .dto.DriverCarBindingRelationship
*/
@Mapper
public interface DriverCarBindingRelationshipMapper extends BaseMapper<DriverCarBindingRelationship> {


    Integer selectWorkStatus(String cityCode);


}




