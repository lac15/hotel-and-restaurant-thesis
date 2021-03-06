package hu.unideb.inf.thesis.hotel.service.impl;

import hu.unideb.inf.thesis.hotel.client.api.service.TableService;
import hu.unideb.inf.thesis.hotel.client.api.vo.ReservedTimeVo;
import hu.unideb.inf.thesis.hotel.client.api.vo.TableVo;
import hu.unideb.inf.thesis.hotel.core.entitiy.ReservedTimeEntity;
import hu.unideb.inf.thesis.hotel.core.entitiy.TableEntity;
import hu.unideb.inf.thesis.hotel.core.repository.ReservedTimeRepository;
import hu.unideb.inf.thesis.hotel.core.repository.TableRepository;
import hu.unideb.inf.thesis.hotel.service.mapper.TableMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ejb.interceptor.SpringBeanAutowiringInterceptor;

import javax.ejb.*;
import javax.interceptor.Interceptors;
import java.util.List;

@Stateless(name = "TableService", mappedName = "TableService")
@TransactionAttribute(TransactionAttributeType.REQUIRED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@Local(TableService.class)
@Interceptors({SpringBeanAutowiringInterceptor.class})
public class TableServiceImpl implements TableService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TableServiceImpl.class);

    @Autowired
    private TableRepository tableRepository;
    @Autowired
    private ReservedTimeRepository reservedTimeRepository;

    @Override
    public List<TableVo> getTables() {
        return TableMapper.toVo(tableRepository.findAll());
    }

    @Override
    public TableVo saveTable(TableVo tableVo) {
        TableEntity tableEntity = tableRepository.findOne(tableVo.getId());

        if (tableEntity == null) {
            tableEntity = new TableEntity();
            TableMapper.toEntity(tableVo, tableEntity);
        }

        return TableMapper.toVo(tableRepository.save(tableEntity));
    }

    @Override
    public void addReservedTimeToTable(TableVo tableVo, ReservedTimeVo reservedTimeVo) {
        TableEntity tableEntity = tableRepository.findByNumber(tableVo.getNumber());
        ReservedTimeEntity reservedTimeEntity = reservedTimeRepository.findOne(reservedTimeVo.getId());

        tableEntity.getReservedTimes().add(reservedTimeEntity);
    }

    @Override
    public TableVo getTableById(Long id) {
        return TableMapper.toVo(tableRepository.findOne(id));
    }

    @Override
    public TableVo getTableByNumber(int number) {
        return TableMapper.toVo(tableRepository.findByNumber(number));
    }

}
