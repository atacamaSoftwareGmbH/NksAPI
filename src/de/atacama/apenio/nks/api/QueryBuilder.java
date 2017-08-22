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

import de.atacama.apenio.nks.api.error.QueryException;
import de.atacama.apenio.nks.api.model.NksEntry;

/**
 * Dient zur Erstellung einer Anfrage an den NKS Server.
 * Dabei kann die Sprache der Anfrage (default: de),
 * die Suchtiefe und der Suchtext direkt angegeben werden.
 * Attribute und Konzepte können über den Konzeptnamen
 * eines Eintrages hinzugefügt werden.
 * Um die Zielmenge zu bestimmen, kann ein TargetBuilder 
 * verwendet werden.
 * 
 * Wichtig: Um eine valide Sucheanfrage zu stellen muss
 * 			derzeit zu jeder Zielmenge die Zielstruktur/en
 * 			angegeben werden.
 * 
 * @author Weert Stamm
 */
public class QueryBuilder {

	private NksQuery query = new NksQuery();

	public QueryBuilder() {
	}

	/**
	 * Bestimmt die Sprache der Suche
	 * @param language
	 * 			mögliche Angaben: de, en, fr
	 * @return eine Instanz zur Verkettung
	 */
	public QueryBuilder setLanguage(String language) {
		query.setLang(language);
		return this;
	}

	/**
	 * Fügt der Anfrage ein einfaches Konzept über den Konzeptnamen
	 * hinzu.
	 * @param conceptName
	 * 			Der Konzeptname, wie im Semantischen Netz definiert.
	 * @return eine Instanz zur Verkettung
	 */
	public QueryBuilder addSimpleConcept(String conceptName) {
		query.addConcept(new NksEntry(conceptName));
		return this;
	}

	/**
	 * Fügt der Anfrage ein Attribute über dessen Konzeptnamen
	 * hinzu.
	 * @param conceptName
	 * 			Der Konzeptname, wie im Semantischen Netz definiert.
	 * @return eine Instanz zur Verkettung
	 */
	public QueryBuilder addAttribute(String conceptName) {
		query.addAttribute(new NksEntry(conceptName));
		return this;
	}

	/**
	 * Fügt der Anfragesuchmenge eine Menge hinzu.
	 * @param targetBuilder
	 * 			Ein TargetBuilder mit dem es möglich ist schnell eine Menge
	 * 			für die Suchmenge zu erstellen
	 * @return eine Instanz zur Verkettung
	 */
	public QueryBuilder addTarget(TargetBuilder targetBuilder) {
		query.addTarget(targetBuilder.create());
		return this;
	}

	/**
	 * Setzt den Text, der benutzt wird um nach Labels zu suchen.
	 * @param text
	 * 			der Suchtext
	 * @return eine Instanz zur Verkettung
	 */
	public QueryBuilder setSearchText(String text) {
		query.setText(text);
		return this;
	}

	/**
	 * Setzt die Tiefe der Response-Baumstruktur, Länge der nach Relevanz
	 * sortierten Listen (z.B. Vorschläge, Suchergebnisse)
	 * @param depth
	 * 			die Tiefe der Suchanfrage
	 * @return eine Instanz zur Verkettung
	 */
	public QueryBuilder setSearchDepth(int depth) {
		query.setDepth(depth);
		return this;
	}

	NksQuery create() {
		validate();
		return query;
	}

	private void validate() {
		for(NksEntry entry : query.getTargetSet()) {
			if(entry.getStructures().size() == 0) {
				throw new QueryException("As of now, every target of a query needs at least one structure.");
			}
		}
	}

}
