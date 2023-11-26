# Spring JavaMailSender

## 純文字

### 依賴

```java
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-mail</artifactId>
</dependency>
```
### application設定

```yaml
spring:
    mail:
        host: smtp.gmail.com
        username: [from]@gmail.com
        password: [開啟mail smtp設定後取得]
        port: 587
        properties:
            mail:
                smtp:
                    auth: true
                    starttls:
                        enable: true
```
需確認
* `mail.smtp.auth: true`：啟用SMTP認證。
* `mail.smtp.starttls.enable: true`：啟用STARTTLS命令。
  * 需要先發送STARTTLS命令。這是一個安全要求，用於確保郵件通過加密連接發送。


### java code

```java
@Autowired
    private JavaMailSender javaMailSender;

String from = "[form]@gmail.com(顯示名稱)";
String to = "[to]@gmail.com";
String subject = "標題測試";
String text = "內容測試";

@Override
public void sendMail() {
    // SimpleMailMessage message = new SimpleMailMessage();
    // message.setFrom(from);
    // message.setTo(to);
    // message.setSubject(subject);
    // message.setText(text);
    // javaMailSender.send(message);
}
```

## 帶HTML

```java
String from = "[form]@gmail.com(顯示名稱)";
String to = "[to]@gmail.com";
String subject = "標題測試";
String text = "<img src='https://p2.bahamut.com.tw/B/2KU/62/c5f3dd027c2903f595b3315ad21ngha5.JPG'/><a href='https://www.youtube.com/'>點我<a>";
try {
    MimeMessage message = javaMailSender.createMimeMessage();
    // MimeMessageHelper 第二個參數 true 表示支援多個附件，false 表示不支援多個附件，預設為 false
    MimeMessageHelper helper = new MimeMessageHelper(message,true);
    helper.setFrom(from);
    helper.setTo(to);
    helper.setSubject(subject);
    // 第二個參數 true 表示支援 HTML，false 表示不支援 HTML，預設為 false
    helper.setText(text,true);

    ClassPathResource fileResource1 = new ClassPathResource("image/pic1.jpeg");
    ClassPathResource fileResource2 = new ClassPathResource("image/pic2.png");
    helper.addAttachment(fileResource1.getFilename(), fileResource1);
    helper.addAttachment(fileResource2.getFilename(), fileResource2);

    javaMailSender.send(message);
} catch (Exception e) {
    e.printStackTrace();
}
```