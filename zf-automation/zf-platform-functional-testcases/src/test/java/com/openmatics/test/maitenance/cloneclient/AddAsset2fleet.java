package com.openmatics.test.maitenance.cloneclient;

import com.openmatics.testinstrumentation.platform.EnvironmentConfiguration;
import com.openmatics.testinstrumentation.platform.IEnvironmentConfiguration;
import com.openmatics.testinstrumentation.platform.PlatformInstrumentation;
import com.openmatics.testinstrumentation.platform.service.client.fleet.FleetManagementApi;
import com.openmatics.testinstrumentation.utils.sql.*;
import org.json.JSONArray;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AddAsset2fleet {

    public static void main(String[] args) throws Exception {

        String envKey = "daedalus";
        String originClientId = "ee986775-b80c-4cd5-9858-359d61a5cf12";
        String clientId = "df8545d8-7179-44eb-94f7-51e6561e963b";
        String fleetName = "xxx";


        IEnvironmentConfiguration configuration = new EnvironmentConfiguration(envKey);
        PlatformInstrumentation inst = new PlatformInstrumentation(configuration);
        FleetManagementApi fmApi = new FleetManagementApi(envKey, clientId);


        String query = "SELECT * FROM [fleet_management_service_schema].[asset2fleet]\n" +
                "join [fleet_management_service_schema].fleet on\n" +
                "\tfleet.id=[asset2fleet].fleet_id\n" +
                "where fleet.name='%s'\n" +
                "and asset_id not in ('42067569-ac3e-4827-8d0a-214fda88ace4','e40ef5b5-0bad-4eba-b4b0-ec0da3e2ef7b')";
        query = String.format(query, fleetName);

        query = "select * from [fleet_management_service_schema].[asset]\n" +
                "where not exists(select * from [fleet_management_service_schema].[asset2fleet]\n" +
                "\t\t\twhere [asset].asset_id=[asset2fleet].asset_id)";


        List<String> assets = new ArrayList<>();
        int fleetId = 0;
        try(DbConnectionFactory connFactory = new DbConnectionFactory(inst.getEnvConf().getDbConnectionString(originClientId))){
            try(DbQueryService dbAccess = new DbQueryService(connFactory)) {
                ResultSet rs = dbAccess.executeQuery(query);
                 while (rs.next()) {
                    assets.add(rs.getString("asset_id"));
                }
            }

            query = String.format("SELECT * FROM [fleet_management_service_schema].[fleet] where name='%s'", fleetName);
            try(DbQueryService dbAccess = new DbQueryService(connFactory)) {
                ResultSet rs = dbAccess.executeQuery(query);
                while (rs.next()) {
                    if (rs.next()) fleetId = rs.getInt("id");
                }
            }
        }

        JSONArray assetArray = new JSONArray();
        for (String assetId : assets){
            assetArray.put(assetId);
        }

        System.out.println("New fleet_id: " + Integer.toString(fleetId));
        System.out.println(assetArray.toString());

        fmApi.getFleetApi().updateFleetAssets(Integer.toString(fleetId), assetArray.toString());
    }

}
