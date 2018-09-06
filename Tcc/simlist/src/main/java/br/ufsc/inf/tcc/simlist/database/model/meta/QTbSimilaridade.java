package br.ufsc.inf.tcc.simlist.database.model.meta;

import static com.mysema.query.types.PathMetadataFactory.*;
import br.ufsc.inf.tcc.simlist.database.model.TbSimilaridade;


import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;

import com.mysema.query.sql.ColumnMetadata;
import java.sql.Types;




/**
 * QTbSimilaridade is a Querydsl query type for TbSimilaridade
 */
@Generated("com.mysema.query.sql.codegen.MetaDataSerializer")
public class QTbSimilaridade extends com.mysema.query.sql.RelationalPathBase<TbSimilaridade> {

    private static final long serialVersionUID = -1961322125;

    public static final QTbSimilaridade tbSimilaridade = new QTbSimilaridade("TB_SIMILARIDADE");

    public final NumberPath<Long> coIdSimilaridade = createNumber("coIdSimilaridade", Long.class);

    public final NumberPath<Long> coTecnicaSimilaridade = createNumber("coTecnicaSimilaridade", Long.class);

    public final NumberPath<Long> coToken = createNumber("coToken", Long.class);

    public final NumberPath<Long> coTokenRef = createNumber("coTokenRef", Long.class);

    public final NumberPath<Integer> vlSimilaridade = createNumber("vlSimilaridade", Integer.class);

    public final com.mysema.query.sql.PrimaryKey<TbSimilaridade> constraintC = createPrimaryKey(coIdSimilaridade);

    public QTbSimilaridade(String variable) {
        super(TbSimilaridade.class, forVariable(variable), "PUBLIC", "TB_SIMILARIDADE");
        addMetadata();
    }

    public QTbSimilaridade(String variable, String schema, String table) {
        super(TbSimilaridade.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QTbSimilaridade(Path<? extends TbSimilaridade> path) {
        super(path.getType(), path.getMetadata(), "PUBLIC", "TB_SIMILARIDADE");
        addMetadata();
    }

    public QTbSimilaridade(PathMetadata<?> metadata) {
        super(TbSimilaridade.class, metadata, "PUBLIC", "TB_SIMILARIDADE");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(coIdSimilaridade, ColumnMetadata.named("CO_ID_SIMILARIDADE").withIndex(1).ofType(Types.BIGINT).withSize(19).notNull());
        addMetadata(coTecnicaSimilaridade, ColumnMetadata.named("CO_TECNICA_SIMILARIDADE").withIndex(2).ofType(Types.BIGINT).withSize(19));
        addMetadata(coToken, ColumnMetadata.named("CO_TOKEN").withIndex(4).ofType(Types.BIGINT).withSize(19));
        addMetadata(coTokenRef, ColumnMetadata.named("CO_TOKEN_REF").withIndex(3).ofType(Types.BIGINT).withSize(19));
        addMetadata(vlSimilaridade, ColumnMetadata.named("VL_SIMILARIDADE").withIndex(5).ofType(Types.INTEGER).withSize(10));
    }

}

