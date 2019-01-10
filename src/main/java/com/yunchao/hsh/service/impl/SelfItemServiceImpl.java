package com.yunchao.hsh.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yunchao.hsh.dao.CustomerMapper;
import com.yunchao.hsh.dao.HshSelfItemDescMapper;
import com.yunchao.hsh.dao.HshSelfItemMapper;
import com.yunchao.hsh.dao.WalletMapper;
import com.yunchao.hsh.dto.resp.PageResultResp;
import com.yunchao.hsh.dto.resp.ScoreItemResp;
import com.yunchao.hsh.model.HshSelfItem;
import com.yunchao.hsh.model.HshSelfItemExample;
import com.yunchao.hsh.model.Wallet;
import com.yunchao.hsh.service.BaseService;
import com.yunchao.hsh.service.ISelfItemService;
import com.yunchao.hsh.utils.IDUtils;
import com.yunchao.hsh.utils.superdir.Result;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class SelfItemServiceImpl extends BaseService implements ISelfItemService {

    @Autowired
    private HshSelfItemMapper itemMapper;

    @Autowired
    private HshSelfItemDescMapper itemDescMapper;

    @Autowired
    private CustomerMapper customerMapper;

    @Autowired
    private WalletMapper walletMapper;

    @Override
    public Result getItemList(Integer pageNum, Integer pageSize, String name) {
        PageHelper.startPage(pageNum, pageSize);
        HshSelfItemExample example = new HshSelfItemExample();
        HshSelfItemExample.Criteria criteria = example.createCriteria();
        example.setOrderByClause("create_time DESC");
        if (StringUtils.isNotBlank(name)) {
            criteria.andItemNameLike(name);
        }
        criteria.andStatusEqualTo((byte) 1);
        List<HshSelfItem> list = this.itemMapper.selectByExample(example);
        PageInfo<HshSelfItem> info = new PageInfo<>(list);
        return Result.ok(info);
    }

    @Override
    public Result addItem(HshSelfItem item, String desc) {
        final long itemId = IDUtils.genItemId();
        item.setItemId(itemId);
        item.setCreateTime(new Date());
        //用来存储商品描述
        item.setCusNum3(desc);
        this.itemMapper.insertSelective(item);
        return Result.ok(null);
    }

    @Override
    public Result updateItem(HshSelfItem item, String desc) {
        item.setCusNum3(desc);
        this.itemMapper.updateByPrimaryKeySelective(item);
        return null;
    }

    @Override
    public Result delItem(long itemId) {
        HshSelfItem item = new HshSelfItem();
        item.setItemId(itemId);
        item.setStatus((byte) 2);
        this.itemMapper.updateByPrimaryKeySelective(item);
        return Result.ok(null);
    }

    @Override
    public HshSelfItem findByItemId(String itemId) {
//        SelfItemResp resp = new SelfItemResp();
        HshSelfItem item = this.itemMapper.selectByPrimaryKey(Long.valueOf(itemId));
        return item;
    }

    @Override
    public List<ScoreItemResp> selectByCusNum1(Double score) {

        HshSelfItemExample example = new HshSelfItemExample();
        HshSelfItemExample.Criteria criteria = example.createCriteria();
        // 1 是推荐商品， 0 不是
        criteria.andCusNum1EqualTo("1");
        criteria.andStatusEqualTo(((byte) 1));
        List<HshSelfItem> hshSelfItems = this.itemMapper.selectByExample(example);
        List<ScoreItemResp> respList = new ArrayList<>();
        if(hshSelfItems != null){
            //所有推荐的商品
            List<HshSelfItem> list = new ArrayList<>();
            if(hshSelfItems.size() > 6){
                list = hshSelfItems.subList(0, 6);
            }else{
                list.addAll(hshSelfItems);
            }
            for (HshSelfItem item : list) {
                ScoreItemResp scoreItemResp = new ScoreItemResp();
                scoreItemResp.setItemId(item.getItemId());
                scoreItemResp.setItemName(item.getItemName());
                String[] split = item.getItemImg().split(";");
                //只放第一张
                scoreItemResp.setItemImg(split[0]);
                scoreItemResp.setItemIntegral(item.getItemIntegral());
                scoreItemResp.setCusNum3(item.getCusNum3());
                scoreItemResp.setItemUnit(item.getItemUnit());
                Double dataScore = item.getItemIntegral().doubleValue();
                if (score > 0) {
                    if (score >= dataScore) {
                        //可兑换
                        scoreItemResp.setFlag(1);
                    } else {
                        //不可兑换
                        scoreItemResp.setFlag(0);
                    }
                }
                respList.add(scoreItemResp);
            }
        }
        return respList;
    }

    @Override
    public ScoreItemResp selectByItemId(Long itemId) {
        ScoreItemResp item = this.itemMapper.getByItemId(itemId);
        return item;
    }

    @Override
    public Result getScoreItemsList(Long customerId, int pageNum, int pageSize, int type) {
        // type 1 当前可换， 2 ，积分特供，查询所有积分
        List<ScoreItemResp> list;
        if (type == 1) {
            Wallet wallet = this.walletMapper.findByCustomerId(customerId);
            //当前积分
            Double currentScore = wallet.getScore();
            PageHelper.startPage(pageNum, pageSize);
            list = this.itemMapper.findLessOrEquelsScore(currentScore);
        } else {
            //查询所有订单
            HshSelfItemExample example = new HshSelfItemExample();
            example.createCriteria().andStatusEqualTo(Byte.valueOf("1"));
            PageHelper.startPage(pageNum, pageSize);
            list = this.itemMapper.selectByExampleScoreResp(example);
        }
        PageInfo<ScoreItemResp> info = new PageInfo<>(list);
        PageResultResp pagedResult = new PageResultResp();
        pagedResult.setPage(pageNum);
        pagedResult.setTotal(info.getPages());
        pagedResult.setRows(list);
        pagedResult.setRecords(info.getTotal());
        return Result.ok(pagedResult);
    }
}
