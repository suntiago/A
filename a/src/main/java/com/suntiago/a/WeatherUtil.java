package com.suntiago.a;

import java.util.HashMap;
import java.util.Map;


/**
 * @author chenjunwei
 * @desc
 * @date 2019/5/14
 */
public class WeatherUtil {
    public static Map<String, Integer> map = new HashMap<>();

    static {
        map.put("大雨", R.mipmap.ic_dayu);
        map.put("暴雨", R.mipmap.ic_baoyu);
        map.put("冰雹", R.mipmap.ic_bingbao);
        map.put("大雪", R.mipmap.ic_daxue);
        map.put("多云", R.mipmap.ic_duoyun);
        map.put("浮尘", R.mipmap.ic_fuchen);
        map.put("雷阵雨", R.mipmap.ic_lezhenyu);
        map.put("霾", R.mipmap.ic_mai);
        map.put("晴", R.mipmap.ic_qing);
        map.put("晴好", R.mipmap.ic_qing);
        map.put("轻沙尘暴", R.mipmap.ic_qingshachenbao);
        map.put("沙尘暴", R.mipmap.ic_shachenbao);
        map.put("特大暴雨", R.mipmap.ic_tedabaoyu);
        map.put("雾", R.mipmap.ic_wu);
        map.put("小雪", R.mipmap.ic_xiaoxue);
        map.put("小雨", R.mipmap.ic_xiaoyu);
        map.put("扬沙", R.mipmap.ic_yangsha);
        map.put("阴", R.mipmap.ic_yin);
        map.put("雨夹雪", R.mipmap.ic_yujiaxue);
        map.put("阵雪", R.mipmap.ic_zhenxue);
        map.put("中雪", R.mipmap.ic_zhongxue);
        map.put("中雨", R.mipmap.ic_zhongyu);
        map.put("阵雨", R.mipmap.ic_zhenyu);
    }

    public static int get(String weather) {
        if (map.containsKey(weather)) {
            return map.get(weather);
        }
        return 0;
    }
}
