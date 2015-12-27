package sample.mybatis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sample.mybatis.domain.City;
import sample.mybatis.mapper.CityMapper;

/**
 * Created by user on 12/26/15.
 */
@RestController
public class CityEndpoint {


    @Autowired
    CityMapper cityMapper;

    @RequestMapping(value = "/list")
    public City list(){
        return this.cityMapper.selectCityById(1l);
    }


}
