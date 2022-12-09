## running

1. build and up docker-compose.yml from docker/dev
``` bash
$ docker-compose build
$ docker-compose up -d
```
2. [task_file_example.xlsx](assets/task_file_example.xlsx)
    - required cells: TYPE and WEBSITE
        - for TYPE use type of product: CAMERA,LENSES,ACCESSORIES or LIGHTING
        - WEBSITE will automatically parse for each resource to scrape (see [implemented web sources](README.md#implemented-web-sources))
3. use endpoint for loading task urls to db from xlsx file. Example:
``` bash
$ curl --location --request POST 'localhost:8080/task/upsert_from_xlsx' \
--form 'file=@"~/Documents/tasks.xlsx"'
```
4. scraping will start automaticly every 15 minutes of hour
5. for init creating report use [endpoint](http://localhost:8080/report)
``` bash
$ curl --location --request GET 'localhost:8080/report'
```
6. Report csv will be saved at: **/downloads/datascraper/reports**
