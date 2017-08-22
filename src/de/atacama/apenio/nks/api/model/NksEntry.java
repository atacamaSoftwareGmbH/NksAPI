/* Copyright (c) 2017 atacama | Software GmbH
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package de.atacama.apenio.nks.api.model;

import java.io.Serializable;
import java.util.*;

/**
 * Repräsentiert ein Element aus dem semantischen Netz.
 * Je nach Ausprägung ein Konzept aus einem Katalog, einer 
 * Klassifikation oder auch einen Begriff aus dem 
 * apenioNKS-Lexikon.
 * 
 * @author Sebastian Zebbities
 */
public class NksEntry implements Serializable {

    private static final long serialVersionUID = 3373646471263067243L;

    private String type;
    private String superType;
    private int listIndex;
    private String cat;
    private String dom;
    private String cName;
    private String label;
    private String lang;
    private Set<String> warnings = new HashSet<>();
    private String entryValue;
    protected String evidence; //Bayes
    protected String score; //Wenn Entry Ergebnis einer Suche war, kann hier der Score eingetragen werden.

    private List<String> abstracts;

    private String noxGrade;

    private String htmlContent;

    private Set<String> structures = new HashSet<>();
    private Map<String, HashSet<String>> dataRelation = new HashMap<>();
    private Map<String, HashSet<String>> objectRelation = new HashMap<>();

    // Admin-Protected
    protected Set<String> fields = new HashSet<>();
    protected Set<String> cores = new HashSet<>();
    protected Map<NksEntry, Double> interceptedEntries;
    protected Set<NksEntry> entries = new HashSet<>();
    protected Set<NksEntry> folders = new HashSet<>();
    protected List<String> titles = new ArrayList<>();
    protected List<String> texts = new ArrayList<>();
    protected Set<NksEntry> targetsOfShape = new HashSet<>(); // ist object Relation
    protected String careLevel; // ist data Relation
    protected NksEntry superTypeEntry;
    protected List<String> multiLabel = new ArrayList<>();
    protected Map<String, String> labelMap = new HashMap<>();


    public NksEntry(String cName) {
        this.setcName(cName);
    }

    public NksEntry() {
    }

    /** Fuegt dem Ordner ein Element hinzu */
    public void addEntry(NksEntry entry) {
        this.entries.add(entry);
    }

    /** Fuegt dem Ordner einen Ordner hinzu */
    public void addFolder(NksEntry folder) {
        this.folders.add(folder);
    }

    public void addStructure(String structure) {
        this.structures.add(structure);
    }


    //#################//
    // GETTER + SETTER //
    //#################//

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSuperType() {
        return superType;
    }

    public void setSuperType(String superType) {
        this.superType = superType;
    }

    public int getListIndex() {
        return listIndex;
    }

    public void setListIndex(int listIndex) {
        this.listIndex = listIndex;
    }

    public String getCat() {
        return cat;
    }

    public void setCat(String cat) {
        this.cat = cat;
    }

    public String getDom() {
        return dom;
    }

    public void setDom(String dom) {
        this.dom = dom;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public Set<String> getWarnings() {
        return warnings;
    }

    public void setWarnings(Set<String> warnings) {
        this.warnings = warnings;
    }

    public String getEntryValue() {
        return entryValue;
    }

    public void setEntryValue(String entryValue) {
        this.entryValue = entryValue;
    }

    public List<String> getAbstracts() {
        return abstracts;
    }

    public void setAbstracts(List<String> abstracts) {
        this.abstracts = abstracts;
    }

    public String getNoxGrade() {
        return noxGrade;
    }

    public void setNoxGrade(String noxGrade) {
        this.noxGrade = noxGrade;
    }

    public String getHtmlContent() {
        return htmlContent;
    }

    public void setHtmlContent(String htmlContent) {
        this.htmlContent = htmlContent;
    }

    public Set<String> getStructures() {
        return structures;
    }

    public void setStructures(Set<String> structures) {
        this.structures = structures;
    }

    public Map<String, HashSet<String>> getDataRelation() {
        return dataRelation;
    }

    public void setDataRelation(Map<String, HashSet<String>> dataRelation) {
        this.dataRelation = dataRelation;
    }

    public Map<String, HashSet<String>> getObjectRelation() {
        return objectRelation;
    }

    public void setObjectRelation(Map<String, HashSet<String>> objectRelation) {
        this.objectRelation = objectRelation;
    }
}
