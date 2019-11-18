package org.ontariotechu;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;

public class Main{
    private static final Logger logger = LoggerFactory.getLogger( Main.class );
    
    public static void main(String args[]){
        logger.info("TEST2!");

        // create an empty Model
        Model model = ModelFactory.createDefaultModel();
    }
}
