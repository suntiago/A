package com.suntiago.a

/**
 * Created by Zaiyu on 2019/4/17.
 */
object A {

    /**
     * 判断des 是否在start~end 区间内
     *
     * @param des   目标数值
     * @param start 起始值
     * @param end   结束值
     * @return 是否在区间内
     */
    fun between(des: Long, start: Long, end: Long): Boolean = des in start..end

    fun between(des: Int, start: Int, end: Int): Boolean = des in start..end
    fun between(des: Float, start: Float, end: Float): Boolean = des in start..end
    fun between(des: Double, start: Double, end: Double): Boolean = des in start..end
}