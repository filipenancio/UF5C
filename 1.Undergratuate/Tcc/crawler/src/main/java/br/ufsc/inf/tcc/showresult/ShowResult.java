package br.ufsc.inf.tcc.showresult;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

import br.ufsc.inf.tcc.comum.UrlDto;
import br.ufsc.inf.tcc.comum.VisitDto;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ShowResult {

	public static void putInResults(UrlDto urlDto) {
		VisitDto visitDtos[] = MontarVisitas.doit(urlDto);
		doit(urlDto, visitDtos, "results");
	}

	public static void putInRevocResults(UrlDto urlDto, VisitDto visitDtos[]) {
		doit(urlDto, visitDtos, "revoc_results");
	}

	private static void doit(UrlDto urlDto, VisitDto visitDtos[], String path) {
		boolean construir = false;
		VisitDto avisitdto[];
		int j = (avisitdto = visitDtos).length;
		for (int i = 0; i < j; i++) {
			VisitDto visitDto = avisitdto[i];
			if (visitDto.getListas().size() <= 0) {
				continue;
			}
			construir = true;
			break;
		}

		if (!construir) {
			return;
		}
		File resultsDir = new File(path);
		if (!resultsDir.exists()) {
			resultsDir.mkdir();
		}
		long idUrl = urlDto.getId();
		String pathResultDir = (new StringBuilder(String.valueOf(path))).append("/url").append(idUrl).toString();
		File resultDir = new File(pathResultDir);
		if (resultDir.exists()) {
			try {
				FileUtils.deleteDirectory(resultDir);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		resultDir.mkdir();
		VisitDto avisitdto1[];
		int l = (avisitdto1 = visitDtos).length;
		for (int k = 0; k < l; k++) {
			VisitDto visitDto = avisitdto1[k];
			MontarHtmlVisita.doit(pathResultDir, urlDto, visitDto);
		}

	}
}
