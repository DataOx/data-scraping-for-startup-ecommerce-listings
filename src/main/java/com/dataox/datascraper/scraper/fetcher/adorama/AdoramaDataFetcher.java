package com.dataox.datascraper.scraper.fetcher.adorama;

import com.dataox.datascraper.scraper.fetcher.DataFetcher;
import com.dataox.datascraper.scraper.fetcher.FetcherRequest;
import com.dataox.datascraper.scraper.fetcher.FetcherResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;

import static com.dataox.datascraper.scraper.fetcher.FetcherUtils.pause;

@Slf4j
@RequiredArgsConstructor
public class AdoramaDataFetcher implements DataFetcher {

    private static final String SPECS_URL_PATTERN = "https://www.adorama.com/Als.Mvc/nspc/SpecsTab?svfor=7day&cacheVersion=1929&svcfor=1day&sku=%s&tabType=Revisedspecs&isVip=false";
    private static final int MAX_TRY_COUNT = 5;
    private static final String COOKIE = "_pxvid=68d07c0e-34e6-11ed-8f53-566a65784269;";

    private final OkHttpClient client;

    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public FetcherResponse fetch(FetcherRequest request) {
        FetcherResponse response = new FetcherResponse();

        try {
            AdoramaFetcherResult result = new AdoramaFetcherResult();
            String mainPage = tryGetMainPage(request.getUrl());

            if (StringUtils.isBlank(mainPage)) {
                response.setError("failed to get result from page");
                return response;
            }

            result.setMainPageHtml(mainPage);

            String specs = "";
            if (StringUtils.containsIgnoreCase(mainPage, "SpecsTab")) {
                specs = tryGetSpecs(request.getUrl());

                if (StringUtils.isBlank(specs)) {
                    response.setError("failed to get specs from page");
                    return response;
                }

                result.setSpecPageHtml(specs);
            } else {
                log.info("no specs on {}", request.getUrl());
            }

            response.setData(mapper.writeValueAsString(result));
        } catch (IOException e) {
            response.setError(e.getMessage());
        }

        return response;
    }

    private String tryGetSpecs(String url) throws IOException {

        for (int i = 0; i < MAX_TRY_COUNT; i++) {
            pause(RandomUtils.nextLong(1000, 2000));

            String specs = getSpecs(url);
            if (StringUtils.containsIgnoreCase(specs, "productSpecification section")
                    || StringUtils.containsIgnoreCase(specs, "spec-section")) {
                return specs;
            }
            else {
                log.info("bad spec on page {}", url);
                waitBlock();
            }
        }

        return "";
    }

    private void waitBlock() {
        long duration = RandomUtils.nextLong(30000, 60000);
        log.info("block. pause {} sec", duration / 1000);
        pause(duration);
    }

    private String tryGetMainPage(String url) throws IOException {

        for (int i = 0; i < MAX_TRY_COUNT; i++) {
            pause(RandomUtils.nextLong(1000, 2000));

            String page = getMainPage(url);
            if (StringUtils.containsIgnoreCase(page, "product-info-container")) {
                return page;
            }
            else {
                log.info("bad page {}", url);
                waitBlock();
            }
        }
        return "";
    }

    private String getMainPage(String url) throws IOException {
        Response response = client.newCall(createRequest(url))
                .execute();

        return response.body().string();
    }

    private String getSpecs(String url) throws IOException {
        String name = getName(url);
        String specUrl = String.format(SPECS_URL_PATTERN, name);
        Response response = client.newCall(createRequest(specUrl))
                .execute();

        return response.body().string();
    }

    private Request createRequest(String specUrl) {
        return new Request.Builder()
                .url(specUrl)
                .addHeader("Cookie", COOKIE)
                .build();
    }

    private String getName(String url) {
        return StringUtils.substringAfterLast(StringUtils.substringBefore(url, ".html"), "/");
    }

    @Setter
    @Getter
    static class AdoramaFetcherResult {

        private String mainPageHtml;

        private String specPageHtml;

    }
}
