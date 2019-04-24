package org.flightythought.smile.admin.bean;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class JourneyNoteInfo {

    private Integer id;

    private Integer journeyId;

    private String content;

    private String coverImageUrl;

    private LocalDateTime noteDate;
}
