package com.demo.demo.repository;

import com.demo.demo.message.request.UpdateUser;
import com.demo.demo.model.Project;
import com.demo.demo.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
@Component
public interface UserRepository extends JpaRepository <Users, Long>{
    Optional<Users> findByUserName(String userName);
//    Optional<Users> findById(Long id);
    Boolean existsByUserName(String userName);
    Boolean existsByEmail(String email);
    Optional<Users> findByEmail(String email);
    @Query("SELECT u.emailVerified FROM Users u WHERE u.email = ?1")
    Boolean findEmailVerifiedByEmail(String email);

    @Query("select p from Users p where lower(p.userName) like concat('%', :userName, '%')")
    List<Users>search (String userName);

    @Query("select p from Users p where lower(p.email) like concat('%', :email, '%')")
    List<Users>searchEmail (String email);

    @Query("select p from Users p where lower(p.specialized) like concat('%', :specialized, '%')")
    List<Users>searchSpecialized (String specialized);

    @Query("select u from Users u where u.deleteUser = :isdelete")
    List<Users> findDeleteUser(Boolean isdelete);

//    List<Users> findAllByBirthday(Date dob);
}
