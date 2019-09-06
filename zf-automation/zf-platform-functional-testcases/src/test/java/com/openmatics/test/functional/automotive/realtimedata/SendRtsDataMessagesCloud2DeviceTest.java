package com.openmatics.test.functional.automotive.realtimedata;

import com.openmatics.test.functional.DbBaseTest;
import com.openmatics.testinstrumentation.automotive.cases.realtimedata.SendRtsDataMessagesCloud2Device;
import com.openmatics.testinstrumentation.utils.sql.DbConnectionFactory;
import com.openmatics.testinstrumentation.utils.testng.Action;
import com.openmatics.testinstrumentation.utils.testng.Arrange;
import com.openmatics.testinstrumentation.utils.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


@Test(groups = "SendRtsDataMessagesCloud2Device")
public class SendRtsDataMessagesCloud2DeviceTest extends DbBaseTest {

    private SendRtsDataMessagesCloud2Device testcase;

    @BeforeClass
    public void beforeSendRtsDataMessagesCloud2DeviceTest() throws Exception {
        testcase = new SendRtsDataMessagesCloud2Device(connectionFactory, platformInstrumentation, getAutomotiveInstr());
    }

    @Arrange("Load device for message sending")
    @Test
    public void givenDevice4Sending() throws Exception
    {
        testcase.givenDevice4Sending();
    }

    @Action("Send message to cloud from device")
    @Assert("")
    @Test(dependsOnMethods = "givenDevice4Sending")
    public void SendMessagesCloud2Device() throws Exception
    {
        testcase.SendMessagesCloud2Device();
    }

    @Override
    protected DbConnectionFactory initiateDbConnection() throws Exception {
        String clientId = this.platformInstrumentation.getClientConf().getClient().getId();
        return new DbConnectionFactory(platformInstrumentation.getEnvConf().getDbConnectionString(clientId));
    }
}
