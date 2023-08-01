package com.Adinz.Proposal.PalyLoad;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JWTLoginSuccessResponse {
    private boolean success;
    private String token;

}
