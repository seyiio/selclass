package com.fkczxt.selclassserver.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author fk
 * @since 2023-03-01
 */
@Getter
@Setter
@TableName("t_time")
@ApiModel(value = "Time对象", description = "")
public class Time implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId
    private Integer id;

    private LocalDateTime starttime;

    private LocalDateTime endtime;
    public Time(LocalDateTime s,LocalDateTime e, Integer id){
        this.id=id;
        starttime=s;
        endtime=e;
    }
    public Time(){

    }
}
