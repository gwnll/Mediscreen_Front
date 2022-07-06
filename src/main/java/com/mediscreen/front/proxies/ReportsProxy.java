package com.mediscreen.front.proxies;

import com.mediscreen.front.model.RiskLevel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "reports", url = "localhost:8083")
public interface ReportsProxy {

    @GetMapping("{patientId}/report")
    RiskLevel generateReport(@PathVariable int patientId);

}
