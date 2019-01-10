package com.yunchao.hsh.dto.resp;

import java.util.List;

/**
 * @Authher:Zhai Qing
 * @Date: 2018/11/23 17:09
 * @Description:
 * @Email: hkwind959@gmail.com
 */
public class PageResultResp {
    private int page;			// 当前页数
    private int total;			// 总页数
    private long records;		// 总记录数
    private List<?> rows;		// 每行显示的内容

    public int getPage() {
        return page;
    }
    public void setPage(int page) {
        this.page = page;
    }
    public int getTotal() {
        return total;
    }
    public void setTotal(int total) {
        this.total = total;
    }
    public long getRecords() {
        return records;
    }
    public void setRecords(long records) {
        this.records = records;
    }
    public List<?> getRows() {
        return rows;
    }
    public void setRows(List<?> rows) {
        this.rows = rows;
    }
}
