package com.yunchao.hsh.service;

import com.yunchao.hsh.dto.resp.ScoreItemResp;
import com.yunchao.hsh.model.HshSelfItem;
import com.yunchao.hsh.utils.superdir.Result;

import java.util.List;

public interface ISelfItemService {
    Result getItemList(Integer pageNum, Integer pageSize,String name);

    Result addItem(HshSelfItem item,String desc);

    Result updateItem(HshSelfItem item, String desc);
    
    Result delItem(long itemId);

    HshSelfItem findByItemId(String itemId);

    List<ScoreItemResp> selectByCusNum1(Double score);

    ScoreItemResp selectByItemId(Long itemId);

    Result getScoreItemsList(Long customerId, int pageNum, int pageSize, int type);
}
