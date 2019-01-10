package com.yunchao.hsh.task.job;

import com.yunchao.hsh.dto.resp.SupplierOrderResp;
import com.yunchao.hsh.service.ISupplierOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
@Component("supplierOrderJob")
public class SupplierOrderJob {

    private Logger log = LoggerFactory.getLogger(ActivityOrderGetLogJob.class);

    @Autowired
    private ISupplierOrderService supplierOrderService;

    /**
     * 每天零晨一点更新数据
     */
    @Scheduled(cron = "0 0 2 * * ?")
    public void updateOrderStatus() {
        //获取用户未确认订单，供应商接单后72小时未确认收货者自动变为收货
        //获取商家已接单未完成的数据
        List<SupplierOrderResp> list = this.supplierOrderService.findEndTimeNoConfirmOrder();
        if (list != null && list.size() > 0) {
            for (SupplierOrderResp orderResp : list) {
                //接单时间
                Date confirmTime = orderResp.getConfirmTime();
                //当前时间
                Date currentDate = new Date();
                long day = currentDate.getTime() - confirmTime.getTime();
                long time = day / (24 * 60 * 60 * 1000*3);
                if (time > 3) {
                    try {
                        orderResp.setOrderStatus(3);
                        orderResp.setFinishTime(new Date());
                        supplierOrderService.confirmOrder(orderResp);
                        log.info("订单状态更新查询成功");
                    } catch (Exception e) {
                        log.error("订单状态更新查询成功失败", e.getMessage());
                    }
                }
            }
        }
        System.out.println("定时查询订单数据执行成功");
    }
}
