package main.web.dto;

import lombok.Data;

@Data
public class CreateReportRequest {

    private String agent;

    private String description;

    public String getAgent() {
        return agent;
    }

    public void setAgent(String agent) {
        this.agent = agent;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
