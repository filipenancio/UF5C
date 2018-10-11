package br.ufsc.inf.tcc.showresult;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

import org.apache.commons.io.FileUtils;

import database.Database;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ExportListsToTxt {

	public static void main(String args[]) {
		String sql = "SELECT content FROM tb_lista";
		String matrix[][] = Database.getMatrizOf(sql);
		File dir = new File("txts_lists");
		if (dir.exists()) {
			try {
				FileUtils.deleteDirectory(dir);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		dir.mkdir();
		for (int i = 0; i < matrix.length; i++) {
			String content = matrix[i][0];
			try {
				String nomeFile = (new StringBuilder("lista_")).append(i).toString();
				String pathFile = (new StringBuilder("txts_lists/")).append(nomeFile).append(".txt").toString();
				FileUtils.writeStringToFile(new File(pathFile), content, Charset.defaultCharset(), false);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}
}
