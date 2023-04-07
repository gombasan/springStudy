package com.example.demo.member;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class MemoryMemberRepository implements MemberRepository{

    private static final Map<Long,Member> repository = new HashMap<>();

    @Override
    public void save(Member member) {
        repository.put(member.getId(),member);
    }

    @Override
    public Member findById(Long memberId) {
        return repository.get(memberId);
    }
}
