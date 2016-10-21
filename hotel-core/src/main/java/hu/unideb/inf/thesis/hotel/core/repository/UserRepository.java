package hu.unideb.inf.thesis.hotel.core.repository;


import hu.unideb.inf.thesis.hotel.core.entitiy.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    UserEntity findByName(String name);

    UserEntity findByEmail(String email);

    //List<Role> findRolesByName(String name);

    //List<UserEntity> getAllUser();

}
