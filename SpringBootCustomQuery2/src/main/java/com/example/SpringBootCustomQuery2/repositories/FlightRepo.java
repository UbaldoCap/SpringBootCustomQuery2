package com.example.SpringBootCustomQuery2.repositories;

import com.example.SpringBootCustomQuery2.Status;
import com.example.SpringBootCustomQuery2.entities.Flight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlightRepo extends JpaRepository<Flight, Long> {
    List<Flight> findAllByOrderByFromAirportAsc(PageRequest pageRequest);
    List<Flight> findAllByStatus(Enum<Status> statusEnum);

    @Query("select f from Flight f where f.status in (:statuslist)")
    List<Flight> findAllByStatusList(List<Enum<Status>> statuslist);
}
