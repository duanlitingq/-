package com.yunchao.hsh.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yunchao.hsh.dao.HshCollectionMapper;
import com.yunchao.hsh.model.HshCollection;
import com.yunchao.hsh.service.ICollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;

@Service
@Transactional
public class CollectionServiceImpl implements ICollectionService {
    @Autowired
    private HshCollectionMapper collectionMapper;

    @Override
    public int addCollection(HshCollection hshCollection) {
        return collectionMapper.insert(hshCollection);
    }

    @Override
    public int updateCollection(HshCollection hshCollection) {
        return collectionMapper.updateByPrimaryKeySelective(hshCollection);
    }

    @Override
    public int delCollction(HshCollection hshCollection) {
        return collectionMapper.deleteCollection(hshCollection);

    }

    @Override
    public HshCollection selectUserCollection(HshCollection collection) {
        return  collectionMapper.selectUserCollection(collection);
    }

    @Override
    public PageInfo<HshCollection> getPage(HashMap<String, Object> map, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        collectionMapper.findPage(map);
        return null;
    }
}
