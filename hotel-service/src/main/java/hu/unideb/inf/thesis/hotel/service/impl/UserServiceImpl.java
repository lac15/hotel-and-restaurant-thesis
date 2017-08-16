package hu.unideb.inf.thesis.hotel.service.impl;

import hu.unideb.inf.thesis.hotel.client.api.service.UserService;
import hu.unideb.inf.thesis.hotel.client.api.vo.*;
import hu.unideb.inf.thesis.hotel.core.entitiy.*;
import hu.unideb.inf.thesis.hotel.core.repository.*;
import hu.unideb.inf.thesis.hotel.service.mapper.RoleMapper;
import hu.unideb.inf.thesis.hotel.service.mapper.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ejb.interceptor.SpringBeanAutowiringInterceptor;

import javax.ejb.*;
import javax.interceptor.Interceptors;
import java.util.ArrayList;
import java.util.List;

@Stateless(name = "UserService", mappedName = "UserService")
@TransactionAttribute(TransactionAttributeType.REQUIRED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@Local(UserService.class)
@Interceptors({SpringBeanAutowiringInterceptor.class})
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    public static final String DEFAULT_USER_ROLE = "ROLE_USER";

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private RoomReserveRepository roomReserveRepository;
    @Autowired
    private TableReserveRepository tableReserveRepository;
    @Autowired
    private OrderRepository orderRepository;

    @Override
    public List<UserVo> getUsers() {
        return UserMapper.toVo(userRepository.findAll());
    }

    @Override
    public UserVo saveUser(UserVo userVo) {
        UserEntity userEntity = userRepository.findOne(userVo.getId());

        if (userEntity == null) {
            userEntity = new UserEntity();
            UserMapper.toEntity(userVo, userEntity);
        }

        return UserMapper.toVo(userRepository.save(userEntity));
    }

    @Override
    public UserVo getUserById(Long id) {
        return UserMapper.toVo(userRepository.findOne(id));
    }

    @Override
    public UserVo getUserByUsername(String username) {
        return UserMapper.toVo(userRepository.findByUsername(username));
    }

    @Override
    public UserVo getUserByEmail(String email) {
        return UserMapper.toVo(userRepository.findByEmail(email));
    }

    @Override
    public Long countUsers() {
        return userRepository.count();
    }

    @Override
    public void addRoomReserveToUser(UserVo userVo, RoomReserveVo roomReserveVo) {
        UserEntity userEntity = userRepository.findOne(userVo.getId());
        RoomReserveEntity roomReserveEntity = roomReserveRepository.findOne(roomReserveVo.getId());

        userEntity.getRoomReserves().add(roomReserveEntity);
    }

    @Override
    public void addTableReserveToUser(UserVo userVo, TableReserveVo tableReserveVo) {
        UserEntity userEntity = userRepository.findOne(userVo.getId());
        TableReserveEntity tableReserveEntity = tableReserveRepository.findOne(tableReserveVo.getId());

        userEntity.getTableReserves().add(tableReserveEntity);
    }

    @Override
    public void addOrderToUser(UserVo userVo, OrderVo orderVo) {
        UserEntity userEntity = userRepository.findOne(userVo.getId());
        OrderEntity orderEntity = orderRepository.findOne(orderVo.getId());

        userEntity.getOrders().add(orderEntity);
    }

    @Override
    public void addRoleToUserByUsername(String username, RoleVo roleVo) {
        boolean contains = false;

        for (RoleEntity role : userRepository.findByUsername(username).getRoles()) {
            contains = role.getName().equals(roleVo.getName());
            if (contains) {
                break;
            }
        }

        if (!contains) {
            userRepository.findByUsername(username).getRoles().add(RoleMapper.toEntity(roleVo));
        }
    }

    @Override
    public void removeRoleFromUserByUsername(String username, RoleVo roleVo) {
        List<RoleEntity> newRoles = new ArrayList<RoleEntity>();

        for (RoleEntity role : userRepository.findByUsername(username).getRoles()) {
            if (!(role.getName().equals(roleVo.getName()))) {
                newRoles.add(role);
            }
        }
        userRepository.findByUsername(username).setRoles(newRoles);
    }

    @Override
    public void setUserActivityByUsername(String username, boolean activity) {
        userRepository.findByUsername(username).setActive(activity);
    }

    @Override
    public void registerUser(UserVo user) {

        UserEntity userEntity = UserMapper.toEntity(user);
        if (userEntity.getRoles() == null) {
            userEntity.setRoles(new ArrayList<>(1));
        }

        addDefaultRole(userEntity);
        userRepository.save(userEntity);
    }

    private void addDefaultRole(UserEntity userEntity) {
        RoleEntity role = roleRepository.findByName(DEFAULT_USER_ROLE);
        userEntity.getRoles().add(role);
    }

}
