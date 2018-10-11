package br.ufsc.inf.tcc.simlist.database.model.meta;

import static com.mysema.query.types.PathMetadataFactory.*;
import br.ufsc.inf.tcc.simlist.database.model.TbLista;


import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;

import com.mysema.query.sql.ColumnMetadata;
import java.sql.Types;




/**
 * QTbLista is a Querydsl query type for TbLista
 */
@Generated("com.mysema.query.sql.codegen.MetaDataSerializer")
public class QTbLista extends com.mysema.query.sql.RelationalPathBase<TbLista> {

    private static final long serialVersionUID = 940307596;

    public static final QTbLista tbLista = new QTbLista("TB_LISTA");

    public final NumberPath<Long> coIdLista = createNumber("coIdLista", Long.class);

    public final StringPath dsParagrafo = createString("dsParagrafo");

    public final StringPath dsTitulo = createString("dsTitulo");

    public final StringPath dsTituloWebpage = createString("dsTituloWebpage");

    public final BooleanPath stAnalise = createBoolean("stAnalise");

    public final com.mysema.query.sql.PrimaryKey<TbLista> constraint5 = createPrimaryKey(coIdLista);

    public QTbLista(String variable) {
        super(TbLista.class, forVariable(variable), "PUBLIC", "TB_LISTA");
        addMetadata();
    }

    public QTbLista(String variable, String schema, String table) {
        super(TbLista.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QTbLista(Path<? extends TbLista> path) {
        super(path.getType(), path.getMetadata(), "PUBLIC", "TB_LISTA");
        addMetadata();
    }

    public QTbLista(PathMetadata<?> metadata) {
        super(TbLista.class, metadata, "PUBLIC", "TB_LISTA");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(coIdLista, ColumnMetadata.named("CO_ID_LISTA").withIndex(1).ofType(Types.BIGINT).withSize(19).notNull());
        addMetadata(dsParagrafo, ColumnMetadata.named("DS_PARAGRAFO").withIndex(3).ofType(Types.VARCHAR).withSize(4000));
        addMetadata(dsTitulo, ColumnMetadata.named("DS_TITULO").withIndex(2).ofType(Types.VARCHAR).withSize(1000));
        addMetadata(dsTituloWebpage, ColumnMetadata.named("DS_TITULO_WEBPAGE").withIndex(4).ofType(Types.VARCHAR).withSize(1000));
        addMetadata(stAnalise, ColumnMetadata.named("ST_ANALISE").withIndex(5).ofType(Types.BOOLEAN).withSize(1));
    }

}

