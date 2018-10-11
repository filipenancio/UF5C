package br.ufsc.inf.tcc.simlist.database.model.meta;

import static com.mysema.query.types.PathMetadataFactory.*;
import br.ufsc.inf.tcc.simlist.database.model.TbConfigSistema;


import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;

import com.mysema.query.sql.ColumnMetadata;
import java.sql.Types;




/**
 * QTbConfigSistema is a Querydsl query type for TbConfigSistema
 */
@Generated("com.mysema.query.sql.codegen.MetaDataSerializer")
public class QTbConfigSistema extends com.mysema.query.sql.RelationalPathBase<TbConfigSistema> {

    private static final long serialVersionUID = 561055881;

    public static final QTbConfigSistema tbConfigSistema = new QTbConfigSistema("TB_CONFIG_SISTEMA");

    public final StringPath dsConfig = createString("dsConfig");

    public final StringPath vlConfig = createString("vlConfig");

    public QTbConfigSistema(String variable) {
        super(TbConfigSistema.class, forVariable(variable), "PUBLIC", "TB_CONFIG_SISTEMA");
        addMetadata();
    }

    public QTbConfigSistema(String variable, String schema, String table) {
        super(TbConfigSistema.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QTbConfigSistema(Path<? extends TbConfigSistema> path) {
        super(path.getType(), path.getMetadata(), "PUBLIC", "TB_CONFIG_SISTEMA");
        addMetadata();
    }

    public QTbConfigSistema(PathMetadata<?> metadata) {
        super(TbConfigSistema.class, metadata, "PUBLIC", "TB_CONFIG_SISTEMA");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(dsConfig, ColumnMetadata.named("DS_CONFIG").withIndex(1).ofType(Types.VARCHAR).withSize(255));
        addMetadata(vlConfig, ColumnMetadata.named("VL_CONFIG").withIndex(2).ofType(Types.VARCHAR).withSize(255));
    }

}

