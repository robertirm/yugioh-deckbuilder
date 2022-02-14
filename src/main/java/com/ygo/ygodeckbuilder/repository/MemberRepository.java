package com.ygo.ygodeckbuilder.repository;

import com.ygo.ygodeckbuilder.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member,Long> {
    Member findByEmail(String email);
}