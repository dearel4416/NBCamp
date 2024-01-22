package com.hobbyhop.domain.joinrequest.dto;

import com.hobbyhop.domain.joinrequest.entity.JoinRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class JoinResponseDTO {
    private Long id;
    private Long sendUserId;
    private Long recvClubId;

    public static JoinResponseDTO fromEntity(JoinRequest joinRequest) {
        return JoinResponseDTO.builder()
                .id(joinRequest.getId())
                .sendUserId(joinRequest.getUser().getId())
                .recvClubId(joinRequest.getClub().getId())
                .build();
    }
}
