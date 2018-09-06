package br.ufsc.inf.tcc.simlist.database.model.meta;

import static com.mysema.query.types.PathMetadataFactory.*;
import br.ufsc.inf.tcc.simlist.database.model.TbTecnicaSimilaridade;


import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;

import com.mysema.query.sql.ColumnMetadata;
import java.sql.Types;




/**
 * QTbTecnicaSimilaridade is a Querydsl query type for TbTecnicaSimilaridade
 */
@Generated("com.mysema.query.sql.codegen.MetaDataSerializer")
public class QTbTecnicaSimilaridade extends com.mysema.query.sql.RelationalPathBase<TbTecnicaSimilaridade> {

    private static final long serialVersionUID = -1496429072;

    public static final QTbTecnicaSimilaridade tbTecnicaSimilaridade = new QTbTecnicaSimilaridade("TB_TECNICA_SIMILARIDADE");

    public final NumberPath<Long> coIdTecnicaSimilaridade = createNumber("coIdTecnicaSimilaridade", Long.class);

    public final StringPath dsTecnicaSimilaridade = createString("dsTecnicaSimilaridade");

    public final StringPath noIdentificador = createString("noIdentificador");

    public final com.mysema.query.sql.PrimaryKey<TbTecnicaSimilaridade> constraint67 = createPrimaryKey(coIdTecnicaSimilaridade);

    public QTbTecnicaSimilaridade(String variable) {
        super(TbTecnicaSimilaridade.class, forVariable(variable), "PUBLIC", "TB_TECNICA_SIMILARIDADE");
        addMetadata();
    }

    public QTbTecnicaSimilaridade(String variable, String schema, String table) {
        super(TbTecnicaSimilaridade.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QTbTecnicaSimilaridade(Path<? extends TbTecnicaSimilaridade> path) {
        super(path.getType(), path.getMetadata(), "PUBLIC", "TB_TECNICA_SIMILARIDADE");
        addMetadata();
    }

    public QTbTecnicaSimilaridade(PathMetadata<?> metadata) {
        super(TbTecnicaSimilaridade.class, metadata, "PUBLIC", "TB_TECNICA_SIMILARIDADE");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(coIdTecnicaSimilaridade, ColumnMetadata.named("CO_ID_TECNICA_SIMILARIDADE").withIndex(1).ofType(Types.BIGINT).withSize(19).notNull());
        addMetadata(dsTecnicaSimilaridade, ColumnMetadata.named("DS_TECNICA_SIMILARIDADE").withIndex(2).ofType(Types.VARCHAR).withSize(255));
        addMetadata(noIdentificador, ColumnMetadata.named("NO_IDENTIFICADOR").withIndex(3).ofType(Types.VARCHAR).withSize(255));
    }

}

