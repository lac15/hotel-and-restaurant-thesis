package hu.unideb.inf.thesis.hotel.service.impl;

import hu.unideb.inf.thesis.hotel.client.api.service.ReservedTimeService;
import hu.unideb.inf.thesis.hotel.client.api.vo.ReservedTimeVo;
import hu.unideb.inf.thesis.hotel.core.entitiy.ReservedTimeEntity;
import hu.unideb.inf.thesis.hotel.core.repository.ReservedTimeRepository;
import hu.unideb.inf.thesis.hotel.service.mapper.ReservedTimeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ejb.interceptor.SpringBeanAutowiringInterceptor;

import javax.ejb.*;
import javax.interceptor.Interceptors;
import java.util.Date;
import java.util.List;

@Stateless(name = "ReservedTimeService", mappedName = "ReservedTimeService")
@TransactionAttribute(TransactionAttributeType.REQUIRED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@Local(ReservedTimeService.class)
@Interceptors({SpringBeanAutowiringInterceptor.class})
public class ReservedTimeServiceImpl implements ReservedTimeService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReservedTimeServiceImpl.class);

    @Autowired
    private ReservedTimeRepository reservedTimeRepository;

    @Override
    public List<ReservedTimeVo> getReservedTimes() {
        return ReservedTimeMapper.toVo(reservedTimeRepository.findAll());
    }

    @Override
    public ReservedTimeVo saveReservedTime(ReservedTimeVo reservedTimeVo) {
        ReservedTimeEntity reservedTimeEntity = reservedTimeRepository.findByReservedTime(reservedTimeVo.getReservedTime());

        if (reservedTimeEntity == null) {
            reservedTimeEntity = new ReservedTimeEntity();
            ReservedTimeMapper.toEntity(reservedTimeVo, reservedTimeEntity);
        }

        return ReservedTimeMapper.toVo(reservedTimeRepository.save(reservedTimeEntity));
    }

    @Override
    public ReservedTimeVo getReservedTimeById(Long id) {
        return ReservedTimeMapper.toVo(reservedTimeRepository.findOne(id));
    }

    @Override
    public ReservedTimeVo getReservedTimeByReservedTime(Date reservedTime) {
        return ReservedTimeMapper.toVo(reservedTimeRepository.findByReservedTime(reservedTime));
    }

    @Override
    public List<ReservedTimeVo> getReservedTimesByTableId(Long tableId) {
        return ReservedTimeMapper.toVo(reservedTimeRepository.findReservedTimesByTableId(tableId));
    }
}
