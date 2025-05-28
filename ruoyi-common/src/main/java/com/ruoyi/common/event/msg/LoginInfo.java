package com.ruoyi.common.event.msg;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginInfo {
    String userAgentHeader;
    String username;
    String status;
    String message;
    Object[] args;
}
