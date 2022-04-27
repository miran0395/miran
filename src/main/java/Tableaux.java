
import java.util.List;
import java.util.Map;
import org.semanticweb.owlapi.model.OWLAxiom;
import org.semanticweb.owlapi.model.OWLException;
import org.semanticweb.owlapi.model.OWLOntology;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author miran
 */
public class Tableaux {
        private final Map<String, String> prefix_nsMap;
        private final MyOWLParser parser;
        private final List<OWLAxiom> fullTbox;
        
        public Tableaux(MyOWLParser parser, OWLOntology T) throws OWLException {
        this.parser = parser;
        //Carico i prefissi del namespace dalla TBox
        prefix_nsMap = parser.getPrefixMap(T);
        fullTbox = parser.getAxioms(T);
        }
        
        
        public Map<String, String> getprefix()
        {
            return prefix_nsMap;
        }
        
         public List<OWLAxiom> getTbox()
        {
            return fullTbox;
        }
        
    
}
