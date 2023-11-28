# 消息佇列(Message Queue)

## JMS(Java Message Service)
* JMS（Java Message Service）：一个规范，等同于JDBC规范，提供了与消息服务相关的API接口
* JMS消息模型
  1. peer-2-peer：點對點模型，消息发送到一个队列中，队列保存消息。队列的消息只能被一个消费者消费，或超时
  2. publish-subscribe：发布订阅模型，消息可以被多个消费者消费，生产者和消费者完全独立，不需要感知对方的存在
* JMS消息种类
  * TextMessage
  * MapMessage
  * BytesMessage
  * StreamMessage
  * ObjectMessage
  * Message（只有消息頭和属性）
* JMS实现：ActiveMQ、Redis、HornetMQ、RabbitMQ、RocketMQ（没有完全遵守JMS规范）

## AMQP(advanced message queuing protocol)

* AMQP：一种协议（高级消息队列协议，也是消息代理规范），规范了网络交换的数据格式，兼容JMS
* 优点：具有跨平台性，服务器供应商，生产者，消费者可以使用不同的语言来实现
* AMQP消息模型
  * direct exchange
  * fanout exchange
  * topic exchange
  * headers exchange
  * system exchange
  * AMQP消息种类：byte[]
  * AMQP實現: RabbitMQ. StormMQ. Rocket™Q

## ActiveMQ

* ActiveMQ 是一個流行的開源消息中間件（Message Broker），它是用 Java 開發的，並且支援多種通訊協議，包括 MQTT、STOMP、AMQP 等，讓你能夠在分散式應用程式之間進行異步通信。
* **ActiveMQ 實現了 Java Message Service（JMS）規範**

啟動服務 `activemq.bat`
訪問服務 `http://127.0.0.1:8161` 
服務port: 61616,  管理後台port: 8161
username&password : admin

Queses : 一般對列
Topics : 訂閱模式

## RabbitMQ

* 基於 Erlang語言 編寫
* 基於 `AMQP`(Advanced Message Queuing Protocol)協議

> mac brew 安裝 https://www.rabbitmq.com/install-homebrew.html
> 會包含 Erlang語言 不用另外安裝(windows 需分別安裝)

* 起動 : `brew services start rabbitmq`
* 停止 : `brew services stop rabbitmq`

後台連 : 5672

`localhost:15672`：HTTP API 用戶端、管理 UI 和 rabbitmqadmin（僅當啟用管理外掛程式時）

username&password : guest

* 佇列(Queue)與交換機(exchange)做綁定
* 交換機 可以綁定 複數佇列(可相同,可不同) 


* Direct(直連交換)模式，多監聽器聽同一個佇列(Queue)，會輪詢處理 


* Topic(主題交換)模式，用routingKey綁定匹配，可以模糊比對(* or #)，匹配上就會被設定好的listener接收處理
  * 可以在多個listener處理同一個消息
* 绑定键匹配规则
  * *（星号）：用来表示一个单词，且该单词是必须出现的
  * ＃（井号）：用来表示任意数量

| 匹配鍵          | topic.*.*        | topic.#          |
| ---------------- | ---------------- | ---------------- |
| topic.order.id   | true             | true             |
| order.topic.id   | false            | false            |
| topic.sm.order.id| false            | true             |
| topic. sm.id     | false            | true             |
| topic.id.order   | true             | true             |
| topic.id         | false            | true             |
| topic.order      | false            | true             |

## RocketMQ

* 可異步...等等設定
* nameServe對多部broker 附載平衡
* spring boot 沒有維護版本，需另外加dependency