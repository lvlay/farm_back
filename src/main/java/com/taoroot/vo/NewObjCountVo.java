package com.taoroot.vo;

/**
 * @author: taoroot
 * @date: 2018/1/22
 * @description: 统计一周数据使用
 */
public class NewObjCountVo {
    private int total;
    private int[] count;

    public NewObjCountVo() {
        count = new int[7];
    }

    public void add(int num) {
        total += num;
    }

    public NewObjCountVo setTotal(int total) {
        this.total = total;
        return this;
    }

    public NewObjCountVo setCount(int[] count) {
        this.count = count;
        return this;
    }

    public int getTotal() {
        return total;
    }

    public int[] getCount() {
        return count;
    }
}
