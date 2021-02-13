package me.parkprin.architecture_study.service.member;

import me.parkprin.architecture_study.domain.member.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    @Autowired
    public MemberRepository memberRepository;


}
