package hu.unideb.inf.thesis.hotel.service.impl;

import hu.unideb.inf.thesis.hotel.client.api.service.ReservedDateService;
import hu.unideb.inf.thesis.hotel.client.api.vo.ReservedDateVo;
import hu.unideb.inf.thesis.hotel.core.entitiy.ReservedDateEntity;
import hu.unideb.inf.thesis.hotel.core.repository.ReservedDateRepository;
import hu.unideb.inf.thesis.hotel.service.mapper.ReservedDateMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ejb.interceptor.SpringBeanAutowiringInterceptor;

import javax.ejb.*;
import javax.interceptor.Interceptors;
import java.util.Date;
import java.util.List;

@Stateless(name = "ReservedDateService", mappedName = "ReservedDateService")
@TransactionAttribute(TransactionAttributeType.REQUIRED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@Local(ReservedDateService.class)
@Interceptors({SpringBeanAutowiringInterceptor.class})
public class ReservedDateServiceImpl implements ReservedDateService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReservedDateServiceImpl.class);

    @Autowired
    private ReservedDateRepository reservedDateRepository;

    @Override
    public List<ReservedDateVo> getReservedDates() {
        return ReservedDateMapper.toVo(reservedDateRepository.findAll());
    }

    @Override
    public ReservedDateVo saveReservedDate(ReservedDateVo reservedDateVo) {
        ReservedDateEntity reservedDateEntity = reservedDateRepository.findByReservedDate(reservedDateVo.getReservedDate());

        if (reservedDateEntity == null) {
            reservedDateEntity = new ReservedDateEntity();
            ReservedDateMapper.toEntity(reservedDateVo, reservedDateEntity);
        }

        return ReservedDateMapper.toVo(reservedDateRepository.save(reservedDateEntity));
    }

    @Override
    public ReservedDateVo getReservedDateById(Long id) {
        return ReservedDateMapper.toVo(reservedDateRepository.findOne(id));
    }

    @Override
    public ReservedDateVo getReservedDateByReservedDate(Date reservedDate) {
        return ReservedDateMapper.toVo(reservedDateRepository.findByReservedDate(reservedDate));
    }

    @Override
    public List<ReservedDateVo> getReservedDatesByRoomId(Long roomId) {
        return ReservedDateMapper.toVo(reservedDateRepository.findReservedDatesByRoomId(roomId));
    }
}
