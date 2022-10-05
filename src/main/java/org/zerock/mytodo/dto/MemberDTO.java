package org.zerock.mytodo.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class MemberDTO {

        private String mid, mpw;
        @Builder.Default
        private String email="", mname="";
        @Builder.Default
        private LocalDateTime regDate=LocalDateTime.now(), modDate=LocalDateTime.now();


}
