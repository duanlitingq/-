package com.yunchao.hsh.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yunchao.hsh.model.Customer;
import com.yunchao.hsh.model.Message;
import com.yunchao.hsh.service.ICustomerService;
import com.yunchao.hsh.service.IMessageService;
import com.yunchao.hsh.utils.ParamUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/page/admin/message")
public class MessageController {
    Logger logger = Logger.getLogger("MessageController.class");
    @Autowired
    private IMessageService messageService;
    @Autowired
    private ICustomerService customerService;

    @RequestMapping(value = "getPage", method = RequestMethod.POST)
    @ResponseBody
    private PageInfo<Message> getPage(HttpServletRequest request) {
        try {
            int pageNum = ParamUtils.getIntParameter(request, "pageNum", 1);
            int pageSize = ParamUtils.getIntParameter(request, "pageSize", 20);
            HashMap<String,Object> map = new HashMap<String,Object>();
            PageHelper.orderBy("  send_time desc");
            PageInfo<Message> page = messageService.getPage(map, pageNum, pageSize);
            List<Message> list = page.getList();
            if (list.size() > 0) {
                for (Message message : list) {
                    Long receiveId = message.getReceiveId();
                    Customer customer = customerService.getIdCustomer(receiveId);
                    if (customer != null) {
                        message.setCustomeName(customer.getNickname());
                    }
                    //list.add(message);
                }
            }
            return page;
        } catch (Exception e) {
            logger.error("菜单分页列表异常！" + e.getMessage());
        }
        return null;
    }
}
