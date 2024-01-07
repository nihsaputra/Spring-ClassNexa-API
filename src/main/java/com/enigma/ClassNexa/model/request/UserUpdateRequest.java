package com.enigma.ClassNexa.model.request;


import com.enigma.classnexa.entity.UserCredential;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserUpdateRequest {
    private String id;
    private String name;
    private String address;
    private String gender;
    private String phoneNumber;
}