package br.ufsc.inf.tcc.simlist.analyser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.ufsc.inf.tcc.simlist.data.Document;
import br.ufsc.inf.tcc.simlist.data.Line;
import br.ufsc.inf.tcc.simlist.data.ListOf;
import br.ufsc.inf.tcc.simlist.data.Text;

public class DocumentNormalizer {

	private static List<String> stopWords = StopWords.getAllPortugueseStopWords();

	public static Document normalize(Document doc) {

		String link = stringNormalize(doc.getLink());
		String titlePage = stringNormalize(doc.getTitlePage());
		Text text = new Text(doc.getText() != null ? stringNormalize(doc.getText().getParagraph()) : "");

		ListOf list = listNormalize(doc.getList());

		return new Document(link, titlePage, text, list);
	}

	private static ListOf listNormalize(ListOf list) {

		if (list == null) {
			list = new ListOf("", new ArrayList<Line>());
		}

		String title = stringNormalize(list.getTitle());

		List<Line> lines = new ArrayList<>();
		for (Line line : list.getLines()) {
			List<String> elements = new ArrayList<>();
			for (String element : line.getElement()) {
				elements.add(stringNormalize(element));
			}
			lines.add(new Line(elements));
		}

		return new ListOf(title, lines);
	}

	private static String stringNormalize(String string) {
		if (string != null) {
			String withOutP = string.replaceAll("\\p{Punct}", " ");
			String withOutPSW = removeStopWords(withOutP);
			String withOutPSWN = removeNumbers(withOutPSW);
			return withOutPSWN;
		}
		return "";
	}

	private static String removeStopWords(String withOutP) {
		String r = "";
		for (String s : withOutP.split(" ")) {
			if (!stopWords.contains(s)) {
				r = r + s + " ";
			}
		}
		return r;
	}

	private static String removeNumbers(String withOutPSW) {
		String r = "";
		for (String s : withOutPSW.split(" ")) {
			if (!s.matches("^[0-9]*$")) {
				r = r + s + " ";
			}
		}
		return r;
	}

	static class StopWords {

		public static List<String> getAllPortugueseStopWords() {
			return Arrays.asList(
			      "de", "a", "o", "que", "e", "do", "da", "em", "um", "para",
			      "é", "com", "não", "uma", "os", "no", "se", "na", "por", "mais",
			      "as", "dos", "como", "mas", "foi", "ao", "ele", "das", "tem",
			      "à", "seu", "sua", "ou", "ser", "quando", "muito", "há", "nos",
			      "já", "está", "eu", "também", "só", "pelo", "pela", "até", "isso",
			      "ela", "entre", "era", "depois", "sem", "mesmo", "aos", "ter",
			      "seus", "quem", "nas", "me", "esse", "eles", "estão", "você",
			      "tinha", "foram", "essa", "num", "nem", "suas", "meu", "às",
			      "minha", "têm", "numa", "pelos", "elas", "havia", "seja", "qual",
			      "será", "nós", "tenho", "lhe", "deles", "essas", "esses", "pelas",
			      "este", "fosse", "dele", "tu", "te", "vocês", "vos", "lhes", "meus",
			      "minhas", "teu", "tua", "teus", "tuas", "nosso", "nossa", "nossos",
			      "nossas", "dela", "delas", "esta", "estes", "estas", "aquele",
			      "aquela", "aqueles", "aquelas", "isto", "aquilo", "estou", "está",
			      "estamos", "estão", "estive", "esteve", "estivemos", "estiveram",
			      "estava", "estávamos", "estavam", "estivera", "estivéramos",
			      "esteja", "estejamos", "estejam", "estivesse", "estivéssemos",
			      "estivessem", "estiver", "estivermos", "estiverem", "hei", "há",
			      "havemos", "hão", "houve", "houvemos", "houveram", "houvera",
			      "houvéramos", "haja", "hajamos", "hajam", "houvesse", "houvéssemos",
			      "houvessem", "houver", "houvermos", "houverem", "houverei", "houverá",
			      "houveremos", "houverão", "houveria", "houveríamos", "houveriam",
			      "sou", "somos", "são", "era", "éramos", "eram", "fui", "foi", "fomos",
			      "foram", "fora", "fôramos", "seja", "sejamos", "sejam", "fosse",
			      "fôssemos", "fossem", "for", "formos", "forem", "serei", "será",
			      "seremos", "serão", "seria", "seríamos", "seriam", "tenho", "tem",
			      "temos", "tém", "tinha", "tínhamos", "tinham", "tive", "teve",
			      "tivemos", "tiveram", "tivera", "tivéramos", "tenha", "tenhamos",
			      "tenham", "tivesse", "tivéssemos", "tivessem", "tiver", "tivermos",
			      "tiverem", "terei", "terá", "teremos", "terão", "teria", "teríamos",
			      "teriam");
		}

	}

}
