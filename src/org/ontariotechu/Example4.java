package org.ontariotechu;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Statement;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.rdf.model.StmtIterator;
import org.apache.jena.sparql.vocabulary.FOAF;

public class Example4{
    private static final Logger logger = LoggerFactory.getLogger( Example4.class );
    
    // some definitions
    static String alexURI = "http://example.org/alex";
    static String jarekURI = "http://example.org/jarek";
    
    public static void main(String args[]){
        logger.info("---------Jena RDF Example4---------");

        // create an empty Model
        Model model = ModelFactory.createDefaultModel();

        // create a person
        Resource alex = model.createResource(alexURI, FOAF.Person)
            .addProperty(FOAF.name, model.createResource()
                         .addProperty(FOAF.firstName, "Alexandar")
                         .addProperty(FOAF.lastName, "Mihaylov"));

        // Resource jarek = model.createResource(jarekURI, FOAF.Person)
        //     .addProperty(FOAF.name, model.createResource()
        //                  .addProperty(FOAF.firstName, "Jarek")
        //                  .addProperty(FOAF.lastName, "Szlichta"));
        
        // // //Create a link
        // alex.addProperty(FOAF.knows, jarek);

        //Retrieve the alex resource
        Resource alexParsed = model.getResource(alexURI);
        // Resource nameParsed = (Resource) alex.getProperty(FOAF.name).getObject();
        Resource nameParsed = alex.getProperty(FOAF.name).getResource();
        String firstNameParsed = nameParsed.getProperty(FOAF.firstName).getString();
        String lastNameParsed = nameParsed.getProperty(FOAF.lastName).getString();
        
        logger.info("\n{} \n\t {} \n\t\t {} \n\t\t {}", alexParsed, nameParsed, firstNameParsed, lastNameParsed);

        nameParsed.addProperty(FOAF.nick, model.createResource()
                               .addProperty(FOAF.firstName, "alex")
                               .addProperty(FOAF.lastName, "m")
                               );
        
        //The language in which to write the model is specified by the lang argument. Predefined values are "RDF/XML", "RDF/XML-ABBREV", "N-TRIPLE", "TURTLE", (and "TTL") and "N3". The default value, represented by null, is "RDF/XML".
        model.write(System.out, "N-TRIPLE");

    }
}
