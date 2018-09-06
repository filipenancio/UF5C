package br.ufsc.inf.tcc.showresult;

import org.json.JSONArray;
import org.json.JSONObject;

import br.ufsc.inf.tcc.comum.IdValorDto;
import br.ufsc.inf.tcc.comum.ListDto;
import br.ufsc.inf.tcc.comum.RegistroDto;
import br.ufsc.inf.tcc.comum.UrlDto;
import br.ufsc.inf.tcc.comum.ValorQtdDto;
import br.ufsc.inf.tcc.comum.VisitDto;
import crawler.Settings;
import database.Database;

public class MontarVisitas {

	public MontarVisitas() {
	}

	public static VisitDto[] doit(UrlDto urlDto) {
		String sql = (new StringBuilder("SELECT id, id_settings from tb_visit WHERE id_url = ")).append(urlDto.getId())
		      .toString();
		String matrix[][] = Database.getMatrizOf(sql);
		VisitDto visitDtos[] = new VisitDto[matrix.length];
		for (int i = 0; i < matrix.length; i++) {
			long id = Long.parseLong(matrix[i][0]);
			int idSettings = Integer.parseInt(matrix[i][1]);
			Settings settings = Settings.getSettings(idSettings);
			VisitDto visit = new VisitDto(id, settings);
			visitDtos[i] = visit;
		}

		VisitDto avisitdto[];
		int k = (avisitdto = visitDtos).length;
		for (int j = 0; j < k; j++) {
			VisitDto visitDto = avisitdto[j];
			ListDto listDtos[] = getListas(visitDto);
			ListDto alistdto[];
			int i1 = (alistdto = listDtos).length;
			for (int l = 0; l < i1; l++) {
				ListDto listDto = alistdto[l];
				visitDto.addLista(listDto);
			}

		}

		return visitDtos;
	}

	public static ListDto[] getListas(VisitDto visitDto) {
		String sql = (new StringBuilder("SELECT content FROM tb_lista WHERE id_visit = ")).append(visitDto.getId())
		      .toString();
		String matrix[][] = Database.getMatrizOf(sql);
		ListDto listDtos[] = new ListDto[matrix.length];
		for (int i = 0; i < matrix.length; i++) {
			JSONObject listJson = new JSONObject(matrix[i][0]);
			listDtos[i] = getListDto(listJson);
		}

		return listDtos;
	}

	public static ListDto getListDto(JSONObject listJson) {
		ListDto listDto = new ListDto();
		JSONArray regstext = listJson.getJSONArray("regstext");
		JSONArray regs = listJson.getJSONArray("regs");
		JSONArray seps = listJson.getJSONArray("seps");
		JSONArray idents = listJson.getJSONArray("idents");
		listDto.registros = getRegistros(regstext);
		listDto.registroDtos = getRegistrosDto(regs);
		listDto.separadores = getSeparadores(seps);
		listDto.identsDesc = getIdentidades(idents);
		return listDto;
	}

	public static String[] getRegistros(JSONArray regstext) {
		String registros[] = new String[regstext.length()];
		for (int i = 0; i < registros.length; i++) {
			JSONObject regJson = regstext.getJSONObject(i);
			String desc = regJson.getString("desc");
			registros[i] = desc;
		}

		return registros;
	}

	public static RegistroDto[] getRegistrosDto(JSONArray regs) {
		RegistroDto registroDtos[] = new RegistroDto[regs.length()];
		for (int i = 0; i < registroDtos.length; i++) {
			RegistroDto registroDto = new RegistroDto();
			JSONArray attsJson = regs.getJSONArray(i);
			String atributos[] = new String[attsJson.length()];
			for (int j = 0; j < atributos.length; j++) {
				JSONObject attJson = attsJson.getJSONObject(j);
				atributos[j] = attJson.getString("att");
			}

			registroDto.addAtributos(atributos);
			registroDtos[i] = registroDto;
		}

		return registroDtos;
	}

	public static IdValorDto[] getSeparadores(JSONArray seps) {
		IdValorDto separadores[] = new IdValorDto[seps.length()];
		for (int i = 0; i < separadores.length; i++) {
			JSONObject sepJson = seps.getJSONObject(i);
			String id = sepJson.getString("id");
			String desc = sepJson.getString("desc");
			IdValorDto idv = new IdValorDto(id, desc);
			separadores[i] = idv;
		}

		return separadores;
	}

	public static ValorQtdDto[] getIdentidades(JSONArray idents) {
		ValorQtdDto identidades[] = new ValorQtdDto[idents.length()];
		for (int i = 0; i < identidades.length; i++) {
			JSONObject identJson = idents.getJSONObject(i);
			String desc = identJson.getString("desc");
			int qtd = identJson.getInt("qtd");
			ValorQtdDto vqtd = new ValorQtdDto(desc, qtd);
			identidades[i] = vqtd;
		}

		return identidades;
	}
}
