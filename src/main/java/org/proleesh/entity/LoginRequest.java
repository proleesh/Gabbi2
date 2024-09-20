package org.proleesh.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * @author sung-hyuklee
 */
@Getter
@Setter
public class LoginRequest {
    private String username;
    private String password;
}
