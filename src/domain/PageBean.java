package domain;

import java.util.List;

/**
 * Created by RZ on 4/21/16.
 */
public class PageBean<T> {
    private int currPageNum; // 页码
    private int pageCount; // 每页条数
    private int totalPage; // 总页数
    private int totalCount; // 总条数
    private List<T> pageContent; // 每页数据
    private SearchCondition condition;

    public SearchCondition getCondition() {
        return condition;
    }

    public void setCondition(SearchCondition condition) {
        this.condition = condition;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getTotalCount() {
        return totalCount;
    }


    public int getCurrPageNum() {
        return currPageNum;
    }

    public void setCurrPageNum(int currPageNum) {
        this.currPageNum = currPageNum;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List<T> getPageContent() {
        return pageContent;
    }

    public void setPageContent(List<T> pageContent) {
        this.pageContent = pageContent;
    }
    public void setTotalPage() {
        totalPage=(int)Math.ceil(totalCount*1.0/pageCount);
    }
}
