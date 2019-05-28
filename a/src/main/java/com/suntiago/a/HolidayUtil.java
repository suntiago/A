package com.suntiago.a;

import java.util.HashMap;
import java.util.Map;


/**
 * @author chenjunwei
 * @desc 节假日
 * @date 2019/5/28
 */
public class HolidayUtil {

  private static Map<String, String> holiday = new HashMap<>();

  static {
    holiday.put("01/01", "元旦");
    holiday.put("05/01", "劳动节");
    holiday.put("10/01", "国庆节");
    holiday.put("正月初一", "春节");
    holiday.put("五月初五", "端午节");
    holiday.put("八月十五", "中秋节");
  }

  public static String getHoliday(String key) {

    return holiday.get(key);
  }
}
