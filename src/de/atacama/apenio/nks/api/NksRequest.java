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

import de.atacama.apenio.nks.api.error.NksException;
import de.atacama.apenio.nks.api.io.net.RestClient;

/**
 * Hauptklasse um mit einem NKS Server zu kommunizieren.
 * Vor dem Benutzen einer Funktion des NKS muss eine valide URL
 * zu einem NKS-Server gesetzt werden. Die URL muss folgende Form haben:
 * 
 * http://[Adresse]:[Port]/[Projektname]/rest
 * 
 * Für die Suche wird ein QueryBuilder verwendet, der es erleichtert eine
 * valide Anfrage an den Server zu stellen.
 * 
 * Das Rückgabeformat ist eine NksResponse.
 * 
 * Die Methodem können außerdem Ausnahmen des Typs RuntimeException werfen.
 * Diese sollten für korrekte Fehlerbehandlung gefangen werden.
 * 
 * @author Weert Stamm
 */
public class NksRequest {

	/**
	 * Zugriff auf die einzige Instanz
	 */
	public static final NksRequest INSTANCE = new NksRequest();

	private static final String SEARCH = "/search";
	
	private static final String PROPOSAL = SEARCH + "/proposal";

	private String url = "http://apenioapp02:19080/NksService/rest";

	private NksRequest() {
	}

	/**
	 * Bietet Zugriff auf die semantische Suche (FindSem) des NKS.
	 * Hier wird, wie in der offiziellen Schnittstellenbeschreibung angegeben,
	 * eine Anfrage übergeben, die mit einem QueryBuilder erstellt wird.
	 * Wird keine Ausnahme geworfen, ist das Ergebnis ein NksResponse.
	 * Darin enthalten sind evtl. Warnungen und die gefundenen Einträge.
	 * 
	 * @param query
	 * 			Die Anfrage als gesamter QueryBuilder
	 * @return
	 * 		Ein NksResponse Objekt, dass alle Daten des Ergebnisses enthält
	 * @throws NksException, QueryException, HttpException
	 */
	public NksResponse search(QueryBuilder query) {
		return request(query.create(), SEARCH);
	}

	public NksResponse proposal(QueryBuilder query) {
		return request(query.create(), PROPOSAL);
	}
	
	/**
	 * Vor dem Benutzen einer Funktion des NKS muss eine valide URL
	 * zu einem NKS-Server gesetzt werden. Die URL muss folgende Form haben:
	 * 
	 * http://[Adresse]:[Port]/[Projektname]/rest
	 * 
	 * @param url
	 * 			eine valide URL zum NKS Server mit folgendem Format:  http://[Adresse]:[Port]/[Projektname]/rest
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	
	private NksResponse request(NksQuery query, String urlAddition) {
		if(url == null || url.isEmpty() || !url.endsWith("rest"))
			throw new NksException("Please define the URL to the NKS in this scheme   http://[Adresse]:[Port]/[Projektname]/rest");
		return RestClient.INSTANCE.request(query, url + urlAddition);
	}

}