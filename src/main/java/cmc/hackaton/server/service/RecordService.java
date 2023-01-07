package cmc.hackaton.server.service;

import cmc.hackaton.server.common.exception.member.MemberNotFoundException;
import cmc.hackaton.server.dto.RecordDto;
import cmc.hackaton.server.entity.Member;
import cmc.hackaton.server.entity.record.Record;
import cmc.hackaton.server.repository.MemberRepository;
import cmc.hackaton.server.repository.RecordRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class RecordService {


    private final MemberRepository memberRepository;
    //private final TeamRepository teamRepository;
    private final RecordRepository recordRepository;

    public String findAllRecordsByMemberId(String token) {
        Member member = memberRepository.findByToken(token)
                .orElseThrow(MemberNotFoundException::new);
        List<Record> allByMember = recordRepository.findAllByMember(member);
        System.out.println(allByMember);

        return token;
    }

    public List<RecordDto> findAllRecords(String token) {
        Member member = memberRepository.findByToken(token)
                .orElseThrow(MemberNotFoundException::new);
        return recordRepository.findAllByMember(member).stream()
            .map(RecordDto::from)
            .collect(Collectors.toList());

    }

    //public List<RecordDto> findAllRecordsByTeamId(String teamId) {
       // return recordRepository.findAllByRecords_MemberId(teamId).stream()
         //       .collect(Collectors.toList());
    //}

    //@Transactional
    //public TeamDto addRecord(String memberToken, TeamDto dto) {

   // };
    //private void validateMemberToken(String memberToken) {
        //if (!memberRepository.existsByToken(memberToken)) {
           // throw new MemberNotFoundException();
        //}
    //}

}
