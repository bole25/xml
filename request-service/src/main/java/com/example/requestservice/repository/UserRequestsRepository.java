package com.example.requestservice.repository;

import com.example.requestservice.model.UserRequests;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRequestsRepository extends JpaRepository<UserRequests, Long> {

    @Query(value = "select * from user_requests where username = ?1", nativeQuery = true)
    UserRequests getRequestsByUsername(String username);

    @Query(value = "SELECT COUNT(ur.username) FROM (requests.user_requests  AS ur INNER JOIN requests.user_requests_requests AS urr" +
            " ON ur.id=urr.user_requests_id INNER JOIN requests.request AS r ON urr.requests_id=r.id) WHERE (" +
            "(ur.username = ?1 OR ur.username = ?2) AND (r.owner_username = ?1 OR r.owner_username = ?2) AND " +
            "r.status = 1)", nativeQuery = true)
    Long connected(String sender, String receiver);
}
