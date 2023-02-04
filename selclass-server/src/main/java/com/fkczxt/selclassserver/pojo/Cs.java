package com.fkczxt.selclassserver.pojo;

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
@TableName("t_cs")
@ApiModel(value = "Cs对象", description = "")
public class Cs implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long studentid;

    private Integer classid;

    private Integer fraction;
}
