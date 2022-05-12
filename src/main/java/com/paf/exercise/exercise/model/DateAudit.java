//package com.paf.exercise.exercise.model;
//
//import javax.persistence.*;
//
//import java.time.LocalDateTime;
//import java.util.Date;
//
//@Embeddable
//public class DateAudit {
//
//    @Column(name = "create_date", nullable = false, updatable=false)
//    private LocalDateTime createDate;
//
//    @Column(name = "modify_date")
//    private LocalDateTime  modifyDate;
//
//    // Constructor
//    public DateAudit() {
//        this.createDate = getCreateDate();
//        this.modifyDate = getModifyDate();
//    }
//
//    public LocalDateTime  getCreateDate() {
//        return createDate;
//    }
//
//    public LocalDateTime  getModifyDate() {
//        return modifyDate;
//    }
//}