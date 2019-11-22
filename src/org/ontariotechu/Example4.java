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
import java.io.FileWriter;
import java.io.BufferedWriter;

public class Example4{
    private static final Logger logger = LoggerFactory.getLogger( Example4.class );

    private static final String outputFileName = "./res/data/simple.rdf";
    
    // some definitions
    static String bilboURI = "http://example.org/bilbo";
    
    public static void main(String args[]) throws Exception{
        logger.info("---------Jena RDF Example4---------");

        // create an empty Model
        Model model = ModelFactory.createDefaultModel();

        // create a person
        Resource bilbo = model.createResource(bilboURI, FOAF.Person)
            .addProperty(FOAF.name, model.createResource()
                         .addProperty(FOAF.firstName, "Bilbo")
                         .addProperty(FOAF.lastName, "Baggins"));


        //Retrieve the bilbo resource
        Resource bilboParsed = model.getResource(bilboURI);
        // Resource nameParsed = (Resource) bilbo.getProperty(FOAF.name).getObject();
        Resource nameParsed = bilbo.getProperty(FOAF.name).getResource();
        String firstNameParsed = nameParsed.getProperty(FOAF.firstName).getString();
        String lastNameParsed = nameParsed.getProperty(FOAF.lastName).getString();
        
        logger.info("\n{} \n\t {} \n\t\t {} \n\t\t {}", bilboParsed, nameParsed, firstNameParsed, lastNameParsed);

        nameParsed.addProperty(FOAF.nick, model.createResource()
                               .addProperty(FOAF.firstName, "bil")
                               .addProperty(FOAF.lastName, "b")
                               );
        
        //The language in which to write the model is specified by the lang argument. Predefined values are "RDF/XML", "RDF/XML-ABBREV", "N-TRIPLE", "TURTLE", (and "TTL") and "N3". The default value, represented by null, is "RDF/XML".
        model.write(System.out, "N-TRIPLE");

        //Write to file
        BufferedWriter writer = new BufferedWriter(new FileWriter(outputFileName));
        model.write(writer);
    }
}
