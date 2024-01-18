package com.consult_app.demo.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Review {
    private int reviewId;
    private int doctorId;
    private int userId;
    private int rating;
    private String review;
    private String createdAt;
    private String updatedAt;
}
