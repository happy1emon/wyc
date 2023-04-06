package com.xg.serviceorder.mapper;

import com.xg.internalcommon.dto.OrderInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author junxuan
* @description 针对表【order_info】的数据库操作Mapper
* @createDate 2023-04-06 13:35:18
* @Entity com.xg.internalcommon.dto.OrderInfo
*/
@Mapper
public interface OrderInfoMapper extends BaseMapper<OrderInfo> {

}




