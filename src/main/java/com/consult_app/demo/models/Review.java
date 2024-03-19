package com.consult_app.demo.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Getter
@Setter
public class Review {
    private int reviewId;
    private int doctorId;
    private int userId;
    private int rating;
    private String review;
    private String createdAt;
    private String updatedAt;
}
