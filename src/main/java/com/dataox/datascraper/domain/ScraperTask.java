package com.dataox.datascraper.domain;

import com.dataox.datascraper.decl.FlowStatus;
import com.dataox.datascraper.decl.ScraperType;
import com.dataox.datascraper.decl.SourceType;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Getter
@Setter
@EqualsAndHashCode(of = "sourceAddress")
public class ScraperTask {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String bin;

    private String brand;

    @Column(columnDefinition="TEXT")
    private String description;

    private String price;

    @Column(name = "source_address", unique = true)
    private String sourceAddress;

    @Column(name = "flow_status")
    @Enumerated(EnumType.STRING)
    private FlowStatus flowStatus;

    @Column(name = "scraper_type")
    @Enumerated(EnumType.STRING)
    private ScraperType scraperType;

    @Column(name = "source_type")
    @Enumerated(EnumType.STRING)
    private SourceType sourceType;

    @Column(name = "created_at")
    private Instant createdAt;

    @Column(name = "executed_at")
    private Instant executedAt;
}
