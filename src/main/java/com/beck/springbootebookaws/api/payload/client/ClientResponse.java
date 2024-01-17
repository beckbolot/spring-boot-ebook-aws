package com.beck.springbootebookaws.api.payload.client;

import com.beck.springbootebookaws.db.entity.HistoryOperation;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ClientResponse {

    private Long id;
    private String firstName;
    private String email;
    private LocalDateTime createdAt;
    private boolean isActive;
    private List<HistoryOperation> operationList;
}
