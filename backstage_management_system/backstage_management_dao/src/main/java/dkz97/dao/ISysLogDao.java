package dkz97.dao;

import dkz97.domain.SysLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ISysLogDao {


    /**
     * 保存日志
     */
    @Insert("insert into syslog(visitTime,username,ip,url,executionTime,method) values(#{visitTime},#{username},#{ip},#{url},#{executionTime},#{method})")
    public void saveSysLog(SysLog sysLog);


    /**
     * 查询所有的日志
     */
    @Select("select * from sysLog")
    public List<SysLog> findAll();
}


