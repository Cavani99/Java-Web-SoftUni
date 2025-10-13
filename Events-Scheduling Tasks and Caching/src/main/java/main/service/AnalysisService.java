package main.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import static main.service.ReportService.REPORTS;

@Service
public class AnalysisService {

    @Async
    public void performPatternScan() {

        System.out.println("Pattern scan initiated...");
        try {
            for (int i = 1; i <= REPORTS.size(); i++) {
                System.out.printf("Analyzing report #%d... from %s\n", i, REPORTS.get(i - 1).getAgent());
                Thread.sleep(300);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println("Pattern scan complete. No anomalies detected.");
    }
}
