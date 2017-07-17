package hu.unideb.inf.thesis.hotel.service.impl;

import hu.unideb.inf.thesis.hotel.client.api.service.RoomTypeService;
import hu.unideb.inf.thesis.hotel.client.api.vo.RoomTypeVo;
import hu.unideb.inf.thesis.hotel.core.entitiy.RoomTypeEntity;
import hu.unideb.inf.thesis.hotel.core.repository.RoomTypeRepository;
import hu.unideb.inf.thesis.hotel.service.mapper.RoomTypeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ejb.interceptor.SpringBeanAutowiringInterceptor;

import javax.ejb.*;
import javax.interceptor.Interceptors;
import java.util.List;

@Stateless(name = "RoomTypeService", mappedName = "RoomTypeService")
@TransactionAttribute(TransactionAttributeType.REQUIRED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@Local(RoomTypeService.class)
@Interceptors({SpringBeanAutowiringInterceptor.class})
public class RoomTypeServiceImpl implements RoomTypeService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RoomTypeServiceImpl.class);

    @Autowired
    private RoomTypeRepository roomTypeRepository;

    @Override
    public List<RoomTypeVo> getRoomTypes() {
        return RoomTypeMapper.toVo(roomTypeRepository.findAll());
    }

    @Override
    public RoomTypeVo saveRoomType(RoomTypeVo roomTypeVo) {
        RoomTypeEntity roomTypeEntity = roomTypeRepository.findOne(roomTypeVo.getId());
        if (roomTypeEntity == null) {
            roomTypeEntity = new RoomTypeEntity();
        }
        RoomTypeMapper.toEntity(roomTypeVo, roomTypeEntity);
        return RoomTypeMapper.toVo(roomTypeRepository.save(roomTypeEntity));
    }

    @Override
    public List<RoomTypeVo> getRoomByCapacity(int capacity) {
        return RoomTypeMapper.toVo(roomTypeRepository.findByCapacity(capacity));
    }

    @Override
    public RoomTypeVo getRoomTypeById(Long id) {
        return RoomTypeMapper.toVo(roomTypeRepository.findOne(id));
    }

}
