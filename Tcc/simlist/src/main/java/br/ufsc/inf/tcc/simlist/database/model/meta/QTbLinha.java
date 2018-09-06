package br.ufsc.inf.tcc.simlist.database.model.meta;

import static com.mysema.query.types.PathMetadataFactory.*;
import br.ufsc.inf.tcc.simlist.database.model.TbLinha;


import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;

import com.mysema.query.sql.ColumnMetadata;
import java.sql.Types;




/**
 * QTbLinha is a Querydsl query type for TbLinha
 */
@Generated("com.mysema.query.sql.codegen.MetaDataSerializer")
public class QTbLinha extends com.mysema.query.sql.RelationalPathBase<TbLinha> {

    private static final long serialVersionUID = 940302419;

    public static final QTbLinha tbLinha = new QTbLinha("TB_LINHA");

    public final NumberPath<Long> coIdLinha = createNumber("coIdLinha", Long.class);

    public final NumberPath<Long> coLista = createNumber("coLista", Long.class);

    public final StringPath dsLinha = createString("dsLinha");

    public final com.mysema.query.sql.PrimaryKey<TbLinha> constraint5f = createPrimaryKey(coIdLinha);

    public QTbLinha(String variable) {
        super(TbLinha.class, forVariable(variable), "PUBLIC", "TB_LINHA");
        addMetadata();
    }

    public QTbLinha(String variable, String schema, String table) {
        super(TbLinha.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QTbLinha(Path<? extends TbLinha> path) {
        super(path.getType(), path.getMetadata(), "PUBLIC", "TB_LINHA");
        addMetadata();
    }

    public QTbLinha(PathMetadata<?> metadata) {
        super(TbLinha.class, metadata, "PUBLIC", "TB_LINHA");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(coIdLinha, ColumnMetadata.named("CO_ID_LINHA").withIndex(1).ofType(Types.BIGINT).withSize(19).notNull());
        addMetadata(coLista, ColumnMetadata.named("CO_LISTA").withIndex(2).ofType(Types.BIGINT).withSize(19));
        addMetadata(dsLinha, ColumnMetadata.named("DS_LINHA").withIndex(3).ofType(Types.VARCHAR).withSize(4000));
    }

}

