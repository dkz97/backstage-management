package dkz97.dao;

import dkz97.domain.Member;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface IMemberDao {

    /**
     * 根绝Id查找
     */

    @Select("select * from member where id = #{id}")
    public Member findById(String id);
}
