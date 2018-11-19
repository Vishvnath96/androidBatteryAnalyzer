package sendemail;

import model.grafanaMetricModel.MetricPayload;
import util.commonutils;

import javax.mail.*;
import javax.mail.internet.*;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public class EmailSender {
     String subject;
     MetricPayload metricPayload;
     String scenario;

    public EmailSender(MetricPayload metricPayload){
        this.metricPayload = metricPayload;
    }
    public void sendEmail() throws MessagingException {
        if(metricPayload.getDeviceMeta().getPlatform().equalsIgnoreCase("overnight")){
            scenario = "Overnight";
        }
        else {
            scenario = "Half an Hour";
        }
        subject = scenario + " "+ "Battery drainage report for app ver\t" + " "+metricPayload.getDeviceMeta().getApkVersion()+" "+"on WiFi";
        Multipart multipart = new MimeMultipart();
        BodyPart messageBodyPart = new MimeBodyPart();

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        //props.put("mail.smtp.socketFactory.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getDefaultInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("mmtmytrip123@gmail.com", "teamandroid");// change
                // accordingly
            }
        });

        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress("batteryreport no-reply@makemytrip.com" + "<" + "no-reply@domain.com" + ">"));// change accordingly
        message.addRecipients(Message.RecipientType.TO, "anuj.arora@go-mmt.com");
        message.addRecipients(Message.RecipientType.TO, "ankit.gupta2@go-mmt.com");
        message.addRecipients(Message.RecipientType.CC, "puneet.jain@go-mmt.com");
        message.addRecipients(Message.RecipientType.CC,"vishvnath.singh@go-mmt.com");
        message.setSubject(subject);

        SimpleDateFormat form = new SimpleDateFormat("hh:mm:ss:SS");

        StringBuffer htmlbody = new StringBuffer("<html>Hi All,<br>");
        htmlbody.append("Please find below battery drainage  observation for App ver \t");
        htmlbody.append(metricPayload.getDeviceMeta().getApkVersion());
        htmlbody.append("\t for Device Nexus 6(Noughat).<br>");
        htmlbody.append("</br>");
        htmlbody.append("<b>Precondition:</b><br>");
        htmlbody.append("1.Network Used :WiFi<br>");
        htmlbody.append("2.Device : Nexus 6");
        //body

        String body = commonutils.buildCasesTable(metricPayload);
        String serviceBody = commonutils.buildServiceTable(metricPayload);
        System.out.println("Executed build cases");
        htmlbody.append("<br>");
        htmlbody.append("<br>");
        htmlbody.append(body);
        htmlbody.append(serviceBody);

        htmlbody.append("</br>");
        htmlbody.append("<font color=green>For Detailed Report kindly click below link:</font>");
        htmlbody.append(": ");
        htmlbody.append("<a href=\"http://gmt.mmt.com:3000/dashboard/db/android-battery-usage-metrics?orgId=1&from=now%2Fd&to=now%2Fd&var-Device=Nexus-6&var-ApkVersion=7.3.1&var-Scenario=HalfnHour\">Click Grafana link</a></br>");
        htmlbody.append("</br>");
        htmlbody.append("<i>note* This is an auto generated email please don't reply.</i><br>");
        htmlbody.append("</br>");
        // Execution time
        htmlbody.append("Test execution time :" + form.format(new Date(System.currentTimeMillis())) + "<br>");
        //htmlbody.append("Execution environment's public IP:\t"+ commonutils.getMyPublicIP() + "<br>");
        htmlbody.append("</br>");
        htmlbody.append("<b>Thanks & regards,</b><br>");
        htmlbody.append("Common-Qa");
        htmlbody.append("</html>");

        messageBodyPart.setContent(htmlbody.toString(),"text/html");
        multipart.addBodyPart(messageBodyPart);
        message.setContent(multipart);
        Transport.send(message);
    }
}
