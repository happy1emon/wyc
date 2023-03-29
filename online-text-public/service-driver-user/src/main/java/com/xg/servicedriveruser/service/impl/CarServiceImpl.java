package service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import .dto.Car;
import service.CarService;
import mapper.CarMapper;
import org.springframework.stereotype.Service;

/**
* @author junxuan
* @description 针对表【car】的数据库操作Service实现
* @createDate 2023-03-29 15:37:33
*/
@Service
public class CarServiceImpl extends ServiceImpl<CarMapper, Car>
    implements CarService{

}




