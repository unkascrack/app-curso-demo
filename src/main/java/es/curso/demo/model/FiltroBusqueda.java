package es.curso.demo.model;

import org.apache.commons.lang3.StringUtils;

public class FiltroBusqueda {

    private Integer page;
    private Integer pageSize;
    private String orderBy;
    private Boolean orderSort;

    public Integer getPage() {
        return page;
    }

    public void setPage(final Integer page) {
        this.page = page;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(final Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getFirstResult() {
        return page != null && pageSize != null ? (page - 1) * pageSize : null;
    }

    public Integer getLastResult() {
        return page != null && pageSize != null ? page - 1 + pageSize : null;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(final String orderBy) {
        this.orderBy = StringUtils.stripToNull(orderBy);
    }

    public Boolean getOrderSort() {
        return orderSort;
    }

    public void setOrderSort(final Boolean orderSort) {
        this.orderSort = orderSort;
    }

    public String getOrderType() {
        return orderSort != null && orderSort ? "DESC" : "ASC";
    }

    @Override
    public String toString() {
        return "FiltroBusqueda [page=" + page + ", pageSize=" + pageSize + ", orderBy=" + orderBy + ", orderSort="
                + orderSort + "]";
    }
}
