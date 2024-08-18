package com.warmer.web.job;

import com.warmer.base.util.Neo4jUtil;
import com.warmer.web.entity.KgDomain;
import com.warmer.web.service.KGGraphService;
import com.warmer.web.service.KGManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DomainCleanService {
    @Autowired
    private KGGraphService kgGraphService;
    @Autowired
    private KGManagerService kgService;
    @Scheduled(cron = "0 0 1 * * ? ")
    public void clearDomain(){
        List<KgDomain> domains = kgService.getDomains();
        if(domains!=null&&domains.size()>0){
            for (KgDomain domainItem : domains) {
                String cypher=String.format("match(n:`%s`) return count(n)",domainItem.getName());
                long nodeCount = Neo4jUtil.getGraphValue(cypher);
                if(nodeCount<10){
                    kgService.deleteDomain(domainItem.getId());
                    kgGraphService.deleteKGDomain(domainItem.getName());
                    // 删除索引
                    Neo4jUtil.deleteIndex(domainItem.getName());
                }
            }
        }
    }
}
