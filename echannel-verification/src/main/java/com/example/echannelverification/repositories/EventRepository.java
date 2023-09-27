package com.example.echannelverification.repositories;

import com.example.echannelverification.enums.Status;
import com.example.echannelverification.models.EventSource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<EventSource, Long> {
    List<EventSource> findAllByStatus(Status status);
}
