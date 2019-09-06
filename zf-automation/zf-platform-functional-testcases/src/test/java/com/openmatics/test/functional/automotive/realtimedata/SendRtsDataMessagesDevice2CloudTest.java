package com.openmatics.test.functional.automotive.realtimedata;

import com.openmatics.test.functional.DbBaseTest;
import com.openmatics.testinstrumentation.automotive.cases.realtimedata.SendRtsDataMessagesDevice2Cloud;
import com.openmatics.testinstrumentation.utils.sql.DbConnectionFactory;
import com.openmatics.testinstrumentation.utils.testng.Action;
import com.openmatics.testinstrumentation.utils.testng.Arrange;
import com.openmatics.testinstrumentation.utils.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


@Test(groups = "SendRtsDataMessagesDevice2Cloud")
public class SendRtsDataMessagesDevice2CloudTest extends DbBaseTest {

    private SendRtsDataMessagesDevice2Cloud testcase;

    @BeforeClass
    public void beforeSendRtsDataMessages2DeviceTest() throws Exception {
        testcase = new SendRtsDataMessagesDevice2Cloud(platformInstrumentation, getAutomotiveInstr(), connectionFactory);
    }

    @Arrange("Load device for message sending")
    @Test
    public void givenDevice4Sending() throws Exception
    {
        testcase.givenDevice4Sending();
    }

    @Action("Send message to cloud from device")
    @Test(dependsOnMethods = "givenDevice4Sending")
    public void whenISendMessagesDevice2Cloud() throws Exception
    {
        testcase.whenISendMessagesDevice2Cloud();
    }

    @Assert("Message was processed and persist stored in db in rts schema.")
    @Test(dependsOnMethods = "whenISendMessagesDevice2Cloud")
    public void thenMessegesAreStoredInDb() throws Exception
    {
        //TODO
        testcase.thenMessagesAreStoredInDb();
    }

    @Assert("Message was processed and persist stored in db in rts schema.")
    @Test(dependsOnMethods = "whenISendMessagesDevice2Cloud")
    public void thenICanGetMessageValuesByApi () throws Exception
    {
        //TODO
    }

    @Override
    protected DbConnectionFactory initiateDbConnection() throws Exception {
        return new DbConnectionFactory(platformInstrumentation.getEnvConf().getDbConnectionString(this.platformInstrumentation.getClientConf().getClient().getId()));
    }

}
