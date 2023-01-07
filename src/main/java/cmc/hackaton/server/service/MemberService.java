package cmc.hackaton.server.service;

import cmc.hackaton.server.common.exception.member.MemberNotFoundException;
import cmc.hackaton.server.dto.MemberDto;
import cmc.hackaton.server.entity.Member;
import cmc.hackaton.server.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberDto findMember(String token) {
        return MemberDto.from(getMember(token));
    }

    @Transactional
    public void saveMember(MemberDto dto) {
        memberRepository.save(dto.toEntity());
    }

    @Transactional
    public void updateMemberNickname(MemberDto dto) {
        Member member = getMember(dto.getToken());
        member.setNickname(dto.getNickname());
    }

    private Member getMember(String token) {
        return memberRepository.findByToken(token)
                .orElseThrow(MemberNotFoundException::new);
    }
}
