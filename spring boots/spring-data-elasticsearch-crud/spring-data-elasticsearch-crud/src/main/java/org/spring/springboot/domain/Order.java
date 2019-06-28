package org.spring.springboot.domain;

import org.springframework.data.elasticsearch.annotations.Document;

import java.io.Serializable;


@Document(indexName = "orderindex", type = "order")
public class Order implements Serializable {

    private static final long serialVersionUID = -1L;

    /**
     * 编号
     */
    private Long id;

    /**
     * 名称
     */
    private String name;

    /**
     * 描述
     */
    private String description;


    /**
     * 评分
     */
    private Integer score;

    public static long getSerialVersionUID() {

        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
}
