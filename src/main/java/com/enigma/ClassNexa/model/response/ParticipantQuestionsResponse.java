package com.enigma.ClassNexa.model.response;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ParticipantQuestionsResponse {
    private String participantName;
    private String question;
    private String course;
    private String chapter;
    private String status;
}
