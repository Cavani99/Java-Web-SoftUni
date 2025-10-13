package main.service;

import main.model.Report;
import main.web.dto.CreateReportRequest;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class LogService {

    private static final String LOG_FILE = "agent-activity.log";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @EventListener
    public void logReport(CreateReportRequest report) {
        String logEntry = String.format("[%s] Report received from Agent %s: %s%n",
                LocalDateTime.now().format(FORMATTER),
                report.getAgent(),
                report.getDescription());

        try (FileWriter writer = new FileWriter(LOG_FILE, true)) {
            writer.write(logEntry);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
