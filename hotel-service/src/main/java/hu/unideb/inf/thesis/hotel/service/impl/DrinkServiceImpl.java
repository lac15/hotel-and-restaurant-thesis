package hu.unideb.inf.thesis.hotel.service.impl;

import hu.unideb.inf.thesis.hotel.client.api.service.DrinkService;
import hu.unideb.inf.thesis.hotel.client.api.vo.DrinkVo;
import hu.unideb.inf.thesis.hotel.core.entitiy.DrinkEntity;
import hu.unideb.inf.thesis.hotel.core.repository.DrinkRepository;
import hu.unideb.inf.thesis.hotel.service.mapper.DrinkMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ejb.interceptor.SpringBeanAutowiringInterceptor;

import javax.ejb.*;
import javax.interceptor.Interceptors;

@Stateless(name = "DrinkService", mappedName = "DrinkService")
@TransactionAttribute(TransactionAttributeType.REQUIRED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@Local(DrinkService.class)
@Interceptors({SpringBeanAutowiringInterceptor.class})
public class DrinkServiceImpl implements DrinkService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DrinkServiceImpl.class);

    @Autowired
    private DrinkRepository drinkRepository;

    /*@Override
    public List<DrinkVo> getDrinks() {
        return DrinkMapper.toVo(drinkRepository.findAll());
    }*/

    @Override
    public DrinkVo saveDrink(DrinkVo drinkVo) {
        DrinkEntity drinkEntity = drinkRepository.findOne(drinkVo.getId());
        if (drinkEntity == null) {
            drinkEntity = new DrinkEntity();
        }
        DrinkMapper.toEntity(drinkVo, drinkEntity);
        return DrinkMapper.toVo(drinkRepository.save(drinkEntity));
    }

    @Override
    public DrinkVo getDrinkById(Long id) {
        return DrinkMapper.toVo(drinkRepository.findOne(id));
    }

    @Override
    public DrinkVo getDrinkByName(String name) {
        return DrinkMapper.toVo(drinkRepository.findByName(name));
    }

    @Override
    public DrinkVo getDrinkByPrice(int price) {
        return DrinkMapper.toVo(drinkRepository.findByPrice(price));
    }
}