1. Start up MySQL
    a.WIN+R
    b.services.msc
    c.start MYSQL80 service
2. Start up Redis
    C:\Users\thinkpad>d:
    D:\>cd D:\Redis-x64-5.0.10
    D:\Redis-x64-5.0.10>redis-server.exe redis.windows.conf
3. Run mysql.txt in MYSQL Client, ex. SQLYOG
4. Start SprintBoot
    a. run SoftcitsSpringboot2020Application
5. Insert Cities from src/main/resources/citilist.json into MYSQL
    a. visit http://localhost:8080/admin/addcities
6. Run JUNIT function: org.softcits.cn.serivce.RemoteDataServiceTest.initBatchWeatherDataTest
7. Vist http://localhost:8080/report/main