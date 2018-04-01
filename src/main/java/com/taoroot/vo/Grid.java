package com.taoroot.vo;

/**
 * @author: taoroot
 * @date: 2018/3/27
 * @description: 分页 实体类
 */
public class Grid {
    private int page;
    private long total;
    private long records;
    private Object rows;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public long getRecords() {
        return records;
    }

    public void setRecords(long records) {
        this.records = records;
    }

    public Object getRows() {
        return rows;
    }

    public void setRows(Object rows) {
        this.rows = rows;
    }
}
