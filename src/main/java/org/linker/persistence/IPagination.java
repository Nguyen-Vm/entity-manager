package org.linker.persistence;

import java.util.ArrayList;
import java.util.List;

/**
 * @author RWM
 * @date 2018/11/28
 */
public class IPagination<T> {
    /** 当前页数 **/
    private int pager;
    /** 总页数 **/
    private int pages;
    /** 每页条数 **/
    private int size;
    /** 总条数 **/
    private long total;
    /** 忽略数据条数 **/
    private int offset;
    /** 数据列表 **/
    private List<T> list = new ArrayList<>();

    public IPagination() {
    }

    public IPagination(int pager, int size) {
        if (pager >= 1 && size >= 1) {
            this.pager = pager;
            this.size = size;
        } else {
            throw new RuntimeException("invalid pager: " + pager + " or size: " + size);
        }
    }

    public static IPagination create(int pager, int size) {
        return new IPagination(pager, size);
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public int getSize() {
        return size;
    }

    public int getPager() {
        return pager == 0 ? 1 : pager;
    }

    public int getOffset() {
        return size * (getPager() - 1);
    }

    public int getPages() {
        return Double.valueOf(Math.ceil((double) total / (double) size)).intValue();
    }

    public long getTotal() {
        return total;
    }

    public List<T> getList() {
        return list;
    }
}
