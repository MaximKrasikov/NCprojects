package com.forms;

import com.entities.domains.PasswordMatches;
import com.entities.domains.password.ValidPassword;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by Admin on 20.02.2019.
 */

@Getter
@Setter
@PasswordMatches
public class UserDto{
    @NotNull
    @NotEmpty
    private String username;

    @ValidPassword
    private String password;

    @NotNull
    @Size(min = 1)
    private String matchingPassword;

}