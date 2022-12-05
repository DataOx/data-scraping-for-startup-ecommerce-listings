package com.dataox.datascraper.scraper.fetcher.abelcine;

import com.dataox.datascraper.scraper.fetcher.DataFetcher;
import com.dataox.datascraper.scraper.fetcher.FetcherRequest;
import com.dataox.datascraper.scraper.fetcher.FetcherResponse;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomUtils;

import java.io.IOException;

import static com.dataox.datascraper.scraper.fetcher.FetcherUtils.pause;


@RequiredArgsConstructor
public class AbelcineDataFetcher implements DataFetcher {

    private final OkHttpClient client;

    @Override
    public FetcherResponse fetch(FetcherRequest request) {
        FetcherResponse response = new FetcherResponse();
        Request req = new Request.Builder()
                .url(request.getUrl())
                .build();

        try {
            Response resp = client.newCall(req).execute();
            response.setData(resp.body().string());
        } catch (IOException e) {
            response.setError(e.getMessage());
        }

        pause(RandomUtils.nextLong(500, 1000));

        return response;
    }
}
