package com.example.demo.dto;

import java.util.List;

public class STSResponse {
    private List<STSDTO> points;

    public STSResponse(List<STSDTO> points) {
        this.points = points;
    }

    public List<STSDTO> getPoints() {
        return points;
    }

    public void setPoints(List<STSDTO> points) {
        this.points = points;
    }
}
