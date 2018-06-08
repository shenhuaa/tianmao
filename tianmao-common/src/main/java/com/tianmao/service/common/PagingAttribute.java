package com.tianmao.service.common;

import java.io.Serializable;

/**
 * 分页实体类
 *
 * @author 陈明
 * @date 2016年11月16日
 */
public class PagingAttribute implements Serializable {
    private static final long serialVersionUID = -575627605731863121L;

    private final static int DEFAULT_PAGE_NUMBER = 1;//默认页

    private int DEFAULT_PAGE_SIZE = 10; //默认每页数量

    private Integer pageNumber;//当前页

    private Integer pageSize; //每页数量

    private String alias;//别名

    private String sortName = "id";  //排序字段

    private String sortOrder = "";//排序

    private String orderBy;

    public PagingAttribute() {

    }

    public PagingAttribute(Integer pageNumber, Integer pageSize) {
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
    }

    public int getPageNumber() {
        if (pageNumber == null) {
            pageNumber = DEFAULT_PAGE_NUMBER;
        }
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getPageSize() {
        if (pageSize == null) {
            pageSize = DEFAULT_PAGE_SIZE;
        }
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getSortName() {
        return sortName;
    }

    public void setSortName(String sortName) {
        this.sortName = camelToUnderline(sortName);
    }

    public void setCamelSortName(String sortName) {
        this.sortName = sortName;
    }

    public String getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(String sortOrder) {
        this.sortOrder = sortOrder;
    }


    public String getOrderBy() {
        String orderBy = "";
        if (alias != null && this.sortName.indexOf(".") == -1) {
            orderBy = this.alias + ".";
        }
        orderBy += this.sortName + " " + this.sortOrder;
        return orderBy;
    }


    /**
     * 将页面或PO字段转换成以下划线分隔的数据库字段
     *
     * @param param 例如： custName
     * @return 换换后字符串 CUST_NAME
     */
    private static String camelToUnderline(String param) {
        if (param == null || "".equals(param.trim())) {
            return "";
        }
        int len = param.length();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            char c = param.charAt(i);
            if (Character.isUpperCase(c)) {
                sb.append("_");
                sb.append(Character.toLowerCase(c));
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    public Integer getBeginSize() {
        return (this.getPageNumber() - 1) * this.getPageSize();
    }
}
