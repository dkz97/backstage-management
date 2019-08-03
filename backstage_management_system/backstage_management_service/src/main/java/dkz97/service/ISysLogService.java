package dkz97.service;


import dkz97.domain.SysLog;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 日志的业务层接口类
 */
public interface ISysLogService {

    /**
     * 保存日志的方法
     */
    public void save(SysLog sysLog);

    /**
     * 查询所有的日志
     */
    public List<SysLog> findAll();
}

