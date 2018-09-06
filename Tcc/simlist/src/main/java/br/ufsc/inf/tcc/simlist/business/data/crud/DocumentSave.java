package br.ufsc.inf.tcc.simlist.business.data.crud;

import static br.ufsc.inf.tcc.simlist.database.model.meta.QTbLinha.tbLinha;
import static br.ufsc.inf.tcc.simlist.database.model.meta.QTbLista.tbLista;
import static br.ufsc.inf.tcc.simlist.database.model.meta.QTbToken.tbToken;

import java.util.List;

import br.ufsc.inf.tcc.simlist.business.data.xml.Document;
import br.ufsc.inf.tcc.simlist.business.data.xml.Line;
import br.ufsc.inf.tcc.simlist.database.sql.SettingsInsert;

public class DocumentSave {

	public void save(Document doc, String fileName) {

		Long idList = saveList(doc, fileName);
		saveLines(doc.getList().getLines(), idList);
	}

	private Long saveList(Document doc, String fileName) {

		//@formatter:off
		SettingsInsert settingsInsert = new SettingsInsert()
		.table(tbLista).primaryKey(tbLista.coIdLista)
		.value(tbLista.dsTitulo, doc.getList().getTitle())
		.value(tbLista.dsParagrafo, doc.getText().getParagraph())
		.value(tbLista.dsTituloWebpage, doc.getTitlePage());
		//formatter:on

		return settingsInsert.insert();
	}

	private void saveLines(List<Line> lines, Long idLista) {

		for (Line line : lines) {
			SettingsInsert settingsInsert = new SettingsInsert()
					.table(tbLinha).primaryKey(tbLinha.coIdLinha)
					.value(tbLinha.coLista, idLista)
					.value(tbLinha.dsLinha, line.getAllElemets());

			Long idLine = settingsInsert.insert();
			
			settingsInsert = new SettingsInsert()
					.table(tbToken).primaryKey(tbToken.coIdToken)
					.value(tbToken.coLinha, idLine)
					.value(tbToken.coLista, idLista)
					.value(tbToken.coTokenPai, null)
					.value(tbToken.dsToken, line.getAllElemets());
			
			Long idTokenPai = settingsInsert.insert();
			
			for (String element : line.getElement()) {
				saveTokens(element, idLine, idLista, idTokenPai);
			}
			
		}
	}

	private void saveTokens(String element, Long idLine, Long idLista, Long idTokenPai) {
		
		for (String token : element.split(" ")) {
			SettingsInsert settingsInsert = new SettingsInsert()
					.table(tbToken).primaryKey(tbToken.coIdToken)
					.value(tbToken.coLinha , idLine)
					.value(tbToken.coLista, idLista)
					.value(tbToken.coTokenPai, idTokenPai)
					.value(tbToken.dsToken, token);
			
			settingsInsert.insert();
		}
		
	}

}
