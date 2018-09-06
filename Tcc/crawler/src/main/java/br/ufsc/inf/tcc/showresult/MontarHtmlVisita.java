package br.ufsc.inf.tcc.showresult;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Iterator;

import org.apache.commons.io.FileUtils;

import br.ufsc.inf.tcc.comum.IdValorDto;
import br.ufsc.inf.tcc.comum.ListDto;
import br.ufsc.inf.tcc.comum.RegistroDto;
import br.ufsc.inf.tcc.comum.UrlDto;
import br.ufsc.inf.tcc.comum.ValorQtdDto;
import br.ufsc.inf.tcc.comum.VisitDto;
import crawler.Settings;

public class MontarHtmlVisita {

	public MontarHtmlVisita() {
	}

	public static void doit(String path, UrlDto urlDto, VisitDto visitDto) {
		String html = getHtml(path, urlDto, visitDto);
		try {
			String nomeFile = (new StringBuilder("visit")).append(visitDto.getId()).toString();
			String pathFile = (new StringBuilder(String.valueOf(path))).append("/").append(nomeFile).append(".html")
			      .toString();
			FileUtils.writeStringToFile(new File(pathFile), html, Charset.defaultCharset(), false);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static String getHtml(String path, UrlDto urlDto, VisitDto visitDto) {

		Settings settings = visitDto.getSettings();

		//@formatter:off
		String html = new StringBuilder()
				.append("<!DOCTYPE html><html><head><title>Visita</title><link rel=\"stylesheet\" type=\"text/css\" href=\"../result.css\"></head><body>")
		      .append("<a href=\"").append(urlDto.getUrl()).append("\">")
		      .append(urlDto.getUrl()).append("</a>")
		      .append("<div><b>N\372mero m\355nimo de registros: </b> ")
		      .append(settings.getNumMinRegistros()).append("</div>")
		      .append("<div><b>N\372mero m\341ximo de caracteres: </b> ")
		      .append(settings.getNumMaxCaracteres()).append("</div>")
		      .append("<div><b>N\372mero de deriva\347\365es: </b> ")
		      .append(settings.getNumDeDerivacoes()).append("</div>")
		      .append("<div><b>Percentual m\355nimo para identidade prevalecente: </b> ")
		      .append(settings.getPercentIdentidadePrevalecente()).append("</div>")
		    .toString();
		//@formatter:on

		for (Iterator<?> iterator = visitDto.getListas().iterator(); iterator.hasNext();) {
			ListDto listDto = (ListDto) iterator.next();
			html = (new StringBuilder(String.valueOf(html)))
			      .append("<div class=\"listdto\">")   
			      .toString();
			html = (new StringBuilder(String.valueOf(html)))
			      .append("<div class=\"itens\"><div>itens</div><ul>")
			      .toString();

			for (int i = 0; i < listDto.registros.length; i++) {
				String reg = listDto.registros[i];
				html = (new StringBuilder(String.valueOf(html)))
				      .append("<li>").append(reg).append("</li>")
				      .toString();
			}

			html = (new StringBuilder(String.valueOf(html)))
					.append("</ul></div>")
					.toString();
			
			html = (new StringBuilder(String.valueOf(html)))
					.append("<div class=\"lista\"><div>separadores</div><ul>")
			      .toString();
			
			for (int j = 0; j < listDto.separadores.length; j++) {
				IdValorDto separador = listDto.separadores[j];
				String id = separador.getId();
				String valor = separador.getValor();
				
				html = (new StringBuilder(String.valueOf(html)))
						.append("<li><b>").append(id).append("</b> ")
						.append(valor).append("</li>")
						.toString();
			}

			html = (new StringBuilder(String.valueOf(html)))
					.append("</ul></div>")
					.toString();
			
			html = (new StringBuilder(String.valueOf(html)))
					.append("<div class=\"lista\"><div>identidades</div><ul>")
				   .toString();
			
			for (int k = 0; k < listDto.identsDesc.length; k++) {
				ValorQtdDto identidade = listDto.identsDesc[k];
				
				String qtd = (new StringBuilder(String.valueOf(identidade.getQtd())))
						.append("x")
						.toString();
				
				String ident = identidade.getValor();
				
				html = (new StringBuilder(String.valueOf(html)))
						.append(" <li><b>").append(qtd).append("</b> ")
						.append(ident).append("</li>")
						.toString();
			}

			html = (new StringBuilder(String.valueOf(html)))
					.append("</ul></div>")
					.toString();
			
			html = (new StringBuilder(String.valueOf(html)))
					.append("<div class=\"separados\"><div>registros separados:</div><br/><table>")
					.toString();
			
			for (int l = 0; l < listDto.registroDtos.length; l++) {
				RegistroDto regDto = listDto.registroDtos[l];
				
				html = (new StringBuilder(String.valueOf(html)))
						.append("<tr>")
						.toString();
				
				for (int j1 = 0; j1 < regDto.getAtributos().length; j1++) {
					String att = regDto.getAtributos()[j1];
					html = (new StringBuilder(String.valueOf(html)))
							.append("<td>").append(att).append("</td>")
							.toString();
				}

				html = (new StringBuilder(String.valueOf(html)))
						.append("</tr>")
						.toString();
			}

			html = (new StringBuilder(String.valueOf(html)))
					.append("</table></div></div>")
					.toString();
			
			html = (new StringBuilder(String.valueOf(html)))
					.append("</div>")
					.toString();
		}

		html = (new StringBuilder(String.valueOf(html)))
				.append("</body></html>")
				.toString();
		
		return html;
	}
}
