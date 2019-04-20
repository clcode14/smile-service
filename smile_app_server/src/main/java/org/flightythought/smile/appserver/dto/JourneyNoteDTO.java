package org.flightythought.smile.appserver.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@ApiModel(value = "养生日记DTO", description = "养生日记DTO")
@Data
public class JourneyNoteDTO {

    /**
     * 封面图片ID
     */
    @ApiModelProperty(value = "封面图片ID")
    private Integer coverImageId;

    /**
     * 养生旅程ID
     */
    @ApiModelProperty(value = "养生旅程ID")
    private Integer journeyId;

    /**
     * 内容
     */
    @ApiModelProperty(value = "content")
    private String content;

    /**
     * 是否发布到朋友圈
     */
    @ApiModelProperty(value = "是否发布到朋友圈")
    private Boolean circleOfFriends;

    /**
     * 体检指标
     */
    List<JourneyNoteNormDTO> norms;

    /**
     * 图片ID
     */
    List<Integer> imageIds;
}
