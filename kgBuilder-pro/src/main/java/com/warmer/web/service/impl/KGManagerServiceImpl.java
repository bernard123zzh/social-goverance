package com.warmer.web.service.impl;

import cn.hutool.core.util.IdUtil;
import com.warmer.base.util.DateUtil;
import com.warmer.web.dao.KnowledgeGraphDao;
import com.warmer.web.entity.KgDomain;
import com.warmer.web.entity.KgNodeDetail;
import com.warmer.web.entity.KgNodeDetailFile;
import com.warmer.web.service.KGManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class KGManagerServiceImpl implements KGManagerService {

    @Autowired
    KnowledgeGraphDao knowledgeGraphDao;
    @Override
    public List<KgDomain> getDomains() {
        return knowledgeGraphDao.getDomains();
    }

    @Override
    public List<KgDomain> getDomainList(String domainName,Integer type,Integer commend) {
        return knowledgeGraphDao.getDomainList(domainName,type,commend);
    }

    @Override
    public Integer saveDomain(KgDomain map) {
        map.setCreateTime(DateUtil.getDateNow());
        map.setModifyTime(DateUtil.getDateNow());
        knowledgeGraphDao.saveDomain(map);
        return map.getId();
    }

    @Override
    public Integer quickCreateDomain(String domain,String domainAlia,Integer type) {
        KgDomain item = new KgDomain();
        item.setName(domainAlia);
        item.setLabel(domain);
        item.setNodeCount(0);
        item.setShipCount(0);
        item.setCreateUser("tc");
        item.setCommend(0);
        item.setType(type);
        item.setStatus(1);
       return  saveDomain(item);
    }

    @Override
    public void updateDomain(KgDomain map) {
        knowledgeGraphDao.updateDomain(map);
    }

    @Override
    public void deleteDomain(Integer id) {
        knowledgeGraphDao.deleteDomain(id);
    }

    @Override
    public List<KgDomain> getDomainByName(String domainName) {
        return knowledgeGraphDao.getDomainByName(domainName);
    }

    @Override
    public KgDomain getDomainByLabel(String label) {
        return knowledgeGraphDao.getDomainByLabel(label);
    }

    @Override
    public List<KgDomain> getDomainById(Integer domainId) {
        return knowledgeGraphDao.getDomainById(domainId);
    }

    @Override
    public KgDomain selectById(Integer domainId) {
        return knowledgeGraphDao.selectById(domainId);
    }

    @Override
    public void saveNodeImage(List<Map<String, Object>> mapList) {
        knowledgeGraphDao.saveNodeImage(mapList);
    }

    @Override
    public void saveNodeContent(Map<String, Object> map) {
        knowledgeGraphDao.saveNodeContent(map);
    }

    @Override
    public void updateNodeContent(Map<String, Object> map) {
        knowledgeGraphDao.updateNodeContent(map);
    }

    @Override
    public List<KgNodeDetailFile> getNodeImageList(Integer domainId, Integer nodeId) {
        return knowledgeGraphDao.getNodeImageList(domainId,nodeId);
    }

    @Override
    public List<KgNodeDetail> getNodeContent(Integer domainId, Integer nodeId) {
        return knowledgeGraphDao.getNodeContent(domainId,nodeId);
    }

    @Override
    public void deleteNodeImage(Integer domainId, Integer nodeId) {
        knowledgeGraphDao.deleteNodeImage(domainId,nodeId);
    }
}
