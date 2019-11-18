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

public class Example3{
    private static final Logger logger = LoggerFactory.getLogger( Example3.class );
    
    // some definitions
    static String alexURI = "http://example.org/alex";
    static String jarekURI = "http://example.org/jarek";
    
    public static void main(String args[]){
        logger.info("---------Jena RDF Example3---------");

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
        // model.write(System.out, "N-TRIPLE");

        //List all the statements in the Model
        StmtIterator iter = model.listStatements();

        while (iter.hasNext()){
            Statement stmt = iter.nextStatement();     //get the next statement
            Resource subject = stmt.getSubject();      //parse the subject
            Property predicate = stmt.getPredicate();  //parse the predicate
            RDFNode object = stmt.getObject();         //parse the object (RDFNode superclass of Resource & Literal)
            String objectStr = (object instanceof Resource) ? object.toString(): "/" + object.toString() + "/";

            logger.info("{} -> {} -> {}",subject, predicate, objectStr);
            
        }
    }
}
