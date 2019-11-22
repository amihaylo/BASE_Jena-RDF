package org.ontariotechu;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.sparql.vocabulary.FOAF;

public class Example2{
    private static final Logger logger = LoggerFactory.getLogger( Example2.class );
    
    // some definitions
     static String bilboURI = "http://example.org/bilbo";
    static String gandalfURI = "http://example.org/gandalf";
    
    public static void main(String args[]){
        logger.info("---------Jena RDF Example2---------");

        // create an empty Model
        Model model = ModelFactory.createDefaultModel();

        // create a person
        Resource bilbo = model.createResource(bilboURI, FOAF.Person)
            .addProperty(FOAF.name, model.createResource()
                         .addProperty(FOAF.firstName, "Bilbo")
                         .addProperty(FOAF.lastName, "Baggins"));

        Resource gandalf = model.createResource(gandalfURI, FOAF.Person)
            .addProperty(FOAF.name, model.createResource()
                         .addProperty(FOAF.firstName, "Gandalf")
                         .addProperty(FOAF.lastName, "TheGrey"));
        
        // //Create a link
        bilbo.addProperty(FOAF.knows, gandalf);
        

        //The language in which to write the model is specified by the lang argument. Predefined values are "RDF/XML", "RDF/XML-ABBREV", "N-TRIPLE", "TURTLE", (and "TTL") and "N3". The default value, represented by null, is "RDF/XML".
        model.write(System.out, "N-TRIPLE");
    }
}
