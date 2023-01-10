package com.microservices.demo.elastic.query.service.api;

import com.microservices.demo.elastic.query.service.business.ElasticQueryService;
import com.microservices.demo.elastic.query.service.model.ElasticQueryServiceRequestModel;
import com.microservices.demo.elastic.query.service.model.ElasticQueryServiceResponseModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/documents")
public class ElasticDocumentController {
    private static final Logger LOG = LoggerFactory.getLogger(ElasticDocumentController.class);

    private final ElasticQueryService elasticQueryService;

    public ElasticDocumentController(ElasticQueryService elasticQueryService) {
        this.elasticQueryService = elasticQueryService;
    }

    @GetMapping("/")
    public @ResponseBody
    ResponseEntity<List<ElasticQueryServiceResponseModel>> getAllDocuments() {
        List<ElasticQueryServiceResponseModel> allDocuments = elasticQueryService.getAllDocuments();
        LOG.info("Elasticsearch returned {} of documents", allDocuments.size());
        return ResponseEntity.ok(allDocuments);
    }

    @GetMapping("/{id}")
    public @ResponseBody
    ResponseEntity<ElasticQueryServiceResponseModel>
    getDocumentById(@PathVariable String id) {
        return ResponseEntity.ok(elasticQueryService.getDocumentsById(id));
    }

    @PostMapping("/get-document-by-text")
    public @ResponseBody
    ResponseEntity<List<ElasticQueryServiceResponseModel>>
    getDocumentByText(@RequestBody ElasticQueryServiceRequestModel elasticQueryServiceRequestModel) {
        List<ElasticQueryServiceResponseModel> documentsByText = elasticQueryService.getDocumentsByText(elasticQueryServiceRequestModel.getText());
        LOG.info("Elasticsearch returned {} of documents", documentsByText.size());
        return ResponseEntity.ok(documentsByText);
    }


}
