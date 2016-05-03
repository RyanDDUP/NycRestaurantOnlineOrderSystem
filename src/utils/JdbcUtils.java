package utils;


import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.dbutils.QueryRunner;

/**
 * Created by RZ on 4/19/16.
 */
public class JdbcUtils {
    private static ComboPooledDataSource cpds;
    static {
        cpds = new ComboPooledDataSource();
    }

    public static  ComboPooledDataSource getDataSource() {
        return cpds;
    }

    public static QueryRunner getQueryRunner() {
        return new QueryRunner(cpds);
    }
}
