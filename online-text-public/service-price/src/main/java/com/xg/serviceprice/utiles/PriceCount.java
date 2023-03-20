package com.xg.serviceprice.utiles;

import com.xg.internalcommon.dto.PriceRule;
import org.springframework.cloud.openfeign.support.SpringEncoder;

import java.math.BigDecimal;

public class PriceCount {
    /**
     * 根据距离、时长、计价规则计算最终价格
     *
     * @param distance  距离
     * @param duration  时长
     * @param priceRule 计价规则
     * @return 计算的价格
     */
    public static Double getPrice(Integer distance, Integer duration, PriceRule priceRule) {
        BigDecimal price = new BigDecimal(0);
        //1 起步价
        Double startFare = priceRule.getStartFare();
        BigDecimal startFareDecimal = new BigDecimal(startFare);
        price = price.add(startFareDecimal);
        //2 里程费
        //总里程 m
        BigDecimal distanceDecimal = new BigDecimal(distance);
        //总里程km
        //ROUND_HALF_UP 四舍五入
        //scale 小数点几位 divide 除法
        BigDecimal distanceKiloMileDecimal = distanceDecimal.divide(new BigDecimal(1000), 2, BigDecimal.ROUND_HALF_UP);
        //起步里程 km
        Integer startMile = priceRule.getStartMile();
        BigDecimal startMileDecimal = new BigDecimal(startMile);
        double distanceSubstract = distanceKiloMileDecimal.subtract(startMileDecimal).doubleValue();
        Double mile = distanceSubstract < 0 ? 0 : distanceSubstract;
        BigDecimal mileDecimal = new BigDecimal(mile);
        BigDecimal pricePerMile = new BigDecimal(priceRule.getUnitPricePerMile());
        BigDecimal milePrice = mileDecimal.multiply(pricePerMile).setScale(2, BigDecimal.ROUND_HALF_UP);
        price = price.add(milePrice);
        //3 时长费
        BigDecimal timeMinute = new BigDecimal(duration);
        BigDecimal timeSec = timeMinute.divide(new BigDecimal(60), 2, BigDecimal.ROUND_HALF_UP);
        BigDecimal pricePerMinute = new BigDecimal(priceRule.getUnitPricePerMinute());
        BigDecimal timePrice = timeSec.multiply(pricePerMinute).setScale(2, BigDecimal.ROUND_HALF_UP);
        price = price.add(timePrice);
        return price.doubleValue();
    }

}
