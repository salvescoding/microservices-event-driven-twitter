package com.microservices.demo.elastic.query.service.business.impl;

import com.microservices.demo.elastic.model.index.impl.TwitterIndexModel;
import com.microservices.demo.elastic.query.client.service.ElasticQueryClient;
import com.microservices.demo.elastic.query.service.api.ElasticDocumentController;
import com.microservices.demo.elastic.query.service.business.ElasticQueryService;
import com.microservices.demo.elastic.query.service.model.ElasticQueryServiceResponseModel;
import com.microservices.demo.elastic.query.service.model.assembler.ElasticQueryServiceResponseModelAssembler;
import com.microservices.demo.elastic.query.service.transformer.ElasticToQueryTransformer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TwitterToElasticQueryService implements ElasticQueryService {

    private static final Logger LOG = LoggerFactory.getLogger(TwitterToElasticQueryService.class);
    private final ElasticQueryClient<TwitterIndexModel> elasticQueryClient;

    private final ElasticQueryServiceResponseModelAssembler transformer;

    public TwitterToElasticQueryService(ElasticQueryClient<TwitterIndexModel> elasticQueryClient, ElasticQueryServiceResponseModelAssembler transformer) {
        this.elasticQueryClient = elasticQueryClient;
        this.transformer = transformer;
    }

    @Override
    public ElasticQueryServiceResponseModel getDocumentById(String id) {
        LOG.info("Querying for document with id: {}", id);
        return transformer.toModel(elasticQueryClient.getIndexModelById(id));
    }

    @Override
    public List<ElasticQueryServiceResponseModel> getAllDocuments() {
        LOG.info("Querying for all documents");
        return transformer.toModels(elasticQueryClient.getAllIndexModels());
    }

    @Override
    public List<ElasticQueryServiceResponseModel> getDocumentsByText(String text) {
        LOG.info("Querying for documents with text: {}", text);
        return transformer.toModels(elasticQueryClient.getIndexModelByText(text));
    }
}
