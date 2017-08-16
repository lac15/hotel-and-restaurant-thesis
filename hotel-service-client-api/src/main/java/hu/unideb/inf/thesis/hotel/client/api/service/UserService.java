package hu.unideb.inf.thesis.hotel.client.api.service;

import hu.unideb.inf.thesis.hotel.client.api.vo.*;

import java.util.List;

public interface UserService {

    UserVo saveUser(UserVo userVo);

    UserVo getUserById(Long id);

    UserVo getUserByUsername(String username);

    UserVo getUserByEmail(String email);

    List<UserVo> getUsers();

    Long countUsers();

    void addRoomReserveToUser(UserVo userVo, RoomReserveVo roomReserveVo);

    void addTableReserveToUser(UserVo userVo, TableReserveVo tableReserveVo);

    void addOrderToUser(UserVo UserVo, OrderVo OrderVo);

    void addRoleToUserByUsername(String username, RoleVo roleVo);

    void removeRoleFromUserByUsername(String username, RoleVo roleVo);

    void setUserActivityByUsername(String username, boolean activity);

    void registerUser(UserVo user);
}
