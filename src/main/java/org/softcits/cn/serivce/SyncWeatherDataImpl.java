package org.softcits.cn.serivce;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.softcits.cn.mapper.CityNoticeTmpMapper;
import org.softcits.cn.mapper.ForecastTmpMapper;
import org.softcits.cn.mapper.YesterdayTmpMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SyncWeatherDataImpl implements SyncWeatherData{

    @Autowired
    private WeatherDataAdmin weatherDataAdmin;
    @Autowired
    private RemoteDataService remoteDataService;
    @Autowired
    private YesterdayTmpMapper yesterdayTmpMapper;
    @Autowired
    private CityNoticeTmpMapper cityNoticeTmpMapper;
    @Autowired
    private ForecastTmpMapper forecastTmpMapper;

    private Logger logger = LoggerFactory.getLogger(SyncWeatherDataImpl.class);
    @Override
    public void synchronizeWeatherData() {
        logger.info("Start clean temp tables ...");
        // clean tmp tables
        weatherDataAdmin.cleanWeatherDataTmp();
        logger.info("Start insert into temp tables ...");
        // insert latest data from national center into tmp tables
        remoteDataService.initBatchWeatherDataTmp();
        logger.info("Start update main table from temp tables ...");
        // update main tables from tmp tables
        yesterdayTmpMapper.updateFromTmpTable();
        cityNoticeTmpMapper.updateFromTmpTable();
        forecastTmpMapper.updateFromTmpTable();
        logger.info("End of synchronization.");
    }
}
