package main.web;

import main.model.Report;
import main.service.AnalysisService;
import main.service.ReportService;
import main.web.dto.CreateReportRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reports")
public class ReportController {

    private final ReportService reportService;
    private final AnalysisService analysisService;

    private final ApplicationEventPublisher eventPublisher;

    @Autowired
    public ReportController(ReportService reportService, AnalysisService analysisService, ApplicationEventPublisher eventPublisher) {
        this.reportService = reportService;
        this.analysisService = analysisService;
        this.eventPublisher = eventPublisher;
    }

    @PostMapping
    public ResponseEntity<Report> createReport(@RequestBody CreateReportRequest request) {

        Report report = reportService.create(request.getAgent(), request.getDescription());
        analysisService.performPatternScan();

        eventPublisher.publishEvent(request);

        return ResponseEntity.ok(report);
    }

    @GetMapping
    public ResponseEntity<List<Report>> getAllReports() {

        List<Report> reports = reportService.getAll();

        return ResponseEntity.ok(reports);
    }
}
