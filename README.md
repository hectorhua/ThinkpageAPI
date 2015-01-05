This is a java project for ThinkpageAPI to fetch weather forecast data.
Thinkpage is a forecasting weather web which provides observe, forecast, life index, alarm, air qualities and other multi-language types data.
The forecasting area covers 2568 Chinese cities and 900 international cities.
The forecast data covers weather data in 7 days and update at least 3 times one day.
The project uses free registration which is available in 15 days and has upper limit 10000 visits.
The data responsed from Thinkpage is JSON format, so the project parses JSON data into single fields which can be writed to Redis or data files.
The users should adjust config file wfintegrator.cfg with paramater like proxyHost, proxyPort, redisHost, redisPort, key, city_num and so on.
The package name should be adjusted in different environment.
