package br.ufsc.inf.tcc.simlist.database.model.meta;

import static com.mysema.query.types.PathMetadataFactory.*;
import br.ufsc.inf.tcc.simlist.database.model.TbToken;


import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;

import com.mysema.query.sql.ColumnMetadata;
import java.sql.Types;




/**
 * QTbToken is a Querydsl query type for TbToken
 */
@Generated("com.mysema.query.sql.codegen.MetaDataSerializer")
public class QTbToken extends com.mysema.query.sql.RelationalPathBase<TbToken> {

    private static final long serialVersionUID = 947866370;

    public static final QTbToken tbToken = new QTbToken("TB_TOKEN");

    public final NumberPath<Long> coIdToken = createNumber("coIdToken", Long.class);

    public final NumberPath<Long> coLinha = createNumber("coLinha", Long.class);

    public final NumberPath<Long> coLista = createNumber("coLista", Long.class);

    public final NumberPath<Long> coTokenPai = createNumber("coTokenPai", Long.class);

    public final StringPath dsToken = createString("dsToken");

    public final com.mysema.query.sql.PrimaryKey<TbToken> constraint6 = createPrimaryKey(coIdToken);

    public QTbToken(String variable) {
        super(TbToken.class, forVariable(variable), "PUBLIC", "TB_TOKEN");
        addMetadata();
    }

    public QTbToken(String variable, String schema, String table) {
        super(TbToken.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QTbToken(Path<? extends TbToken> path) {
        super(path.getType(), path.getMetadata(), "PUBLIC", "TB_TOKEN");
        addMetadata();
    }

    public QTbToken(PathMetadata<?> metadata) {
        super(TbToken.class, metadata, "PUBLIC", "TB_TOKEN");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(coIdToken, ColumnMetadata.named("CO_ID_TOKEN").withIndex(1).ofType(Types.BIGINT).withSize(19).notNull());
        addMetadata(coLinha, ColumnMetadata.named("CO_LINHA").withIndex(4).ofType(Types.BIGINT).withSize(19));
        addMetadata(coLista, ColumnMetadata.named("CO_LISTA").withIndex(3).ofType(Types.BIGINT).withSize(19));
        addMetadata(coTokenPai, ColumnMetadata.named("CO_TOKEN_PAI").withIndex(2).ofType(Types.BIGINT).withSize(19));
        addMetadata(dsToken, ColumnMetadata.named("DS_TOKEN").withIndex(5).ofType(Types.VARCHAR).withSize(1000));
    }

}

