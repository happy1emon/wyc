package com.xg.servicemap.service.impl;

import com.xg.internalcommon.constant.AmapConfigConstants;
import com.xg.internalcommon.constant.CommonStatusEnum;
import com.xg.internalcommon.dto.DictDistrict;
import com.xg.internalcommon.dto.ResponseResult;
import com.xg.servicemap.mapper.DictDistrictMapper;
import com.xg.servicemap.remote.MapDicDistrictClient;
import com.xg.servicemap.remote.MapDirectionClient;
import com.xg.servicemap.service.DictDistrictService;
import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.security.auth.kerberos.KerberosKey;

@Service
public class DictDistrictServiceImpl implements DictDistrictService {

    @Autowired
    private MapDicDistrictClient mapDicDistrictClient;

    @Autowired
    private DictDistrictMapper districtMapper;


    @Override
    public ResponseResult initDictDistrict(String keywords) {
        //请求地图
        String dictDistrict = mapDicDistrictClient.DictDistrict(keywords);
        System.out.println(dictDistrict);
        //解析结果
        JSONObject disDistrictJsonObject = JSONObject.fromObject(dictDistrict);
        int status = disDistrictJsonObject.getInt(AmapConfigConstants.STATUS);
        if (status != 1) {
            return ResponseResult.fail(CommonStatusEnum.MAP_DISTRICT_ERRO.getCode(), CommonStatusEnum.MAP_DISTRICT_ERRO.getValue());
        }
        JSONArray districtJsonArray = disDistrictJsonObject.getJSONArray(AmapConfigConstants.DISTRICTS);
        for (int i = 0; i < districtJsonArray.size(); i++) {
            JSONObject districtJsonObject = districtJsonArray.getJSONObject(i);
            String addressCode = districtJsonObject.getString(AmapConfigConstants.ADCODE);
            String addressName = districtJsonObject.getString(AmapConfigConstants.NAME);
            String parentAddressCode = "0";
            String level = districtJsonObject.getString(AmapConfigConstants.LEVEL);
            insertDicDistrict(addressCode, addressName, level, parentAddressCode);
            JSONArray provinceJsonArray = districtJsonObject.getJSONArray(AmapConfigConstants.DISTRICTS);
            for (int j = 0; j < provinceJsonArray.size(); j++) {
                JSONObject provinceJsonObject = provinceJsonArray.getJSONObject(j);
                String provinceAddressCode = provinceJsonObject.getString(AmapConfigConstants.ADCODE);
                String provinceAddressName = provinceJsonObject.getString(AmapConfigConstants.NAME);
                String provinceParentAddressCode = addressCode;
                String provinceLevel = provinceJsonObject.getString(AmapConfigConstants.LEVEL);

                insertDicDistrict(provinceAddressCode, provinceAddressName, provinceLevel, provinceParentAddressCode);
                JSONArray cityJsonArray = provinceJsonObject.getJSONArray(AmapConfigConstants.DISTRICTS);
                for (int k = 0; k < cityJsonArray.size(); k++) {
                    JSONObject cityJsonObject = cityJsonArray.getJSONObject(k);
                    String cityAddressCode = cityJsonObject.getString(AmapConfigConstants.ADCODE);
                    String cityAddressName = cityJsonObject.getString(AmapConfigConstants.NAME);
                    String cityParentAddressCode = provinceAddressCode;
                    String cityLevel = provinceJsonObject.getString(AmapConfigConstants.LEVEL);

                    insertDicDistrict(cityAddressCode, cityAddressName, cityLevel, cityParentAddressCode);


                    JSONArray districtsJsonArray = cityJsonObject.getJSONArray(AmapConfigConstants.DISTRICTS);
                    for (int l = 0; l < districtsJsonArray.size(); l++) {
                        JSONObject districtsJsonObject = districtsJsonArray.getJSONObject(l);
                        String disctrictsAddressCode = districtsJsonObject.getString(AmapConfigConstants.ADCODE);
                        String disctrictsAddressName = districtsJsonObject.getString(AmapConfigConstants.NAME);
                        String disctrictsParentAddressCode = cityAddressCode;
                        String disctrictsLevel = districtsJsonObject.getString(AmapConfigConstants.LEVEL);
                        if (disctrictsLevel.trim().equals(AmapConfigConstants.STREET)){
                            continue;
                        }

                        insertDicDistrict(disctrictsAddressCode, disctrictsAddressName, disctrictsLevel, disctrictsParentAddressCode);
                    }
                }
            }
        }
        //插入数据库


        return ResponseResult.success();
    }

    public int generateLevel(String level) {
        int levelInt = 0;
        if (level.trim().equals("country")) {
            levelInt = 0;
        } else if (level.trim().equals("provice")) {
            levelInt = 1;
        } else if (level.trim().equals("city")) {
            levelInt = 2;
        } else if (level.trim().equals("district")) {
            levelInt = 3;
        }

        return levelInt;
    }

    public void insertDicDistrict(String addressCode, String addressName, String level, String parentAddressCode) {
        DictDistrict district = new DictDistrict();
        district.setAddressCode(addressCode);
        district.setAddressName(addressName);
        district.setParentAddressCode(parentAddressCode);
        int levelInt = generateLevel(level);
        district.setLevel(levelInt);

        districtMapper.insert(district);
    }


}
