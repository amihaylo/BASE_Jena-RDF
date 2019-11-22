package org.ontariotechu;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Literal;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Statement;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.rdf.model.StmtIterator;
import org.apache.jena.sparql.vocabulary.FOAF;
import org.apache.jena.util.FileManager;
import java.io.InputStream;
import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;

public class Example5{
    private static final Logger logger = LoggerFactory.getLogger( Example5.class );

    private static final String inputFileName = "./res/data/simple.rdf";
    
    // some definitions
    static String bilboURI = "http://example.org/bilbo";
    
    public static void main(String args[]){
        logger.info("---------Jena RDF Example5---------");

        // create an empty Model
        Model model = ModelFactory.createDefaultModel();

        // use the FileManager to find the input file
        InputStream in = FileManager.get().open( inputFileName );
        if (in == null) 
            throw new IllegalArgumentException("File: " + inputFileName + " not found");

        // read the RDF/XML file
        model.read(in, null);

        //The language in which to write the model is specified by the lang argument. Predefined values are "RDF/XML", "RDF/XML-ABBREV", "N-TRIPLE", "TURTLE", (and "TTL") and "N3". The default value, represented by null, is "RDF/XML".
        model.write(System.out, "N-TRIPLE");

        String queryString = "SELECT ?x WHERE { ?x  <http://xmlns.com/foaf/0.1/firstName> 'bilbo' }";
        Query query = QueryFactory.create(queryString);
        QueryExecution qexec = QueryExecutionFactory.create(query, model);
        try {
            ResultSet results = qexec.execSelect();
            while ( results.hasNext() ) {
                QuerySolution soln = results.nextSolution();
                Resource x = soln.getResource("x");
                System.out.println(x);
            }
        } finally {
            qexec.close();
        }
    }
}
