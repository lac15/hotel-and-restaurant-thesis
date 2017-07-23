package hu.unideb.inf.thesis.hotel.service.impl;

import hu.unideb.inf.thesis.hotel.client.api.service.RoomReserveService;
import hu.unideb.inf.thesis.hotel.client.api.vo.RoomReserveVo;
import hu.unideb.inf.thesis.hotel.client.api.vo.RoomVo;
import hu.unideb.inf.thesis.hotel.core.entitiy.RoomReserveEntity;
import hu.unideb.inf.thesis.hotel.core.repository.RoomReserveRepository;
import hu.unideb.inf.thesis.hotel.service.mapper.RoomMapper;
import hu.unideb.inf.thesis.hotel.service.mapper.RoomReserveMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ejb.interceptor.SpringBeanAutowiringInterceptor;

import javax.ejb.*;
import javax.interceptor.Interceptors;
import java.util.Date;
import java.util.List;

@Stateless(name = "RoomReserveService", mappedName = "RoomReserveService")
@TransactionAttribute(TransactionAttributeType.REQUIRED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@Local(RoomReserveService.class)
@Interceptors({SpringBeanAutowiringInterceptor.class})
public class RoomReserveServiceImpl implements RoomReserveService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RoomReserveServiceImpl.class);

    @Autowired
    private RoomReserveRepository roomReserveRepository;

    @Override
    public RoomReserveVo saveRoomReserve(RoomReserveVo roomReserveVo) {
        RoomReserveEntity roomReserveEntity = new RoomReserveEntity();

        RoomReserveMapper.toEntity(roomReserveVo, roomReserveEntity);
        return RoomReserveMapper.toVo(roomReserveRepository.save(roomReserveEntity));
    }

    @Override
    public RoomReserveVo getRoomReserveById(Long id) {
        return RoomReserveMapper.toVo(roomReserveRepository.findOne(id));
    }

    @Override
    public List<RoomReserveVo> getRoomReservesByStartTime(Date startTime) {
        return RoomReserveMapper.toVo(roomReserveRepository.findByStartTime(startTime));
    }

    @Override
    public List<RoomReserveVo> getRoomReservesByEndTime(Date endTime) {
        return RoomReserveMapper.toVo(roomReserveRepository.findByEndTime(endTime));
    }

}
