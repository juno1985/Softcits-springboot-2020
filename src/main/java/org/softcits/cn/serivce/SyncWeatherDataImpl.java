package org.softcits.cn.serivce;

import org.softcits.cn.mapper.CityNoticeTmpMapper;
import org.softcits.cn.mapper.ForecastTmpMapper;
import org.softcits.cn.mapper.YesterdayTmpMapper;
import org.springframework.beans.factory.annotation.Autowired;

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

    @Override
    public void synchronizeWeatherData() {
        // clean tmp tables
        weatherDataAdmin.cleanWeatherData();
        // insert latest data from national center into tmp tables
        remoteDataService.initBatchWeatherDataTmp();
        // update main tables from tmp tables
        yesterdayTmpMapper.updateFromTmpTable();
        cityNoticeTmpMapper.updateFromTmpTable();
        forecastTmpMapper.updateFromTmpTable();
    }
}
