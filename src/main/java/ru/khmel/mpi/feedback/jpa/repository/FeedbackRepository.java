package ru.khmel.mpi.feedback.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import ru.khmel.mpi.feedback.jpa.entity.Feedback;

public interface FeedbackRepository extends JpaRepository<Feedback, Long>,
    QuerydslPredicateExecutor<Feedback> {
}
