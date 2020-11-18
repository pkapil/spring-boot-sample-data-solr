package org.rf.springbootsampledatasolr;

import org.apache.commons.math3.random.RandomDataGenerator;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SolrAppStarter implements CommandLineRunner {


    @Value("${solr.host}")
    String solrHost;

    @Value("${solr.core}")
    String solrCore;


    public static void main(String[] args) {
        SpringApplication.run(SolrAppStarter.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        HttpSolrClient solr = new HttpSolrClient.Builder("http://" + solrHost + "/" + solrCore ).build();
        SolrInputDocument solrInputDocument = new SolrInputDocument();
        solrInputDocument.addField("id", new RandomDataGenerator().nextInt(10, 10101010));
        solrInputDocument.addField("name", "Sony Playstation");
        solr.add(solrInputDocument);
        solr.commit();
    }

}
