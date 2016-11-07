package hu.unideb.inf.thesis.hotel.service.impl;

import hu.unideb.inf.thesis.hotel.client.api.service.FoodService;
import hu.unideb.inf.thesis.hotel.client.api.vo.FoodVo;
import hu.unideb.inf.thesis.hotel.core.entitiy.FoodEntity;
import hu.unideb.inf.thesis.hotel.core.repository.FoodRepository;
import hu.unideb.inf.thesis.hotel.core.repository.FoodTypeRepository;
import hu.unideb.inf.thesis.hotel.service.mapper.FoodMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ejb.interceptor.SpringBeanAutowiringInterceptor;

import javax.ejb.*;
import javax.interceptor.Interceptors;
import java.util.List;

@Stateless(name = "FoodService", mappedName = "FoodService")
@TransactionAttribute(TransactionAttributeType.REQUIRED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@Local(FoodService.class)
@Interceptors({SpringBeanAutowiringInterceptor.class})
public class FoodServiceImpl implements FoodService {

    private static final Logger LOGGER = LoggerFactory.getLogger(FoodServiceImpl.class);

    @Autowired
    private FoodRepository foodRepository;
    @Autowired
    private FoodTypeRepository foodTypeRepository;

    @Override
    public FoodVo saveFood(FoodVo foodVo) {
        FoodEntity foodEntity = foodRepository.findOne(foodVo.getId());
        if (foodEntity == null) {
            foodEntity = new FoodEntity();
        }
        FoodMapper.toEntity(foodVo, foodEntity);
        return FoodMapper.toVo(foodRepository.save(foodEntity));
    }

    @Override
    public void saveFoodWithType(FoodVo foodVo, String typeName) {
        saveFood(foodVo);
        foodTypeRepository.findByName(typeName).getFoods().add(FoodMapper.toEntity(foodVo));
    }

    @Override
    public void deleteFood(Long id, String typeName) {
        foodTypeRepository.findByName(typeName).getFoods().remove(foodRepository.findOne(id));
        foodRepository.delete(id);
    }

    @Override
    public FoodVo getFoodById(Long id) {
        return FoodMapper.toVo(foodRepository.findOne(id));
    }

    @Override
    public FoodVo getFoodByName(String name) {
        return FoodMapper.toVo(foodRepository.findByName(name));
    }

    @Override
    public FoodVo getFoodByPrice(int price) {
        return FoodMapper.toVo(foodRepository.findByPrice(price));
    }

    @Override
    public List<FoodVo> getFoodByTypeName(String typeName) {
        return FoodMapper.toVo(foodRepository.findFoodsByFoodTypeEntityName(typeName));
    }

    @Override
    public List<FoodVo> getFoods() {
        return FoodMapper.toVo(foodRepository.findAll());
    }
}
