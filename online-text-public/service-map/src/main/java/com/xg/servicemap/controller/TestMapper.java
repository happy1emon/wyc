package com.xg.servicemap.controller;

import com.xg.internalcommon.dto.DictDistrict;
import com.xg.servicemap.mapper.DictDistrictMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class TestMapper {

    @Autowired
    private DictDistrictMapper dictDistrictMapper;

    @GetMapping("/test")
    public String test(){return "service map";}


    @GetMapping("/test-map")
    public String testMap(){
        Map<String,Object> map=new HashMap<>();
        map.put("address_code","110000");
        List<DictDistrict> dictDistricts = dictDistrictMapper.selectByMap(map);
        System.out.println(dictDistricts);
        return "test Mapper";
    }
}
