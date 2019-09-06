package com.openmatics.test.functional;

import com.openmatics.testinstrumentation.utils.sql.DbConnectionFactory;

/**
 * @author marek.rasocha@inventi.cz
 *         date: 07.03.2019.
 */
public abstract class DbBaseTest extends ApiTestBase {
    protected DbConnectionFactory connectionFactory;

    public DbBaseTest(){
    }

    public DbBaseTest(String envKey, String clientId, String clientSuffix) throws Exception {
        super(envKey, clientId, clientSuffix);
    }

    @Override
    protected void executeBeforeClass() throws Exception {
        super.executeBeforeClass();
        connectionFactory = initiateDbConnection();
    }

    @Override
    protected void executeAfterClass() throws Exception {
        super.executeAfterClass();
        if (connectionFactory != null) {
            connectionFactory.closeConnection();
        }
    }

    /**
     * The method should initiatize connection factory.
     *
     * @throws Exception The method should get
     */
    protected abstract DbConnectionFactory initiateDbConnection() throws Exception;
}
