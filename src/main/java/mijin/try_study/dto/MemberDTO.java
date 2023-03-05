package mijin.try_study.dto;

import lombok.*;
import mijin.try_study.entity.MemberEntity;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MemberDTO {
    //관리자들이 로그인 할 수 있는 도메인
    private Long id;
    private Long number;
    private Long password;

    public static MemberDTO toMemberDTO(MemberEntity memberEntity) {
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setId(memberEntity.getId());
        memberDTO.setNumber(memberEntity.getNumber());
        memberDTO.setPassword(memberEntity.getPassword());
        return memberDTO;
    }
}
