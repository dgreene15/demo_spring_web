package com.example.demo.webclient.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ArticlePost {
    private int id;
    private String userId;
    private String title;
    private String body;
}
