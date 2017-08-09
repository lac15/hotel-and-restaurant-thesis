package hu.unideb.inf.thesis.hotel.service.impl;

import hu.unideb.inf.thesis.hotel.client.api.service.RoomService;
import hu.unideb.inf.thesis.hotel.client.api.vo.ReservedDateVo;
import hu.unideb.inf.thesis.hotel.client.api.vo.RoomReserveVo;
import hu.unideb.inf.thesis.hotel.client.api.vo.RoomVo;
import hu.unideb.inf.thesis.hotel.core.entitiy.ReservedDateEntity;
import hu.unideb.inf.thesis.hotel.core.entitiy.RoomEntity;
import hu.unideb.inf.thesis.hotel.core.entitiy.RoomReserveEntity;
import hu.unideb.inf.thesis.hotel.core.repository.ReservedDateRepository;
import hu.unideb.inf.thesis.hotel.core.repository.RoomRepository;
import hu.unideb.inf.thesis.hotel.core.repository.RoomReserveRepository;
import hu.unideb.inf.thesis.hotel.service.mapper.RoomMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ejb.interceptor.SpringBeanAutowiringInterceptor;

import javax.ejb.*;
import javax.interceptor.Interceptors;
import java.util.List;

@Stateless(name = "RoomService", mappedName = "RoomService")
@TransactionAttribute(TransactionAttributeType.REQUIRED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@Local(RoomService.class)
@Interceptors({SpringBeanAutowiringInterceptor.class})
public class RoomServiceImpl implements RoomService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RoomServiceImpl.class);

    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private ReservedDateRepository reservedDateRepository;
    @Autowired
    private RoomReserveRepository roomReserveRepository;

    @Override
    public List<RoomVo> getRooms() {
        return RoomMapper.toVo(roomRepository.findAll());
    }

    @Override
    public RoomVo saveRoom(RoomVo roomVo) {
        RoomEntity roomEntity = roomRepository.findOne(roomVo.getId());

        if (roomEntity == null) {
            roomEntity = new RoomEntity();
            RoomMapper.toEntity(roomVo, roomEntity);
        }

        return RoomMapper.toVo(roomRepository.save(roomEntity));
    }

    @Override
    public void addReservedDateToRoom(RoomVo roomVo, ReservedDateVo reservedDateVo) {
        RoomEntity roomEntity = roomRepository.findByNumber(roomVo.getNumber());
        ReservedDateEntity reservedDateEntity = reservedDateRepository.findOne(reservedDateVo.getId());

        roomEntity.getReservedDates().add(reservedDateEntity);
    }

    @Override
    public RoomVo getRoomById(Long id) {
        return RoomMapper.toVo(roomRepository.findOne(id));
    }

    @Override
    public RoomVo getRoomByNumber(int number) {
        return RoomMapper.toVo(roomRepository.findByNumber(number));
    }

}
