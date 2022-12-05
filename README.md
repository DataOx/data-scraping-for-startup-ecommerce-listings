# datascraper

## description
The project aims to extract public content from trusted web sources and then analyze it. 
There are 3 web sources for scraping with 3 categories in each.
### implemented web sources
-   [B&H](https://www.bhphotovideo.com/c/product/1505274-REG/red_digital_cinema_710_0329_red_ranger_with_helium.html)
-   [AbelCine](https://www.abelcine.com/rent/lenses-accessories/cine-lenses/kowa-50mm-cine-prominar-anamorphic-t23-prime-pl-mount)
-   [Adorama](https://www.adorama.com/coeasf40.html)
---
## running
1. build and up docker-compose.yml from docker/dev
``` bash
$ docker-compose build
$ docker-compose up -d
```
2. [task_file_example.xlsx](example/task_file_example.xlsx)
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
6. Roport csv will be saved at: **/downloads/datascraper/reports**
