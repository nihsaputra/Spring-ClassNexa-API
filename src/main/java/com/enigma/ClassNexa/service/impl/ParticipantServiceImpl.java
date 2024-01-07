package com.enigma.ClassNexa.service.impl;

import com.enigma.classnexa.entity.Admin;
import com.enigma.classnexa.entity.Participant;
import com.enigma.classnexa.entity.UserCredential;
import com.enigma.classnexa.model.request.UserCreateRequest;
import com.enigma.classnexa.model.request.UserUpdateRequest;
import com.enigma.classnexa.model.response.UserResponse;
import com.enigma.classnexa.repository.ParticipantRepository;
import com.enigma.classnexa.service.ParticipantService;
import com.enigma.classnexa.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ParticipantServiceImpl implements ParticipantService {
    private final ParticipantRepository participantRepository;
    private final UserService userService;
    @Override
    @Transactional(rollbackFor = Exception.class)
    public String create(UserCreateRequest request) {

        Participant buildParticipant = Participant.builder()
                .name(request.getName())
                .gender(request.getGender())
                .address(request.getAddress())
                .phoneNumber(request.getPhoneNumber())
                .userCredential(request.getUserCredential())
                .build();

        Participant participant = participantRepository.saveAndFlush(buildParticipant);
        return participant.getName();
    }
    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<UserResponse> getAll() {
        List<Participant> findAll = participantRepository.findAll();

        List<UserResponse> userGetResponses = new ArrayList<>();
        for (Participant participant : findAll) {
            UserResponse buildResponse = UserResponse.builder()
                    .id(participant.getId())
                    .name(participant.getName())
                    .gender(participant.getGender())
                    .address(participant.getAddress())
                    .email(participant.getUserCredential().getEmail())
                    .phoneNumber(participant.getPhoneNumber())
                    .build();

            userGetResponses.add(buildResponse);
        }

        return userGetResponses;
    }
    @Override
    @Transactional(rollbackFor = Exception.class)
    public UserResponse getById(String request) {
        Optional<Participant> optionalParticipant = participantRepository.findById(request);
        if (!optionalParticipant.isPresent()) throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Not Found");

        return UserResponse.builder()
                .id(optionalParticipant.get().getId())
                .name(optionalParticipant.get().getName())
                .gender(optionalParticipant.get().getGender())
                .address(optionalParticipant.get().getAddress())
                .email(optionalParticipant.get().getUserCredential().getEmail())
                .phoneNumber(optionalParticipant.get().getPhoneNumber())
                .build();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public UserResponse update(UserUpdateRequest request) {
        UserResponse findId = getById(request.getId());
        UserCredential userCredential =(UserCredential) userService.loadUserByUsername(findId.getEmail());

        Participant buildParticipant = Participant.builder()
                .id(findId.getId())
                .name(request.getName())
                .gender(request.getGender())
                .address(request.getAddress())
                .phoneNumber(request.getPhoneNumber())
                .userCredential(userCredential)
                .build();

        Participant participant = participantRepository.saveAndFlush(buildParticipant);

        return UserResponse.builder()
                .id(participant.getId())
                .name(participant.getName())
                .gender(participant.getGender())
                .address(participant.getAddress())
                .email(participant.getUserCredential().getEmail())
                .phoneNumber(participant.getPhoneNumber())
                .build();
    }
}