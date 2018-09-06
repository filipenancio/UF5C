package br.ufsc.inf.tcc.simlist.database.model.meta;

import static com.mysema.query.types.PathMetadataFactory.*;
import br.ufsc.inf.tcc.simlist.database.model.TbMigracao;


import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;

import com.mysema.query.sql.ColumnMetadata;
import java.sql.Types;




/**
 * QTbMigracao is a Querydsl query type for TbMigracao
 */
@Generated("com.mysema.query.sql.codegen.MetaDataSerializer")
public class QTbMigracao extends com.mysema.query.sql.RelationalPathBase<TbMigracao> {

    private static final long serialVersionUID = -1970567634;

    public static final QTbMigracao tbMigracao = new QTbMigracao("TB_MIGRACAO");

    public final StringPath author = createString("author");

    public final StringPath comments = createString("comments");

    public final StringPath contexts = createString("contexts");

    public final DateTimePath<java.sql.Timestamp> dateexecuted = createDateTime("dateexecuted", java.sql.Timestamp.class);

    public final StringPath description = createString("description");

    public final StringPath exectype = createString("exectype");

    public final StringPath filename = createString("filename");

    public final StringPath id = createString("id");

    public final StringPath labels = createString("labels");

    public final StringPath liquibase = createString("liquibase");

    public final StringPath md5sum = createString("md5sum");

    public final NumberPath<Integer> orderexecuted = createNumber("orderexecuted", Integer.class);

    public final StringPath tag = createString("tag");

    public QTbMigracao(String variable) {
        super(TbMigracao.class, forVariable(variable), "PUBLIC", "TB_MIGRACAO");
        addMetadata();
    }

    public QTbMigracao(String variable, String schema, String table) {
        super(TbMigracao.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QTbMigracao(Path<? extends TbMigracao> path) {
        super(path.getType(), path.getMetadata(), "PUBLIC", "TB_MIGRACAO");
        addMetadata();
    }

    public QTbMigracao(PathMetadata<?> metadata) {
        super(TbMigracao.class, metadata, "PUBLIC", "TB_MIGRACAO");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(author, ColumnMetadata.named("AUTHOR").withIndex(2).ofType(Types.VARCHAR).withSize(255).notNull());
        addMetadata(comments, ColumnMetadata.named("COMMENTS").withIndex(9).ofType(Types.VARCHAR).withSize(255));
        addMetadata(contexts, ColumnMetadata.named("CONTEXTS").withIndex(12).ofType(Types.VARCHAR).withSize(255));
        addMetadata(dateexecuted, ColumnMetadata.named("DATEEXECUTED").withIndex(4).ofType(Types.TIMESTAMP).withSize(23).withDigits(10).notNull());
        addMetadata(description, ColumnMetadata.named("DESCRIPTION").withIndex(8).ofType(Types.VARCHAR).withSize(255));
        addMetadata(exectype, ColumnMetadata.named("EXECTYPE").withIndex(6).ofType(Types.VARCHAR).withSize(10).notNull());
        addMetadata(filename, ColumnMetadata.named("FILENAME").withIndex(3).ofType(Types.VARCHAR).withSize(255).notNull());
        addMetadata(id, ColumnMetadata.named("ID").withIndex(1).ofType(Types.VARCHAR).withSize(255).notNull());
        addMetadata(labels, ColumnMetadata.named("LABELS").withIndex(13).ofType(Types.VARCHAR).withSize(255));
        addMetadata(liquibase, ColumnMetadata.named("LIQUIBASE").withIndex(11).ofType(Types.VARCHAR).withSize(20));
        addMetadata(md5sum, ColumnMetadata.named("MD5SUM").withIndex(7).ofType(Types.VARCHAR).withSize(35));
        addMetadata(orderexecuted, ColumnMetadata.named("ORDEREXECUTED").withIndex(5).ofType(Types.INTEGER).withSize(10).notNull());
        addMetadata(tag, ColumnMetadata.named("TAG").withIndex(10).ofType(Types.VARCHAR).withSize(255));
    }

}

