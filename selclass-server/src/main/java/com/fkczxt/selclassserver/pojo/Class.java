package com.fkczxt.selclassserver.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
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
 * @since 2023-01-18
 */
@Getter
@Setter
@TableName("t_class")
@ApiModel(value = "Class对象", description = "")
public class Class implements Serializable {

    private static final long serialVersionUID = 1L;

    private String classname;

    private Long teacher;

    private Double point;
    @TableId(type = IdType.INPUT)
    private Integer classid;

    private Integer number;

    private String time;
    private String teachername;
    private Integer selected;
}
