
import java.io.File;
import java.util.List;
import java.util.Map;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLDocumentFormat;
import org.semanticweb.owlapi.manchestersyntax.parser.ManchesterOWLSyntaxParserImpl;
import org.semanticweb.owlapi.model.OWLAxiom;
import org.semanticweb.owlapi.model.OWLClassExpression;
import org.semanticweb.owlapi.model.OWLException;
import org.semanticweb.owlapi.util.mansyntax.ManchesterOWLSyntaxParser;

//parseOntology


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author miran
 */
public class ProvaMain {


    
    public static void main(String[] args) {
        
        
        String conceptText = "ALC:A and (ALC:R some (not(ALC:A)))";
        OWLOntology terminology = null;
        MyOWLParser parser=new MyOWLParser();
        
        String terminologyPath= "C:\\Users\\miran\\Desktop\\ConsegnaVer2_Gruppo2_CiroMicheleMartino_MarcoMennillo\\"
                + "Consegna_Gruppo2_CiroMicheleMartino_MarcoMennillo\\ontology\\ontology_grande.owl";
        try {
            terminology = parser.loadOntologyFromFile(terminologyPath);
            System.out.println(terminology);
            OWLClassExpression concept = parser.parseConcept(terminology, conceptText);
            Tableaux tableauxReference = new Tableaux(parser, terminology);
            
            Map<String, String> map= tableauxReference.getprefix();
            
            System.out.println(map);
            
            List<OWLAxiom> fullTbox = parser.getAxioms(terminology);
            
            System.out.println(fullTbox.get(1));

         } catch (OWLException e) {
            System.out.println("Errore.");
            e.printStackTrace();
        }
        
    
}
    
}