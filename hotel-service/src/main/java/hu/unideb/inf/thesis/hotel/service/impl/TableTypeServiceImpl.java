package hu.unideb.inf.thesis.hotel.service.impl;

import hu.unideb.inf.thesis.hotel.client.api.service.TableTypeService;
import hu.unideb.inf.thesis.hotel.client.api.vo.TableTypeVo;
import hu.unideb.inf.thesis.hotel.client.api.vo.TableVo;
import hu.unideb.inf.thesis.hotel.core.entitiy.TableTypeEntity;
import hu.unideb.inf.thesis.hotel.core.repository.TableTypeRepository;
import hu.unideb.inf.thesis.hotel.service.mapper.TableMapper;
import hu.unideb.inf.thesis.hotel.service.mapper.TableTypeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ejb.interceptor.SpringBeanAutowiringInterceptor;

import javax.ejb.*;
import javax.interceptor.Interceptors;
import java.util.List;

@Stateless(name = "TableTypeService", mappedName = "TableTypeService")
@TransactionAttribute(TransactionAttributeType.REQUIRED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@Local(TableTypeService.class)
@Interceptors({SpringBeanAutowiringInterceptor.class})
public class TableTypeServiceImpl implements TableTypeService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TableTypeServiceImpl.class);

    @Autowired
    private TableTypeRepository tableTypeRepository;

    @Override
    public List<TableTypeVo> getTableTypes() {
        return TableTypeMapper.toVo(tableTypeRepository.findAll());
    }

    @Override
    public TableTypeVo saveTableType(TableTypeVo tableTypeVo) {
        TableTypeEntity tableTypeEntity = tableTypeRepository.findOne(tableTypeVo.getId());

        if (tableTypeEntity == null) {
            tableTypeEntity = new TableTypeEntity();
            TableTypeMapper.toEntity(tableTypeVo, tableTypeEntity);
        }

        return TableTypeMapper.toVo(tableTypeRepository.save(tableTypeEntity));
    }

    @Override
    public TableTypeVo getTableTypeBySeats(int seats) {
        return TableTypeMapper.toVo(tableTypeRepository.findBySeats(seats));
    }

    @Override
    public TableTypeVo getTableTypeById(Long id) {
        return TableTypeMapper.toVo(tableTypeRepository.findOne(id));
    }

    @Override
    public List<TableVo> getTablesByTableTypeId(Long tableTypeId) {
        return TableMapper.toVo(tableTypeRepository.findOne(tableTypeId).getTables());
    }
}
