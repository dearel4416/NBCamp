package com.hobbyhop.domain.joinrequest.service.impl;

import com.hobbyhop.domain.club.service.impl.ClubServiceImpl;
import com.hobbyhop.domain.clubmember.dto.ClubMemberResponseDTO;
import com.hobbyhop.domain.clubmember.entity.ClubMember;
import com.hobbyhop.domain.clubmember.enums.MemberRole;
import com.hobbyhop.domain.clubmember.pk.ClubMemberPK;
import com.hobbyhop.domain.clubmember.service.impl.ClubMemberServiceImpl;
import com.hobbyhop.domain.joinrequest.dto.JoinResponseDTO;
import com.hobbyhop.domain.joinrequest.entity.JoinRequest;
import com.hobbyhop.domain.joinrequest.enums.JoinRequestStatus;
import com.hobbyhop.domain.joinrequest.repository.JoinRequestRepository;

import com.hobbyhop.test.ClubTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@DisplayName("[JoinRequest]")
class JoinRequestServiceImplTest implements ClubTest {

    @InjectMocks
    private JoinRequestServiceImpl sut;

    @Mock
    private ClubServiceImpl clubService;
    @Mock
    private JoinRequestRepository joinRequestRepository;
    @Mock
    private ClubMemberServiceImpl clubMemberService;
    private JoinRequest joinRequest;
    private JoinResponseDTO joinResponseDTO;
    private ClubMember clubMember;
    private ClubMemberPK clubMemberPk;


    @BeforeEach
    void setUp() {
        joinRequest = JoinRequest.builder()
                .club(TEST_CLUB)
                .user(TEST_USER)
                .status(JoinRequestStatus.PENDING)
                .build();
        clubMemberPk = ClubMemberPK.builder()
                .club(TEST_CLUB)
                .user(TEST_USER)
                .build();

        clubMember = ClubMember.builder()
                .memberRole(MemberRole.ADMIN)
                .clubMemberPK(clubMemberPk)
                .build();
        joinResponseDTO = JoinResponseDTO.fromEntity(joinRequest);
    }


    @DisplayName("[Send]")
    @Test
    void joinRequest_요청_보내기_성공() {
        given(clubService.findClub(TEST_CLUB_ID)).willReturn(TEST_CLUB);
        given(clubMemberService.isClubMember(TEST_CLUB_ID, TEST_USER_ID)).willReturn(false);
        given(joinRequestRepository.save(any())).willReturn(joinRequest);

        assertThat(sut.sendRequest(TEST_CLUB_ID, TEST_USER)).isEqualTo(joinResponseDTO);
    }
    @DisplayName("[Get]")
    @Test
    void joinRequest_조회() {
        // Given
        given(clubMemberService.findByClubAndUser(TEST_CLUB_ID, TEST_USER_ID)).willReturn(clubMember);
        given(joinRequestRepository.findByClub_IdAndStatus(TEST_CLUB_ID, JoinRequestStatus.PENDING)).willReturn(List.of(joinRequest));

        assertThat(sut.getRequestByClub(TEST_CLUB_ID, TEST_USER).get(0).getId()).isEqualTo(joinResponseDTO.getId());
        assertThat(sut.getRequestByClub(TEST_CLUB_ID, TEST_USER).get(0).getUsername()).isEqualTo(joinResponseDTO.getUsername());
        assertThat(sut.getRequestByClub(TEST_CLUB_ID, TEST_USER).get(0).getRecvClubId()).isEqualTo(joinResponseDTO.getRecvClubId());
        assertThat(sut.getRequestByClub(TEST_CLUB_ID, TEST_USER).get(0).getSendUserId()).isEqualTo(joinResponseDTO.getSendUserId());

    }

    @DisplayName("[Process]")
    @Test
    void joinRequest_처리() {
        // Given
        given(joinRequestRepository.findById(1L)).willReturn(Optional.of(joinRequest));
        given(clubMemberService.joinClub(TEST_CLUB, TEST_USER, MemberRole.MEMBER)).willReturn(ClubMemberResponseDTO.builder()
                .clubId(TEST_CLUB_ID)
                .userId(TEST_USER_ID)
                .build());
        // When
        sut.processRequest(1L, JoinRequestStatus.APPROVED);

        // Then
        verify(clubMemberService).joinClub(TEST_CLUB, TEST_USER, MemberRole.MEMBER);
    }
}