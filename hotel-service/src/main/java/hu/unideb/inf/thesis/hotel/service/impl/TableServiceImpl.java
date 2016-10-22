package hu.unideb.inf.thesis.hotel.service.impl;

import hu.unideb.inf.thesis.hotel.client.api.service.TableService;
import hu.unideb.inf.thesis.hotel.client.api.vo.TableVo;
import hu.unideb.inf.thesis.hotel.core.entitiy.TableEntity;
import hu.unideb.inf.thesis.hotel.core.repository.TableRepository;
import hu.unideb.inf.thesis.hotel.service.mapper.TableMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ejb.interceptor.SpringBeanAutowiringInterceptor;

import javax.ejb.*;
import javax.interceptor.Interceptors;

@Stateless(name = "TableService", mappedName = "TableService")
@TransactionAttribute(TransactionAttributeType.REQUIRED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@Local(TableService.class)
@Interceptors({SpringBeanAutowiringInterceptor.class})
public class TableServiceImpl implements TableService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TableServiceImpl.class);

    @Autowired
    private TableRepository tableRepository;

    /*@Override
    public List<RoleVo> getRoles() {
        return RoleMapper.toVo(roleRepository.findAll());
    }*/

    @Override
    public TableVo saveTable(TableVo tableVo) {
        TableEntity tableEntity = tableRepository.findOne(tableVo.getId());
        if (tableEntity == null) {
            tableEntity = new TableEntity();
        }
        TableMapper.toEntity(tableVo, tableEntity);
        return TableMapper.toVo(tableRepository.save(tableEntity));
    }

    @Override
    public TableVo getTableById(Long id) {
        return TableMapper.toVo(tableRepository.findOne(id));
    }

    @Override
    public TableVo getTableByNumber(int number) {
        return TableMapper.toVo(tableRepository.findByNumber(number));
    }

    @Override
    public void setTableReservedByNumber(int number, boolean reserved) {
        tableRepository.findByNumber(number).setReserved(reserved);
    }

}
