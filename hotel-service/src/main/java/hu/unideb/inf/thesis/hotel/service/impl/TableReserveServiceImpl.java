package hu.unideb.inf.thesis.hotel.service.impl;

import hu.unideb.inf.thesis.hotel.client.api.service.TableReserveService;
import hu.unideb.inf.thesis.hotel.client.api.vo.TableReserveVo;
import hu.unideb.inf.thesis.hotel.client.api.vo.TableVo;
import hu.unideb.inf.thesis.hotel.core.entitiy.TableEntity;
import hu.unideb.inf.thesis.hotel.core.entitiy.TableReserveEntity;
import hu.unideb.inf.thesis.hotel.core.repository.TableRepository;
import hu.unideb.inf.thesis.hotel.core.repository.TableReserveRepository;
import hu.unideb.inf.thesis.hotel.service.mapper.TableMapper;
import hu.unideb.inf.thesis.hotel.service.mapper.TableReserveMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ejb.interceptor.SpringBeanAutowiringInterceptor;

import javax.ejb.*;
import javax.interceptor.Interceptors;
import java.util.Date;
import java.util.List;

@Stateless(name = "TableReserveService", mappedName = "TableReserveService")
@TransactionAttribute(TransactionAttributeType.REQUIRED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@Local(TableReserveService.class)
@Interceptors({SpringBeanAutowiringInterceptor.class})
public class TableReserveServiceImpl implements TableReserveService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TableReserveServiceImpl.class);

    @Autowired
    private TableReserveRepository tableReserveRepository;
    @Autowired
    private TableRepository tableRepository;

    @Override
    public TableReserveVo saveTableReserve(TableReserveVo tableReserveVo, TableVo tableVo) {
        TableEntity tableEntity = tableRepository.findOne(tableVo.getId());

        TableReserveEntity tableReserveEntity = tableReserveRepository.findByStartTimeAndEndTimeAndTableEntity(
                tableReserveVo.getStartTime(), tableReserveVo.getEndTime(), tableEntity);

        if (tableReserveEntity == null) {
            tableReserveEntity = new TableReserveEntity();
            TableReserveMapper.toEntity(tableReserveVo, tableReserveEntity);
            tableReserveEntity.setTableEntity(tableEntity);
        }

        return TableReserveMapper.toVo(tableReserveRepository.save(tableReserveEntity));
    }

    @Override
    public TableReserveVo getTableReserveById(Long id) {
        return TableReserveMapper.toVo(tableReserveRepository.findOne(id));
    }

    @Override
    public List<TableReserveVo> getTableReservesByStartTime(Date startTime) {
        return TableReserveMapper.toVo(tableReserveRepository.findByStartTime(startTime));
    }

    @Override
    public List<TableReserveVo> getTableReservesByEndTime(Date endTime) {
        return TableReserveMapper.toVo(tableReserveRepository.findByEndTime(endTime));
    }

}
