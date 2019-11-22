package org.ontariotechu;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;

public class HelloRDF{
    private static final Logger logger = LoggerFactory.getLogger( HelloRDF.class );
    
    public static void main(String args[]){
        logger.info("Hello Jena RDF");

        //Just to ensure that it works
        Model model = ModelFactory.createDefaultModel();
    }
}
