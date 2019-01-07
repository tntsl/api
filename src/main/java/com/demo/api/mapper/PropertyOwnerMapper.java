package com.demo.api.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.demo.api.user.domain.PropertyOwner;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * @author Lye
 */
@Repository
public interface PropertyOwnerMapper extends BaseMapper<PropertyOwner> {

    @Select("select * from dt_property_owners where belong_account=#{userId} and `type`=1")
    public PropertyOwner getUserInfoByUserId(Integer userId);
}
