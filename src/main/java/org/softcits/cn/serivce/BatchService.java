package org.softcits.cn.serivce;

import java.util.List;

import org.softcits.cn.model.Yesterday;

public interface BatchService {

	Integer insertBatch(List<Yesterday> yesList);
}
