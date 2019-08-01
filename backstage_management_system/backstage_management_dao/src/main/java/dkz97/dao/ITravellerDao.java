package dkz97.dao;

import dkz97.domain.Traveller;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ITravellerDao {

    /**
     * 多对多查询旅客
     */
    @Select("select * from traveller where id in (select travellerId from order_traveller where orderId = #{aid})")
    public List<Traveller> findByOrdersId(String aid);
}
