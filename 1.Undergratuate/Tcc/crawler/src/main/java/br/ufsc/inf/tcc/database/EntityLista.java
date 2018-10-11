package br.ufsc.inf.tcc.database;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import br.ufsc.inf.tcc.comum.IdValorDto;
import br.ufsc.inf.tcc.comum.ListDto;
import br.ufsc.inf.tcc.comum.PageDto;
import br.ufsc.inf.tcc.comum.RegistroDto;
import br.ufsc.inf.tcc.comum.ValorQtdDto;
import crawler.Settings;
import database.Database;
import database.SequenceCtrl;

public class EntityLista {

	public EntityLista() {
	}

	public static synchronized void inserirListasColetadas(PageDto pageDto, Settings settings) {
		inserirVisita(pageDto, settings);
	}

	private static void inserirVisita(PageDto pageDto, Settings settings) {
		SequenceCtrl seqCtrl = SequenceCtrl.getInstance();
		List<ListDto> listasDto = pageDto.getListas();
		LinkedList<String> sqls = new LinkedList<String>();
		long idVisit = seqCtrl.getNextIdVisita();
		long idUrl = pageDto.getUrlDto().getId();
		int idSett = settings.getId();

		String sqlVisit = (new StringBuilder("INSERT INTO tb_visit VALUES("))
		      .append(idVisit).append(",").append(idUrl).append(",").append(idSett).append(")")
		      .toString();
		sqls.addLast(sqlVisit);

		for (Iterator<ListDto> iterator = listasDto.iterator(); iterator.hasNext();) {
			ListDto listDto = iterator.next();
			String jsonString = getJsonDe(listDto);
			System.out.println(jsonString);

			if (jsonString != null) {
				long id = seqCtrl.getNextIdLista();
				jsonString = jsonString.replaceAll("'", "''");
				String sql = (new StringBuilder("INSERT INTO tb_lista VALUES ("))
				      .append(id).append(",").append(idVisit).append(",'").append(jsonString).append("')")
				      .toString();
				sqls.addLast(sql);
			}
		}

		Database.insert(sqls);
	}

	private static String getJsonDe(ListDto listDto) {
		String registros[] = listDto.registros;
		JSONArray regsJson = new JSONArray();

		for (int i = 0; i < registros.length; i++) {
			String registro = registros[i];

			JSONObject regJson = new JSONObject();
			regJson.put("desc", registro);

			regsJson.put(regJson);
		}

		RegistroDto registrosDtos[] = listDto.registroDtos;
		JSONArray regsDtosJson = new JSONArray();

		for (int k = 0; k < registrosDtos.length; k++) {
			RegistroDto regDto = registrosDtos[k];

			JSONArray attsJson = new JSONArray();
			String atts[] = regDto.getAtributos();

			for (int i2 = 0; i2 < atts.length; i2++) {
				String att = atts[i2];

				JSONObject attJson = new JSONObject();
				attJson.put("att", att);

				attsJson.put(attJson);
			}

			regsDtosJson.put(attsJson);
		}

		JSONArray sepsJson = new JSONArray();

		for (int i1 = 0; i1 < listDto.separadores.length; i1++) {
			IdValorDto separador = listDto.separadores[i1];

			JSONObject sepJson = new JSONObject();
			sepJson.put("id", separador.getId());
			sepJson.put("desc", separador.getValor());

			sepsJson.put(sepJson);
		}

		JSONArray identsJson = new JSONArray();

		for (int k1 = 0; k1 < listDto.identsDesc.length; k1++) {
			ValorQtdDto ident = listDto.identsDesc[k1];

			JSONObject identJson = new JSONObject();
			identJson.put("desc", ident.getValor());
			identJson.put("qtd", ident.getQtd());

			identsJson.put(identJson);
		}

		JSONObject listJson = new JSONObject();
		listJson.put("regstext", regsJson);
		listJson.put("regs", regsDtosJson);
		listJson.put("seps", sepsJson);
		listJson.put("idents", identsJson);

		return listJson.toString();
	}
}
