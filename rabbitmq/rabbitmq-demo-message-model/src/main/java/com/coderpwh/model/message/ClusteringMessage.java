package com.coderpwh.model.message;

import java.io.Serializable;

public class ClusteringMessage  implements Serializable {

    public static final String QUEUE = "QUEUE_CLUSTERING";

    public static final String EXCHANGE = "EXCHANGE_CLUSTERING";

    /**
     * 编号
     */
    private Integer id;

    public ClusteringMessage setId(Integer id) {
        this.id = id;
        return this;
    }

    public Integer getId() {
        return id;
    }

    @Override
    public String toString() {
        return "ClusteringtMessage{" +
                "id=" + id +
                '}';
    }

}
