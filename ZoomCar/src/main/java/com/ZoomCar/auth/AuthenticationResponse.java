package com.ZoomCar.auth;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@Getter
@NoArgsConstructor
public class AuthenticationResponse {

    private String token;
}