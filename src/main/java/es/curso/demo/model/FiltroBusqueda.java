package es.curso.demo.model;

import java.io.Serializable;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;

import flexjson.JSON;

public abstract class FiltroBusqueda implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonIgnore
    @JSON(include = false)
    private boolean total = false;

    @JsonIgnore
    @JSON(include = false)
    private Integer page;

    @JsonIgnore
    @JSON(include = false)
    private Integer pageSize;

    @JsonIgnore
    @JSON(include = false)
    private String orderBy;

    @JsonIgnore
    @JSON(include = false)
    private Boolean orderSort;

    /**
     * @return
     */
    @JSON(include = false)
    abstract Map<String, String> getAttrToColumnConversion();

    public boolean isTotal() {
        return total;
    }

    public void setTotal(final boolean total) {
        this.total = total;
    }

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

    @JsonIgnore
    @JSON(include = false)
    public Integer getFirstResult() {
        return page != null && pageSize != null ? (page - 1) * pageSize : null;
    }

    @JsonIgnore
    @JSON(include = false)
    public Integer getLastResult() {
        return page != null && pageSize != null ? (page - 1) * pageSize + pageSize : null;
    }

    @JsonIgnore
    @JSON(include = false)
    public String getOrderByColumn() {
        return getAttrToColumnConversion().get(orderBy);
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

    @JsonIgnore
    @JSON(include = false)
    public String getOrderType() {
        return orderSort != null && orderSort ? "DESC" : "ASC";
    }

    @Override
    public String toString() {
        return "FiltroBusqueda [page=" + page + ", pageSize=" + pageSize + ", orderBy=" + orderBy + ", orderSort="
                + orderSort + "]";
    }
}
