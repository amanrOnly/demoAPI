package com.dtdl.demoAPI.payload;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {

    private int userID;
    private String userName;
    private int contactNumber;
    private String address;
    private String location;
}
