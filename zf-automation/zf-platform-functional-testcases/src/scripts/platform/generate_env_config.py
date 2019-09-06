import argparse
import json
import os

#from collections import OrderedDict
#git hub from service source: https://git.openmatics.com/ops/cloud-platform-scripts/aad-applications-registrator/tree/master/config
#python3 ./generate_env_config.py --dir ~/wspace/om-git/ops/cloud-platform-scripts/aad-applications-registrator/config --env tauri

values = {
    "daedalus" : {
        "url": "https://backend-daedalus.daedalus01d.openmatics.com/",
        "tenant": "d630d990-458e-402d-bbe5-56b10bbb2cb6",
        "openshift": {
            "hostName":"https://daedalus.openmatics.com",
            "saToken": "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJrdWJlcm5ldGVzL3NlcnZpY2VhY2NvdW50Iiwia3ViZXJuZXRlcy5pby9zZXJ2aWNlYWNjb3VudC9uYW1lc3BhY2UiOiJkYWVkYWx1cyIsImt1YmVybmV0ZXMuaW8vc2VydmljZWFjY291bnQvc2VjcmV0Lm5hbWUiOiJzZXJ2aWNlLW1hbmFnZW1lbnQtc2VydmljZS10b2tlbi1qZDVrNSIsImt1YmVybmV0ZXMuaW8vc2VydmljZWFjY291bnQvc2VydmljZS1hY2NvdW50Lm5hbWUiOiJzZXJ2aWNlLW1hbmFnZW1lbnQtc2VydmljZSIsImt1YmVybmV0ZXMuaW8vc2VydmljZWFjY291bnQvc2VydmljZS1hY2NvdW50LnVpZCI6IjViM2JjMzljLThlNzEtMTFlOC05YzdkLTAwMGQzYTI4OGUzYiIsInN1YiI6InN5c3RlbTpzZXJ2aWNlYWNjb3VudDpkYWVkYWx1czpzZXJ2aWNlLW1hbmFnZW1lbnQtc2VydmljZSJ9.rxv01qgT_8KHAk5laA8HaMIqsHJIHhsRYLyFMShUimWDjOvbS2-BnOBNlC_0g74tuo10z9D3OdvGzasPbNovcuvELElbjXSl7A4k85jOQIJkNf45o6Ig5AClK58WGEwXnP9F04cBdyYbnH5pYqKLwJXB8c-KsrVbhldR93CoeMTITG9BJi5iuK37eWspI8XryYcpAykmeaXgf0emoaudhT6s0qPPOrpgXB740gWN-Rd-b0Q8hCafWywHGkOpMa8HOCB84-iqEIBLKmlEWB_VcNbmwsz3dnWu2SmFvikxOlp7BVKaQsCwryDHBp01z1rfKv8wRb7AukPbihq9IA9ITw"
        },
        "git": {
            "repoUrl": "",
            "repoBranch": ""
        },
        "db": {
            "host": "daedalus-dbserver-cdo-1",
            "name": "daedalus-globalService-CDO",
            "user": "daedalus",
            "password": "Operations01"
        },
        "iotHub" : {
            "hostName":"daedalus-iot-CDO-1.azure-devices.net"
        }
    },
    "tauri" : {
        "url": "https://backend-tauri.daedalus01d.openmatics.com/",
        "tenant": "968ff878-be61-4cc1-8139-d08e4a9f8ec8",
        "openshift": {
            "hostName":"https://daedalus.openmatics.com",
            "saToken": "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJrdWJlcm5ldGVzL3NlcnZpY2VhY2NvdW50Iiwia3ViZXJuZXRlcy5pby9zZXJ2aWNlYWNjb3VudC9uYW1lc3BhY2UiOiJ0YXVyaSIsImt1YmVybmV0ZXMuaW8vc2VydmljZWFjY291bnQvc2VjcmV0Lm5hbWUiOiJzZXJ2aWNlLW1hbmFnZW1lbnQtc2VydmljZS10b2tlbi03NjIyaiIsImt1YmVybmV0ZXMuaW8vc2VydmljZWFjY291bnQvc2VydmljZS1hY2NvdW50Lm5hbWUiOiJzZXJ2aWNlLW1hbmFnZW1lbnQtc2VydmljZSIsImt1YmVybmV0ZXMuaW8vc2VydmljZWFjY291bnQvc2VydmljZS1hY2NvdW50LnVpZCI6ImUxZTMwNjNmLThlNzctMTFlOC1iMjE0LTAwMGQzYTM4N2UyNyIsInN1YiI6InN5c3RlbTpzZXJ2aWNlYWNjb3VudDp0YXVyaTpzZXJ2aWNlLW1hbmFnZW1lbnQtc2VydmljZSJ9.LgGbCtTC83AJS_2SQuWEAhzLhGWjFrwp2PZr7RmRTZtRvFwElhHS3N8i7XnQmgbHHpHkLLaX5yN6Uv8ZKnVtm_EGreJ-dgh22Ii9Te8mFeOd2rOA6zIzZq798trknQKBDuXfCIanuwYj3oKAqahCXT5sWHtkkDce5EblQjXprFMulzJKJcUBbW6bemBk6IfIQZ91cNNOSDIf8Eo714i7jmMheFBhSK6DKWWYkzqlj18MY-lf86tRl8_tiHQnUiqMr5GX_GMhm5KuljR5bK4n9VefHix1SKLfHGCEs28BddTU4oZC_9hQPCx9-LnFZkzGhqRcW_d4tn7meV0FcY8jaA"
        },
        "git": {
            "repoUrl": "",
            "repoBranch": ""
        },
        "db": {
            "host": "tauri-dbserver-cdo-1",
            "name": "tauri-globalService-CDO",
            "user": "tauri",
            "password": "Operations01"
        },
        "iotHub" : {
            "hostName":"tauri-iot-CDO-1.azure-devices.net"
        }
    },
    "iris" : {
        "url": "https://backend-iris.ocpp-azwe-apps.zf.com/",
        "tenant": "133f5ed3-93f7-4213-befe-d7b6c80707f9",
        "git": {
            "repoUrl": "",
            "repoBranch": ""
        },
        "db": {
            "host": "iris-dbserver-cdo-1",
            "name": "iris-globalService-CDO",
            "user": "iris",
            "password": "Operations01"
        },
        "iotHub" : {
            "hostName":"tauri-iot-CDO-1.azure-devices.net"
        }
    }
}


