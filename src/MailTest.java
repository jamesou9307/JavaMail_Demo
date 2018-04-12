import com.sun.mail.util.MailSSLSocketFactory;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class MailTest {

    public static void main(String[] args){

        //设置收件人邮箱
        String to="291387044@qq.com";
        //设置发件人邮箱
        String from="1309989525@qq.com";

        //发送邮件的主机
        String host="smtp.qq.com";



        try{

            //获取系统属性
            Properties properties=System.getProperties();

            //设置邮件服务器
            properties.setProperty("mail.smtp.host",host);

            //qq邮箱需要ssl
            MailSSLSocketFactory mailSSLSocketFactory=new MailSSLSocketFactory();
            mailSSLSocketFactory.setTrustAllHosts(true);
            properties.setProperty("mail.smtp.ssl.enable","true");
            properties.put("mail.smtp.ssl.socketFactory",mailSSLSocketFactory);

            //设置用户认证部分
            properties.put("mail.smtp.auth","true");
            MyAuthenticator myAuthenticator=new MyAuthenticator("1309989525","wktuhwpdkszcbabd");

            //获取默认的session对象
            Session session=Session.getDefaultInstance(properties,myAuthenticator);

            session.setDebug(true);

            //创建默认的mime对象
            MimeMessage message=new MimeMessage(session);

            //set from:头部头字段
            message.setFrom(new InternetAddress(from));

            //set to:头部头字段
            message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));

            //set subject:头部头字段
            message.setSubject("THis_is_the_Subject_Line!");

            //设置消息体
            message.setText("hello-world!");


            //发送消息
            Transport.send(message);
            System.out.println("Sent message succefully....");



        }catch (Exception e){
            e.printStackTrace();
        }

    }

}

