package br.ufsc.inf.tcc.simlist.database.model.meta;

import static com.mysema.query.types.PathMetadataFactory.*;
import br.ufsc.inf.tcc.simlist.database.model.TbMigracaoTrava;


import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;

import com.mysema.query.sql.ColumnMetadata;
import java.sql.Types;




/**
 * QTbMigracaoTrava is a Querydsl query type for TbMigracaoTrava
 */
@Generated("com.mysema.query.sql.codegen.MetaDataSerializer")
public class QTbMigracaoTrava extends com.mysema.query.sql.RelationalPathBase<TbMigracaoTrava> {

    private static final long serialVersionUID = 1359751680;

    public static final QTbMigracaoTrava tbMigracaoTrava = new QTbMigracaoTrava("TB_MIGRACAO_TRAVA");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final BooleanPath locked = createBoolean("locked");

    public final StringPath lockedby = createString("lockedby");

    public final DateTimePath<java.sql.Timestamp> lockgranted = createDateTime("lockgranted", java.sql.Timestamp.class);

    public final com.mysema.query.sql.PrimaryKey<TbMigracaoTrava> tbMigracaoTravaPk = createPrimaryKey(id);

    public QTbMigracaoTrava(String variable) {
        super(TbMigracaoTrava.class, forVariable(variable), "PUBLIC", "TB_MIGRACAO_TRAVA");
        addMetadata();
    }

    public QTbMigracaoTrava(String variable, String schema, String table) {
        super(TbMigracaoTrava.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QTbMigracaoTrava(Path<? extends TbMigracaoTrava> path) {
        super(path.getType(), path.getMetadata(), "PUBLIC", "TB_MIGRACAO_TRAVA");
        addMetadata();
    }

    public QTbMigracaoTrava(PathMetadata<?> metadata) {
        super(TbMigracaoTrava.class, metadata, "PUBLIC", "TB_MIGRACAO_TRAVA");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(id, ColumnMetadata.named("ID").withIndex(1).ofType(Types.INTEGER).withSize(10).notNull());
        addMetadata(locked, ColumnMetadata.named("LOCKED").withIndex(2).ofType(Types.BOOLEAN).withSize(1).notNull());
        addMetadata(lockedby, ColumnMetadata.named("LOCKEDBY").withIndex(4).ofType(Types.VARCHAR).withSize(255));
        addMetadata(lockgranted, ColumnMetadata.named("LOCKGRANTED").withIndex(3).ofType(Types.TIMESTAMP).withSize(23).withDigits(10));
    }

}

