package hu.unideb.inf.thesis.hotel.service.impl;

import hu.unideb.inf.thesis.hotel.client.api.service.RoleService;
import hu.unideb.inf.thesis.hotel.client.api.vo.RoleVo;
import hu.unideb.inf.thesis.hotel.core.entitiy.RoleEntity;
import hu.unideb.inf.thesis.hotel.core.repository.RoleRepository;
import hu.unideb.inf.thesis.hotel.service.mapper.RoleMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ejb.interceptor.SpringBeanAutowiringInterceptor;

import javax.ejb.*;
import javax.interceptor.Interceptors;
import java.util.List;

@Stateless(name = "RoleService", mappedName = "RoleService")
@TransactionAttribute(TransactionAttributeType.REQUIRED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@Local(RoleService.class)
@Interceptors({SpringBeanAutowiringInterceptor.class})
public class RoleServiceImpl implements RoleService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RoleServiceImpl.class);

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<RoleVo> getRoles() {
        return RoleMapper.toVo(roleRepository.findAll());
    }

    @Override
    public RoleVo saveRole(RoleVo roleVo) {
        RoleEntity roleEntity = roleRepository.findOne(roleVo.getId());

        if (roleEntity == null) {
            roleEntity = new RoleEntity();
            RoleMapper.toEntity(roleVo, roleEntity);
        }

        return RoleMapper.toVo(roleRepository.save(roleEntity));
    }

    @Override
    public RoleVo getRoleById(Long id) {
        return RoleMapper.toVo(roleRepository.findOne(id));
    }

    @Override
    public RoleVo getRoleByName(String name) {
        return RoleMapper.toVo(roleRepository.findByName(name));
    }

    @Override
    public List<RoleVo> getRolesByUserId(Long userId) {
        return RoleMapper.toVo(roleRepository.findRolesByUserId(userId));
    }

}
