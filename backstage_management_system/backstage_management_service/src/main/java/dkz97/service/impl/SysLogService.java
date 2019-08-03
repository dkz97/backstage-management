package dkz97.service.impl;

import dkz97.dao.ISysLogDao;
import dkz97.domain.SysLog;
import dkz97.service.ISysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SysLogService implements ISysLogService {

    @Autowired
    private ISysLogDao sysLogDao;

    /**
     * 保存日志信息类
     * @param sysLog
     */
    @Override
    public void save(SysLog sysLog) {
        sysLogDao.saveSysLog(sysLog);
    }

    // 查询所有的日志信息
    @Override
    public List<SysLog> findAll() {
        return sysLogDao.findAll();
    }


}
