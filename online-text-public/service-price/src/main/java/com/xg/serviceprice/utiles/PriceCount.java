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
        double price=0;
        //1 起步价
        Double startFare = priceRule.getStartFare();
        price=add(price,startFare);
        //2 里程费
        //总里程km
        //ROUND_HALF_UP 四舍五入
        //scale 小数点几位 divide 除法
        double distanceKMile=divide(distance,1000);
        //起步里程 km
        double  startKMile = (double)priceRule.getStartMile();
        double distanceSubstract =subtract(distanceKMile,startKMile);
        double mile = distanceSubstract < 0 ? 0 : distanceSubstract;
        double unitPricePerMile = priceRule.getUnitPricePerMile();
        double milePrice=multiply(mile,unitPricePerMile);
        price=add(price,milePrice);
        //3 时长费
        double timeMinute = divide(duration, 60);
        double pricePerMinute=priceRule.getUnitPricePerMinute();
        double timePrice = multiply(timeMinute, pricePerMinute);
        price=add(price,timePrice);
        BigDecimal priceBigDemcimal=BigDecimal.valueOf(price).setScale(2,BigDecimal.ROUND_HALF_UP);
        return priceBigDemcimal.doubleValue();
    }

    public static double add(double v1,double v2){
        BigDecimal b1=BigDecimal.valueOf(v1);
        BigDecimal b2=BigDecimal.valueOf(v2);
        return b1.add(b2).doubleValue();
    }

    public static double divide(int v1,int v2){
        if (v2<=0){
            throw new IllegalArgumentException("除数非法");
        }
        BigDecimal b1=BigDecimal.valueOf(v1);
        BigDecimal b2=BigDecimal.valueOf(v2);
        return b1.divide(b2,2,BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    public static double subtract(double v1,double v2){
        BigDecimal b1=BigDecimal.valueOf(v1);
        BigDecimal b2=BigDecimal.valueOf(v2);
        return b1.subtract(b2).doubleValue();
    }

    public static double multiply(double v1,double v2){
        BigDecimal b1=BigDecimal.valueOf(v1);
        BigDecimal b2=BigDecimal.valueOf(v2);
        return b1.multiply(b2).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
    }


}
