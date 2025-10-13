package main.service;

import main.model.Report;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class ReportService {

    public static final List<Report> REPORTS = new ArrayList<>();


    public Report create(String agent, String message) {

        Report report = Report.builder()
                .agent(agent)
                .message(message)
                .createdOn(LocalDateTime.now())
                .build();

        REPORTS.add(report);

        return report;
    }

    public List<Report> getAll() {

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        return REPORTS;
    }
}
