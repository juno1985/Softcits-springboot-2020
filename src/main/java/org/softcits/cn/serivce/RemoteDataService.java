package org.softcits.cn.serivce;

import org.softcits.cn.pojo.Response;

public interface RemoteDataService {

	String getRemoteData(String url);

	Response getResponseFromJSON(String jsonStr);
}
