package com.openmatics.testinstrumentation.platform.service.global.clientmanagement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PermanentClientIdList {

    private Map<String, List<String>> permanentClients;

    public PermanentClientIdList() {
        permanentClients = new HashMap<String, List<String>>();
        // tauri
        List<String> listTauri = new ArrayList<String>();
        listTauri.add("19309c32-1769-42ba-9829-d18d5e9e072d");
        listTauri.add("49a3bc07-2309-48df-8959-732297707e65");
        listTauri.add("8870965f-d5cd-4d2e-a2f9-71eeb65ba2fd");
        permanentClients.put("tauri", listTauri);
        // daedalus
        List<String> listDaedalus = new ArrayList<String>();
        listDaedalus.add("df8545d8-7179-44eb-94f7-51e6561e963b");
        listDaedalus.add("f2e129d8-8f15-4df3-af46-c7fc5513d0aa");
        // some older of daedalus
        listDaedalus.add("ee986775-b80c-4cd5-9858-359d61a5cf12");
        listDaedalus.add("fd539114-e6a3-4f90-8367-b368fbc003b2");
        listDaedalus.add("a39850ed-2714-4ddc-85b5-442561435e18");
        listDaedalus.add("5d5b4d01-75fc-43c3-83c7-bd18cd5f0ddf");
        listDaedalus.add("3efda49f-784c-4c77-9119-21569cc08a1d");
        permanentClients.put("daedalus", listDaedalus);
    }

    public List<String> get(String envKey) {
        if (!permanentClients.containsKey(envKey)) {
            throw new RuntimeException(String.format("Environment %s has not define permanent client list.", envKey));
        }
        return permanentClients.get(envKey);
    }

}
