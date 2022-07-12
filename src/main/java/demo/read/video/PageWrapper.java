package demo.read.video;

import java.io.Serializable;

/**
 * 分页返回对象
 * @author xuyongsheng
 * @date   2022/02/28
 **/
public class PageWrapper<T> implements Serializable {
    /**
     * 列表实体数据
     */
    private T list;

    /**
     * 当前页码
     */
    private int pageNum;
    /**
     * 页大小
     */
    private int pageSize;
    /**
     * 总记录数
     */
    private long total;
    /**
     * 总页数
     */
    private int pages;


    public T getList() {
        return list;
    }

    public void setList(T list) {
        this.list = list;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    /**
     * 判断当前页是否还有数据，用于优化减少查询list
     * @return
     */
    public boolean hadData() {
        if ((pageNum - 1) * pageSize >= total) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 获取空的初始化
     * @param pageNum
     * @param pageSize
     * @param <T>
     * @return
     */
    public static <T> PageWrapper<T> instance(int pageNum, int pageSize) {
        PageWrapper pageWrapper = new PageWrapper();
        pageWrapper.setPageSize(pageSize);
        pageWrapper.setPageNum(pageNum);
        return pageWrapper;
    }
}
