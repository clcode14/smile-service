package org.flightythought.smile.admin.bean;

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
    private LocalDateTime noteDate;
}
