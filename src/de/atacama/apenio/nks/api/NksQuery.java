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
package de.atacama.apenio.nks.api;

import java.util.HashSet;
import java.util.Set;

import de.atacama.apenio.nks.api.model.NksEntry;

/**
 * Interne repr�sentation der Anfrage.
 */
public class NksQuery {

	/**
	 * AnfrageSprache
	 */
	private String lang = "de";

	/**
	 * Suchtiefe
	 */
	private int depth = 0;

	/**
	 * Mengen (z.B. Kataloge oder Ordner) in denen gesucht werden soll
	 */
	private Set<NksEntry> targetSet = new HashSet<>();

	/**
	 * Patientenmerkmale, die die Suche begrenzen können
	 */
	private Set<NksEntry> attributes = new HashSet<>();

	/**
	 * Apeniocodes (Phenomäne, Interventionen, etc.) oder z.B. ICD-Diagnosen
	 */
	private Set<NksEntry> combinatedConcept = new HashSet<>();

	/**
	 * Suchtext
	 */
	private String text = "";

	/**
	 * Optionale SessionID. Wird für den Sicherheitsmodus benutzt
	 */
	private String sessionID;

	// TODO: Fragwuerdige felder
	private boolean doBayes = true;
	private NksEntry template;
	private String textContext;
	private String metaContext;
	private String hierarchy;
	private int mode;

	NksQuery() {
	}

	public void addTarget(final NksEntry se) {
		targetSet.add(se);
	}

	public void addAttribute(final NksEntry se) {
		attributes.add(se);
	}

	public boolean isTarget(final String cName) {
		for (NksEntry se : targetSet)
			if (se.getcName().equals(cName)) return true;
		return false;
	}

	public void addConcept(final NksEntry se) {
		combinatedConcept.add(se);
	}

	/////////////////////
	// GETTER + SETTER //
	/////////////////////

	public int getDepth() {
		return depth;
	}

	public void setDepth(final int depth) {
		this.depth = depth;
	}

	public Set<NksEntry> getTargetSet() {
		return targetSet;
	}

	public Set<NksEntry> getAttributes() {
		return attributes;
	}

	public void setAttributes(final Set<NksEntry> attributes) {
		this.attributes = attributes;
	}

	public Set<NksEntry> getCombinatedConcept() {
		return combinatedConcept;
	}

	public void setCombinatedConcept(final Set<NksEntry> combinatedConcept) {
		this.combinatedConcept = combinatedConcept;
	}

	public void setDoBayes(final boolean b) {
		this.doBayes = b;
	}

	public boolean isDoBayes() {
		return doBayes;
	}

	public String getLang() {
		return lang;
	}

	public void setLang(final String lang) {
		this.lang = lang;
	}

	public String getSearchText() {
		return text;
	}

	public void setSearchText(final String searchText) {
		this.text = searchText;
	}

	public void setTargetSet(final HashSet<NksEntry> targetSet) {
		this.targetSet = targetSet;
	}

	public String getSessionID() {
		return sessionID;
	}

	public void setSessionID(final String sessionID) {
		this.sessionID = sessionID;
	}

	public String getText() {
		return text;
	}

	public void setText(final String text) {
		this.text = text;
	}

	public int getMode() {
		return mode;
	}

	public void setMode(final int mode) {
		this.mode = mode;
	}

	public String getMetaContext() {
		return metaContext;
	}

	public void setMetaContext(final String metaContext) {
		this.metaContext = metaContext;
	}

	public String getTextContext() {
		return textContext;
	}

	public void setTextContext(final String textContext) {
		this.textContext = textContext;
	}

	public String getHierarchy() {
		return hierarchy;
	}

	public void setHierarchy(final String hierarchy) {
		this.hierarchy = hierarchy;
	}

	public NksEntry getTemplate() {
		return template;
	}

	public void setTemplate(final NksEntry template) {
		this.template = template;
	}


	/**
	 * @return einen Text, der dieses Object zu Debug-Zwecken beschreibt
	 */
	public String getSummary() {

		StringBuffer summary = new StringBuffer();

		summary.append("Text-Search = " + text + " \n");
		summary.append("xml:lang = " + lang + " \n");
		summary.append("depth    = " + depth + " \n");

		summary.append("Target-Sets: \n");
		for (NksEntry se : targetSet)
			summary.append("   - " + se.getcName() + "\n");

		summary.append("Attributes: \n");
		for (NksEntry se : attributes)
			summary.append("   - " + se.getcName() + "\n");

		summary.append("Combinated Concepts: \n");
		for (NksEntry se : combinatedConcept)
			summary.append("   - " + se.getcName() + "\n");

		return summary.toString();
	}

}
