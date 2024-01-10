package com.fkczxt.selclassserver.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@TableName("t_apply")
@ApiModel(value = "apply", description = "")
public class Apply implements Serializable {

    private static final long serialVersionUID = 1L;
    private int id;
    private int type;

    private String classname;
    private Long teacher;
    private Integer classid;
    private Double point;
    private Integer number;
    private String time;
    private String teachername;
    private LocalDateTime starttime;
    private LocalDateTime endtime;
    private String reason;
    private Integer status;
    private LocalDateTime applytime;
    private LocalDateTime dealtime;
    private String dealperson;
    private String dealreason;
}