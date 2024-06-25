# Firewall Log Data SIEM 
SIEM (Security Information and Event Management) Development Project Using Firewall Log Data </br>
- Implement SIEM with Event-Driven Architecture
- Analyzing firewall log data to perform abnormal source IP detection, port scanning detection, and DDos detection
</br></br>

**[Skills]**</br>
- Kafka Cluster : Confluent Kafka Community (7.x.x)</br>
- Kafka Client : Apache kafka (3.7.0)</br>
- Debezium</br>
- SpringBoot</br>
- Swagger</br>
- Redis</br>
- Mysql</br>
- MongoDB</br>
- ChatGPT API</br>

</br>

**[Architecture]** 
</br>
![image](https://github.com/LimHyunJune/firewall-log-data-siem/assets/48524793/590a9fff-07bd-40fe-9e03-8579f8a56459)
<br>
- Data Collection </br>
  1. Collecting logs and storing MysqlDB</br>
  2. Send from Mysql DB to Kafka using CDC</br>
- Data Analysis </br>
  1. Subscribe to kafka and analyze abnormal logs through ChatGPT </br>
  2. Caching analysis results to Redis </br>
  3. Save analysis results to MongoDB after caching </br>
  4. Send abnormal data to Kafka </br>
- Data Delivery </br>
  1. Query and provide analysis data from cache and DB
  2. Alarm to user when abnormal data is detected through Kafka
  3. Link Kafka with Elastic Search to create a real-time dashboard
