package com.example.dstay.votes.Repository;

import com.example.dstay.votes.Entity.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VoteRepository extends JpaRepository<Vote, Long> {

    Optional<Vote> findByUsers(Long id);

}
