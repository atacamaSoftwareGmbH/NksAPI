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

import de.atacama.apenio.nks.api.model.NksEntry;

/**
 * Erstellt eine Zielmenge anhand eines Konzeptnames.
 * Strukturen können hinzugefügt werden.
 * 
 * @author Weert Stamm
 */
public class TargetBuilder {

	NksEntry entry;

	/**
	 * Die Zielmenge wird durch einen Konzeptnamen definiert.
	 * @param targetConceptName
	 * 			der Konzeptname der Zielmenge
	 */
	public TargetBuilder(String targetConceptName) {
		entry = new NksEntry(targetConceptName);
	}

	/**
	 * Durch Strukturen kann die Zielmenge weiter eingeschränkt werden. 
	 * @param structure
	 * 			der KonzeptName einer Struktur
	 * @return eine Instanz zur Verkettung
	 */
	public TargetBuilder addStructure(String structure) {
		entry.addStructure(structure);
		return this;
	}

	NksEntry create() {
		return entry;
	}

}
