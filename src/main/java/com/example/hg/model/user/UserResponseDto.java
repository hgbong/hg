package com.example.hg.model.user;

import lombok.*;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.persistence.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDto {

    private Long userId;

    private String userName;
}
