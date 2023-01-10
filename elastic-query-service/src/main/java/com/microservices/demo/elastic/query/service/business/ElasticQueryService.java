package com.microservices.demo.elastic.query.service.business;

import com.microservices.demo.elastic.query.service.model.ElasticQueryServiceResponseModel;

import java.util.List;

public interface ElasticQueryService {

    ElasticQueryServiceResponseModel getDocumentsById(String id);

    List<ElasticQueryServiceResponseModel> getAllDocuments();

    List<ElasticQueryServiceResponseModel> getDocumentsByText(String text);
}
