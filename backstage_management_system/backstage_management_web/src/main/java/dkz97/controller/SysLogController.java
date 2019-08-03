package dkz97.controller;

import dkz97.domain.SysLog;
import dkz97.service.impl.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/sysLog")
public class SysLogController {

    @Autowired
    private SysLogService sysLogService;

    /**
     * 查询所有的日志信息
     */
    @RequestMapping("/findAll.do")
    public ModelAndView findAllSysLog() {
        ModelAndView mv = new ModelAndView();
        List<SysLog> sysLogs = sysLogService.findAll();
        mv.addObject("sysLogs",sysLogs);
        mv.setViewName("syslog-list");

        return mv;
    }
}
