package com.calsoft.pos.config;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrRequest;
import org.apache.solr.client.solrj.SolrResponse;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.NoOpResponseParser;
import org.apache.solr.client.solrj.response.SolrResponseBase;
import org.apache.solr.common.params.SolrParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.solr.core.SolrCallback;

import java.io.IOException;

public class SolrTaskCallBack implements SolrCallback<SolrResponse> {

    private static final Logger logger = LoggerFactory.getLogger(SolrTaskCallBack.class);
    private final String indexEndPoint;

    public SolrTaskCallBack(String indexEndPoint) {
        this.indexEndPoint = indexEndPoint;
    }

    @Override
    public SolrResponse doInSolr(SolrClient solrClient) throws SolrServerException, IOException {
        SolrRequest request = new SolrRequest(SolrRequest.METHOD.GET, indexEndPoint) {

            @Override
            public SolrParams getParams() {
                return null;
            }

            @Override
            protected SolrResponse createResponse(SolrClient solrClient) {
                SolrResponseBase response = new SolrResponseBase();
                try {
                    response.setResponse(solrClient.request(this));
                } catch (SolrServerException e) {
                    logger.error("Sole Exception while try to execute doSolr Call Back :: ", e);
                } catch (IOException e) {
                    logger.error("Sole Exception while try to execute doSolr Call Back :: ", e);
                }
                return response;
            }
        };
        NoOpResponseParser dontMessWithSolr = new NoOpResponseParser();
        dontMessWithSolr.setWriterType("json");
        request.setResponseParser(dontMessWithSolr);
        final SolrResponse process = request.process(solrClient);
        return process;
    }
}