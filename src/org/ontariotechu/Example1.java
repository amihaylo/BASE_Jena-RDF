package org.ontariotechu;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.sparql.vocabulary.FOAF;

public class Example1{
    private static final Logger logger = LoggerFactory.getLogger( Example1.class );
    
    // some definitions
    static String bilboURI = "http://example.org/bilbo";
    
    public static void main(String args[]){
        logger.info("---------Jena RDF Example1---------");

        // create an empty Model
        Model model = ModelFactory.createDefaultModel();

        // create a person
        Resource bilbo = model.createResource(bilboURI, FOAF.Person)
            .addProperty(FOAF.firstName, "Bilbo")
            .addProperty(FOAF.lastName, "Baggins");
        

        //The language in which to write the model is specified by the lang argument. Predefined values are "RDF/XML", "RDF/XML-ABBREV", "N-TRIPLE", "TURTLE", (and "TTL") and "N3". The default value, represented by null, is "RDF/XML".
        model.write(System.out, "N-TRIPLE");
    }
}
