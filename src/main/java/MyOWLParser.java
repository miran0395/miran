
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.expression.ShortFormEntityChecker;
import org.semanticweb.owlapi.manchestersyntax.renderer.ManchesterOWLSyntaxPrefixNameShortFormProvider;
import org.semanticweb.owlapi.model.OWLAxiom;
import org.semanticweb.owlapi.model.OWLClassExpression;
import org.semanticweb.owlapi.model.OWLDocumentFormat;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.model.parameters.Imports;
import org.semanticweb.owlapi.util.BidirectionalShortFormProvider;
import org.semanticweb.owlapi.util.BidirectionalShortFormProviderAdapter;
import org.semanticweb.owlapi.util.ShortFormProvider;
import org.semanticweb.owlapi.util.mansyntax.ManchesterOWLSyntaxParser;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author miran
 */
public class MyOWLParser {
    
     private final OWLOntologyManager manager;

    public MyOWLParser() {
        this.manager = OWLManager.createOWLOntologyManager();
    }
    
    public OWLOntology loadOntologyFromFile(String path) throws OWLOntologyCreationException {
        return manager.loadOntologyFromOntologyDocument(new File(path));
    }
    
     public OWLClassExpression parseConcept(OWLOntology ont, String ce) {
        ManchesterOWLSyntaxParser manchesterParser = OWLManager.createManchesterParser();
        manchesterParser.setDefaultOntology(ont);
        manchesterParser.setStringToParse(ce);
        ShortFormEntityChecker checker = new ShortFormEntityChecker(getShortFormProvider(ont));
        manchesterParser.setOWLEntityChecker(checker);
        return manchesterParser.parseClassExpression();
    }
     
     private BidirectionalShortFormProvider getShortFormProvider(OWLOntology ont) {
        Set<OWLOntology> ontologies = manager.getOntologies();
        ShortFormProvider sfp = new ManchesterOWLSyntaxPrefixNameShortFormProvider(manager.getOntologyFormat(ont));
        BidirectionalShortFormProvider shortFormProvider = new BidirectionalShortFormProviderAdapter(ontologies, sfp);
        return shortFormProvider;
    }
     
     public Map<String, String> getPrefixMap(OWLOntology ontology) {
        OWLDocumentFormat format = manager.getOntologyFormat(ontology);
        Map<String, String> map = new HashMap<>();
        if(format != null && format.isPrefixOWLDocumentFormat())
            map = new HashMap<>(format.asPrefixOWLDocumentFormat().getPrefixName2PrefixMap());
        return map;
    }
     
     
     public List<OWLAxiom> getAxioms(OWLOntology ont) {
        return new ArrayList<>(ont.getAxioms(Imports.EXCLUDED));
    }
    
}
