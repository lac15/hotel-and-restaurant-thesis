package hu.unideb.inf.thesis.hotel.client.api.service;

import hu.unideb.inf.thesis.hotel.client.api.vo.RoleVo;

import java.util.List;

public interface RoleService {

    RoleVo saveRole(RoleVo roleVo);

    RoleVo getRoleById(Long id);

    RoleVo getRoleByName(String name);

    List<RoleVo> getRoles();

    List<RoleVo> getRolesByUserId(Long userId);

}
