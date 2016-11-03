package hu.unideb.inf.thesis.hotel.service.impl;

import hu.unideb.inf.thesis.hotel.client.api.service.FoodTypeService;
import hu.unideb.inf.thesis.hotel.client.api.vo.FoodTypeVo;
import hu.unideb.inf.thesis.hotel.core.entitiy.FoodTypeEntity;
import hu.unideb.inf.thesis.hotel.core.repository.FoodTypeRepository;
import hu.unideb.inf.thesis.hotel.service.mapper.FoodTypeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ejb.interceptor.SpringBeanAutowiringInterceptor;

import javax.ejb.*;
import javax.interceptor.Interceptors;

@Stateless(name = "FoodTypeService", mappedName = "FoodTypeService")
@TransactionAttribute(TransactionAttributeType.REQUIRED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@Local(FoodTypeService.class)
@Interceptors({SpringBeanAutowiringInterceptor.class})
public class FoodTypeServiceImpl implements FoodTypeService {

    private static final Logger LOGGER = LoggerFactory.getLogger(FoodTypeServiceImpl.class);

    @Autowired
    private FoodTypeRepository foodTypeRepository;

    /*@Override
    public List<FoodVo> getFoods() {
        return FoodMapper.toVo(foodRepository.findAll());
    }*/

    @Override
    public FoodTypeVo saveFoodType(FoodTypeVo foodTypeVo) {
        FoodTypeEntity foodTypeEntity = foodTypeRepository.findOne(foodTypeVo.getId());
        if (foodTypeEntity == null) {
            foodTypeEntity = new FoodTypeEntity();
        }
        FoodTypeMapper.toEntity(foodTypeVo, foodTypeEntity);
        return FoodTypeMapper.toVo(foodTypeRepository.save(foodTypeEntity));
    }

    @Override
    public FoodTypeVo getFoodTypeById(Long id) {
        return FoodTypeMapper.toVo(foodTypeRepository.findOne(id));
    }

    @Override
    public FoodTypeVo getFoodTypeByName(String name) {
        return FoodTypeMapper.toVo(foodTypeRepository.findByName(name));
    }
}
