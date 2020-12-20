package com.springboot.express.entity;

import lombok.Data;

@Data
public class WorkloadVO {
    private String names;
    private int month;
    private int receiveWorkloads;
    private int assignWorkloads;
    private int receiveFaults;
    private int assignFaults;
    private int maxWorkloads;
    private int minWorkloads;
}
