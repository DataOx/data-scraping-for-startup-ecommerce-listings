package com.dataox.datascraper.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table
@Getter
@Setter
public class FetcherResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String error;

    @Lob
    private String result;

    @Column(columnDefinition="TEXT", name = "result_text")
    private String resultText;

    @Column(name = "created_at")
    private Instant createdAt;

    @JoinColumn(name = "scraper_task_id")
    @ManyToOne(optional = false)
    private ScraperTask task;
}
