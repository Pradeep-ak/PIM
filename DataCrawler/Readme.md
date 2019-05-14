# Datacrawler
It's pull the data for demo from site and publish's the data to the PIM system.

## Infromation 
We have used python script to crawler the data. We use the API from the site to pull the data for different Products. Please add the product ID into the csv at location `/app/products_list.csv` where the `/app/` is mounted directoy. 

The python script is part of the `datacrawler-server` docker image with requried modules installed. 
Docker-Composer will start the container but will not run the script as user has to log into the container are execute.
This depandace is created as running the scrpit to load the data is not required every time, it's one time activity.

Below are the commands login into the container and run the script.
```
docker exec -it --user root <container id> /bin/bash
cd /app/
pyhton test.py
```

It will crawler the data and created the json from the response. It will also publish the data to required Kafka topics.
