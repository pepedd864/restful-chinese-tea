package restful.database;

import org.hibernate.boot.model.naming.PhysicalNamingStrategy;
import org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl;

public class SQLServer extends PhysicalNamingStrategyStandardImpl {
    public static final PhysicalNamingStrategy INSTANCE = new SQLServer();
}
