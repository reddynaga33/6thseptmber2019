package com.openmatics.testinstrumentation.platform.utils;

import org.json.JSONObject;
import java.util.*;

public class ServiceVersionResolver {

    /**
     * Method find latest (the most up-to-date) service version of service
     * @param artefactId obsolete deprecated, now not in using
     * @param service object service which has service version list
     * @return latest version name
     */
    @Deprecated()
    public static String getLastVersion(String artefactId, JSONObject service){
        List<String[][]> versions = new ArrayList<String[][]>();
        for (Object item: service.getJSONArray("serviceVersionSet")){
            JSONObject version = (JSONObject) item;
            String versionName = version.getString("versionName");
            if (versionName.equalsIgnoreCase("latest"))
            {
                return versionName;
            }
            String sortName = null;
            if (versionName.contains("SNAPSHOT")) {
                sortName = versionPadding(versionName.split("-")[0]) + "_A";
            } else if (versionName.contains("RC")){
                String unifiedName = versionName.replace("\\.RC","-RC");
                String rcSuffix = "0";
                if (unifiedName.split("RC").length > 1) rcSuffix = unifiedName.split("RC")[1];
                sortName = versionPadding(unifiedName.split("-")[0]) + "_B" + rcSuffix;
            } else {
                sortName = versionPadding(versionName) + "_C";
            }
            String[][] v = new String[][]{{versionName, sortName}};
            versions.add(v);
        }

        Collections.sort(versions, new Comparator<String[][]>() {
            public int compare(String[][] o1, String[][] o2) {
                return o2[0][1].compareTo(o1[0][1]);
            }
        });

        // TODO when will be in version RC and also RC0 how resovle it ?
        if (versions.size() == 0) return null;
        return versions.get(0)[0][0];
    }

    private static String versionPadding(String version){
        StringBuilder builder = new StringBuilder();
        for(String part : version.split("\\.")){
            builder.append(String.format("%5s", part).replace(" ", "0"));
        }
        return builder.toString();
    }


}
