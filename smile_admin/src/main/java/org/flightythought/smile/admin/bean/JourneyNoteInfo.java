package org.flightythought.smile.admin.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.time.LocalDateTime;

import org.flightythought.smile.admin.framework.serializer.JsonLocalDateTimeSerializer;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Data
public class JourneyNoteInfo {

    private Integer id;

    private Integer journeyId;

    private String content;

    private String coverImageUrl;
    
    @JsonSerialize(using = JsonLocalDateTimeSerializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime noteDate;
}
