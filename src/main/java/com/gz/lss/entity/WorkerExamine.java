package com.gz.lss.entity;

import java.io.Serializable;

/**
 * @Author guozhi
 * @Date 2019/6/2 13:20
 * @Description TODO
 */

public class WorkerExamine implements Serializable {
    private Integer review_id;
    private String worker_name;
    private String current;
    private String want;
    private String description;
    private String state;

    public WorkerExamine() {
    }

    @Override
    public String toString() {
        return "WorkerExamine{" +
                "review_id=" + review_id +
                ", worker_name='" + worker_name + '\'' +
                ", current='" + current + '\'' +
                ", want='" + want + '\'' +
                ", description='" + description + '\'' +
                ", state='" + state + '\'' +
                '}';
    }

    public Integer getReview_id() {
        return review_id;
    }

    public void setReview_id(Integer review_id) {
        this.review_id = review_id;
    }

    public String getWorker_name() {
        return worker_name;
    }

    public void setWorker_name(String worker_name) {
        this.worker_name = worker_name;
    }

    public String getCurrent() {
        return current;
    }

    public void setCurrent(String current) {
        this.current = current;
    }

    public String getWant() {
        return want;
    }

    public void setWant(String want) {
        this.want = want;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
