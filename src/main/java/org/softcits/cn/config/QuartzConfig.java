package org.softcits.cn.config;


import org.quartz.*;
import org.softcits.cn.job.SyncWeather;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Date;


@Configuration
public class QuartzConfig {

private static final int INTERVAL = 60 * 60;
	
	//JobDetail
	@Bean
	public JobDetail weatherDataSyncJobDetail() {
		return JobBuilder.newJob(SyncWeather.class)
		.withIdentity("WeatherDataSyncJob").storeDurably().build();
	}
	//Trigger
	@Bean
	public Trigger weatherDataSyncTrigger() {
		//多少秒之后执行
		Date startTime = new Date(System.currentTimeMillis() + 1000 * 60 * 60);
		//每一小时执行
		SimpleScheduleBuilder schedBuilder = SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(INTERVAL).repeatForever();
		return TriggerBuilder.newTrigger().forJob(weatherDataSyncJobDetail())
				.withIdentity("weatherDataSyncTrigger").startAt(startTime).withSchedule(schedBuilder).build();
	}


}
