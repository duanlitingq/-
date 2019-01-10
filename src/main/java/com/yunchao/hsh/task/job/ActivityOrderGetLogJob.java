package com.yunchao.hsh.task.job;

import com.yunchao.hsh.model.HshActivityOrderGetLog;
import com.yunchao.hsh.service.IActivityOrderService;
import com.yunchao.hsh.service.IHshActivityOrderGetLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @Authher:Zhai Qing
 * @Date: 2018/11/21 18:19
 * @Description:
 * @Email: hkwind959@gmail.com
 */
@Component("activityOrderGetLogJob")
public class ActivityOrderGetLogJob {

    private Logger log = LoggerFactory.getLogger(ActivityOrderGetLogJob.class);

    @Autowired
    private IHshActivityOrderGetLogService activityOrderGetLogService;

    @Autowired
    private IActivityOrderService activityOrderService;

    /**
     * 每天零晨一点更新数据
     */
    @Scheduled(cron = "0 0 1 * * ?")
    public void updateOrderStatus() {

        Calendar calendar = Calendar.getInstance();
        //根据截止时间减去一个月判断是否等于当前时间，如果相等，修改状态为确定领取，否则不改变
        List<HshActivityOrderGetLog> list = this.activityOrderGetLogService.findByEndTimeEqules();
        for (HshActivityOrderGetLog getLog : list) {
            Long logId = getLog.getLogId();
            //截止时间
            Date endTime = getLog.getEndTime();
            calendar.setTime(endTime);
            //减去一个月
            calendar.add(Calendar.MONTH, -1);
            //减去的时间
            Date date = calendar.getTime();
            //当前时间
            Date currentDate = new Date();
            if (date.equals(currentDate)) {
                try {
                    HshActivityOrderGetLog activityOrderGetLog = new HshActivityOrderGetLog();
                    activityOrderGetLog.setLogId(logId);
                    activityOrderGetLog.setStatus((byte) 0);
                    this.activityOrderGetLogService.updateByOrderIdStatus(activityOrderGetLog);
                    log.info("修改活动订单领取日志");
                } catch (Exception e) {
                    log.error("修改失败--{}", e.getMessage());
                }
            }
        }
        /*=======================================判断是否12条记录领取完成================================*/
        List<String> strings = this.activityOrderGetLogService.selectByOrderIdGroupBy();
        for (String orderId : strings) {
            Byte aByte = this.activityOrderGetLogService.sumByOrderIdStatus(orderId);
            String count = String.valueOf(aByte);
            if (count.equals("12")){
                //已完成
                this.activityOrderService.updateOrderStatus(orderId);
            }
        }

        System.out.println("定时更新活动订单状态数据执行成功");

    }

//    public static void main(String[] args) {
//        Calendar calendar = Calendar.getInstance();
////        Date currentDate = new Date();
////        calendar.setTime(currentDate);
////        calendar.add(Calendar.MONTH, -1);
////        Date time = calendar.getTime();
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//
//
////        System.out.println(currentStr + "===========" + format);
//
////        Date currentDate = new Date();
////
////        int count = 0;
////        for (int i = 0; i < 12; i++) {
////            //依据的时间
////            calendar.setTime(currentDate);
////            calendar.add(Calendar.MONTH, i);
////            Date time = calendar.getTime();
////            System.out.println(sdf.format(time) + "=====" + count++);
////        }
//
////        System.out.println(time.equals(currentDate));
//    }

}
