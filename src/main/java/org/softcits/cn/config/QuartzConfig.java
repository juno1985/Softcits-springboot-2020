package org.softcits.cn.config;


import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.TriggerBuilder;
import org.softcits.cn.job.SyncWeather;
import org.quartz.Trigger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


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
		//每两秒执行
		SimpleScheduleBuilder schedBuilder = SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(INTERVAL).repeatForever();
		return TriggerBuilder.newTrigger().forJob(weatherDataSyncJobDetail())
				.withIdentity("weatherDataSyncTrigger").startNow().withSchedule(schedBuilder).build();
	}


}
