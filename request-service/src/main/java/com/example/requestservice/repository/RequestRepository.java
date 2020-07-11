package com.example.requestservice.repository;

import com.example.requestservice.model.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Set;

@Repository
public interface RequestRepository extends JpaRepository<Request, Long> {

    @Query(value = "SELECT * FROM requests.request WHERE owner_username = ?1 AND status = 1", nativeQuery = true)
    Set<Request> getOwnersUpcoming(String username);

    @Query(value = "SELECT * FROM requests.request WHERE owner_username = ?1 AND status = 0", nativeQuery = true)
    Set<Request> getOwnersPending(String username);

    @Query("SELECT u FROM Request u WHERE u.status = 0")
    Set<Request> getVehiclesPending();

    @Modifying
    @Query(value = "UPDATE requests.request SET status = '1' WHERE (id = ?1)", nativeQuery = true)
    void approveRequest(Long id);

    @Modifying
    @Query(value = "UPDATE requests.request SET status = '2' WHERE (id = ?1)", nativeQuery = true)
    void rejectRequest(Long id);

    @Query(value = "SELECT * FROM requests.request WHERE status = 0", nativeQuery = true)
    Set<Request> getAllPendingRequests();

}
