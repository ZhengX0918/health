package com.zx.health.commons;

import org.quartz.TriggerUtils;
import org.quartz.impl.triggers.CronTriggerImpl;
import org.springframework.scheduling.support.CronSequenceGenerator;
import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CronUtil {

    public static List<String> getRecentTriggerTime(String cron) {
        List<String> list = new ArrayList<String>();
        try {
            CronTriggerImpl cronTriggerImpl = new CronTriggerImpl();
            cronTriggerImpl.setCronExpression(cron);
            // 这个是重点，一行代码搞定
            List<Date> dates = TriggerUtils.computeFireTimes(cronTriggerImpl, null, 40);
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            for (Date date : dates) {
                list.add(dateFormat.format(date));
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static void main(String[] args) throws Exception {
//        List<String> list = seeExcuteTime("* * * ? * 3");
//        for (int i = 0; i < list.size(); i++) {
//            System.out.println(list.get(i));
//        }

        String cronStr = "* * * ? * 3";
        cronStr = "0 10 12 28 * ?";
        cronStr = "0 15 10 9W * ?";
        List<String> recentTriggerTime = getRecentTriggerTime(cronStr);

        for (String s : recentTriggerTime) {
            System.out.println(s);
        }

    }

}
