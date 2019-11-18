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
    static String alexURI = "http://example.org/alex";
    static String jarekURI = "http://example.org/jarek";
    
    public static void main(String args[]){
        logger.info("---------Jena RDF Example2---------");

        // create an empty Model
        Model model = ModelFactory.createDefaultModel();

        // create a person
        Resource alex = model.createResource(alexURI, FOAF.Person)
            .addProperty(FOAF.name, model.createResource()
                         .addProperty(FOAF.firstName, "Alex")
                         .addProperty(FOAF.lastName, "Mihaylov"));

        Resource jarek = model.createResource(jarekURI, FOAF.Person)
            .addProperty(FOAF.name, model.createResource()
                         .addProperty(FOAF.firstName, "Jarek")
                         .addProperty(FOAF.lastName, "Szlichta"));
        
        // //Create a link
        alex.addProperty(FOAF.knows, jarek);
        

        //The language in which to write the model is specified by the lang argument. Predefined values are "RDF/XML", "RDF/XML-ABBREV", "N-TRIPLE", "TURTLE", (and "TTL") and "N3". The default value, represented by null, is "RDF/XML".
        model.write(System.out, "N-TRIPLE");
    }
}