def main():
    parser = argparse.ArgumentParser()
    parser.add_argument('--env', required=True, help='name of environment (daed,...)')
    parser.add_argument('--dir', required=True, help='path to dir with files with services (daed,...)')
    args = parser.parse_args()
    env_name = args.env
    file_service = os.path.join(args.dir, env_name + '-applications.json')
    env_values = values[env_name]

    print('Services configuration will be read from file:')
    print(file_service)
    with open(file_service) as json_file:
        service_source = json.load(json_file)

    config = {}
    services = {}
    for service in service_source:
        #TODO via urllib.parse
        name = (service['uriId']).replace('net://','')
        item = {}
        item['name'] = service['name']
        item['servicePath'] = service['uriId']
        item['aadId'] =  service['appId']
        item['aadSecret'] =  service['secret']
        services[name] = item


    config['services'] = services
    config['db'] = env_values['db']
    config['iotHub'] = env_values['iotHub']
    config['tenant'] = env_values['tenant']
    config['url'] = env_values['url']
    config['openshift'] = env_values['openshift']


    with open(env_name + "_config.json", 'w') as outfile:
        json.dump(config, outfile,  sort_keys = False, indent = 4, ensure_ascii=False )
    print("Output was will write to the file:")
    print(env_name + "_config.json")


if __name__ == '__main__':
        main()



